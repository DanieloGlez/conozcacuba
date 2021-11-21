package service.nom;

import dto.nom.DailyActivityDto;
import service.Relation;
import service.Services;
import service.ServicesLocator;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DailyActivityServices implements Services<DailyActivityDto>, Relation<DailyActivityDto> {
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

    @Override
    public List<DailyActivityDto> loadRelated(int id) throws SQLException {
        LinkedList<DailyActivityDto> activityDtoLinkedList=new LinkedList<>();
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{?=call tpp.r_contract_service_n_daily_activity_get_by_id(?)}");
        callableStatement.registerOutParameter(1,Types.REF_CURSOR);
        callableStatement.setInt(2,id);
        ResultSet resultSet= (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()){
            activityDtoLinkedList.add(
                    new DailyActivityDto(resultSet.getInt(1),
                            resultSet.getString(2))
            );
        }
        return activityDtoLinkedList;
    }
}
