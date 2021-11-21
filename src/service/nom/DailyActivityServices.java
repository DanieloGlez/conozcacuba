package service.nom;

import dto.nom.DailyActivityDto;
import service.Services;
import service.ServicesLocator;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DailyActivityServices implements Services<DailyActivityDto> {
    @Override
    public DailyActivityDto load(int id) throws SQLException {
        return null;
    }

    @Override
    public List<DailyActivityDto> loadAll() throws SQLException {
        List<DailyActivityDto> dailyActivityDtos = new LinkedList<>();

        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.n_daily_activity_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
            dailyActivityDtos.add(new DailyActivityDto(
                    resultSet.getInt("id_daily_activity"),
                    resultSet.getString("name")
            ) {
            });
        }

        return dailyActivityDtos;
    }

    @Override
    public void insert(DailyActivityDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_daily_activity_insert(?,?)}");
        callableStatement.setString("description", dto.getName());
        callableStatement.setInt("description", dto.getId());
        callableStatement.execute();
    }

    @Override
    public void update(DailyActivityDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
