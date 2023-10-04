package com.example.casestudy.Repository.customer;

import com.example.casestudy.Database.JDBCUtil;
import com.example.casestudy.Model.Customer;
import com.example.casestudy.Model.CustomerType;
import com.example.casestudy.Model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerTypeRepository implements ICustomerTypeRepository{
    @Override
    public void add(CustomerType obj) {

    }

    @Override
    public CustomerType findByID(int id) {
        CustomerType customerType = null;
        String sql = "Select * from customer_type where customer_type_id = ?";
        try(
                Connection connection = JDBCUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);){
                preparedStatement.setInt(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    String customerTypeName = resultSet.getString("customer_type_name");
                    customerType = new CustomerType(id,customerTypeName);
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerType;
    }

    @Override
    public List<CustomerType> findAll() {
        List<CustomerType> customerTypes = new ArrayList<>();
        String sql = "select * from customer_type";
        try(
                Connection connection = JDBCUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int customerTypeId = rs.getInt("customer_type_id");
                String customerTypeName = rs.getString("customer_type_name");
                CustomerType customerType = new CustomerType(customerTypeId,customerTypeName);
                customerTypes.add(customerType);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerTypes;
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
