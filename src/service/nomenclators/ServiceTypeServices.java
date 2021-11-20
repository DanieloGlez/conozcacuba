package service.nomenclators;

import dto.nomenclators.ServiceTypeDto;
import service.Services;
import service.ServicesLocator;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ServiceTypeServices implements Services<ServiceTypeDto> {
    @Override
    public ServiceTypeDto load(int id) throws SQLException {
        return null;
    }

    @Override
    public List<ServiceTypeDto> loadAll() throws SQLException {
        List<ServiceTypeDto> serviceTypeDtos = new LinkedList<>();

        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.n_service_type_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
            serviceTypeDtos.add(new ServiceTypeDto(
                    resultSet.getInt("id_service_type"),
                    resultSet.getString("name")
            ));
        }

        return serviceTypeDtos;
    }

    @Override
    public void insert(ServiceTypeDto dto) throws SQLException {

    }

    @Override
    public void update(ServiceTypeDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
