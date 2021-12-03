package service.rep;

import dto.rep.ContractServiceReportDto;
import service.Services;
import service.ServicesLocator;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ContractServiceReportServices implements Services<ContractServiceReportDto> {

    @Override
    public ContractServiceReportDto load(int id) throws SQLException {
        return null;
    }

    @Override
    public List<ContractServiceReportDto> loadAll() throws SQLException {
        List<ContractServiceReportDto> contractServiceReportDtos = new LinkedList<>();

        ServicesLocator.getContractServiceServices().loadAll().forEach(contractServiceDto -> {
            contractServiceReportDtos.add(new ContractServiceReportDto(contractServiceDto));
        });

        return contractServiceReportDtos;
    }

    @Override
    public void insert(ContractServiceReportDto dto) throws SQLException {

    }

    @Override
    public void update(ContractServiceReportDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }
}
