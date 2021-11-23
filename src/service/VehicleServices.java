package service;

import dto.ContractDto;
import dto.VehicleDto;
import dto.nom.VehicleBrandDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class VehicleServices implements Services<VehicleDto>, Relation<VehicleDto>{
    @Override
    public VehicleDto load(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.vehicle_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        ContractDto contractDto = null;
        resultSet.next();

        return new VehicleDto(
                resultSet.getInt("id_vehicle"),
                resultSet.getString("chapa"),
                ServicesLocator.getVehicleBrandServices().load(resultSet.getInt("id_vehicle_brand")),
                resultSet.getInt("capacity_without_baggage"),
                resultSet.getInt("capacity_with_baggage"),
                resultSet.getDate("production_date").toLocalDate()
        );
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
                    resultSet.getInt("id_vehicle"),
                    resultSet.getString("chapa"),
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
        callableStatement.setInt(1, dto.getId());
        callableStatement.setString(2, dto.getRegistration());
        callableStatement.setInt(3, dto.getCapacityWithoutBaggage());
        callableStatement.setInt(4, dto.getCapacityWithBaggage());
        callableStatement.setInt(5, dto.getCapacityTotal());
        callableStatement.setDate(6, dto.toDate());
        callableStatement.setInt(7, dto.getBrand().getId());
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

    @Override
    public List<VehicleDto> loadRelated(int id) throws SQLException {
        LinkedList<VehicleDto> vehicleDtoLinkedList=new LinkedList<>();
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.r_contract_transport_vehicle_load_by_id(?)}");
        callableStatement.registerOutParameter(1,Types.REF_CURSOR);
        callableStatement.setInt(2,id);
        callableStatement.execute();
        ResultSet resultSet= (ResultSet) callableStatement.getObject(1);
        int idVehicle;
        VehicleDto vehicleDto;

        while (resultSet.next()){
            idVehicle = resultSet.getInt("id_vehicle");
            vehicleDto = load(idVehicle);
            vehicleDtoLinkedList.add(vehicleDto);
        }
        return vehicleDtoLinkedList;
    }
}

