package service;

import dto.ContractDto;
import dto.ContractServiceDto;
import dto.ContractTransportDto;
import dto.ModalityTransportHrKmDto;
import dto.nom.CompanyTransportDto;
import service.nom.CompanyTransportServices;

import java.sql.*;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ContractTransportServices implements Services<ContractTransportDto> {
    @Override
    public ContractTransportDto load(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.contract_transport_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();
        ContractDto contractDto = ServicesLocator.getContractServices().load(id);

        callableStatement.close();
        connection.close();
        return new ContractTransportDto(
                id,
                contractDto.getContractTypeDto(),
                contractDto.getStartDate(),
                contractDto.getFinishDate(),
                contractDto.getConciliationDate(),
                contractDto.getDescription(),
                ServicesLocator.getCompanyTransportServices().load(resultSet.getInt("id_company_transport")),
                ServicesLocator.getVehicleServices().loadRelated(id)
        );
    }

    @Override
    public List<ContractTransportDto> loadAll() throws SQLException {
        List<ContractTransportDto> contractTransportDtos = new LinkedList<>();
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.contract_transport_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        int id;
        ContractDto contract = new ContractDto();

        callableStatement.close();
        connection.close();
        while (resultSet.next()) {
            id = resultSet.getInt("id_contract");
            contract = ServicesLocator.getContractServices().load(id);
            contractTransportDtos.add(new ContractTransportDto(
                    id,
                    contract.getContractTypeDto(),
                    contract.getStartDate(),
                    contract.getFinishDate(),
                    contract.getConciliationDate(),
                    contract.getDescription(),
                    ServicesLocator.getCompanyTransportServices().load(resultSet.getInt("id_company_transport")),
                    ServicesLocator.getVehicleServices().loadRelated(id)
            ) {
            });
        }

        return contractTransportDtos;
    }

    @Override
    public void insert(ContractTransportDto dto) throws SQLException {
        ContractDto contractDto = new ContractDto(0,
                dto.getContractTypeDto(),
                dto.getStartDate(),
                dto.getFinishDate(),
                dto.getConciliationDate(),
                dto.getDescription());
        ServicesLocator.getContractServices().insert(contractDto);
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.contract_transport_insert(?,?)}");
        callableStatement.setInt(1, contractDto.getId());
        callableStatement.setInt(2, dto.getTransportCompany().getId());
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }

    @Override
    public void update(ContractTransportDto dto) throws SQLException {
        ContractDto contractDto = new ContractDto(dto.getId(),
                dto.getContractTypeDto(),
                dto.getStartDate(),
                dto.getFinishDate(),
                dto.getConciliationDate(),
                dto.getDescription());
        ServicesLocator.getContractServices().update(contractDto);
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.contract_transport_update(?,?)}");
        callableStatement.setDouble(1, contractDto.getId());
        callableStatement.setInt(2, dto.getTransportCompany().getId());
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        ServicesLocator.getContractServices().delete(id);
    }
}
