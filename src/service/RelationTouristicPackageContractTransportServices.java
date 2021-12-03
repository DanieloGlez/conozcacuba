package service;

import dto.ContractDto;
import dto.TouristicPackageDto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

public class RelationTouristicPackageContractTransportServices {
    public void insert(TouristicPackageDto touristicPackageDto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{ call tpp.r_touristic_package_contract_transport_insert(?,?)}");
        List<ContractDto> list = touristicPackageDto.getContracts();
        ListIterator<ContractDto> listIterator = list.listIterator();

        while (listIterator.hasNext()){
            callableStatement.setInt(2, touristicPackageDto.getId());
            callableStatement.setInt(1, listIterator.next().getId());
            callableStatement.execute();
        }

        callableStatement.close();
        connection.close();
    }

    public void update(TouristicPackageDto touristicPackageDto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{ call tpp.r_touristic_package_contract_transport_delete(?)}");
        callableStatement.setInt(1, touristicPackageDto.getId());
        callableStatement.execute();

        insert(touristicPackageDto);

        callableStatement.close();
        connection.close();
    }
}
