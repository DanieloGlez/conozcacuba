package service;

import dto.VehicleDto;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class VehicleServices implements Services<VehicleDto> {
    @Override
    public VehicleDto load(int id) throws SQLException {
        return null;
    }

    @Override
    public List<VehicleDto> loadAll() throws SQLException {
        List<VehicleDto> vehicles = new LinkedList<>();

        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.vehicle_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
            vehicles.add(new VehicleDto(
                    resultSet.getString("id_vehicle"),
                    ServicesLocator.getVehicleBrandServices().load(resultSet.getInt("id_vehicle_brand")),
                    resultSet.getInt("capacity_without_baggage"),
                    resultSet.getInt("capacity_with_baggage"),
                    resultSet.getDate("production_date").toLocalDate()
            ));
        }

        return vehicles;
    }

    @Override
    public void insert(VehicleDto dto) throws SQLException {

        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.vehicle_insert(?,?,?,?,?,?)}");
        callableStatement.setString(1, dto.getId());
        callableStatement.setInt(2, dto.getCapacityWithoutBaggage());
        callableStatement.setInt(3, dto.getCapacityWithBaggage());
        callableStatement.setInt(4, dto.getCapacityTotal());
        callableStatement.setDate(5, dto.toDate());
        callableStatement.setInt(6, dto.getBrand().getId());
        callableStatement.execute();
    }

    @Override
    public void update(VehicleDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
