package com.example.casestudy.Controller;

import com.example.casestudy.Model.Customer;
import com.example.casestudy.Service.customer.CustomerService;
import com.example.casestudy.Service.customer.CustomerServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerServlet", value = "/customer")
public class CustomerServlet extends HttpServlet {
    private CustomerService customerService = new CustomerServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null || action.equals("")){
            action ="";
        }
        switch (action){
            case "list":
                showListCustomer(request,response);
                break;
//            case "create":
//                showCreate(request,response);
//                break;
//            case  "edit":
//                showEdit(request,response);
//                break;
//            case "delete":
//                deleteEmployee(request,response);
//                break;
//            case "deleteAll":
//                deleteEmployeeAll(request,response);
//                break;
//            case "search":
//                searchEmployees(request,response);
//                break;
            default:
                showListCustomer(request,response);
                break;
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

    }
}
