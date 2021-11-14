package service;

import java.sql.SQLException;
import java.util.List;

public interface Service<T> {
    Class<T> getGenericType();
    List<T> getAll() throws SQLException;

}
