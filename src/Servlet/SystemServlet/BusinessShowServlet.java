package Servlet.SystemServlet;

import Bean.Business;
import Bean.System;
import Dao.BusinessDao;
import Dao.SystemDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import Servlet.GsonUtil;

@WebServlet(name = "BusinessShowServlet", value = "/businessShowServlet")
public class BusinessShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BusinessDao businessDao = new BusinessDao();
        List<Business> list=businessDao.selectAllBusiness();
        int count = list.size();
        if(request.getParameter("page")!=null){
            int page = Integer.parseInt(request.getParameter("page"));
            int limit = Integer.parseInt(request.getParameter("limit"));
            list=businessDao.selectAllBusinessByPage((page-1)*limit,limit);
        }else{
            list=businessDao.selectAllBusinessByPage(0,10);
        }
        String data = GsonUtil.simpleBusinessListToJson(list);
        String json = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+data+"}";
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
