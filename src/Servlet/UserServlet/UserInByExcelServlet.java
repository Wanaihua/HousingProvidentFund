package Servlet.UserServlet;

import Bean.Business;
import Bean.Company;
import Dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "UserInByExcelServlet",urlPatterns = "/userInByExcelServlet")
public class UserInByExcelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userfilePath=request.getParameter("userfilePath");
        System.out.println(userfilePath);
        if(new File(userfilePath).exists()){
            UserDao dao=new UserDao();
            dao.insertUsersByExcel(userfilePath,(Company)request.getSession().getAttribute("company"));
        }
        Business business=new Business();
        business.setBusinessMessage("EXCEL表用户批量导入");
        business.setTime(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
        business.setUser("Root");
        Dao.BusinessDao businessDao=new Dao.BusinessDao();
        businessDao.insertOneBusiness(business);
        request.getRequestDispatcher("/userSelectServlet").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
