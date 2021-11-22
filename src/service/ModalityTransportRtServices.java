package service;

import dto.ModalityTransportHrKmDto;
import dto.ModalityTransportRtDto;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ModalityTransportRtServices implements Services<ModalityTransportRtDto>{
    @Override
    public ModalityTransportRtDto load(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.modality_transport_rt_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();

        return new ModalityTransportRtDto(
                resultSet.getInt("id_modality_transport_rt"),
                resultSet.getString("rtDescription"),
                resultSet.getFloat("ostRt"),
                resultSet.getFloat("costRoundTrip"),
                ServicesLocator.getContractServices().load( resultSet.getInt("id_contract")),
                ServicesLocator.getVehicleServices().load( resultSet.getInt("id_vehicle"))
        );
    }

    @Override
    public List<ModalityTransportRtDto> loadAll() throws SQLException {
        List<ModalityTransportRtDto> modalityTransportRtDtos = new LinkedList<>();

        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.modality_transport_rt_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
            modalityTransportRtDtos.add(new ModalityTransportRtDto(
                    resultSet.getInt("id_modality_transport_rt"),
                    resultSet.getString("rt_description"),
                    resultSet.getFloat("cost_rt"),
                    resultSet.getFloat("cost_round_trip"),
                    ServicesLocator.getContractServices().load(resultSet.getInt("id_contract")),
                    ServicesLocator.getVehicleServices().load(resultSet.getInt("id_vehicle"))
            ) {
            });
        }

        return modalityTransportRtDtos;
    }

    @Override
    public void insert(ModalityTransportRtDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{call tpp.modality_transport_rt_insert(?,?,?,?,?)}");
        callableStatement.setString("rt_description", dto.getRtDescription());
        callableStatement.setFloat("cost_rt", dto.getCostRt());
        callableStatement.setFloat("cost_round_trip", dto.getCostRoundTrip());
        callableStatement.setInt("id_contract", dto.getContractDto().getId());
        callableStatement.setInt("id_vehicle", dto.getVehicleDto().getId());

        callableStatement.execute();
    }

    @Override
    public void update(ModalityTransportRtDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.modality_transport_rt_update(?,?,?,?,?)}");
        callableStatement.setString("rt_description", dto.getRtDescription());
        callableStatement.setFloat("cost_rt", dto.getCostRt());
        callableStatement.setFloat("cost_round_trip", dto.getCostRoundTrip());
        callableStatement.setInt("id_contract", dto.getContractDto().getId());
        callableStatement.setInt("id_vehicle", dto.getVehicleDto().getId());

        callableStatement.execute();


    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.modality_transport_rt_delete(?)}");
        callableStatement.setInt("id_modality_transport_rt", id);
        callableStatement.execute();

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
