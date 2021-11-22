package service.nom;

import dto.nom.CompanyServiceDto;
import dto.nom.FoodPlanDto;
import dto.nom.RoomTypeDto;
import service.Relation;
import service.Services;
import service.ServicesLocator;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class RoomTypeServices implements Services<RoomTypeDto>, Relation<RoomTypeDto> {
    @Override
    public RoomTypeDto load(int id_room_type) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.n_room_type_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id_room_type);

        callableStatement.execute();

        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();

        return new RoomTypeDto(
                resultSet.getInt("id_room_type"),
                resultSet.getString("name")
        );
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
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_room_type_insert(?)}");
        callableStatement.setString(1, dto.getName());
        callableStatement.execute();
    }

    @Override
    public void update(RoomTypeDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_room_type_update(?)}");
        callableStatement.setInt(1, dto.getId());
        callableStatement.setString(2, dto.getName());
        callableStatement.execute();

    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_room_type_delete(?)}");
        callableStatement.setInt(1, id);
        callableStatement.execute();

    }

    @Override
    public String getGenericType() {
        return null;
    }

    @Override
    public List<RoomTypeDto> loadRelated(int id) throws SQLException {
        LinkedList<RoomTypeDto> roomTypeDtos=new LinkedList<>();
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{?=call tpp.r_hotel-room_type_load_by_id(?)}");
        callableStatement.registerOutParameter(1,Types.REF_CURSOR);
        callableStatement.setInt(2,id);
        callableStatement.execute();
        ResultSet resultSet= (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()){
            roomTypeDtos.add(
                    new RoomTypeDto(resultSet.getInt(1),
                            resultSet.getString(2))
            );
        }
        return roomTypeDtos;
    }
}
