package service;

import dto.DailyActivityDto;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DailyActivityServices implements Services<DailyActivityDto> {
    @Override
    public DailyActivityDto load(String id) throws SQLException {
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
    public void delete(String id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
