package Servlet.CompanyServlet;

import Bean.Business;
import Bean.Company;
import Bean.User;
import Dao.BusinessDao;
import Dao.CompanyDao;
import Dao.UserDao;
import Servlet.GsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "CompanyDeleteServlet", urlPatterns = "/companyDeleteServlet")
public class CompanyDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CompanyDao dao = new CompanyDao();
        Company company = dao.selectCompanyBySomething("UNITACCNUM", request.getParameter("UNITACCNUM")).get(0);
        company.setACCSTATE("9");
        UserDao userDao = new UserDao();
        List<User> list = userDao.selectUserBySomething("UNITACCNUM", company.getUNITACCNUM());
        for (User user : list) {
            user.setPERACCSTATE("9");
            userDao.updateOneUser(user);
        }
        Business business = new Business();
        business.setBusinessMessage("删除单位:"+company.getUNITACCNAME());
        business.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        business.setUser("Root");
        BusinessDao businessDao = new BusinessDao();
        businessDao.insertOneBusiness(business);
        dao.updateOneCompany(company);
        response.sendRedirect("company/showAllCompany.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
