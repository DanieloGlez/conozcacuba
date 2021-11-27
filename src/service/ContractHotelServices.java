package service;

import dto.*;
import dto.nom.ContractTypeDto;
import dto.nom.DailyActivityDto;

import java.sql.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ContractHotelServices implements Services<ContractHotelDto> {
    @Override
    public ContractHotelDto load(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.contract_hotel_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id);
        callableStatement.execute();

        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();

        callableStatement.close();
        connection.close();

        return new ContractHotelDto(
                id,
                resultSet.getDate("start_date"),
                resultSet.getDate("finish_date"),
                resultSet.getDate("conciliation_date"),
                resultSet.getString("description"),
                ServicesLocator.getContractServices().load(id).getContractTypeDto(),
                ServicesLocator.getSeasonServices().loadRelated(id),
                ServicesLocator.getHotelServices().load(resultSet.getInt("id_hotel"))
        );
    }

    @Override
    public List<ContractHotelDto> loadAll() throws SQLException {
        List<ContractHotelDto> ListContractHotelDto = new LinkedList<>();
        ContractHotelDto contractHotelDto;
        SeasonDto seasonDto;
        Connection connection = ServicesLocator.getConnection();
        int idContractHotel;
        int idSeason;
        List<Integer> idContainerContractHotel = new LinkedList<>();
        List<Integer> idContainerSeason = new LinkedList<>();

        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.contract_hotel_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
            idContractHotel = resultSet.getInt("id_contract");
            idSeason = resultSet.getInt("id_season");

            if(!idContainerContractHotel.contains(idContractHotel)){//inserto los elementos de la primera aparicion de un identificador
                ContractDto contractDto = ServicesLocator.getContractServices().load(idContractHotel);
                idContainerContractHotel.add(idContractHotel);
                idContainerSeason.clear();
                seasonDto = ServicesLocator.getSeasonServices().load(idSeason);
                List<SeasonDto> ListSeasonInsert = new LinkedList<>();
                ListSeasonInsert.add(seasonDto);
                idContainerSeason.add(idSeason);

                ListContractHotelDto.add(new ContractHotelDto(
                        idContractHotel,
                        contractDto.getStartDate(),
                        contractDto.getFinishDate(),
                        contractDto.getConciliationDate(),
                        contractDto.getDescription(),
                        contractDto.getContractTypeDto(),
                        ListSeasonInsert,
                        ServicesLocator.getHotelServices().load(resultSet.getInt("id_hotel"))
                ));
            }else {//inserto para el mismo id los tipos de hab, planes alim y modalidades diferentes
                int size = ListContractHotelDto.size();
                contractHotelDto = ListContractHotelDto.get(size - 1);

                if(!idContainerSeason.contains(idSeason)){
                    contractHotelDto.getSeasons().add(ServicesLocator.getSeasonServices().load(idSeason));
                    idContainerSeason.add(idSeason);
                }
            }
        }

        callableStatement.close();
        connection.close();
        return ListContractHotelDto;
    }

    @Override
    public void insert(ContractHotelDto dto) throws SQLException {
        ContractDto contractDto = new ContractDto(0,
                dto.getContractTypeDto(),
                dto.getStartDate(),
                dto.getFinishDate(),
                dto.getConciliationDate(),
                dto.getDescription());
        ServicesLocator.getContractServices().insert(contractDto);
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.contract_hotel_insert(?,?)}");
        callableStatement.setInt(1, contractDto.getId());
        callableStatement.setInt(2, dto.getHotel().getId());
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }

    @Override
    public void update(ContractHotelDto dto) throws SQLException {
        ContractDto contractDto = new ContractDto(dto.getId(),
                dto.getContractTypeDto(),
                dto.getStartDate(),
                dto.getFinishDate(),
                dto.getConciliationDate(),
                dto.getDescription());
        ServicesLocator.getContractServices().insert(contractDto);
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.contract_hotel_update(?,?)}");
        callableStatement.setInt(1, contractDto.getId());
        callableStatement.setInt(2, dto.getHotel().getId());
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        ServicesLocator.getContractServices().delete(id);
    }
}
