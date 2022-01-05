package controller;
import config.Dao.AdminDao;
import model.Admin;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    AdminDao adminDao = new AdminDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/login/account.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request,response);


    }


    public void processRequest(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html;charset=UTF-8");
        String user = request.getParameter("user_name");
        String pass = request.getParameter("passs_word");
        Admin admin = adminDao.login(user, pass);
        if (admin == null) {
            try {
                request.setAttribute("mess", "ten tai khoan sai moi nhap lai");
                request.getRequestDispatcher("/view/login/account.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                request.getRequestDispatcher("/view/admin/showAdmin.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
