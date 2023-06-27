package Servlet.TellerServlet;

import Bean.Business;
import Dao.TellerDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LogoutTellerServlet", value = "/logoutTellerServlet")
public class LogoutTellerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TellerDao tellerDao=new TellerDao();
        String TELLERACCNUM=request.getParameter("TELLERACCNUM");
        String TELLERNAME=request.getParameter("TELLERNAME");
        tellerDao.deleteOneTellerByNUM(TELLERACCNUM);
        Business business=new Business();
        business.setBusinessMessage("注销了柜员:"+TELLERNAME);
        business.setTime(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
        business.setUser("ROOT");
        Dao.BusinessDao businessDao=new Dao.BusinessDao();
        businessDao.insertOneBusiness(business);
        request.setAttribute("msg","注销成功");
        request.getRequestDispatcher("Teller/LogoutTeller.jsp").forward(request,response);
    }
}
