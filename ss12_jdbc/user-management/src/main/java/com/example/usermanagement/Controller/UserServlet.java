package com.example.usermanagement.Controller;

import com.example.usermanagement.DAO.IUserDAO;
import com.example.usermanagement.DAO.UserDAO;
import com.example.usermanagement.Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserServlet", value = "/users")
public class UserServlet extends HttpServlet {
    private IUserDAO userDAO = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case  "list":
                listUser(request,response);
                break;
            case "create":
                showCreate(request,response);
                break;
            case "edit":
                showUpdate(request,response);
                break;
            case "delete":
                showDelete(request,response);
                break;
            case "search":
                showSearch(request,response);
                break;
            case "sapXep":
                showSapXep(request,response);
            default:
                listUser(request,response);
                break;
        }
    }

    private void showSapXep(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userDAO.sapXepTheoName();
        request.setAttribute("users",users);
        request.getRequestDispatcher("/user/list.jsp").forward(request,response);
    }

    private void showSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String countrySearch = request.getParameter("countryName");

        List<User> country = userDAO.findByCountry(countrySearch);

        request.setAttribute("users",country);
        request.getRequestDispatcher("/user/list.jsp").forward(request,response);

    }

    private void showDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userDAO.selectUser(id);
        request.setAttribute("user",user);
        request.getRequestDispatcher("/user/delete.jsp").forward(request,response);

    }

    private void showUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userDAO.selectUser(id);
        request.setAttribute("user",user);
        request.getRequestDispatcher("/user/update.jsp").forward(request,response);
    }

    private void showCreate(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/user/create.jsp").forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) {
        List<User> users = userDAO.findAll();
        request.setAttribute("users",users);
        try {
            request.getRequestDispatcher("/user/list.jsp").forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action ="";
        }
        try{
            switch (action){
                case "create":
                    createUser(request,response);
                    break;
                case "update":
                    updateUsers(request,response);
                    break;
                case "delete":
                    deleteUsers(request,response);
                    break;
                case "search":
                    break;
                default:
                    listUser(request,response);
                    break;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private void deleteUsers(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id =Integer.parseInt( request.getParameter("id"));
        userDAO.deleteUser(id);
        response.sendRedirect("/users?action=list");

    }

    private void updateUsers(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User user = new User(id,name,email,country);
        userDAO.updateUser(user);
        response.sendRedirect("/users?action=list");


    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User user = new User(-1,name,email,country);
        userDAO.insertUser(user);
        response.sendRedirect("/users?aciton=list");
    }
}
