package service.rep;

import dto.rep.ContractTransportReportDto;
import service.Services;
import service.ServicesLocator;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ContractTransportReportServices implements Services<ContractTransportReportDto> {


    @Override
    public ContractTransportReportDto load(int id) throws SQLException {
        return null;
    }

    @Override
    public List<ContractTransportReportDto> loadAll() throws SQLException {
        List<ContractTransportReportDto> contractTransportReportDtos = new LinkedList<>();

        ServicesLocator.getContractTransportServices().loadAll().forEach(contractTransportDto -> {
            contractTransportReportDtos.add(new ContractTransportReportDto(contractTransportDto));
        });

        return contractTransportReportDtos;
    }

    @Override
    public void insert(ContractTransportReportDto dto) throws SQLException {

    }

    @Override
    public void update(ContractTransportReportDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }
}
