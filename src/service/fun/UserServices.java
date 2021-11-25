package service.fun;

import dto.fun.UserDto;
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
    public UserDto load(int id) throws SQLException {
        return null;
    }

    public List<UserDto> loadAll() throws SQLException {
        List<UserDto> users = new LinkedList<>();

        Connection connection = service.ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.f_user_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
           users.add(new UserDto(
                    resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("name"),
                    resultSet.getString("password"),
                    ServicesLocator.getRoleServices().load(resultSet.getInt("id_role"))
            ));
        }

        connection.close();
        return users;
    }

    @Override
    public void insert(UserDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.f_user_insert(?,?,?,?)}");
        callableStatement.setString(1, dto.getUsername());
        callableStatement.setString(2, dto.getName());
        callableStatement.setString(3, dto.getPassword());
        callableStatement.setInt(4, dto.getRole().getId());

        callableStatement.execute();

        connection.close();
    }

    @Override
    public void update(UserDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.f_user_delete(?)}");
        callableStatement.setInt(1, id);
        callableStatement.execute();

        connection.close();
    }

    @Override
    public String getGenericType() {
        return UserDto.class.getSimpleName();
    }
}
