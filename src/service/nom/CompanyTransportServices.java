package service.nom;

import dto.nom.CompanyTransportDto;
import service.Services;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CompanyTransportServices implements Services<CompanyTransportDto> {
    @Override
    public CompanyTransportDto load(int id) throws SQLException {
        return null;
    }

    @Override
    public List<CompanyTransportDto> loadAll() throws SQLException {
        List<CompanyTransportDto> companyTransportDtos = new LinkedList<>();

        Connection connection = service.ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.n_company_transport_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
            companyTransportDtos.add(new CompanyTransportDto(
                    resultSet.getInt("id_company_transport"),
                    resultSet.getString("name")
            ));
        }

        return companyTransportDtos;
    }

    @Override
    public void insert(CompanyTransportDto dto) throws SQLException {
        Connection connection = service.ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_company_transport_insert(?)}");
        callableStatement.setString("name", dto.getName());
        callableStatement.execute();
    }

    @Override
    public void update(CompanyTransportDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
