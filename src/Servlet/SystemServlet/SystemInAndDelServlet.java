package Servlet.SystemServlet;

import Bean.Business;
import Bean.System;
import Dao.BusinessDao;
import Dao.SystemDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SystemInAndDelServlet", urlPatterns = "/systemInAndDelServlet")
public class SystemInAndDelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SystemDao dao = new SystemDao();
        System system = new System();
        system.setId(Integer.parseInt(request.getParameter("id")));
        system.setSEQNAME(request.getParameter("SEQNAME"));
        system.setMAXSEQ(request.getParameter("MAXSEQ"));
        system.setDESC(request.getParameter("DESC"));
        dao.updateOneSystem(system);
        Business business = new Business();
        business.setBusinessMessage("修改系统参数");
        business.setTime(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
        business.setUser("Root");
        BusinessDao businessDao = new BusinessDao();
        businessDao.insertOneBusiness(business);
        request.setAttribute("msg", "修改成功");
        request.getRequestDispatcher("system/SystemParameter.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
