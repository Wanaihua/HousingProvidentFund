package Servlet.UserServlet;

import Bean.Business;
import Dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LogoutUserServlet", value = "/logoutUserServlet")
public class LogoutUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ACCNUM= request.getParameter("ACCNUM");
        UserDao dao=new UserDao();
        String NAME=dao.selectUserByNUM(ACCNUM).getNAME();
        dao.deleteOneUser(ACCNUM);
        Business business=new Business();
        business.setBusinessMessage("用户注销:"+NAME);
        business.setTime(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
        business.setUser("Root");
        Dao.BusinessDao businessDao=new Dao.BusinessDao();
        businessDao.insertOneBusiness(business);
        request.setAttribute("msg","注销成功");
        request.getRequestDispatcher("user/logout.jsp").forward(request, response);
    }
}
