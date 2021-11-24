package service;

import dto.nom.VehicleBrandDto;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class VehicleBrandServices implements Services<VehicleBrandDto> {

    @Override
    public VehicleBrandDto load(int id_vehicle_brand) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.n_vehicle_brand_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id_vehicle_brand);

        callableStatement.execute();

        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();

        return new VehicleBrandDto(
                resultSet.getInt("id_vehicle_brand"),
                resultSet.getString("name")
        );
    }

    @Override
    public List<VehicleBrandDto> loadAll() throws SQLException {

        LinkedList<VehicleBrandDto> vehicleBrandlist = new LinkedList<VehicleBrandDto>();
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.n_vehicle_brand_load}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
            vehicleBrandlist.add(new VehicleBrandDto(resultSet.getInt("id_vehicle_brand"),
                    resultSet.getString("name")));
        }

        return vehicleBrandlist;
    }

    @Override
    public void insert(VehicleBrandDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_vehicle_brand_insert(?)}");
        callableStatement.setString(1, dto.getName());
        callableStatement.execute();
    }

    @Override
    public void update(VehicleBrandDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_vehicle_brand_update(?,?)}");
        callableStatement.setInt(1, dto.getId());
        callableStatement.setString(2, dto.getName());
        callableStatement.execute();

    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_vehicle_brand_delete(?)}");
        callableStatement.setInt(1, id);
        callableStatement.execute();

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
