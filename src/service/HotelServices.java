package service;

import dto.ContractDto;
import dto.HotelDto;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class HotelServices implements Services<HotelDto> {


    @Override
    public HotelDto load(int id) throws SQLException {
        return null;
    }

    @Override
    public List<HotelDto> loadAll() throws SQLException {
        List<HotelDto> hotelDtos = new LinkedList<>();

        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.hotel_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
            hotelDtos.add(new HotelDto(
                    resultSet.getInt("id_hotel"),
                    resultSet.getString("name"),
                    resultSet.getString("address"),
                    resultSet.getString("category"),
                    resultSet.getString("telephone_number"),
                    resultSet.getString("fax"),
                    resultSet.getString("email"),
                    resultSet.getFloat("dist_to_city"),
                    resultSet.getFloat("dist_to_airport"),
                    resultSet.getInt("rooms_amount"),
                    resultSet.getInt("floors_amount"),
                    ServicesLocator.getHotelFranchiseServices().load(resultSet.getInt("id_hotel_franchise")),
                    ServicesLocator.getProvinceServices().load(resultSet.getInt("id_province")),
                    ServicesLocator.getLocalizationServices().load(resultSet.getInt("id_localization")),
                    ServicesLocator.getRoomTypeServices().loadRelated(resultSet.getInt("id_hotel")),
                    ServicesLocator.getFoodPlanServices().loadRelated(resultSet.getInt("id_hotel")),
                    ServicesLocator.getModalityCommercialServices().loadRelated(resultSet.getInt("id_hotel"))
            ) {
            });
        }

        return hotelDtos;
    }

    @Override
    public void insert(HotelDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{call tpp.hotel_insert(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        callableStatement.setString("name", dto.getName());
                callableStatement.setString("address", dto.getAddress());
                callableStatement.setString("category", dto.getCategory());
                callableStatement.setString("telephone_number", dto.getTelephoneNumber());
                callableStatement.setString("fax", dto.getFax());
                callableStatement.setString("emial", dto.getEmail());
                callableStatement.setFloat("dist_to_city", dto.getDistToCity());
                callableStatement.setFloat("dist_to_airport", dto.getDistToAirport());
                callableStatement.setInt("rooms_amount", dto.getRoomsAmount());
                callableStatement.setInt("floors_amount", dto.getFloorsAmount());
                callableStatement.setInt("id_hotel_franchise", dto.getHotelFranchise().getId());
                callableStatement.setInt("id_province", dto.getProvince().getId());
                callableStatement.setInt("id_localization", dto.getLocalization().getId());
        callableStatement.execute();
    }

    @Override
    public void update(HotelDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
