package service.nom;

import dto.nom.CompanyServiceDto;
import dto.nom.ServiceTypeDto;
import service.Services;
import service.ServicesLocator;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ServiceTypeServices implements Services<ServiceTypeDto> {
    @Override
    public ServiceTypeDto load(int id_service_type) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.n_service_type_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id_service_type);

        callableStatement.execute();

        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();

        return new ServiceTypeDto(
                resultSet.getInt("id_service_type"),
                resultSet.getString("name")
        );
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
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_service_type_insert(?)}");
        callableStatement.setString("name", dto.getName());
        callableStatement.execute();
    }

    @Override
    public void update(ServiceTypeDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_service_type_update(?)}");
        callableStatement.setInt(1, dto.getId());
        callableStatement.setString(2, dto.getName());
        callableStatement.execute();


    }

    @Override
    public void delete(int id_service_type) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_service_type_delete(?)}");
        callableStatement.setInt(1, id_service_type);
        callableStatement.execute();

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
