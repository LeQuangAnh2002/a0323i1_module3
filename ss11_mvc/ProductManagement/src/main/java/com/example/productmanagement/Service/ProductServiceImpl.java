package com.example.productmanagement.Service;

import com.example.productmanagement.Model.Product;
import com.example.productmanagement.Repository.ProductRepository;
import com.example.productmanagement.Repository.ProductRepositoryImpl;

import java.util.List;

public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository = new ProductRepositoryImpl();
    @Override
    public void add(Product product) {
        productRepository.add(product);
    }

    @Override
    public void update(Product product) {
        productRepository.update(product);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
