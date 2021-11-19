package service.nomenclators;

import dto.nomenclators.HotelFranchiseDto;
import service.Services;

import java.sql.SQLException;
import java.util.List;

public class HotelFranchiseServices implements Services<HotelFranchiseDto> {
    @Override
    public HotelFranchiseDto load(String id) throws SQLException {
        return null;
    }

    @Override
    public List<HotelFranchiseDto> loadAll() throws SQLException {
        return null;
    }

    @Override
    public void insert(HotelFranchiseDto dto) throws SQLException {

    }

    @Override
    public void update(HotelFranchiseDto dto) throws SQLException {

    }

    @Override
    public void delete(String id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
