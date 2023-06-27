package Servlet.CompanyServlet;

import Bean.Company;
import Dao.CompanyDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import Servlet.GsonUtil;

@WebServlet(name = "CompanyShowServlet", value = "/companyShowServlet")
public class CompanyShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cid = Integer.parseInt(request.getParameter("cid"));
        if(cid==1){
            request.setAttribute("cid",cid);
            request.getRequestDispatcher("company/showCompany.jsp").forward(request,response);
        }else{
            CompanyDao companyDao = new CompanyDao();
            List<Company> company;
            int count=0;
            company=companyDao.selectAllCompany();
            count = company.size();
            if(request.getParameter("page")!=null){
                int page = Integer.parseInt(request.getParameter("page"));
                int limit = Integer.parseInt(request.getParameter("limit"));
                company=companyDao.selectAllCompanyByPage((page-1)*limit,limit);
            }else{
                company=companyDao.selectAllCompanyByPage(0,10);
            }
            String data = GsonUtil.simpleCompanyListToJson(company);
            String json = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+data+"}";
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
        }

    }
}
