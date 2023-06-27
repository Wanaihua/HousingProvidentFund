package Servlet;

import Bean.Admin;
import Bean.Teller;
import Dao.AdminDao;
import Dao.TellerDao;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            HttpSession session = request.getSession();
            if(request.getParameter("username")==null||request.getParameter("userpass")==null){
                request.setAttribute("msg", "用户名或密码不能为空");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            String username = new String(request.getParameter("username").getBytes("iso-8859-1"), "utf-8");
            String userpass = new String(request.getParameter("userpass").getBytes("iso-8859-1"), "utf-8");
            if (request.getParameter("type").equals("root")) {
                Admin admin = new AdminDao().LoginAdmin(username, userpass);
                if (admin != null) {
                    session.setAttribute("admin", admin);
                    response.sendRedirect("admin/adminIndex.jsp");
                }else{
                    request.setAttribute("rootMsg", "用户名或密码错误");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                Teller teller = new TellerDao().LoginTeller(username, userpass);
                if (teller != null) {
                    session.setAttribute("teller", teller);
                    response.sendRedirect("Teller/tellerIndex.jsp");
                }else{
                    request.setAttribute("msg", "用户名或密码错误");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
