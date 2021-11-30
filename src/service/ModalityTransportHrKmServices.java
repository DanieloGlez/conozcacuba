package service;

import dto.ModalityTransportHrKmDto;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ModalityTransportHrKmServices implements Services<ModalityTransportHrKmDto>{
    @Override
    public ModalityTransportHrKmDto load(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.modality_transport_hr_km_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();
        callableStatement.close();
        connection.close();

        return new ModalityTransportHrKmDto(
                resultSet.getInt("id_modality_transport_hr_km"),
                resultSet.getFloat("costTraveledKm"),
                resultSet.getFloat("costHr"),
                resultSet.getFloat("costKmExtras"),
                resultSet.getFloat("costHrExtras"),
                ServicesLocator.getContractTransportServices().load( resultSet.getInt("id_contract")),
                ServicesLocator.getVehicleServices().load( resultSet.getInt("id_vehicle"))
        );
    }

    @Override
    public List<ModalityTransportHrKmDto> loadAll() throws SQLException {
        List<ModalityTransportHrKmDto> modalityTransportHrKmDtos = new LinkedList<>();
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.modality_transport_hr_km_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
            modalityTransportHrKmDtos.add(new ModalityTransportHrKmDto(
                    resultSet.getInt("id_modality_transport_hr_km"),
                    resultSet.getFloat("cost_traveled_km"),
                    resultSet.getFloat("cost_hr"),
                    resultSet.getFloat("cost_km_extras"),
                    resultSet.getFloat("cost_hr_extras"),
                    ServicesLocator.getContractTransportServices().load(resultSet.getInt("id_contract")),
                    ServicesLocator.getVehicleServices().load(resultSet.getInt("id_vehicle"))
            ) {
            });
        }

        callableStatement.close();
        connection.close();
        return modalityTransportHrKmDtos;
    }

    @Override
    public void insert(ModalityTransportHrKmDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.modality_transport_hr_km_insert(?,?,?,?,?,?)}");
        callableStatement.setInt(1, dto.getContractTransport().getId());
        callableStatement.setInt(2, dto.getVehicle().getId());
        callableStatement.setFloat(3, dto.getCostTraveledKm());
        callableStatement.setFloat(4, dto.getCostKmExtras());
        callableStatement.setFloat(5, dto.getCostHrExtras());
        callableStatement.setFloat(6, dto.getCostHr());
        callableStatement.execute();
        callableStatement.close();
        connection.close();
    }

    @Override
    public void update(ModalityTransportHrKmDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.modality_transport_hr_km_update(?,?,?,?,?,?,?)}");
        callableStatement.setInt(1, dto.getId());
        callableStatement.setInt(2, dto.getContractTransport().getId());
        callableStatement.setInt(3, dto.getVehicle().getId());
        callableStatement.setDouble(4, dto.getCostTraveledKm());
        callableStatement.setDouble(5, dto.getCostKmExtras());
        callableStatement.setDouble(6, dto.getCostHrExtras());
        callableStatement.setDouble(7, dto.getCostHr());
        callableStatement.execute();
        callableStatement.close();
        connection.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.modality_transport_hr_km_delete(?)}");
        callableStatement.setInt(1, id);
        callableStatement.execute();
        callableStatement.close();
        connection.close();
    }
}
