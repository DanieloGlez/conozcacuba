package service;

import dto.nomenclators.VehicleBrandDto;

import java.sql.*;
import java.util.List;

public class VehicleBrandServices implements Services<VehicleBrandDto> {


    @Override
    public VehicleBrandDto load(int id_vehicle_brand) throws SQLException {
        VehicleBrandDto vehicleBrandDto;
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{?=call tpp.n_vehicle_brand_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.VARCHAR);
        callableStatement.setInt(2, id_vehicle_brand);
        callableStatement.execute();

        vehicleBrandDto = new VehicleBrandDto(1, (String) callableStatement.getObject(1));
        return vehicleBrandDto;
    }

    @Override
    public List<VehicleBrandDto> loadAll() throws SQLException {
        return null;
    }

    @Override
    public void insert(VehicleBrandDto dto) throws SQLException {

    }

    @Override
    public void update(VehicleBrandDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
