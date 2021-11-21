package service.nomenclators;

import dto.nomenclators.CompanyServiceDto;
import service.Services;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CompanyServiceServices implements Services<CompanyServiceDto> {
    @Override
    public CompanyServiceDto load(int id) throws SQLException {
        return null;
    }

    @Override
    public List<CompanyServiceDto> loadAll() throws SQLException {
        List<CompanyServiceDto> companiesService = new LinkedList<>();

        Connection connection = service.ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.n_company_service_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
            companiesService.add(new CompanyServiceDto(
                    resultSet.getInt("id_company_service"),
                    resultSet.getString("name")
            ));
        }

        return companiesService;
    }

    @Override
    public void insert(CompanyServiceDto dto) throws SQLException {

    }

    @Override
    public void update(CompanyServiceDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
