package Servlet.UserServlet;

import Bean.Business;
import Bean.Company;
import Bean.User;
import Dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserDeleteServlet",urlPatterns = "/userDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao dao=new UserDao();
        int UNITACCNUM=dao.findUnitAccnumByAccnum(request.getParameter("ACCNUM"));
        String name=dao.selectUserByNUM(request.getParameter("ACCNUM")).getNAME();
        Business business=new Business();
        business.setBusinessMessage("用户注销:"+name);
        business.setTime(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
        business.setUser("Root");
        Dao.BusinessDao businessDao=new Dao.BusinessDao();
        businessDao.insertOneBusiness(business);
        dao.deleteOneUser(request.getParameter("ACCNUM"));
        dao.updateCompanyPERSNUM(UNITACCNUM);
        request.getRequestDispatcher("user/showAllUser.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response );
    }
}
