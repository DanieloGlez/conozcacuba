package service;

import java.sql.SQLException;
import java.util.List;

public interface Relation<T> {
    List<T> loadRelated(int id) throws SQLException;
}
