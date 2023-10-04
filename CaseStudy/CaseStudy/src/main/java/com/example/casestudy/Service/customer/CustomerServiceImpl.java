package com.example.casestudy.Service.customer;

import com.example.casestudy.Model.Customer;
import com.example.casestudy.Repository.customer.CustomerRepository;
import com.example.casestudy.Repository.customer.ICustomerRepository;

import java.util.List;

public class CustomerServiceImpl implements CustomerService{
    private ICustomerRepository customerRepository = new CustomerRepository();
    @Override
    public void add(Customer obj) {

    }

    @Override
    public Customer findByID(int V) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void update(Customer obj) {

    }

    @Override
    public void delete(int V) {

    }

    @Override
    public int getNumberPage() {
        return 0;
    }

    @Override
    public List<Customer> getPaging(int V) {
        return null;
    }
}
