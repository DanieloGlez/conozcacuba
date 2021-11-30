package service;

import dto.ContractServiceDto;
import dto.nom.CompanyServiceDto;
import dto.nom.ServiceTypeDto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

public class RelationContractServiceServiceTypeServices {
    public void insert(ContractServiceDto contractServiceDto, List<ServiceTypeDto> serviceTypeDtos) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{ call tpp.r_contract_service_n_service_type_insert(?,?)}");
        ListIterator <ServiceTypeDto> listIterator = serviceTypeDtos.listIterator();
        int idContract = contractServiceDto.getId();

        while (listIterator.hasNext()){
            callableStatement.setInt(1, idContract);
            callableStatement.setInt(2, listIterator.next().getId());
            callableStatement.execute();
        }

        callableStatement.close();
        connection.close();
    }

    public void update(ContractServiceDto contractServiceDto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatementD = connection.prepareCall("{ call tpp.r_contract_service_n_company_service_delete(?)}");
        callableStatementD.setInt(1, contractServiceDto.getId());
        callableStatementD.execute();
        CallableStatement callableStatement = connection.prepareCall("{ call tpp.r_contract_service_n_service_type_update(?,?)}");
        ListIterator<ServiceTypeDto> listIterator = contractServiceDto.getServiceType().listIterator();
        int idContract = contractServiceDto.getId();

        while (listIterator.hasNext()){
            callableStatement.setInt(1, idContract);
            callableStatement.setInt(2, listIterator.next().getId());
            callableStatement.execute();
        }

        callableStatement.close();
        connection.close();
    }
}
