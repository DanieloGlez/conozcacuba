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

public class ContractTransportServices implements Services<ContractTransportDto>{
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

        connection.close();
        return contractTransportDtos;
    }

    @Override
    public void insert(ContractTransportDto dto) throws SQLException {
        insertInContract(dto);
        int id = findIdContract(dto);
        dto.setId(id);
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.contract_transport_insert(?,?)}");
        callableStatement.setInt(1, dto.getId());
        callableStatement.setInt(2, dto.getTransportCompany().getId());
        callableStatement.execute();

        connection.close();
    }

    private int findIdContract(ContractTransportDto dto) {
        int id = 0;
        try {
            LinkedList<ContractDto> contractDtoLinkedList = (LinkedList<ContractDto>) ServicesLocator.getContractServices().loadAll();
            Iterator<ContractDto> i = contractDtoLinkedList.iterator();
            boolean found = false;
            while (i.hasNext() && !found) {
                ContractDto contractDtoCurrent = i.next();
                if (contractDtoCurrent.getDescription().equals(dto.getDescription())) {
                    id = contractDtoCurrent.getId();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

    @Override
    public void update(ContractTransportDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.contract_transport_update(?,?)}");
        callableStatement.setInt(1, dto.getId());
        callableStatement.setInt(2, dto.getTransportCompany().getId());
        callableStatement.execute();

        connection.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.contract_transport_delete(?)}");
        callableStatement.setInt(1, id);
        callableStatement.execute();

        connection.close();
    }

    @Override
    public String getGenericType() {
        return null;
    }

    private void insertInContract(ContractTransportDto contractTransportDto) throws SQLException {
        ContractDto contractDto = new ContractDto(
                contractTransportDto.getId(),
                ServicesLocator.getContractTypeServices().load(contractTransportDto.getContractTypeDto().getId()),
                contractTransportDto.getStartDate(),
                contractTransportDto.getFinishDate(),
                contractTransportDto.getConciliationDate(),
                contractTransportDto.getDescription());
        try {
            ServicesLocator.getContractServices().insert(contractDto);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
