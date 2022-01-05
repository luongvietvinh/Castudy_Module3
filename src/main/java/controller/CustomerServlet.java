package controller;

import config.Dao.CustomerDao;
import config.Dao.Roledao;
import model.Customer;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";

        }
        switch (action) {
            case "create":
                request.getRequestDispatcher("/view/customer/createCustomer.jsp").forward(request, response);
                break;
            case "edit":
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

        RequestDispatcher dispatcher = request.getRequestDispatcher("showCustomer.jsp");
        List<Customer> customerList = customerDao.searchByName(name);;
        request.setAttribute("customer", customerList);
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
//        Date create_date = Date.valueOf(request.getParameter("create_date"));
//        Date modify_date = Date.valueOf(request.getParameter("modify_date"));
        customerservice.add(new Customer( full_name, passwords, email,phone,address,img));
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
//        Date create_date = Date.valueOf(request.getParameter("create_date"));
//        Date modify_date = Date.valueOf(request.getParameter("modify_date"));
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
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
