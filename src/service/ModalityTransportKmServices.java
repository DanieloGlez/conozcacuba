package service;

import dto.ModalityTransportHrKmDto;
import dto.ModalityTransportKmDto;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ModalityTransportKmServices implements Services<ModalityTransportKmDto>{
    @Override
    public ModalityTransportKmDto load(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.modality_transport_km_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();

        return new ModalityTransportKmDto(
                resultSet.getInt("id_modality_transport_km"),
                resultSet.getFloat("costKm"),
                resultSet.getFloat("costKmRoundTrip"),
                resultSet.getFloat("costHrWait"),
                ServicesLocator.getContractTransportServices().load( resultSet.getInt("id_contract")),
                ServicesLocator.getVehicleServices().load( resultSet.getInt("id_vehicle"))
        );
    }

    @Override
    public List<ModalityTransportKmDto> loadAll() throws SQLException {
        List<ModalityTransportKmDto> modalityTransportKmDtos = new LinkedList<>();
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{ ? = call tpp.modality_transport_km_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        int idTypeContract;

        while (resultSet.next()) {
            modalityTransportKmDtos.add(new ModalityTransportKmDto(
                    resultSet.getInt("id_modality_transport_km"),
                    resultSet.getFloat("cost_km"),
                    resultSet.getFloat("cost_km_round_trip"),
                    resultSet.getFloat("cost_hr_wait"),
                    ServicesLocator.getContractTransportServices().load(resultSet.getInt("id_contract")),
                    ServicesLocator.getVehicleServices().load(resultSet.getInt("id_vehicle"))
            ) {
            });
        }

        return modalityTransportKmDtos;
    }

    @Override
    public void insert(ModalityTransportKmDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{call tpp.modality_transport_km_insert(?,?,?,?,?)}");
        callableStatement.setFloat(1, dto.getCostKm());
        callableStatement.setFloat(2, dto.getCostKmRoundTrip());
        callableStatement.setFloat(3, dto.getCostHrWait());
        callableStatement.setInt(4, dto.getContractTransport().getId());
        callableStatement.setInt(5, dto.getVehicle().getId());
        callableStatement.execute();
    }

    @Override
    public void update(ModalityTransportKmDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.modality_transport_km_update(?,?,?,?,?,?)}");
        callableStatement.setInt(1, dto.getId());
        callableStatement.setFloat(2, dto.getCostKm());
        callableStatement.setFloat(3, dto.getCostKmRoundTrip());
        callableStatement.setFloat(4, dto.getCostHrWait());
        callableStatement.setInt(5, dto.getContractTransport().getId());
        callableStatement.setInt(6, dto.getVehicle().getId());
        callableStatement.execute();
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.modality_transport_km_delete(?)}");
        callableStatement.setInt(1, id);
        callableStatement.execute();


    }

    @Override
    public String getGenericType() {
        return null;
    }
}
