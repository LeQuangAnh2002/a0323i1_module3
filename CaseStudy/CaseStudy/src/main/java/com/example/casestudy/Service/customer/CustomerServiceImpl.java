package com.example.casestudy.Service.customer;

import com.example.casestudy.Model.Customer;
import com.example.casestudy.Repository.customer.CustomerRepository;
import com.example.casestudy.Repository.customer.ICustomerRepository;

import java.util.List;

public class CustomerServiceImpl implements CustomerService{
    private ICustomerRepository customerRepository = new CustomerRepository();
    @Override
    public void add(Customer customer) {
        customerRepository.add(customer);
    }

    @Override
    public Customer findByID(int id) {
        return customerRepository.findByID(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void update(Customer customer) {
        customerRepository.update(customer);
    }

    @Override
    public void delete(int id) {
            customerRepository.delete(id);
    }

    @Override
    public int getNumberPage() {
        return customerRepository.getNumberPage();
    }

    @Override
    public List<Customer> getPaging(int index) {
        return customerRepository.getPaging(index);
    }

    @Override
    public List<Customer> searchEmployee(String string) {
        return customerRepository.searchEmployee(string);
    }
}
