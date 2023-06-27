package Servlet.CompanyServlet;

import Bean.Business;
import Bean.Company;
import Dao.BusinessDao;
import Dao.CompanyDao;
import Dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "CompanyInByExcelServlet", value = "/companyInByExcelServlet")
public class CompanyInByExcelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String file=request.getParameter("CompanyfilePath");
        String companyfilePath="D:\\Users\\30626\\Downloads\\"+file;

        System.out.println(companyfilePath);
        if(new File(companyfilePath).exists()){
            CompanyDao dao=new CompanyDao();
            dao.insertCompanysByExcel(companyfilePath);
        }
        Business business=new Business();
        business.setBusinessMessage("导入单位");
        business.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        business.setUser("Root");
        BusinessDao businessDao=new BusinessDao();
        businessDao.insertOneBusiness(business);
        request.setAttribute("msg","导入成功");
        request.getRequestDispatcher("company/showAllCompany.jsp").forward(request, response);
    }
}
