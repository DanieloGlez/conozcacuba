package service;

import dto.RelationContractHotelRoomTypeFoodPlanSeasonDto;

import java.sql.*;

public class RelationContractHotelRoomFoodSeasonServices {
    public void insert(RelationContractHotelRoomTypeFoodPlanSeasonDto relation) throws SQLException {
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

    public void update(RelationContractHotelRoomTypeFoodPlanSeasonDto relation) throws SQLException {
        insert(relation);
    }

    public void delete(int idContractHotel) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{ call tpp.r_season_room_type_food_plan_delete(?)}");
        callableStatement.setInt(1, idContractHotel);
        callableStatement.execute();
        callableStatement.close();
        connection.close();
    }

    public float load(int idContactHotel) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{ ? = call tpp.r_season_room_type_food_plan_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, idContactHotel);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        float cost = 0;

        while (resultSet.next()){
            if (resultSet.getFloat("price") > cost)
                cost = resultSet.getFloat("price");
        }

        return cost;
    }
}
