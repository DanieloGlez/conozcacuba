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
                ServicesLocator.getContractServices().load( resultSet.getInt("id_contract")),
                ServicesLocator.getVehicleServices().load( resultSet.getInt("id_vehicle"))
        );
    }

    @Override
    public List<ModalityTransportKmDto> loadAll() throws SQLException {
        List<ModalityTransportKmDto> modalityTransportKmDtos = new LinkedList<>();

        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.modality_transport_km_load()}");
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
                    ServicesLocator.getContractServices().load(resultSet.getInt("id_contract")),
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
        callableStatement.setFloat("cost_km", dto.getCostKm());
        callableStatement.setFloat("cost_km_round_trip", dto.getCostKmRoundTrip());
        callableStatement.setFloat("cost_hr_wait", dto.getCostHrWait());
        callableStatement.setInt("id_contract", dto.getContractDto().getId());
        callableStatement.setInt("id_vehicle", dto.getVehicleDto().getId());

        callableStatement.execute();
    }

    @Override
    public void update(ModalityTransportKmDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.modality_transport_km_update(?,?,?,?,?)}");
        callableStatement.setFloat("cost_km", dto.getCostKm());
        callableStatement.setFloat("cost_km_round_trip", dto.getCostKmRoundTrip());
        callableStatement.setFloat("cost_hr_wait", dto.getCostHrWait());
        callableStatement.setInt("id_contract", dto.getContractDto().getId());
        callableStatement.setInt("id_vehicle", dto.getVehicleDto().getId());

        callableStatement.execute();


    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
