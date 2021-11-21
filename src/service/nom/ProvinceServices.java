package service.nom;

import dto.nom.ProvinceDto;
import service.Services;
import service.ServicesLocator;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ProvinceServices implements Services<ProvinceDto> {
    @Override
    public ProvinceDto load(int id) throws SQLException {
        return null;
    }

    @Override
    public List<ProvinceDto> loadAll() throws SQLException {
        List<ProvinceDto> provinceDtos = new LinkedList<>();

        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.n_province_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
            provinceDtos.add(new ProvinceDto(
                    resultSet.getInt("id_province"),
                    resultSet.getString("name")
            ));
        }

        return provinceDtos;
    }

    @Override
    public void insert(ProvinceDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_province_insert(?)}");
        callableStatement.setString("name", dto.getName());
        callableStatement.execute();
    }

    @Override
    public void update(ProvinceDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
