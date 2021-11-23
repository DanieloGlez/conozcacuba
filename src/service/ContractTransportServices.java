package service;

import dto.ContractDto;
import dto.ContractTransportDto;
import dto.nom.CompanyTransportDto;
import service.nom.CompanyTransportServices;

import java.sql.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ContractTransportServices implements Services<ContractTransportDto>{
    @Override
    public ContractTransportDto load(int id) throws SQLException {
        return null;
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
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{call tpp.contract_transport_insert(?)}");
        callableStatement.setInt("id_company_transport", dto.getTransportCompany().getId());
        callableStatement.execute();
    }

    @Override
    public void update(ContractTransportDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
