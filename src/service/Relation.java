package service;

import java.util.List;

public interface Relation<T> {
    List<T> loadRelated(int id);
}
