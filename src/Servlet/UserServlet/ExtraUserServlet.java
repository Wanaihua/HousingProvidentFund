package Servlet.UserServlet;

import Bean.Business;
import Dao.BusinessDao;
import Dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ExtraUserServlet", value = "/extraUserServlet")
public class ExtraUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ACCNUM = Integer.parseInt(request.getParameter("ACCNUM"));
        UserDao dao=new UserDao();
        String BALANCE=dao.selectBalanceByAccnum(ACCNUM);
        String NAME=dao.selectUserNameByAccnum(ACCNUM);
        request.setAttribute("msg", "您的余额为:"+BALANCE+"元,请取走您的现金");
        dao.setBalanceByAccnum(ACCNUM,0);
        Business business=new Business();
        business.setBusinessMessage("取款:用户"+ACCNUM+"取走了"+NAME+"元");
        business.setTime(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
        business.setUser("Root");
        BusinessDao businessDao=new Dao.BusinessDao();
        businessDao.insertOneBusiness(business);
        request.getRequestDispatcher("user/extract.jsp").forward(request, response);
    }
}
