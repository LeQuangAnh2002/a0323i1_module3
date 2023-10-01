package com.example.danhsachkhachhang.Service;

import com.example.danhsachkhachhang.Model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer findById(int id);
    void save(Customer customer);
    void update(int id,Customer customer);
    void delete(int id);
}
