package service.rep;

import dto.rep.ContractHotelReportDto;
import dto.rep.TouristicPackageReportDto;
import service.Services;
import service.ServicesLocator;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TouristicPackageReportServices implements Services<TouristicPackageReportDto> {
    @Override
    public TouristicPackageReportDto load(int id) throws SQLException {
        return null;
    }

    @Override
    public List<TouristicPackageReportDto> loadAll() throws SQLException {
        List<TouristicPackageReportDto> touristicPackageReportDtos = new LinkedList<>();

        ServicesLocator.getTouristicPackageServices().loadAll().forEach(touristicPackageDto -> {
            touristicPackageReportDtos.add(new TouristicPackageReportDto(touristicPackageDto));
        });

        return touristicPackageReportDtos;
    }

    @Override
    public void insert(TouristicPackageReportDto dto) throws SQLException {

    }

    @Override
    public void update(TouristicPackageReportDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }
}
