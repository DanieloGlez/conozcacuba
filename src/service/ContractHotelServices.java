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
        List<ContractHotelDto> contractHotelDtos = new LinkedList<>();
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.contract_hotel_load}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
            ContractDto contractDto = ServicesLocator.getContractServices().load(resultSet.getInt("id_contract"));
            HotelDto hotelDto = ServicesLocator.getHotelServices().load(resultSet.getInt(2));
            LinkedList<SeasonDto> seasonDtos = (LinkedList<SeasonDto>) ServicesLocator.getSeasonServices().loadRelated(resultSet.getInt("id_contract"));


            contractHotelDtos.add(new ContractHotelDto(
                    contractDto.getId(),
                    contractDto.getStartDate(),
                    contractDto.getFinishDate(),
                    contractDto.getConciliationDate(),
                    contractDto.getDescription(),
                    contractDto.getContractTypeDto(),
                    seasonDtos,
                    hotelDto
            ) {
            });
        }

        connection.close();
        return contractHotelDtos;
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

        connection.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        ServicesLocator.getContractServices().delete(id);
    }

    @Override
    public String getGenericType() {
        return null;
    }

    private void insertInContract(ContractHotelDto contractHotelDto) throws SQLException {
        ContractDto contractDto = new ContractDto(
                contractHotelDto.getId(),
                ServicesLocator.getContractTypeServices().load(contractHotelDto.getContractTypeDto().getId()),
                contractHotelDto.getStartDate(),
                contractHotelDto.getFinishDate(),
                contractHotelDto.getConciliationDate(),
                contractHotelDto.getDescription());
        try {
            ServicesLocator.getContractServices().insert(contractDto);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
