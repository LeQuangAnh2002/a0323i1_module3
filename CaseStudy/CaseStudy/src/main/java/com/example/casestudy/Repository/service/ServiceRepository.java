package com.example.casestudy.Repository.service;

import com.example.casestudy.Model.Service;
import com.example.casestudy.Repository.customer.CustomerRepository;
import com.example.casestudy.Repository.customer.ICustomerRepository;

import java.util.List;

public class ServiceRepository implements IServiceRepository{


    @Override
    public void add(Service obj) {

    }

    @Override
    public Service findByID(int V) {
        return null;
    }

    @Override
    public List<Service> findAll() {
        return null;
    }

    @Override
    public void update(Service obj) {

    }

    @Override
    public void delete(int V) {

    }

    @Override
    public int getNumberPage() {
        return 0;
    }

    @Override
    public List<Service> getPaging(int V) {
        return null;
    }
}
