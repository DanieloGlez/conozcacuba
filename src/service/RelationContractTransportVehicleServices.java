package service;

import dto.ContractServiceDto;
import dto.ContractTransportDto;
import dto.VehicleDto;
import dto.nom.CompanyServiceDto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

public class RelationContractTransportVehicleServices {
    public void insert(ContractTransportDto contractTransportDto, List<VehicleDto> vehicleDtos) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{ call tpp.r_contract_transport_vehicle_insert(?,?)}");
        ListIterator<VehicleDto> listIterator = vehicleDtos.listIterator();
        int idContract = contractTransportDto.getId();

        while (listIterator.hasNext()){
            callableStatement.setInt(1, idContract);
            callableStatement.setInt(2, listIterator.next().getId());
            callableStatement.execute();
        }

        callableStatement.close();
        connection.close();
    }
}
