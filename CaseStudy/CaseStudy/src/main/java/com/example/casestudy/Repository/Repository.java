package com.example.casestudy.Repository;

import java.util.List;

public interface Repository<T> {
    void add(T obj);
    T findByID(int V);
    List<T> findAll();
    void update(T obj);
    void delete(int V);
    int getNumberPage();
    List<T> getPaging(int V);
}
