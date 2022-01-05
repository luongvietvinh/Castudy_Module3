package controller;


import config.Dao.AdminDao;
import config.Dao.Roledao;
import model.Admin;
import model.Role;
import service.Managerservice;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import static java.lang.Double.parseDouble;

@WebServlet(urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    AdminDao adminDao = new AdminDao();
    Managerservice managerService = new Managerservice();
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
                request.setAttribute("roles", roleList);
                request.getRequestDispatcher("/view/admin/createAdmin.jsp").forward(request, response);
                break;
            case "edit":
                roleList = roledao.showRole();
                request.setAttribute("roles", roleList);
                request.getRequestDispatcher("view/admin/editAdmin.jsp").forward(request, response);
                break;
            case "delete":
                try {
                    deleteManager(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showList(request, response);
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
                try {
                    createManager(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "edit":
                try {
                    editManager(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "search":
                searchByName(request, response);
                break;
        }
    }

    public void searchByName(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("search");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/showAdmin.jsp");
        List<Admin> admins = adminDao.searchByName(name);

        request.setAttribute("admins", admins);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void createManager(HttpServletRequest request, HttpServletResponse response) {

        String user_name = request.getParameter("user_name");
        String passwords = request.getParameter("passwords");
        String full_name = request.getParameter("full_name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String img = request.getParameter("img");
        Double salary = Double.parseDouble(request.getParameter("salary"));
        Double coefficients_salary = Double.parseDouble(request.getParameter("coefficients_salary"));
        int id_role = Integer.parseInt(request.getParameter("id_role"));
        managerService.add(new Admin( user_name, passwords,full_name, email, phone, address, img, salary, coefficients_salary,id_role));
        try {
            response.sendRedirect("/admin");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editManager(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String user_name = request.getParameter("user_name");
        String passwords = request.getParameter("passwords");
        String full_name = request.getParameter("full_name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String img = request.getParameter("img");
        Double salary = Double.parseDouble(request.getParameter("salary"));
        Double coefficients_salary = Double.parseDouble(request.getParameter("coefficients_salary"));
        int id_role = Integer.parseInt(request.getParameter("id_role"));
        Admin admin = new Admin(id , user_name, passwords,full_name, email, phone, address, img, salary, coefficients_salary,id_role);
        managerService.edit(id, admin);
        try {
            response.sendRedirect("/admin");
        } catch (IOException e) {
            e.printStackTrace();

            }
        }


    public void deleteManager(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        managerService.delete(id);

        try {
            response.sendRedirect("/admin");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/showAdmin.jsp");
        List<Admin> admins = adminDao.showListManager();
        request.setAttribute("admins", admins);
        dispatcher.forward(request, response);
    }
}