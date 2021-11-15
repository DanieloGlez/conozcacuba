package service.functionality;

import dto.functionality.UserDto;
import service.Services;
import service.ServicesLocator;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserServices implements Services<UserDto> {
    private UserDto activeUser;

    public UserDto getActiveUser() {
        return activeUser;
    }

    @Override
    public UserDto load(String id) throws SQLException {
        return null;
    }

    public List<UserDto> loadAll() throws SQLException {
        List<UserDto> users = new LinkedList<>();

        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.f_user_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
           users.add(new UserDto(
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getBoolean("is_admin")
            ));
        }

        return users;
    }

    @Override
    public void insert(UserDto dto) throws SQLException {

    }

    @Override
    public void update(UserDto dto) throws SQLException {

    }

    @Override
    public void delete(String id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return UserDto.class.getSimpleName();
    }
}
