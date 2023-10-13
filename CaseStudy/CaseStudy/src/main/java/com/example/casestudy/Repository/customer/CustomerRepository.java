package com.example.casestudy.Repository.customer;

import com.example.casestudy.Database.JDBCUtil;
import com.example.casestudy.Model.Customer;
import com.example.casestudy.Model.CustomerType;
import jdk.nashorn.internal.scripts.JD;

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
    public void add(Customer customer) {
        String sql = "Insert into customer(customer_name,customer_birthday,customer_gender,customer_id_card,customer_phone,customer_email,customer_address,customer_type_id,status) values(?,?,?,?,?,?,?,?,?)";
        try(
                Connection connection = JDBCUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ) {
                statement.setString(1,customer.getCustomerName());
//                được sử dụng để chuyển đổi một đối tượng java.util.Date thành một đối tượng java.sql.Date.
            java.sql.Date dateSQL= new java.sql.Date(customer.getCustomerBirthday().getTime());
                statement.setDate(2,dateSQL);
                statement.setBoolean(3,customer.getCustomerGender());
                statement.setString(4,customer.getCustomerIDCard());
                statement.setString(5,customer.getCustomerPhone());
                statement.setString(6,customer.getCustomerEmail());
                statement.setString(7,customer.getCustomerAddress());
                statement.setInt(8,customer.getCustomerType().getCustomerTypeID());
                statement.setBoolean(9,true);
                statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer findByID(int id) {
            Customer customer = null;
        String sql = " select * from customer where customer_id= ?";
        try(
                Connection connection = JDBCUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);

                ){
                    statement.setInt(1,id);
                    ResultSet rs = statement.executeQuery();
                    while (rs.next()){

                        String name = rs.getString("customer_name");
                        Date birthday = rs.getDate("customer_birthday");
                        Boolean gender = rs.getBoolean("customer_gender");
                        String idCart = rs.getString("customer_id_card");
                        String phone = rs.getString("customer_phone");
                        String email = rs.getString("customer_email");
                        String address = rs.getString("customer_address");
                        int type_id = rs.getInt("customer_type_id");
                        CustomerType customerType = customerTypeRepository.findByID(type_id);
                         customer = new Customer(id,name,birthday,gender,idCart,phone,email,address,customerType,true);

                    }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return customer;
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
        String sql = "select * from customer where status = 1 ;";
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

                Customer customer = new Customer(id,name,birthday,gender,idCart,phone,email,address,customerType,true);
                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }


    @Override
    public void update(Customer customer) {
        String sql = "Update customer set  customer_name=? , customer_birthday= ?, customer_gender= ?, customer_id_card= ?, " +
                " customer_phone= ? , customer_email=?, customer_address= ?, customer_type_id = ? where customer_id= ?";
        try(
                Connection connection = JDBCUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);

                ){
                statement.setString(1,customer.getCustomerName());
            java.sql.Date dateSQL = new java.sql.Date(customer.getCustomerBirthday().getTime());
                statement.setDate(2,dateSQL);
                statement.setBoolean(3,customer.getCustomerGender());
                statement.setString(4,customer.getCustomerIDCard());
                statement.setString(5,customer.getCustomerPhone());
                statement.setString(6,customer.getCustomerEmail());
                statement.setString(7,customer.getCustomerAddress());
                statement.setInt(8,customer.getCustomerType().getCustomerTypeID());
                statement.setInt(9,customer.getCustomerID());
                statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        try{
             connection = JDBCUtil.getConnection();
                connection.setAutoCommit(false);
                PreparedStatement statement1 = connection.prepareStatement("update  contract_detail set status = b'0' where contract_id in (select contract_id from contract where customer_id = ?) ;");
                statement1.setInt(1,id);
                statement1.executeUpdate();
                PreparedStatement statement2 = connection.prepareStatement("update contract set status = b'0' where customer_id in (select customer_id from customer where customer_id = ?);");
                statement2.setInt(1,id);
                statement2.executeUpdate();
                PreparedStatement statement3 = connection.prepareStatement("update  customer set status = b'0' where customer_id = ?;");
                statement3.setInt(1,id);
                statement3.executeUpdate();
                connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if(connection != null){
                try{
                    connection.rollback();
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
        }finally {
            if (connection != null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public int getNumberPage() {
        String sql = "SELECT count(*) FROM customer where status = 1;";
        try(
                Connection connection = JDBCUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ) {
                ResultSet rs = statement.executeQuery();
                while (rs.next()){
                    int total = rs.getInt(1);
                    int countPage = 0;
                    countPage = total/5;
                    if(total % 5 == 0){
                        return countPage;

                    }else {
                        countPage++;
                    }
                    return countPage;
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Customer> getPaging(int index) {
        List<Customer> customerList = new ArrayList<>();
        String sql = "select * from customer where status = 1 limit ?,5;";
        try(
                Connection connection = JDBCUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ){
                    statement.setInt(1,(index-1)*5);
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

                        Customer customer = new Customer(id,name,birthday,gender,idCart,phone,email,address,customerType,true);
                        customerList.add(customer);

                    }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    @Override
    public List<Customer> searchEmployee(String string) {
        List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT * FROM customer where customer_name like ?  and status = 1;";
        try(
                Connection connection = JDBCUtil.getConnection();
                PreparedStatement statement =connection.prepareStatement(sql);
                ) {
                statement.setString(1,"%"+string+"%");
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

                    Customer customer = new Customer(id,name,birthday,gender,idCart,phone,email,address,customerType,true);
                    customerList.add(customer);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerList;
    }
}
