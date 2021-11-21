package service.nom;

import dto.nom.RoomTypeDto;
import service.Services;
import service.ServicesLocator;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class RoomTypeServices implements Services<RoomTypeDto> {
    @Override
    public RoomTypeDto load(int id) throws SQLException {
        return null;
    }

    @Override
    public List<RoomTypeDto> loadAll() throws SQLException {
        List<RoomTypeDto> roomTypeDtos = new LinkedList<>();

        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.n_room_type_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
            roomTypeDtos.add(new RoomTypeDto(
                    resultSet.getInt("id_room_type"),
                    resultSet.getString("name")
            ));
        }

        return roomTypeDtos;
    }

    @Override
    public void insert(RoomTypeDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_room_type_insert(?)}");
        callableStatement.setString("name", dto.getName());
        callableStatement.execute();
    }

    @Override
    public void update(RoomTypeDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
