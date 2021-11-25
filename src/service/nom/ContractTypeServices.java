package service.nom;

import dto.nom.CompanyServiceDto;
import dto.nom.ContractTypeDto;
import service.Services;
import service.ServicesLocator;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class ContractTypeServices implements Services <ContractTypeDto> {
    @Override
    public ContractTypeDto load(int idContractType) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{ ? = call tpp.n_contract_type_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, idContractType);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();

        callableStatement.close();
        connection.close();
        return new ContractTypeDto(resultSet.getInt("id_contract_type"), resultSet.getString("name"));
    }

    @Override
    public List<ContractTypeDto> loadAll() throws SQLException {
        List<ContractTypeDto> contractTypeDtos = new LinkedList<>();

        Connection connection = service.ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.n_contract_type_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
            contractTypeDtos.add(new ContractTypeDto(
                    resultSet.getInt("id_contract_type"),
                    resultSet.getString("name")
            ));
        }

        callableStatement.close();
        connection.close();
        return contractTypeDtos;
    }

    @Override
    public void insert(ContractTypeDto dto) throws SQLException {
        Connection connection = service.ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_contract_type_insert(?)}");
        callableStatement.setString(1, dto.getName());
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }

    @Override
    public void update(ContractTypeDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_contract_type_update(?,?)}");
        callableStatement.setInt(1, dto.getId());
        callableStatement.setString(2, dto.getName());
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }

    @Override
    public void delete(int id_contract_type) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_contract_type_delete(?)}");
        callableStatement.setInt(1, id_contract_type);
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }
}
