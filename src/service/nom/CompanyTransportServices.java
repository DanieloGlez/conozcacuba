package service.nom;

import dto.nom.CompanyServiceDto;
import dto.nom.CompanyTransportDto;
import service.Services;
import service.ServicesLocator;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CompanyTransportServices implements Services<CompanyTransportDto> {
    @Override
    public CompanyTransportDto load(int id_company_transport) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.n_company_transport_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id_company_transport);

        callableStatement.execute();

        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();

        callableStatement.close();
        connection.close();
        return new CompanyTransportDto(
                resultSet.getInt("id_company_transport"),
                resultSet.getString("name")
        );
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

        callableStatement.close();
        connection.close();
        return companyTransportDtos;
    }

    @Override
    public void insert(CompanyTransportDto dto) throws SQLException {
        Connection connection = service.ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_company_transport_insert(?)}");
        callableStatement.setString(1, dto.getName());
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }

    @Override
    public void update(CompanyTransportDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_company_transport_update(?,?)}");
        callableStatement.setInt(1, dto.getId());
        callableStatement.setString(2, dto.getName());
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }

    @Override
    public void delete(int id_company_transport) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.n_company_transport_delete(?)}");
        callableStatement.setInt(1, id_company_transport);
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }
}
