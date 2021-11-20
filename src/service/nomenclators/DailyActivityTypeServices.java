package service.nomenclators;

import dto.DailyActivityDto;
import service.Services;

import java.sql.*;
import java.util.List;

public class DailyActivityTypeServices implements Services<DailyActivityDto> {
    @Override
    public DailyActivityDto load(int id) throws SQLException {
        return null;
    }

    @Override
    public List<DailyActivityDto> loadAll() throws SQLException {
        return null;
    }

    @Override
    public void insert(DailyActivityDto dto) throws SQLException {

    }

    @Override
    public void update(DailyActivityDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
