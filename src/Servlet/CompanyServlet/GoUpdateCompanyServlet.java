package Servlet.CompanyServlet;

import Bean.Business;
import Bean.Company;
import Dao.BusinessDao;
import Dao.CompanyDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "GoUpdateCompanyServlet", value = "/goUpdateCompanyServlet")
public class GoUpdateCompanyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String num=request.getParameter("UNITACCNUM");
        Date date=new Date();
        String nowDate=sdf.format(date);
        CompanyDao dao=new CompanyDao();
        dao.updateCompanyPayDate(nowDate,num);
        Company company=dao.selectCompanyByNUM(num);
        request.setAttribute("company",company);
        request.setAttribute("msg","缴费成功");
        Business business=new Business();
        business.setBusinessMessage("单位缴费:"+company.getUNITACCNAME());
        business.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        business.setUser("Root");
        BusinessDao businessDao=new BusinessDao();
        businessDao.insertOneBusiness(business);
        int id=request.getParameter("id")==null?0:Integer.parseInt(request.getParameter("id"));
        if(id==1) request.getRequestDispatcher("company/Remittance.jsp").forward(request,response);
        else request.getRequestDispatcher("company/SupplementaryPayment.jsp").forward(request,response);
    }
}
