package com.example.casestudy.Service.customer;

import com.example.casestudy.Model.CustomerType;
import com.example.casestudy.Repository.customer.CustomerTypeRepository;
import com.example.casestudy.Repository.customer.ICustomerTypeRepository;

import java.util.List;

public class CustomerTypeServiceImpl implements CustomerTypeService{
    private ICustomerTypeRepository customerTypeRepository = new CustomerTypeRepository();
    @Override
    public void add(CustomerType obj) {

    }

    @Override
    public CustomerType findByID(int id) {
        return customerTypeRepository.findByID(id);
    }

    @Override
    public List<CustomerType> findAll() {
        return customerTypeRepository.findAll();
    }

    @Override
    public void update(CustomerType obj) {

    }

    @Override
    public void delete(int V) {

    }

    @Override
    public int getNumberPage() {
        return 0;
    }

    @Override
    public List<CustomerType> getPaging(int V) {
        return null;
    }
}
