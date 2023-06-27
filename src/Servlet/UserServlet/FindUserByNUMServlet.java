package Servlet.UserServlet;

import Bean.User;
import Dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "FindUserByNUMServlet", value = "/findUserByNUMServlet")
public class FindUserByNUMServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String ACCNUM= request.getParameter("ACCNUM");
        UserDao dao=new UserDao();
        User user=dao.selectUserByACCNUM(ACCNUM);
        if(user==null){
            request.setAttribute("msg","账号不存在或已被注销,请重新注册");
        }
        if(user!=null) request.setAttribute("user",user);
        switch (id){
            case 1:
                request.getRequestDispatcher("user/logout.jsp").forward(request, response);
                break;
            case 2:
                request.getRequestDispatcher("user/lock.jsp").forward(request, response);
                break;
            case 3:
                request.getRequestDispatcher("user/open.jsp").forward(request, response);
                break;
            case 4:
                request.getRequestDispatcher("user/extract.jsp").forward(request, response);
                break;
        }
    }
}
