package controller;

import config.Dao.CustomerDao;
import config.Dao.ManagerDao;
import config.Dao.Roledao;
import model.Customer;
import model.Manager;
import model.Role;
import service.Customerservice;
import service.Managerservice;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    ManagerDao managerDao = new ManagerDao();
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
                request.getRequestDispatcher("editAdmin.jsp").forward(request, response);
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
        List<Manager> managerList = managerDao.searchByName(name);

        request.setAttribute("manager", managerList);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void createManager(HttpServletRequest request, HttpServletResponse response) {
        int id_role = Integer.parseInt(request.getParameter("id_role"));
        String user_name = request.getParameter("user_name");
        String passwords = request.getParameter("passwords");
        String full_name = request.getParameter("full_name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String img = String.valueOf(Integer.parseInt(request.getParameter("img")));
        Double salary = Double.valueOf(request.getParameter("salary"));
        Double coefficients_salary = Double.valueOf(request.getParameter("coefficients_salary"));
        String status = request.getParameter("status");
        Date create_date = Date.valueOf(request.getParameter("create_date"));
        Date modify_date = Date.valueOf(request.getParameter("modify_date"));
        managerService.add(new Manager(id_role, user_name, passwords,full_name, email, phone, address, img, salary, coefficients_salary, status, create_date, modify_date));
        try {
            response.sendRedirect("/admin");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editManager(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        int id_role = Integer.parseInt(request.getParameter("id_role"));
        String user_name = request.getParameter("user_name");
        String passwords = request.getParameter("passwords");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String img = String.valueOf(Integer.parseInt(request.getParameter("img")));
        Double salary = Double.valueOf(request.getParameter("salary"));
        Double coefficients_salary = Double.valueOf(request.getParameter("coefficients_salary"));
        String status = request.getParameter("status");
        Date create_date = Date.valueOf(request.getParameter("create_date"));
        Date modify_date = Date.valueOf(request.getParameter("modify_date"));
        Manager manager = new Manager(id, id_role, user_name, passwords, email, phone, address, img, salary, coefficients_salary, status, create_date, modify_date);
        managerService.edit(id, manager);
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
        List<Manager> managerList = managerDao.showListManager();
        request.setAttribute("managers", managerList);
        dispatcher.forward(request, response);
    }
}