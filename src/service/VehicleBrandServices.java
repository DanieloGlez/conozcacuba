package service;

import dto.nomenclators.VehicleBrandDto;

import java.sql.*;
import java.util.List;

public class VehicleBrandServices implements Services<VehicleBrandDto>{




    @Override
    public VehicleBrandDto load(int id) throws SQLException {

        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.n_vehicle_brand_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.OTHER);
        callableStatement.setInt("id_vehicle_brand",id);

        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        VehicleBrandDto vehicleBrandDto=new VehicleBrandDto(resultSet.getInt("id_vehicle_brand"), resultSet.getString("name"));

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
