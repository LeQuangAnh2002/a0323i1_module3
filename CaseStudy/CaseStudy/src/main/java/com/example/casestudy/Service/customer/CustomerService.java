package com.example.casestudy.Service.customer;

import com.example.casestudy.Model.Customer;
import com.example.casestudy.Service.Service;

import java.util.List;

public interface CustomerService extends Service<Customer> {
    List<Customer> searchEmployee(String string);
}
