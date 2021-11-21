package service.nom;

import dto.nom.ModalityCommercialDto;
import service.Services;
import service.ServicesLocator;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ModalityComertialServices implements Services<ModalityCommercialDto> {
    @Override
    public ModalityCommercialDto load(int id) throws SQLException {
        return null;
    }

    @Override
    public List<ModalityCommercialDto> loadAll() throws SQLException {
        List<ModalityCommercialDto> modalityCommercialDtos = new LinkedList<>();

        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.n_modality_hotel_comertial_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
            modalityCommercialDtos.add(new ModalityCommercialDto(
                    resultSet.getInt("id_modality_hotel_comertial"),
                    resultSet.getString("name")
            ));
        }

        return modalityCommercialDtos;
    }

    @Override
    public void insert(ModalityCommercialDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_modality_hotel_comertial_insert(?)}");
        callableStatement.setString("name", dto.getName());
        callableStatement.execute();
    }

    @Override
    public void update(ModalityCommercialDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
