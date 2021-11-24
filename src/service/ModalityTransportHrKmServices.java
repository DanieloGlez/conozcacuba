package service;

import dto.ContractDto;
import dto.ModalityTransportHrKmDto;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ModalityTransportHrKmServices implements Services<ModalityTransportHrKmDto>{
    @Override
    public ModalityTransportHrKmDto load(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.modality_transport_hr_km_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();

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

        return modalityTransportHrKmDtos;
    }

    @Override
    public void insert(ModalityTransportHrKmDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{call tpp.modality_transport_hr_km_insert(?,?,?,?,?,?)}");
        callableStatement.setFloat(1, dto.getCostTraveledKm());
        callableStatement.setFloat(2, dto.getCostKmExtras());
        callableStatement.setFloat(3, dto.getCostHrExtras());
        callableStatement.setInt(4, dto.getContractTransport().getId());
        callableStatement.setInt(5, (int) dto.getCostHr());
        callableStatement.setFloat(6, dto.getCostHr());
        callableStatement.execute();
    }

    @Override
    public void update(ModalityTransportHrKmDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.modality_transport_hr_km_update(?,?,?,?,?,?,?)}");
        callableStatement.setInt(1,dto.getId());
        callableStatement.setFloat(2, dto.getCostTraveledKm());
        callableStatement.setFloat(3, dto.getCostKmExtras());
        callableStatement.setFloat(4, dto.getCostHrExtras());
        callableStatement.setInt(5, dto.getContractTransport().getId());
        callableStatement.setInt(6, (int) dto.getCostHr());
        callableStatement.setFloat(7, dto.getCostHr());
        callableStatement.execute();

    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.modality_transport_hr_km_delete(?)}");
        callableStatement.setInt(1, id);
        callableStatement.execute();
    }

    @Override
    public String getGenericType() {
        return null;
    }
}
