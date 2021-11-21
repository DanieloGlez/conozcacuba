package service;

import dto.ContractDto;
import service.nom.ContractTypeServices;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ContractServices implements Services<ContractDto> {
    @Override
    public ContractDto load(int id) throws SQLException {
        return null;
    }

    @Override
    public List<ContractDto> loadAll() throws SQLException {
        List<ContractDto> contractDtos = new LinkedList<>();

        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.contract_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        int idTypeContract;

        while (resultSet.next()) {
            contractDtos.add(new ContractDto(
                    idTypeContract = resultSet.getInt("id_contract"),
                    ServicesLocator.getContractTypeServices().load(idTypeContract),
                    resultSet.getDate("start_date"),
                    resultSet.getDate("finish_date"),
                    resultSet.getDate("conciliation_date"),
                    resultSet.getString("description")
            ) {
            });
        }

        return contractDtos;
    }

    @Override
    public void insert(ContractDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{call tpp.contract_insert(?,?,?,?,?)}");
        callableStatement.setInt("id_contract_type", dto.getContractTypeDto().getId());
        callableStatement.setDate("start_date", (Date) dto.getStartDate());
        callableStatement.setDate("finish_date", (Date) dto.getFinishDate());
        callableStatement.setDate("conciliation0_date", (Date) dto.getConciliationDate());
        callableStatement.setString("description", dto.getDescription());
        callableStatement.execute();
    }

    @Override
    public void update(ContractDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
