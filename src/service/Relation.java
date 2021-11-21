package service;

import java.util.List;

public interface Relation<T> {
    List<T> load(int id);
}
