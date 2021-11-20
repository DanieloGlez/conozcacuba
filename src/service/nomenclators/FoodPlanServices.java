package service.nomenclators;

import dto.nomenclators.FoodPlanDto;
import dto.nomenclators.ServiceTypeDto;
import service.Services;
import service.ServicesLocator;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class FoodPlanServices implements Services<FoodPlanDto> {
    @Override
    public FoodPlanDto load(int id) throws SQLException {
        return null;
    }

    @Override
    public List<FoodPlanDto> loadAll() throws SQLException {
        List<FoodPlanDto> foodPlanDtos = new LinkedList<>();

        Connection connection = ServicesLocator.getConnection();
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

        return foodPlanDtos;
    }

    @Override
    public void insert(FoodPlanDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_food_plan_insert(?)}");
        callableStatement.setString("name", dto.getName());
        callableStatement.execute();
    }

    @Override
    public void update(FoodPlanDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
