package com.example.productmanagement.Service;

import com.example.productmanagement.Model.Product;

import java.util.List;

public interface ProductService {
    void add(Product product);
    void update(Product product);
    void delete(Product product);
    Product findById(int id);
    List<Product> findAll();
}
