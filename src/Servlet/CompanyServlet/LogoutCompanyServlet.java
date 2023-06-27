package Servlet.CompanyServlet;

import Bean.Business;
import Dao.BusinessDao;
import Dao.CompanyDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "LogoutCompanyServlet", value = "/logoutCompanyServlet")
public class LogoutCompanyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String num=request.getParameter("UNITACCNUM");
        CompanyDao dao=new CompanyDao();
        String name=dao.selectCompanyByNUM(num).getUNITACCNAME();
        dao.logoutCompany(num);
        request.setAttribute("msg","注销成功");
        Business business=new Business();
        business.setBusinessMessage("单位注销:"+name);
        business.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        business.setUser("Root");
        BusinessDao businessDao=new BusinessDao();
        businessDao.insertOneBusiness(business);
        request.getRequestDispatcher("company/logout.jsp").forward(request,response);
    }
}
