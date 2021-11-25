package service.nom;

import dto.nom.CompanyServiceDto;
import dto.nom.DailyActivityDto;
import dto.nom.ModalityCommercialDto;
import service.Relation;
import service.Services;
import service.ServicesLocator;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ModalityCommercialServices implements Services<ModalityCommercialDto>, Relation<ModalityCommercialDto> {
    @Override
    public ModalityCommercialDto load(int id_modality_comertial) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.n_modality_hotel_comertial_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id_modality_comertial);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();

        callableStatement.close();
        connection.close();
        return new ModalityCommercialDto(
                id_modality_comertial,
                resultSet.getString("name")
        );
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

        callableStatement.close();
        connection.close();
        return modalityCommercialDtos;
    }

    @Override
    public void insert(ModalityCommercialDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_modality_hotel_comertial_insert(?)}");
        callableStatement.setString(1, dto.getName());
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }

    @Override
    public void update(ModalityCommercialDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_modality_hotel_comertial_update(?,?)}");
        callableStatement.setInt(1, dto.getId());
        callableStatement.setString(2, dto.getName());
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }

    @Override
    public void delete(int id_modality_hotel_comertial) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_modality_hotel_comertial_delete(?)}");
        callableStatement.setInt(1, id_modality_hotel_comertial);
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }

    @Override
    public List<ModalityCommercialDto> loadRelated(int id) throws SQLException {
        LinkedList<ModalityCommercialDto> modalityCommercialDtos=new LinkedList<>();
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{ ? =call tpp.r_hotel_modality_hotel_comertial_load_by_id(?)}");
        callableStatement.registerOutParameter(1,Types.REF_CURSOR);
        callableStatement.setInt(2,id);
        callableStatement.execute();
        ResultSet resultSet= (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()){
            modalityCommercialDtos.add(load(resultSet.getInt("id_modality_hotel_comertial")));
        }

        callableStatement.close();
        connection.close();
        return modalityCommercialDtos;
    }
}
