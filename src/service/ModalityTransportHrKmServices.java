package service;

import dto.ContractDto;
import dto.ModalityTransportHrKmDto;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ModalityTransportHrKmServices implements Services<ModalityTransportHrKmDto>{
    @Override
    public ModalityTransportHrKmDto load(int id) throws SQLException {
        return null;
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
                    ServicesLocator.getContractServices().load(resultSet.getInt("id_contract")),
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
        CallableStatement callableStatement = connection.prepareCall("{call tpp.modality_transport_hr_km_insert(?,?,?,?,?)}");
        callableStatement.setFloat("cost_traveled_km", dto.getCostTraveledKm());
        callableStatement.setFloat("cost_km_extras", dto.getCostKmExtras());
        callableStatement.setFloat("cost_hr_extras", dto.getCostHrExtras());
        callableStatement.setInt("id_contract", dto.getContractDto().getId());
        callableStatement.setString("id_vehicle", dto.getVehicleDto().getId());
        callableStatement.setFloat("cost_hr", dto.getCostHr());

        callableStatement.execute();
    }

    @Override
    public void update(ModalityTransportHrKmDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
