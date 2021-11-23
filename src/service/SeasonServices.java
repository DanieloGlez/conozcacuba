package service;

import dto.ContractDto;
import dto.SeasonDto;
import dto.nom.RoomTypeDto;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class SeasonServices implements Services<SeasonDto>, Relation<SeasonDto>{
    @Override
    public SeasonDto load(int id) throws SQLException {
        return null;
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

        return seasonDtos;
    }

    @Override
    public void insert(SeasonDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{call tpp.season_insert(?,?,?,?,?)}");
        callableStatement.setString("name", dto.getName());
        callableStatement.setDate("start_date", (Date) dto.getStartDate());
        callableStatement.setDate("finish_date", (Date) dto.getFinishDate());
        callableStatement.setString("description", dto.getDescription());
        callableStatement.execute();
    }

    @Override
    public void update(SeasonDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return null;
    }

    @Override
    public List<SeasonDto> loadRelated(int id) throws SQLException {
        LinkedList<SeasonDto> seasonDtoLinkedList=new LinkedList<>();
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{ ? =call tpp.r_season_contract_hotel_load_by_id(?)}");
        callableStatement.registerOutParameter(1,Types.REF_CURSOR);
        callableStatement.setInt(2,id);
        callableStatement.execute();
        ResultSet resultSet= (ResultSet) callableStatement.getObject(1);
        resultSet.next();
        int idSeason = resultSet.getInt("id_season");
        SeasonDto seasonDto  = load(idSeason);

        while (resultSet.next()){
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

        return seasonDtoLinkedList;
    }
}
