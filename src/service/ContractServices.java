package service;

import dto.ContractDto;
import dto.nom.CompanyServiceDto;
import dto.nom.ContractTypeDto;
import dto.nom.RoomTypeDto;
import service.nom.ContractTypeServices;

import java.sql.*;
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
        ContractDto contractDto = null;
        resultSet.next();

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

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.contract_load}");
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

        return contractDtos;
    }

    @Override
    public void insert(ContractDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.contract_insert(?,?,?,?,?)}");
        callableStatement.setInt("id_contract_type", dto.getContractTypeDto().getId());
        callableStatement.setDate("start_date", dto.getStartDate());
        callableStatement.setDate("finish_date", dto.getFinishDate());
        callableStatement.setDate("conciliation0_date", dto.getConciliationDate());
        callableStatement.setString("description", dto.getDescription());
        callableStatement.execute();
    }

    @Override
    public void update(ContractDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.contract_update(?,?,?,?,?)}");
        callableStatement.setInt("id_contract_type", dto.getContractTypeDto().getId());
        callableStatement.setDate("start_date", dto.getStartDate());
        callableStatement.setDate("finish_date", dto.getFinishDate());
        callableStatement.setDate("conciliation0_date", dto.getConciliationDate());
        callableStatement.setString("description", dto.getDescription());
        callableStatement.execute();



    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.contract_delete(?)}");
        callableStatement.setInt("id_contract_type", id);
        callableStatement.execute();
    }

    @Override
    public String getGenericType() {
        return null;
    }

    @Override
    public List<ContractDto> loadRelated(int id) throws SQLException {
        LinkedList<ContractDto> contractDtoLinkedList=new LinkedList<>();
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{ ? =call tpp.r_touristic_package_contract_by_id(?)}");
        callableStatement.registerOutParameter(1,Types.REF_CURSOR);
        callableStatement.setInt(2,id);
        ResultSet resultSet= (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()){
            ContractTypeDto contractTypeDto=ServicesLocator.getContractTypeServices().load(resultSet.getInt("id_contract_type"));

            contractDtoLinkedList.add(
                    new ContractDto(
                            resultSet.getInt("id"),
                            contractTypeDto,
                            resultSet.getDate("start_date"),
                            resultSet.getDate("finish_date"),
                            resultSet.getDate("conciliation_date"),
                            resultSet.getString("description")
            ));
        }
        return contractDtoLinkedList;
    }
}
