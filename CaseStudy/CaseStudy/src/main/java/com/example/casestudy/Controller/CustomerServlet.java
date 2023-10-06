package com.example.casestudy.Controller;

import com.example.casestudy.Model.Customer;
import com.example.casestudy.Model.CustomerType;
import com.example.casestudy.Service.customer.CustomerService;
import com.example.casestudy.Service.customer.CustomerServiceImpl;
import com.example.casestudy.Service.customer.CustomerTypeService;
import com.example.casestudy.Service.customer.CustomerTypeServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    private CustomerService customerService = new CustomerServiceImpl();
    private CustomerTypeService customerTypeService = new CustomerTypeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if(action == null || action.equals("")){
            action ="";
        }
        switch (action){
            case "list":
                showListCustomer(request,response);
                break;
            case "create":
                showCreate(request,response);
                break;
            case  "edit":
                showEdit(request,response);
                break;
            case "delete":
                deleteEmployee(request,response);
                break;
//            case "deleteAll":
//                deleteEmployeeAll(request,response);
//                break;
            case "search":
                searchEmployees(request,response);
                break;
            case "detail":
                showDetailCusomter(request,response);
            default:
                showListCustomer(request,response);
                break;
        }
    }

    private void searchEmployees(HttpServletRequest request, HttpServletResponse response) {
        List<Customer> customerLists ;
        String searchCustomer = request.getParameter("search");

        if(searchCustomer.equals("")){
            customerLists = customerService.findAll();
        }else {
            customerLists = customerService.searchEmployee(searchCustomer);

        }
        request.setAttribute("listCustomers",customerLists);
        try {
            request.getRequestDispatcher("/WEB-INF/customer/list.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        customerService.delete(id);
        try {
            response.sendRedirect("/customer?action=list");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showEdit(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = customerService.findByID(id);
        List<CustomerType> customerTypes = customerTypeService.findAll();
        request.setAttribute("customerTypes",customerTypes);
        request.setAttribute("customer",customer);
        try {
            request.getRequestDispatcher("/WEB-INF/customer/update.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showDetailCusomter(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = customerService.findByID(id);
        List<CustomerType> customerTypes = customerTypeService.findAll();
        request.setAttribute("customerTypes",customerTypes);
        request.setAttribute("customer",customer);
        try {
            request.getRequestDispatcher("/WEB-INF/customer/detail.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreate(HttpServletRequest request, HttpServletResponse response) {
            List<CustomerType> customerTypes = customerTypeService.findAll();
            List<Customer> listCustomers = customerService.findAll();
            request.setAttribute("listCustomers",listCustomers);
            request.setAttribute("customerTypes",customerTypes);
        try {
            request.getRequestDispatcher("/WEB-INF/customer/create.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showListCustomer(HttpServletRequest request, HttpServletResponse response) {
        List<Customer> listCustomers = customerService.findAll();
        request.setAttribute("listCustomers",listCustomers);
        try {
            request.getRequestDispatcher("/WEB-INF/customer/list.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if(action == null){
            action="";
        }
        switch (action){
            case "create":
                createCustomer(request,response);
                break;
            case "update":
                updateCustomer(request,response);
                break;
//            default:
//                showList(request,response);
//                break;

        }
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        Date date = new Date();
        try {
            date = sdf.parse(birthday);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        String genderString = request.getParameter("gender");
        boolean gender = true;
        if(genderString.equals("female")){
            gender = false;
        }
        String cardID = request.getParameter("cardID");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int typeCustomer = Integer.parseInt(request.getParameter("typeCustomer"));
        CustomerType customerType = customerTypeService.findByID(typeCustomer);
        Customer customer = new Customer(id,name,date,gender,cardID,phone,email,address,customerType,true);
        customerService.update(customer);
        try {
            response.sendRedirect("/customer?action=list");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createCustomer(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = sdf.parse(birthday);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        String genderString = request.getParameter("gender") ;
        boolean gender = true;
        if(genderString.equals("")){
            gender = true;
        }else if(genderString.equals("female")){
            gender = false;
        }
        String cardID = request.getParameter("cardID");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int typeCustomer = Integer.parseInt(request.getParameter("typeCustomer"));
        CustomerType customerType = customerTypeService.findByID(typeCustomer);
        Customer customer =new Customer(-1,name,date,gender,cardID,phone,email,address,customerType,true);
        customerService.add(customer);
        try {
            response.sendRedirect("/customer?action=list");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
