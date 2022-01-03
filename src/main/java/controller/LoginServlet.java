package controller;

import config.Dao.CustomerDao;
import config.Dao.LoginDao;
import config.Dao.ManagerDao;
import model.Manager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    LoginDao loginDao = new LoginDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login/account.html").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

//
//    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
//
//        response.setContentType("text/html;charset=UTF-8");
//        String user = request.getParameter("user_name");
//        String pass = request.getParameter("passs_word");
//        Manager manager = loginDao.login(user, pass);
//        if (manager == null) {
//            try {
//                request.getRequestDispatcher("/login/account.html").forward(request, response);
//            } catch (ServletException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            try {
//                request.getRequestDispatcher("/view/manager/showManager").forward(request, response);
//            } catch (ServletException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }


}
