package service.nomenclators;

import dto.nomenclators.LocalizationDto;
import dto.nomenclators.ServiceTypeDto;
import service.Services;
import service.ServicesLocator;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class LocalizationServices implements Services<LocalizationDto> {
    @Override
    public LocalizationDto load(int id) throws SQLException {
        return null;
    }

    @Override
    public List<LocalizationDto> loadAll() throws SQLException {
        List<LocalizationDto> localizationDtos = new LinkedList<>();

        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.n_localization_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
            localizationDtos.add(new LocalizationDto(
                    resultSet.getInt("id_localization"),
                    resultSet.getString("name")
            ));
        }

        return localizationDtos;
    }

    @Override
    public void insert(LocalizationDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_localization_insert(?)}");
        callableStatement.setString("name", dto.getName());
        callableStatement.execute();
    }

    @Override
    public void update(LocalizationDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
