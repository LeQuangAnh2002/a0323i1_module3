package com.example.casestudy.Service.customer;

import com.example.casestudy.Model.Customer;
import com.example.casestudy.Model.CustomerType;
import com.example.casestudy.Repository.customer.CustomerRepository;
import com.example.casestudy.Repository.customer.ICustomerRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

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

    public List<String> validateMap(Map<String,String> data){
        List<String> errorMessages = new ArrayList<>();

            for(Map.Entry<String,String> entry: data.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (key.equals("name")){
                    if (value.isEmpty()){
                        errorMessages.add("Tên không được để trống");
                    }else if(!isValidateName(value)){
                        errorMessages.add("Tên không hợp lệ");
                    }
                } else if(key.equals("birthday")){
                    if (!isValidateDate(value)){
                        errorMessages.add("Ngày tháng năm không đúng định dạng");
                    }
                } else if(key.equals("card")){
                    if(value.isEmpty()){
                        errorMessages.add("CCCD không được để trống");
                    }else if(!isValidateCardID(value)){
                        errorMessages.add("CCCD không hợp lệ");
                    }

                }else if(key.equals("phone")){
                    if(value.isEmpty()){
                        errorMessages.add("SDT không được để trống");
                    }else if(!isValidatePhone(value)){
                        errorMessages.add("SDT không hợp lệ");
                    }
                }else if(key.equals("email")){
                    if(value.isEmpty()){
                        errorMessages.add("Email không được để trống");
                    }else if(!isValidateEmail(value)){
                        errorMessages.add("Email không hợp lệ");
                    }
                }else if(key.equals("address")){
                   if(value.isEmpty()){
                       errorMessages.add("Địa chỉ không được để trống");
                   }
                }else if(key.equals("typeCustomer")){
                        try {
                            if (value == null || value.isEmpty()) {
                                errorMessages.add("Không được để trống");
                            } else if (!isValidateTypeCustomer(Integer.parseInt(value))) {
                                errorMessages.add("Loại khách hàng không tồn tại trong csdl");
                            }
                        }catch (NumberFormatException e){
                            e.printStackTrace();
                            errorMessages.add("Lỗi định dạng trong SQL");
                        }
                }
            }
            return errorMessages;
    }
    public static  boolean isValidateName(String name){
         String USERNAME_REGEX ="^[\\p{L}\\s]{7,20}$";
        return Pattern.matches(USERNAME_REGEX,name);
    }
    public static boolean isValidateDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            Date dateParse = sdf.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateParse);
            int year = calendar.get(Calendar.YEAR);
            if( year >= 1900 && year <=2100){
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
            return false;
    }
    public static  boolean isValidateCardID(String card){
         String ID_CARD_REGEX= "^[0-9]{9,12}$";
        return Pattern.matches(ID_CARD_REGEX,card);
    }
    public static  boolean isValidatePhone(String phone){
        String CELL_PHONE_REGEX= "^(0[3|5|7|8|9])+([0-9]{8})$";
        return Pattern.matches(CELL_PHONE_REGEX,phone);
    }
    public static  boolean isValidateEmail(String email){
        String EMAIL_REGEX= "^\\w+@\\w+[.]\\w+$";
        return Pattern.matches(EMAIL_REGEX,email);
    }
    public static boolean isValidateTypeCustomer(int typeID){

                if( typeID >=1 && typeID <=5){
                    return true;
                }
                return false;
            }

}