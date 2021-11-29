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
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.modality_transport_rt_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();
        callableStatement.close();
        connection.close();

        return new ModalityTransportRtDto(
                resultSet.getInt("id_modality_transport_rt"),
                resultSet.getString("rtDescription"),
                resultSet.getFloat("ostRt"),
                resultSet.getFloat("costRoundTrip"),
                ServicesLocator.getContractTransportServices().load( resultSet.getInt("id_contract")),
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
                    ServicesLocator.getContractTransportServices().load(resultSet.getInt("id_contract")),
                    ServicesLocator.getVehicleServices().load(resultSet.getInt("id_vehicle"))
            ) {
            });
        }

        callableStatement.close();
        connection.close();
        return modalityTransportRtDtos;
    }

    @Override
    public void insert(ModalityTransportRtDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.modality_transport_rt_insert(?,?,?,?,?)}");
        callableStatement.setInt(1, dto.getContractTransport().getId());
        callableStatement.setInt(2, dto.getVehicle().getId());
        callableStatement.setString(3, dto.getRtDescription());
        callableStatement.setFloat(4, dto.getCostRt());
        callableStatement.setFloat(5, dto.getCostRoundTrip());
        callableStatement.execute();
        callableStatement.close();
        connection.close();
    }

    @Override
    public void update(ModalityTransportRtDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.modality_transport_rt_update(?,?,?,?,?,?)}");
        callableStatement.setInt(1,dto.getId());
        callableStatement.setInt(2, dto.getContractTransport().getId());
        callableStatement.setInt(3, dto.getVehicle().getId());
        callableStatement.setString(4, dto.getRtDescription());
        callableStatement.setFloat(5, dto.getCostRt());
        callableStatement.setFloat(6, dto.getCostRoundTrip());
        callableStatement.execute();
        callableStatement.close();
        connection.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.modality_transport_rt_delete(?)}");
        callableStatement.setInt(1, id);
        callableStatement.execute();
        callableStatement.close();
        connection.close();
    }
}
