package com.example.casestudy.Repository.customer;

import com.example.casestudy.Model.Customer;
import com.example.casestudy.Repository.Repository;

import java.util.List;

public interface ICustomerRepository extends Repository<Customer> {
    List<Customer> searchEmployee(String string);
}
