package service.rep;

import dto.rep.TouristicPackageReportDto;
import dto.rep.TouristicPackageSalesReportDto;
import service.Services;
import service.ServicesLocator;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TouristicPackageSalesReportServices implements Services<TouristicPackageSalesReportDto> {
    @Override
    public TouristicPackageSalesReportDto load(int id) throws SQLException {
        return null;
    }

    @Override
    public List<TouristicPackageSalesReportDto> loadAll() throws SQLException {
        List<TouristicPackageSalesReportDto> touristicPackageSalesReportDtos = new LinkedList<>();

        ServicesLocator.getTouristicPackageServices().loadAll().forEach(touristicPackageSalesDto -> {
            touristicPackageSalesReportDtos.add(new TouristicPackageSalesReportDto(touristicPackageSalesDto));
        });

        return touristicPackageSalesReportDtos;
    }

    @Override
    public void insert(TouristicPackageSalesReportDto dto) throws SQLException {

    }

    @Override
    public void update(TouristicPackageSalesReportDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }
}
