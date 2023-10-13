package com.example.casestudy.Controller;

import com.example.casestudy.Model.Customer;
import com.example.casestudy.Model.CustomerType;
import com.example.casestudy.Service.customer.CustomerService;
import com.example.casestudy.Service.customer.CustomerServiceImpl;
import com.example.casestudy.Service.customer.CustomerTypeService;
import com.example.casestudy.Service.customer.CustomerTypeServiceImpl;
import com.example.casestudy.dto.CustomerDto;
import com.example.casestudy.util.ValidateUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    private CustomerService customerService = new CustomerServiceImpl();
    private CustomerTypeService customerTypeService = new CustomerTypeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null || action.equals("")) {
            action = "";
        }
        switch (action) {
            case "list":
                showListCustomer(request, response);
                break;
            case "create":
                showCreate(request, response);
                break;
            case "edit":
                showEdit(request, response);
                break;
            case "delete":
                deleteCustomer(request, response);
                break;
//            case "deleteAll":
//                deleteEmployeeAll(request,response);
//                break;
            case "search":
                searchEmployees(request, response);
                break;
            case "detail":
                showDetailCusomter(request, response);
            default:
                showListCustomer(request, response);
                break;
        }
    }

    private void searchEmployees(HttpServletRequest request, HttpServletResponse response) {
        List<Customer> customerLists;
        String searchCustomer = request.getParameter("search");

        if (searchCustomer.equals("")) {
            customerLists = customerService.findAll();
        } else {
            customerLists = customerService.searchEmployee(searchCustomer);

        }
        request.setAttribute("listCustomers", customerLists);
        try {
            request.getRequestDispatcher("/WEB-INF/customer/list.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) {
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
        request.setAttribute("customerTypes", customerTypes);
        request.setAttribute("customer", customer);
        try {
            request.getRequestDispatcher("/WEB-INF/customer/update.jsp").forward(request, response);
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
        request.setAttribute("customerTypes", customerTypes);
        request.setAttribute("customer", customer);
        try {
            request.getRequestDispatcher("/WEB-INF/customer/detail.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreate(HttpServletRequest request, HttpServletResponse response) {
        List<CustomerType> customerTypes = customerTypeService.findAll();
        List<Customer> listCustomers = customerService.findAll();
        request.setAttribute("listCustomers", listCustomers);
        request.setAttribute("customerTypes", customerTypes);
        try {
            request.getRequestDispatcher("/WEB-INF/customer/create.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showListCustomer(HttpServletRequest request, HttpServletResponse response) {
        String index = request.getParameter("pages");
        if (index == null) {
            index = "1";
        }
        int pages = customerService.getNumberPage();
        int inexPages = Integer.parseInt(index);
        List<Customer> listCustomers = customerService.getPaging(inexPages);
        request.setAttribute("listCustomers", listCustomers);
        request.setAttribute("pages", pages);
        request.setAttribute("number", index);
        try {
            request.getRequestDispatcher("/WEB-INF/customer/list.jsp").forward(request, response);
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

        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createCustomer(request, response);
                break;
            case "update":
                updateCustomer(request, response);
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        Date date = new Date();
        try {
            date = sdf.parse(birthday);
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(dateParse);
//            int year = calendar.get(Calendar.YEAR);
//            if( year >= 1900 && year <=2100){
//                date = dateParse;
//
//            }else {
//                request.setAttribute("errorDate","Ngày tháng năm không hợp lệ");
//            }

        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("errorDate", "Ngày tháng sai định dạng");
        }
        String genderString = request.getParameter("gender");
        boolean gender = true;
        if (genderString.equals("female")) {
            gender = false;
        }
        String cardID = request.getParameter("cardID");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int typeCustomer = Integer.parseInt(request.getParameter("typeCustomer"));
        CustomerType customerType = customerTypeService.findByID(typeCustomer);
        Customer customer = new Customer(id, name, date, gender, cardID, phone, email, address, customerType, true);
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
            String genderString = request.getParameter("gender");
            String card = request.getParameter("cardID");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String address = request.getParameter("address");

            String typeCustomer = request.getParameter("typeCustomer");


            //Kiểm tra tính hợp lệ của dữ liệu
            Map<String, String> data = new HashMap<>();
            data.put("name", name);
            data.put("birthday", birthday);
            data.put("card", card);
            data.put("phone", phone);
            data.put("email", email);
            data.put("address", address);
            data.put("typeCustomer", typeCustomer);

            List<String> errorMessages = customerService.validateMap(data);

            //kiểm tra nếu có lỗi
            if (!errorMessages.isEmpty()) {
                List<CustomerType> customerTypes = customerTypeService.findAll();
                CustomerType customerType = this.customerTypeService.findByID(Integer.parseInt(typeCustomer));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false);
                Date date = null;
                try {
                    date = sdf.parse(birthday);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String dateString = sdf.format(date);
                CustomerDto customer1 = new CustomerDto(name, dateString, genderString == "Male", card, phone, email, address, customerType, true);
                request.setAttribute("errorMessages", errorMessages);
                request.setAttribute("customer", customer1);
                request.setAttribute("customerTypes", customerTypes);
                try {
                    request.getRequestDispatcher("/WEB-INF/customer/create.jsp").forward(request, response);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;

            }

            // Xử lý tạo khách hàng
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false);
                Date date = sdf.parse(birthday);

                boolean gender = true;
                if (genderString.equals("female")) {
                    gender = false;
                }
                CustomerType customerType = customerTypeService.findByID(Integer.parseInt(typeCustomer));
                Customer customer = new Customer(-1, name, date, gender, card, phone, email, address, customerType, true);
                customerService.add(customer);
                response.sendRedirect("/customer?action=list");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }


        }
    }

