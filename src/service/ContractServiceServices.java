package service;

import dto.ContractServiceDto;
import dto.functionality.UserDto;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ContractServiceServices implements Services<ContractServiceDto>{

    @Override
    public ContractServiceDto load(int id) throws SQLException {
        return null;
    }

    @Override
    public List<ContractServiceDto> loadAll() throws SQLException {
        return null;
    }

    @Override
    public void insert(ContractServiceDto dto) throws SQLException {

    }

    @Override
    public void update(ContractServiceDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return null;
    }
}

