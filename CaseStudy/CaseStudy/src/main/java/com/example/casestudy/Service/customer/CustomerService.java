package com.example.casestudy.Service.customer;

import com.example.casestudy.Model.Customer;
import com.example.casestudy.Service.Service;

import java.util.List;
import java.util.Map;

public interface CustomerService extends Service<Customer> {
    List<Customer> searchEmployee(String string);
    List<String>  validateMap(Map<String,String> data);
}
