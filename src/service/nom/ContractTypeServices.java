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
    public ContractTypeDto load(int id_contract_type) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.n_contract_type_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id_contract_type);

        callableStatement.execute();

        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();

        return new ContractTypeDto(
                resultSet.getInt("id_contract_type"),
                resultSet.getString("name")
        );
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

        return contractTypeDtos;
    }

    @Override
    public void insert(ContractTypeDto dto) throws SQLException {
        Connection connection = service.ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_contract_type_insert(?)}");
        callableStatement.setString("name", dto.getName());
        callableStatement.execute();
    }

    @Override
    public void update(ContractTypeDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return null;
    }
}