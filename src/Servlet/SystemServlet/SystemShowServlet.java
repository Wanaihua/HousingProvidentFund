package Servlet.SystemServlet;

import Bean.System;
import Dao.SystemDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import Servlet.GsonUtil;

@WebServlet(name = "SystemShowServlet", value = "/systemShowServlet")
public class SystemShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SystemDao dao=new SystemDao();
        List<System> system=dao.selectAllSystem();
        String data = GsonUtil.simpleSystemListToJson(system);
        String json = "{\"code\":0,\"msg\":\"\",\"count\":"+2+",\"data\":"+data+"}";
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }
}
