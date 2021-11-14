package service.functionality;

import dto.functionality.UserDto;
import service.Service;
import service.ServicesLocator;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserServices implements Service<UserDto> {
    private UserDto activeUser;

    public UserDto getActiveUser() {
        return activeUser;
    }

    @Override
    public Class<UserDto> getGenericType() {
        return UserDto.class;
    }

    public List<UserDto> getAll() throws SQLException {
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
}
