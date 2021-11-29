package service;

import dto.HotelReportDto;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class HotelReportServices {
    public List<HotelReportDto> load_all() throws SQLException {
        List<HotelReportDto> hotelReportDtoList = new LinkedList<>();
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{ ? = call tpp.hotel_report_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()){
            hotelReportDtoList.add(new HotelReportDto(
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
                    ServicesLocator.getLocalizationServices().load(resultSet.getInt("id_localizaton"))
            ));
        }

        return hotelReportDtoList;
    }
}
