package com.example.productmanagement.Repository;

import com.example.productmanagement.Model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    void add(Product product);
    Product findById(int id);
    void update( Product product);
    void delete(Product product);
}
