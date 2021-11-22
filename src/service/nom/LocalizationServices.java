package service.nom;

import dto.nom.CompanyServiceDto;
import dto.nom.LocalizationDto;
import service.Services;
import service.ServicesLocator;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class LocalizationServices implements Services<LocalizationDto> {
    @Override
    public LocalizationDto load(int id_localization) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.n_localization_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id_localization);

        callableStatement.execute();

        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();

        return new LocalizationDto(
                resultSet.getInt("id_localization"),
                resultSet.getString("name")
        );
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
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_localization_insert(?)}");
        callableStatement.setString(1, dto.getName());
        callableStatement.execute();
    }

    @Override
    public void update(LocalizationDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_hotel_localization_update(?)}");
        callableStatement.setInt(1, dto.getId());
        callableStatement.setString(2, dto.getName());
        callableStatement.execute();

    }

    @Override
    public void delete(int id_localization) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_localization_delete(?)}");
        callableStatement.setInt(1, id_localization);
        callableStatement.execute();

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
