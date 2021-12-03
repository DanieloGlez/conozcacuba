package service;

import dto.RelationContractServiceDailyActivityDto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class RelationContractServiceDailyActivityServices {
    public void insert(RelationContractServiceDailyActivityDto relation) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{ call tpp.r_contract_service_n_daily_activity_insert(?,?,?)}");
        callableStatement.setInt(1, relation.getIdContractService());
        callableStatement.setInt(2, relation.getDailyActivityDto().getId());
        callableStatement.setFloat(3, relation.getPrice());
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }

    public void update(RelationContractServiceDailyActivityDto relation) throws SQLException {
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
