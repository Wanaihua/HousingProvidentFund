package Servlet.UserServlet;

import Bean.User;
import Dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import Servlet.GsonUtil;

@WebServlet(name = "UserShowServlet", value = "/userShowServlet")
public class UserShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        List<User> user;
        int count=0;
        user=userDao.selectAllUser();
        count = user.size();
        if(request.getParameter("page")!=null){
            int page = Integer.parseInt(request.getParameter("page"));
            int limit = Integer.parseInt(request.getParameter("limit"));
            user=userDao.selectAllUserByPage((page-1)*limit,limit);
        }else{
            user=userDao.selectAllUserByPage(0,10);
        }
        String data = GsonUtil.simpleUserListToJson(user);
        String json = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+data+"}";
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
