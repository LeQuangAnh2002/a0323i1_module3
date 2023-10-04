package com.example.casestudy.Repository.customer;

import com.example.casestudy.Database.JDBCUtil;
import com.example.casestudy.Model.Customer;
import com.example.casestudy.Model.CustomerType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerRepository implements ICustomerRepository{
    ICustomerTypeRepository customerTypeRepository = new CustomerTypeRepository();
    @Override
    public void add(Customer obj) {

    }

    @Override
    public Customer findByID(int V) {
        return null;
    }
//(`customer_id`,
//            `customer_name`,
//            `customer_birthday`,
//            `customer_gender`,
//            `customer_id_card`,
//            `customer_phone`,
//            `customer_email`,
//            `customer_address`,
//            `customer_type_id`)
    @Override
    public List<Customer> findAll() {
        List<Customer> customerList = new ArrayList<>();
        String sql = "select * from customer ;";
        try(
                Connection connection = JDBCUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ){
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
               int id = rs.getInt("customer_id");
               String name = rs.getString("customer_name");
                Date birthday = rs.getDate("customer_birthday");
                Boolean gender = rs.getBoolean("customer_gender");
                String idCart = rs.getString("customer_id_card");
                String phone = rs.getString("customer_phone");
                String email = rs.getString("customer_email");
                String address = rs.getString("customer_address");
                int type_id = rs.getInt("customer_type_id");
                CustomerType customerType = customerTypeRepository.findByID(type_id);
                Customer customer = new Customer(id,name,birthday,gender,idCart,phone,email,address,customerType);
                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
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
