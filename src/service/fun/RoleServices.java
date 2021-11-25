package service.fun;

import dto.fun.RoleDto;
import dto.nom.CompanyTransportDto;
import dto.nom.DailyActivityDto;
import service.Services;
import service.ServicesLocator;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class RoleServices implements Services<RoleDto> {
    @Override
    public RoleDto load(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.f_role_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id);

        callableStatement.execute();

        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();

        connection.close();
        return new RoleDto(
                resultSet.getInt("id_role"),
                resultSet.getString("name")
        );
    }

    @Override
    public List<RoleDto> loadAll() throws SQLException {
        List<RoleDto> roleDtos = new LinkedList<>();

        Connection connection = service.ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.f_role_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
            roleDtos.add(new RoleDto(
                    resultSet.getInt("id_role"),
                    resultSet.getString("name")
            ));
        }

        connection.close();
        return roleDtos;
    }

    @Override
    public void insert(RoleDto dto) throws SQLException {

    }

    @Override
    public void update(RoleDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
