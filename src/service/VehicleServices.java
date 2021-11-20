package service;

import dto.VehicleDto;
import dto.nomenclators.VehicleBrandDto;

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
        VehicleBrandServices vehicleBrandServices = ServicesLocator.getVehicleBrandServices();

        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.f_user_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
            vehicles.add(new VehicleDto(
                    resultSet.getString("id_vehicle"),
                    vehicleBrandServices.load(resultSet.getInt("id_vehicle_brand"))
                    ,resultSet.getInt("capacity_without_baggage"),
                    resultSet.getInt("capacity_with_baggage"),
                    resultSet.getInt("capacity_total"),
                    resultSet.getDate("production_date")
            ));
        }

        return vehicles;

    }

    @Override
    public void insert(VehicleDto dto) throws SQLException {


        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{call tpp.vehicle_insert(?,?,?,?,?,?)}");
        callableStatement.setString("id_vehicle", dto.getId());
        callableStatement.setInt("capacity_without_baggage", dto.getCapacityWithoutBaggage());
        callableStatement.setInt("capacity_with_bagge",dto.getCapacityWithBaggage());
        callableStatement.setInt("capacity_total",dto.getCapacityTotal());
        callableStatement.setDate("production_date", (java.sql.Date) dto.getProductionDate());
        callableStatement.setInt("id_vehicle_brand",dto.getBrand().getId());
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
