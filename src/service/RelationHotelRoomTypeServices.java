package service;

import dto.ContractTransportDto;
import dto.HotelDto;
import dto.nom.FoodPlanDto;
import dto.nom.RoomTypeDto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ListIterator;

public class RelationHotelRoomTypeServices {
    public void insert(HotelDto hotelDto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{ call tpp.r_hotel_room_type_insert(?,?)}");
        ListIterator<RoomTypeDto> listIterator = hotelDto.getRoomTypes().listIterator();
        int idHotel = hotelDto.getId();

        while (listIterator.hasNext()){
            callableStatement.setInt(1, idHotel);
            callableStatement.setInt(2, listIterator.next().getId());
            callableStatement.execute();
        }

        callableStatement.close();
        connection.close();
    }

    public void update(HotelDto hotelDto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{ call tpp.r_hotel_room_type_delete(?)}");
        callableStatement.setInt(1, hotelDto.getId());
        callableStatement.execute();

        insert(hotelDto);

        callableStatement.close();
        connection.close();
    }
}
