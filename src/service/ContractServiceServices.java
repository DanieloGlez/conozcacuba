package service;

import dto.ContractDto;
import dto.ContractServiceDto;
import dto.VehicleDto;
import dto.nom.ContractTypeDto;
import dto.nom.DailyActivityDto;
import dto.nom.ProvinceDto;

import java.sql.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ContractServiceServices implements Services<ContractServiceDto> {

    @Override
    public ContractServiceDto load(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.contract_service_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();

        callableStatement.close();
        connection.close();
        return new ContractServiceDto(
                id,
                resultSet.getDate("start_date"),
                resultSet.getDate("finish_date"),
                resultSet.getDate("conciliation_date"),
                resultSet.getString("description"),
                ServicesLocator.getContractServices().load(id).getContractTypeDto(),
                resultSet.getFloat("pax_cost"),
                ServicesLocator.getProvinceServices().load(resultSet.getInt("id_province")),
                ServicesLocator.getDailyActivityServices().loadRelated(id)
        );
    }

    @Override
    public List<ContractServiceDto> loadAll() throws SQLException {
        List<ContractServiceDto> contractServiceDtos = new LinkedList<>();
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.contract_service_load}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        ContractDto contractDto;

        while (resultSet.next()) {
            contractDto = ServicesLocator.getContractServices().load(resultSet.getInt("id_contract"));
            contractServiceDtos.add(new ContractServiceDto(
                    contractDto.getId(),
                    contractDto.getStartDate(),
                    contractDto.getFinishDate(),
                    contractDto.getConciliationDate(),
                    contractDto.getDescription(),
                    contractDto.getContractTypeDto(),
                    resultSet.getFloat(1),
                    ServicesLocator.getProvinceServices().load(resultSet.getInt(3)),
                    ServicesLocator.getDailyActivityServices().loadRelated(contractDto.getId())
            ) {
            });
        }

        callableStatement.close();
        connection.close();
        return contractServiceDtos;
    }

    @Override
    public void insert(ContractServiceDto dto) throws SQLException {
        ContractDto contractDto = new ContractDto(0,
                dto.getContractTypeDto(),
                dto.getStartDate(),
                dto.getFinishDate(),
                dto.getConciliationDate(),
                dto.getDescription());
        ServicesLocator.getContractServices().insert(contractDto);
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.contract_service_insert(?,?,?)}");
        callableStatement.setDouble(1, dto.getPaxCost());
        callableStatement.setInt(2, dto.getIdProvince().getId());
        callableStatement.setInt(3, contractDto.getId());
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }

    @Override
    public void update(ContractServiceDto dto) throws SQLException {
        ContractDto contractDto = new ContractDto(dto.getId(),
                dto.getContractTypeDto(),
                dto.getStartDate(),
                dto.getFinishDate(),
                dto.getConciliationDate(),
                dto.getDescription());
        ServicesLocator.getContractServices().update(contractDto);
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.contract_service_update(?,?,?)}");
        callableStatement.setDouble(1, dto.getPaxCost());
        callableStatement.setInt(2, dto.getIdProvince().getId());
        callableStatement.setInt(3, dto.getId());
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        ServicesLocator.getContractServices().delete(id);
    }
}

