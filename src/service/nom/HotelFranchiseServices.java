package service.nom;

import dto.nom.HotelFranchiseDto;
import service.Services;
import service.ServicesLocator;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class HotelFranchiseServices implements Services<HotelFranchiseDto> {
    @Override
    public HotelFranchiseDto load(int id) throws SQLException {
        return null;
    }

    @Override
    public List<HotelFranchiseDto> loadAll() throws SQLException {
        List<HotelFranchiseDto> hotelFranchiseDtos = new LinkedList<>();

        Connection connection = service.ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.n_hotel_franchise_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
            hotelFranchiseDtos.add(new HotelFranchiseDto(
                    resultSet.getInt("id_hotel_franchise"),
                    resultSet.getString("name")
            ));
        }

        return hotelFranchiseDtos;
    }

    @Override
    public void insert(HotelFranchiseDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_hotel_franchise_insert(?)}");
        callableStatement.setString("name", dto.getName());
        callableStatement.execute();
    }

    @Override
    public void update(HotelFranchiseDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
