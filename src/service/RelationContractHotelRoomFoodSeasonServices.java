package service;

import dto.RelationContractHotelRoomFoodSeasonDto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class RelationContractHotelRoomFoodSeasonServices {
    public void insert(RelationContractHotelRoomFoodSeasonDto relation) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{ call tpp.r_season_room_type_food_plan_insert(?,?,?,?,?)}");
        callableStatement.setDouble(1, relation.getPrice());
        callableStatement.setInt(2, relation.getIdFoodPlan());
        callableStatement.setInt(3, relation.getIdSeason());
        callableStatement.setInt(4, relation.getIdRoomType());
        callableStatement.setInt(5, relation.getIdContractHotel());
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }
}
