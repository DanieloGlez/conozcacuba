package service;

import dto.HotelDto;

import java.sql.SQLException;
import java.util.List;

public class HotelServices implements Services<HotelDto> {


    @Override
    public HotelDto load(int id) throws SQLException {
        return null;
    }

    @Override
    public List<HotelDto> loadAll() throws SQLException {
        return null;
    }

    @Override
    public void insert(HotelDto dto) throws SQLException {

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
