package Service;

import entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private static List<Customer> customerList = new ArrayList<Customer>();

    static {

        Customer customer1 = new Customer("Anh","12/12/2002","Hà Nội","images/channels4_profile.jpg");

        Customer customer2 = new Customer("Huy","12/05/2002","Quảng Trị","images/images1.jpg");
        Customer customer3 = new Customer("Hoang","28/07/2002","HCM","images/img_5terre.jpg");
        Customer customer4 = new Customer("Ngoc","15/06/2002","Đồng Nai","images/Nguyen_Huy_Binh1.jpg");
        customerList.add(customer1);
        customerList.add(customer2);
        customerList.add(customer3);
        customerList.add(customer4);


    }
    public  List<Customer> getList(){
        return customerList;
    }
}
