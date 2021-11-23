package service;

import dto.ContractDto;
import dto.HotelDto;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class HotelServices implements Services<HotelDto> {


    @Override
    public HotelDto load(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.hotel_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();


        return new HotelDto(
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


        );
    }

    @Override
    public List<HotelDto> loadAll() throws SQLException {
        List<HotelDto> hotelDtos = new LinkedList<>();

        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.hotel_load}");
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
        callableStatement.setString(1, dto.getName());
        callableStatement.setString(2, dto.getAddress());
        callableStatement.setString(3, dto.getCategory());
        callableStatement.setString(4, dto.getTelephoneNumber());
        callableStatement.setString(5, dto.getFax());
        callableStatement.setString(6, dto.getEmail());
        callableStatement.setFloat(7, dto.getDistToCity());
        callableStatement.setFloat(8, dto.getDistToAirport());
        callableStatement.setInt(9, dto.getRoomsAmount());
        callableStatement.setInt(10, dto.getFloorsAmount());
        callableStatement.setInt(11, dto.getHotelFranchise().getId());
        callableStatement.setInt(12, dto.getProvince().getId());
        callableStatement.setInt(13, dto.getLocalization().getId());
        callableStatement.execute();
    }

    @Override
    public void update(HotelDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{call tpp.hotel_update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        callableStatement.setInt(1,dto.getId());
        callableStatement.setString(2,dto.getName());
        callableStatement.setString(3,dto.getAddress());
        callableStatement.setString(4,dto.getCategory());
        callableStatement.setString(5, dto.getTelephoneNumber());
        callableStatement.setString(6, dto.getFax());
        callableStatement.setString(7, dto.getEmail());
        callableStatement.setFloat(8, dto.getDistToCity());
        callableStatement.setFloat(9, dto.getDistToAirport());
        callableStatement.setInt(10, dto.getRoomsAmount());
        callableStatement.setInt(11, dto.getFloorsAmount());
        callableStatement.setInt(12, dto.getHotelFranchise().getId());
        callableStatement.setInt(13, dto.getProvince().getId());
        callableStatement.setInt(14, dto.getLocalization().getId());
        callableStatement.execute();

    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{call tpp.hotel_delete(?)}");
        callableStatement.setInt(1,id);
        callableStatement.execute();

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
