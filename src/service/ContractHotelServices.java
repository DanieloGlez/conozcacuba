package service;

import dto.*;
import dto.nom.DailyActivityDto;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ContractHotelServices implements Services<ContractHotelDto>{
    @Override
    public ContractHotelDto load(int id) throws SQLException {
        return null;
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


            ContractDto contractDto=ServicesLocator.getContractServices().load(resultSet.getInt("id_contract"));
            HotelDto hotelDto=ServicesLocator.getHotelServices().load(resultSet.getInt("id_hotel"));
            LinkedList<SeasonDto> seasonDtos= (LinkedList<SeasonDto>) ServicesLocator.getSeasonServices().loadAll();


            contractHotelDtos.add(new ContractHotelDto(
                    contractDto.getId(),
                    contractDto.getContractTypeDto(),
                    contractDto.getStartDate(),
                    contractDto.getFinishDate(),
                    contractDto.getConciliationDate(),
                    contractDto.getDescription(),
                    hotelDto,
                    seasonDtos

            ) {
            });
        }


        return contractHotelDtos;
    }

    @Override
    public void insert(ContractHotelDto dto) throws SQLException {

    }

    @Override
    public void update(ContractHotelDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
