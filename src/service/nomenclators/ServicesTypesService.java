package service.nomenclators;

import dto.nomenclators.CompanyServiceDto;
import dto.nomenclators.ServicesTypeDto;
import service.Services;
import service.ServicesLocator;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ServicesTypesService implements Services<ServicesTypeDto> {
    @Override
    public ServicesTypeDto load(int id) throws SQLException {
        return null;
    }

    @Override
    public List<ServicesTypeDto> loadAll() throws SQLException {
        List<ServicesTypeDto> servicesTypeDtos = new LinkedList<>();

        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.n_service_type_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
            servicesTypeDtos.add(new ServicesTypeDto(
                    resultSet.getInt("id_service_type"),
                    resultSet.getString("name")
            ));
        }

        return servicesTypeDtos;
    }

    @Override
    public void insert(ServicesTypeDto dto) throws SQLException {

    }

    @Override
    public void update(ServicesTypeDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
