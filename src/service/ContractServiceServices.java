package service;

import dto.ContractServiceDto;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ContractServiceServices implements Services<ContractServiceDto>{

    @Override
    public ContractServiceDto load(int id) throws SQLException {
        return null;
    }

    @Override
    public List<ContractServiceDto> loadAll() throws SQLException {
        List<ContractServiceDto> contractServiceDtos = new LinkedList<>();

        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.contract_service_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
/*
        while (resultSet.next()) {
            contractServiceDtos.add(new ContractServiceDto(
                    resultSet.getInt("id_contract"),
                    resultSet.getInt("id_contract_type"),
                    resultSet.getDate("start_date"),
                    resultSet.getDate("finish_date"),
                    resultSet.getDate("conciliation_date"),
                    resultSet.getString("description"),
                    resultSet.getString("id_service_type"),
                    resultSet.getString("id_province"),
                    resultSet.getFloat("pax_cost"),
                    resultSet.get("id_daily_activity")
            ) {
            });
        }*/

        return contractServiceDtos;
    }

    @Override
    public void insert(ContractServiceDto dto) throws SQLException {

    }

    @Override
    public void update(ContractServiceDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return null;
    }
}

