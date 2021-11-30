package service;

import dto.ContractDto;
import dto.ContractServiceDto;
import dto.nom.CompanyServiceDto;
import dto.nom.ContractTypeDto;
import dto.nom.RoomTypeDto;
import service.nom.ContractTypeServices;

import java.sql.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ContractServices implements Services<ContractDto>, Relation<ContractDto> {
    @Override
    public ContractDto load(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.contract_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();

        callableStatement.close();
        connection.close();
        return new ContractDto(
                resultSet.getInt("id_contract"),
                ServicesLocator.getContractTypeServices().load(resultSet.getInt("id_contract_type")),
                resultSet.getDate("start_date"),
                resultSet.getDate("finish_date"),
                resultSet.getDate("conciliation_date"),
                resultSet.getString("description")
        );
    }

    @Override
    public List<ContractDto> loadAll() throws SQLException {
        List<ContractDto> contractDtos = new LinkedList<>();
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.contract_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {

            contractDtos.add(new ContractDto(
                    resultSet.getInt("id_contract"),
                    ServicesLocator.getContractTypeServices().load(resultSet.getInt("id_contract_type")),
                    resultSet.getDate("start_date"),
                    resultSet.getDate("finish_date"),
                    resultSet.getDate("conciliation_date"),
                    resultSet.getString("description")
            ) {
            });
        }

        callableStatement.close();
        connection.close();
        return contractDtos;
    }

    @Override
    public void insert(ContractDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.contract_insert(?,?,?,?,?)}");
        callableStatement.setDate(1, dto.getStartDate());
        callableStatement.setDate(2, dto.getFinishDate());
        callableStatement.setDate(3, dto.getConciliationDate());
        callableStatement.setString(4, dto.getDescription());
        callableStatement.setInt(5, dto.getContractTypeDto().getId());
        callableStatement.execute();

        connection.setAutoCommit(false);
        callableStatement = connection.prepareCall("{ ? = call tpp.contract_return_id_max()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();
        dto.setId(resultSet.getInt(1));

        callableStatement.close();
        connection.close();
    }

    @Override
    public void update(ContractDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.contract_update(?,?,?,?,?,?)}");
        callableStatement.setInt(1, dto.getId());
        callableStatement.setDate(2, dto.getStartDate());
        callableStatement.setDate(3, dto.getFinishDate());
        callableStatement.setDate(4, dto.getConciliationDate());
        callableStatement.setString(5, dto.getDescription());
        callableStatement.setInt(6, dto.getContractTypeDto().getId());
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.contract_delete(?)}");
        callableStatement.setInt(1, id);
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }

    @Override
    public List<ContractDto> loadRelated(int id) throws SQLException {
        LinkedList<ContractDto> contractDtoLinkedList = new LinkedList<>();
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{ ? =call tpp.r_touristic_package_contract_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        int idContract;

        while (resultSet.next()) {
            idContract = resultSet.getInt("id_contract");
            contractDtoLinkedList.add(ServicesLocator.getContractServices().load(idContract));
        }

        connection.close();
        connection.close();
        return contractDtoLinkedList;
    }
}