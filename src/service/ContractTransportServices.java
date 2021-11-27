package service;

import dto.*;
import dto.nom.CompanyTransportDto;
import dto.nom.ContractTypeDto;
import service.nom.CompanyTransportServices;

import java.sql.*;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ContractTransportServices implements Services<ContractTransportDto> {
    @Override
    public ContractTransportDto load(int id) throws SQLException {
        ContractTransportDto contractTransportDto;
        ContractDto contractDto;
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        int idContract;
        int idVehicle;
        List<Integer> idContainerVehicle = new LinkedList<>();
        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.contract_transport_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();

        idContract = resultSet.getInt("id_contract");
        contractDto = ServicesLocator.getContractServices().load(idContract);

        contractTransportDto = new ContractTransportDto(
                idContract,
                contractDto.getContractTypeDto(),
                contractDto.getStartDate(),
                contractDto.getFinishDate(),
                contractDto.getConciliationDate(),
                contractDto.getDescription(),
                ServicesLocator.getCompanyTransportServices().load(resultSet.getInt("id_company_transport")));

        while (resultSet.next()) {
            idVehicle = resultSet.getInt("id_vehicle");

            if (!idContainerVehicle.contains(idVehicle)) {
                contractTransportDto.getVehicles().add(ServicesLocator.getVehicleServices().load(idVehicle));
                idContainerVehicle.add(idVehicle);
            }
        }

        callableStatement.close();
        connection.close();
        return contractTransportDto;
    }

    @Override
    public List<ContractTransportDto> loadAll() throws SQLException {
        List<ContractTransportDto> ListContractTransportDto = new LinkedList<>();
        ContractTransportDto contractTransportDto = null;
        VehicleDto vehicleDto;
        Connection connection = ServicesLocator.getConnection();
        int idContractTransport;
        int idVehicle;
        List<Integer> idContainerContractTransport = new LinkedList<>();
        List<Integer> idContainerVehicle = new LinkedList<>();

        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.contract_transport_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
            idContractTransport = resultSet.getInt("id_contract");
            idVehicle = resultSet.getInt("id_vehicle");

            if (!idContainerContractTransport.contains(idContractTransport)) {//inserto los elementos de la primera aparicion de un identificador
                ContractDto contractDto = ServicesLocator.getContractServices().load(idContractTransport);
                idContainerContractTransport.add(idContractTransport);
                idContainerVehicle.clear();
                vehicleDto = ServicesLocator.getVehicleServices().load(idVehicle);
                List<VehicleDto> ListVehicleInsert = new LinkedList<>();
                ListVehicleInsert.add(vehicleDto);
                idContainerVehicle.add(idVehicle);

                contractTransportDto = new ContractTransportDto(
                        idContractTransport,
                        contractDto.getContractTypeDto(),
                        contractDto.getStartDate(),
                        contractDto.getFinishDate(),
                        contractDto.getConciliationDate(),
                        contractDto.getDescription(),
                        ServicesLocator.getCompanyTransportServices().load(resultSet.getInt("id_company_transport")));
                contractTransportDto.setVehicles(ListVehicleInsert);
                ListContractTransportDto.add(contractTransportDto);
            } else {//inserto para el mismo id los tipos de hab, planes alim y modalidades diferentes
                if (!idContainerVehicle.contains(idVehicle)) {
                    contractTransportDto.getVehicles().add(ServicesLocator.getVehicleServices().load(idVehicle));
                    idContainerVehicle.add(idVehicle);
                }
            }
        }

        callableStatement.close();
        connection.close();
        return ListContractTransportDto;
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
