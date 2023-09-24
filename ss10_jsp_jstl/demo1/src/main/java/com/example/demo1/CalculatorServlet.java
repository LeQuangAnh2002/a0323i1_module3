package com.example.demo1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CalculatorServlet", urlPatterns = "/calculator-servlet")
public class CalculatorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            float rate = Float.parseFloat(request.getParameter("rate"));
            float usd = Float.parseFloat(request.getParameter("usd"));

            float vnd =  rate * usd;
            request.setAttribute("vnd",vnd);
            request.getRequestDispatcher("index.jsp").forward(request,response);
    }
}
