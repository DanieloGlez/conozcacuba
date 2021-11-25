package service;

import dto.ContractDto;
import dto.SeasonDto;
import dto.nom.CompanyServiceDto;
import dto.nom.RoomTypeDto;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class SeasonServices implements Services<SeasonDto>, Relation<SeasonDto>{
    @Override
    public SeasonDto load(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{ ? = call tpp.season_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();
        callableStatement.close();
        connection.close();
        return new SeasonDto(
                id,
                resultSet.getString("name"),
                resultSet.getDate("start_date"),
                resultSet.getDate("finish_date"),
                resultSet.getString("description")
        );
    }

    @Override
    public List<SeasonDto> loadAll() throws SQLException {
        List<SeasonDto> seasonDtos = new LinkedList<>();

        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.season_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
            seasonDtos.add(new SeasonDto(
                    resultSet.getInt("id_season"),
                    resultSet.getString("name"),
                    resultSet.getDate("start_date"),
                    resultSet.getDate("finish_date"),
                    resultSet.getString("description")
            ) {
            });
        }

        callableStatement.close();
        connection.close();
        return seasonDtos;
    }

    @Override
    public void insert(SeasonDto dto) throws SQLException {

    }

    @Override
    public void update(SeasonDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.season_delete(?)}");
        callableStatement.setInt(1, id);
        callableStatement.execute();
        callableStatement.close();
        connection.close();
    }

    public List<SeasonDto> loadRelated(int id) throws SQLException {
        LinkedList<SeasonDto> seasonDtoLinkedList=new LinkedList<>();
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{ ? =call tpp.r_season_contract_hotel_load_by_id(?)}");
        callableStatement.registerOutParameter(1,Types.REF_CURSOR);
        callableStatement.setInt(2,id);
        callableStatement.execute();
        ResultSet resultSet= (ResultSet) callableStatement.getObject(1);
        int idSeason;
        SeasonDto seasonDto;

        while (resultSet.next()){
            idSeason = resultSet.getInt("id_season");
            seasonDto = load(idSeason);
            seasonDtoLinkedList.add(
                    new SeasonDto(
                            idSeason,
                            seasonDto.getName(),
                            seasonDto.getStartDate(),
                            seasonDto.getFinishDate(),
                            seasonDto.getDescription()
                    )
            );
        }

        callableStatement.close();
        connection.close();
        return seasonDtoLinkedList;
    }
}
