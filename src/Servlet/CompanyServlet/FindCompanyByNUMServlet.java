package Servlet.CompanyServlet;

import Bean.Company;
import Dao.CompanyDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "FindCompanyByNUMServlet", value = "/findCompanyByNUMServlet")
public class FindCompanyByNUMServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        if(request.getParameter("UNITACCNUM")==null){
            request.setAttribute("msg","请输入单位账号");
            if(id==1) request.getRequestDispatcher("company/Remittance.jsp").forward(request,response);
            else if(id==2) request.getRequestDispatcher("company/SupplementaryPayment.jsp").forward(request,response);
            else request.getRequestDispatcher("company/logout.jsp").forward(request,response);
            return;
        }
        String UNITACCNUM=request.getParameter("UNITACCNUM");
        CompanyDao dao=new CompanyDao();
        Company company=dao.selectCompanyByNUM(UNITACCNUM);
        if(company==null){
            request.setAttribute("msg","单位账号不存在或已被注销,请重新注册");
            if(id==1) request.getRequestDispatcher("company/Remittance.jsp").forward(request,response);
            else if(id==2) request.getRequestDispatcher("company/SupplementaryPayment.jsp").forward(request,response);
            else request.getRequestDispatcher("company/logout.jsp").forward(request,response);
            return;
        }
        request.setAttribute("company",company);
        if(id==1) request.getRequestDispatcher("company/Remittance.jsp").forward(request,response);
        else if(id==2) request.getRequestDispatcher("company/SupplementaryPayment.jsp").forward(request,response);
        else request.getRequestDispatcher("company/logout.jsp").forward(request,response);
    }
}
