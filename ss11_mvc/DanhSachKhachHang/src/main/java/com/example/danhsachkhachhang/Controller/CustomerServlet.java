package com.example.danhsachkhachhang.Controller;

import com.example.danhsachkhachhang.Model.Customer;
import com.example.danhsachkhachhang.Service.CustomerService;
import com.example.danhsachkhachhang.Service.CustomerServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerServlet", value = "/customers")
public class CustomerServlet extends HttpServlet {
    private CustomerService customerServlet = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null || action.equals("")){
            action = "";
        }
        switch (action) {
            case "create":
                break;
            case "edit":
                break;
            case "delete":
                break;
            default:
                listCusomter(request,response);
                break;
        }
    }

    private void listCusomter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customers = customerServlet.findAll();
        request.setAttribute("customers",customers);
        request.getRequestDispatcher("customer/list.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
