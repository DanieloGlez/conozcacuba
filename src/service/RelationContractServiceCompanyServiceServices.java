package service;

import dto.ContractServiceDto;
import dto.nom.CompanyServiceDto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

public class RelationContractServiceCompanyServiceServices {
    public void insert(ContractServiceDto contractServiceDto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{ call tpp.r_contract_service_n_company_service_insert(?,?)}");
        ListIterator<CompanyServiceDto> listIterator = contractServiceDto.getCompaniesService().listIterator();
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
        int idContract = contractServiceDto.getId();
        CallableStatement callableStatement = connection.prepareCall("{ call tpp.r_contract_service_n_company_service_delete(?)}");
        callableStatement.setInt(1, idContract);
        callableStatement.execute();

        insert(contractServiceDto);

        callableStatement.close();
        connection.close();
    }
}
