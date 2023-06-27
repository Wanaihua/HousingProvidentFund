package Servlet.UserServlet;

import Bean.Business;
import Dao.BusinessDao;
import Dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LockUserServlet", value = "/lockUserServlet")
public class LockUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ACCNUM = Integer.parseInt(request.getParameter("ACCNUM"));
        UserDao dao = new UserDao();
        String NAME=dao.selectUserNameByAccnum(ACCNUM);
        dao.lockUser(ACCNUM);
        request.setAttribute("msg", "封存成功");
        Business business = new Business();
        business.setBusinessMessage("封存用户:"+NAME);
        business.setTime(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
        business.setUser("Root");
        BusinessDao businessDao = new BusinessDao();
        businessDao.insertOneBusiness(business);
        request.getRequestDispatcher("user/lock.jsp").forward(request, response);
    }
}
