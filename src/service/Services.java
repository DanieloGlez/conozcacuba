package service;

import dto.Dto;

import java.sql.SQLException;
import java.util.List;

public interface Services<T> {
    // CRUD operations
    T load(int id) throws SQLException;
    List<T> loadAll() throws SQLException;
    void insert(T dto) throws SQLException;
    void update(T dto) throws SQLException;
    void delete(int id) throws SQLException;

    // Dynamic JFXTreeTableView related
    String getGenericType();
}
