package controller;

import config.Dao.CustomerDao;

import config.Dao.Roledao;
import model.Customer;
import model.Role;
import service.Customerservice;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import java.util.List;

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    CustomerDao customerDao = new CustomerDao();
    Customerservice customerservice = new Customerservice();
    Roledao roledao = new Roledao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";

        }
        switch (action) {
            case "create":
                List<Role> roleList = roledao.showRole();
                request.setAttribute("roles" , roleList);
                request.getRequestDispatcher("/view/customer/createCustomer.jsp").forward(request, response);
                break;
            case "edit":
                roleList = roledao.showRole();
                request.setAttribute("roles" , roleList);
                request.getRequestDispatcher("/view/customer/editCustomer.jsp").forward(request, response);
                break;
            case "delete":
             deleteProduct(request,response);
                break;
            default:
             showListCustomer(request,response);
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
             createCustomer(request,response);
                break;
            case "edit":
             editCustomer(request,response);
                break;
            case "search":
                searchByName(request,response);
                break;
        }
    }

    public void searchByName (HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("search");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/customer/showCustomer.jsp");
        List<Customer> customerList = customerDao.searchByName(name);;
        request.setAttribute("customers", customerList);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void createCustomer(HttpServletRequest request, HttpServletResponse response)  {

        String full_name = request.getParameter("full_name");
        String passwords = request.getParameter("passwords");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String img = request.getParameter("img");
        int id_role = Integer.parseInt(request.getParameter("id_role"));
        customerservice.add(new Customer( full_name, passwords, email,phone,address,img,id_role));
        try {
            response.sendRedirect("/customer");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editCustomer(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        int id_role = Integer.parseInt(request.getParameter("id_role"));
        String full_name = request.getParameter("full_name");
        String passwords = request.getParameter("passwords");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String img = request.getParameter("img");
        Customer customer = new Customer(id, id_role, full_name, passwords, email,phone,address,img);
        customerservice.edit(id,customer);

        try {
            response.sendRedirect("/customer");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        customerservice.delete(id);
        try {
            response.sendRedirect("/customer");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showListCustomer(HttpServletRequest request, HttpServletResponse response){
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/customer/showCustomer.jsp");
        List<Customer> customerList = customerDao.showListCustomer();
        request.setAttribute("customers", customerList);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
