package Servlet.TellerServlet;

import Bean.Business;
import Bean.Teller;
import Dao.AdminDao;
import Dao.BusinessDao;
import Dao.TellerDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "TellerUpdateServlet", value = "/tellerUpdateServlet")
public class TellerUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        TellerDao tellerDao = new TellerDao();
        String id = request.getParameter("id");
        String oldPassword = request.getParameter("oldPassword");
        String password = request.getParameter("newPassword1");
        String name=tellerDao.findNameByid(id);
        if(!tellerDao.LoginTellerTest(id,oldPassword)){
            request.setAttribute("msg","原密码错误");
            request.getRequestDispatcher("Teller/Update.jsp").forward(request,response);
        }else if(tellerDao.updateTeller(id,password)){
            Business business=new Business();
            business.setBusinessMessage("修改柜员密码");
            business.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            business.setUser(name);
            BusinessDao businessDao=new BusinessDao();
            businessDao.insertOneBusiness(business);
            request.setAttribute("msg","修改成功");
            request.getRequestDispatcher("admin/Update.jsp").forward(request,response);
        }else{
            request.setAttribute("msg","修改失败");
            request.getRequestDispatcher("admin/Update.jsp").forward(request,response);
        }
    }
}
