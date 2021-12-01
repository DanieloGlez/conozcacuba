package service;

import dto.ContractHotelDto;
import dto.ContractServiceDto;
import dto.SeasonDto;
import dto.nom.CompanyServiceDto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ListIterator;

public class RelationContractHotelSeasonServices {
    public void insert(ContractHotelDto contractHotelDto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{ call tpp.r_season_contract_hotel_insert(?,?)}");
        ListIterator<SeasonDto> listIterator = contractHotelDto.getSeasons().listIterator();
        int idContract = contractHotelDto.getId();

        while (listIterator.hasNext()){
            callableStatement.setInt(1, idContract);
            callableStatement.setInt(2, listIterator.next().getId());
            callableStatement.execute();
        }

        callableStatement.close();
        connection.close();
    }

    public void update(ContractHotelDto contractHotelDto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        int idContract = contractHotelDto.getId();
        CallableStatement callableStatement = connection.prepareCall("{ call tpp.r_season_contract_hotel_delete(?)}");
        callableStatement.setInt(1, idContract);
        callableStatement.execute();

        insert(contractHotelDto);

        callableStatement.close();
        connection.close();
    }
}
