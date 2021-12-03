package service.rep;

import dto.rep.ContractTransportReportDto;
import dto.rep.HotelReportDto;
import service.Services;
import service.ServicesLocator;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class HotelReportServices implements Services<HotelReportDto> {

    @Override
    public HotelReportDto load(int id) throws SQLException {
        return null;
    }

    @Override
    public List<HotelReportDto> loadAll() throws SQLException {
        List<HotelReportDto> hotelReportDtos = new LinkedList<>();

        ServicesLocator.getHotelServices().loadAll().forEach(hotelDto -> {
            hotelReportDtos.add(new HotelReportDto(hotelDto));
        });

        return hotelReportDtos;
    }

    @Override
    public void insert(HotelReportDto dto) throws SQLException {

    }

    @Override
    public void update(HotelReportDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }
}
