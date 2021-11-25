package service.nom;

import dto.nom.DailyActivityDto;
import dto.nom.FoodPlanDto;
import dto.nom.VehicleBrandDto;
import service.Relation;
import service.Services;
import service.ServicesLocator;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class FoodPlanServices implements Services<FoodPlanDto>, Relation<FoodPlanDto> {
    @Override
    public FoodPlanDto load(int id_food_plan) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.n_food_plan_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id_food_plan);

        callableStatement.execute();

        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();

        callableStatement.close();
        connection.close();
        return new FoodPlanDto(
                resultSet.getInt("id_food_plan"),
                resultSet.getString("name")
        );
    }

    @Override
    public List<FoodPlanDto> loadAll() throws SQLException {
        List<FoodPlanDto> foodPlanDtos = new LinkedList<>();

        Connection connection = service.ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.n_food_plan_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
            foodPlanDtos.add(new FoodPlanDto(
                    resultSet.getInt("id_food_plan"),
                    resultSet.getString("name")
            ));
        }

        callableStatement.close();
        connection.close();
        return foodPlanDtos;
    }

    @Override
    public void insert(FoodPlanDto dto) throws SQLException {
        Connection connection = service.ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_food_plan_insert(?)}");
        callableStatement.setString(1, dto.getName());
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }

    @Override
    public void update(FoodPlanDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_food_plan_update(?,?)}");
        callableStatement.setInt(1, dto.getId());
        callableStatement.setString(2, dto.getName());
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }

    @Override
    public void delete(int id_food_plan) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_food_plan_delete(?)}");
        callableStatement.setInt(1, id_food_plan);
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }

    @Override
    public List<FoodPlanDto> loadRelated(int id) throws SQLException {
        LinkedList<FoodPlanDto> foodPlanDtoLinkedList=new LinkedList<>();
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{ ? =call tpp.r_hotel_food_plan_load_by_id(?)}");
        callableStatement.registerOutParameter(1,Types.REF_CURSOR);
        callableStatement.setInt(2,id);
        callableStatement.execute();
        ResultSet resultSet= (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()){
            foodPlanDtoLinkedList.add(load(resultSet.getInt("id_food_plan")));
        }

        callableStatement.close();
        connection.close();
        return foodPlanDtoLinkedList;
    }
}
