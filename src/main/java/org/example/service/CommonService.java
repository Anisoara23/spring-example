package org.example.service;

public interface CommonService<T, ID> {

    void add(T entity);

    void remove(ID id);
}
