package service.rep;

import dto.rep.ContractHotelReportDto;
import service.Services;
import service.ServicesLocator;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ContractHotelReportServices implements Services<ContractHotelReportDto> {
    @Override
    public ContractHotelReportDto load(int id) throws SQLException {
        return null;
    }

    @Override
    public List<ContractHotelReportDto> loadAll() throws SQLException {
        List<ContractHotelReportDto> contractHotelReportDtos = new LinkedList<>();

        ServicesLocator.getContractHotelServices().loadAll().forEach(contractHotelDto -> {
            contractHotelReportDtos.add(new ContractHotelReportDto(contractHotelDto));
        });

        return contractHotelReportDtos;
    }

    @Override
    public void insert(ContractHotelReportDto dto) throws SQLException {

    }

    @Override
    public void update(ContractHotelReportDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }
}
