package service;

import dto.ContractServiceDto;
import dto.RelationContractServiceDailyActDto;
import dto.nom.DailyActivityDto;
import dto.nom.ServiceTypeDto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

public class RelationContractServiceDailyActServices {
    public void insert(RelationContractServiceDailyActDto relation) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{ call tpp.r_contract_service_n_daily_activity_insert(?,?,?)}");
        callableStatement.setInt(1, relation.getIdContractService());
        callableStatement.setInt(2, relation.getDailyActivityDto().getId());
        callableStatement.setFloat(3, relation.getCost());
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }

    public void update(RelationContractServiceDailyActDto relation) throws SQLException {
        insert(relation);
    }

    public void delete(int idContractService) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{ call tpp.r_contract_service_n_daily_activity_delete(?)}");
        callableStatement.setInt(1, idContractService);
        callableStatement.execute();
        callableStatement.close();
        connection.close();
    }
}
