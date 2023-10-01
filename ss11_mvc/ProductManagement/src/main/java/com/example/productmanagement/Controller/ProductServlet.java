package com.example.productmanagement.Controller;

import com.example.productmanagement.Model.Product;
import com.example.productmanagement.Service.ProductService;
import com.example.productmanagement.Service.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private ProductService productService = new ProductServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null || action.equals("")){
            action = "";
        }
        switch (action){
            case "create":
                showCreate(request,response);
                break;
            case "update":
                showUpdate(request,response);
                break;
            case "delete":
                showDelete(request,response);
                break;
            case "view" :
                viewProduct(request,response);
                break;
            default:
                listProducts(request,response);
                break;
        }
    }

    private void viewProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        request.setAttribute("product",product);
        try {
            request.getRequestDispatcher("/product/view.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showDelete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);

        request.setAttribute("product",product);

        try {
            request.getRequestDispatcher("/product/delete.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showUpdate(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        request.setAttribute("product",product);
        try {
            request.getRequestDispatcher("/product/update.jsp").forward(request,response);
        }catch (ServletException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void showCreate(HttpServletRequest request, HttpServletResponse response) {

        try {
            request.getRequestDispatcher("/product/create.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = productService.findAll();
        request.setAttribute("products",products);
        try {
            request.getRequestDispatcher("/product/list.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null || action.equals("")){
            action = "";
        }
        switch (action){
            case "create":
                createProduct(request,response);
                break;
            case "update":
                updateProduct(request,response);
                break;
            case "delete":
                deleteProduct(request,response);
                break;
            case "search":
                searchName(request,response);
                break;
        }
    }

    private void searchName(HttpServletRequest request, HttpServletResponse response) {
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        productService.delete(product);
        try {
            response.sendRedirect("/products?action=list");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String desc = request.getParameter("desc");


        Product productOnly = productService.findById(id);
        productOnly.setProductName(name);
        productOnly.setPrice(price);
        productOnly.setDescription(desc);
        productService.update(productOnly);
        try {
            response.sendRedirect("/products?action=list");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("desc");
        int id =(int) (Math.random()*10000);
        Product product = new Product(id,name,price,description);
        productService.add(product);
        try {
            response.sendRedirect("/products?action=list");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
