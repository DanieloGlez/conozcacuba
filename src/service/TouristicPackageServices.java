package service;

import dto.TouristicPackageDto;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TouristicPackageServices implements Services<TouristicPackageDto>{
    @Override
    public TouristicPackageDto load(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.touristic_package_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();

        return new TouristicPackageDto(
                resultSet.getInt("id_touristic_package"),
                resultSet.getString("promotionla_name"),
                resultSet.getInt("days_amount"),
                resultSet.getInt("nights_amount"),
                resultSet.getInt("pax_amount"),
                resultSet.getFloat("cost_hotel"),
                resultSet.getFloat("cost_transport"),
                resultSet.getFloat("cost_transport_hotel_airport"),
                resultSet.getFloat("cost_total"),
                resultSet.getFloat("price"),
                ServicesLocator.getContractServices().loadRelated(id)
        );
    }

    @Override
    public List<TouristicPackageDto> loadAll() throws SQLException {
        List<TouristicPackageDto> touristicPackageDtos = new LinkedList<>();
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.touristic_package_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        int idTourPack;


        while (resultSet.next()) {
            idTourPack = resultSet.getInt("id_touristic_package");
            touristicPackageDtos.add(new TouristicPackageDto(
                    idTourPack,
                    resultSet.getString("promotional_name"),
                    resultSet.getInt("days_amount"),
                    resultSet.getInt("nights_amount"),
                    resultSet.getInt("pax_amount"),
                    resultSet.getFloat("cost_hotel"),
                    resultSet.getFloat("cost_transport"),
                    resultSet.getFloat("cost_transport_hotel_airport"),
                    resultSet.getFloat("cost_total"),
                    resultSet.getFloat("price"),
                    ServicesLocator.getContractServices().loadRelated(idTourPack)
            ) {
            });
        }

        return touristicPackageDtos;
    }

    @Override
    public void insert(TouristicPackageDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.touristic_package_insert(?,?,?,?,?,?,?,?,?)}");
        callableStatement.setString(1, dto.getPromotionalName());
        callableStatement.setInt(2, dto.getDaysAmount());
        callableStatement.setInt(3, dto.getNightsAmount());
        callableStatement.setInt(4, dto.getPaxAmount());
        callableStatement.setFloat(5, dto.getCostHotel());
        callableStatement.setFloat(6, dto.getCostTransport());
        callableStatement.setFloat(7, dto.getCostTransportHA());
        callableStatement.setFloat(8, dto.getCostTotal());
        callableStatement.setFloat(9, dto.getPrice());
        callableStatement.execute();
    }

    @Override
    public void update(TouristicPackageDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.touristic_package_update(?,?,?,?,?,?,?,?,?,?)}");
        callableStatement.setInt(1, dto.getId());
        callableStatement.setString(2, dto.getPromotionalName());
        callableStatement.setInt(3, dto.getDaysAmount());
        callableStatement.setInt(4, dto.getNightsAmount());
        callableStatement.setInt(5, dto.getPaxAmount());
        callableStatement.setFloat(6, dto.getCostHotel());
        callableStatement.setFloat(7, dto.getCostTransport());
        callableStatement.setFloat(8, dto.getCostTransportHA());
        callableStatement.setFloat(9, dto.getCostTotal());
        callableStatement.setFloat(10, dto.getPrice());
        callableStatement.execute();
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{ call tpp.touristic_package_delete(?)}");
        callableStatement.setInt(1, id);
        callableStatement.execute();
    }


    @Override
    public String getGenericType() {
        return null;
    }
}
