package Servlet.CompanyServlet;

import Bean.Business;
import Bean.Company;
import Dao.BusinessDao;
import Dao.CompanyDao;
import Dao.SystemDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "CompanyInAndUpServlet", urlPatterns = "/companyInAndUpServlet")
public class CompanyInAndUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CompanyDao dao = new CompanyDao();
        SystemDao systemDao = new SystemDao();
        String cid = request.getParameter("cid");
        if ("1".equals(cid)) {
            String NextNumber = dao.selectCompanyNextNumber();
            if(!systemDao.checkNumber("UNITACCNUM", NextNumber)){
                request.setAttribute("msg", "单位账号已达上限");
                request.setAttribute("cid", "1");
                request.getRequestDispatcher("company/showCompany.jsp").forward(request, response);
            }
            Company company = new Company(
                    NextNumber,
                    new String(request.getParameter("UNITACCNAME").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("UNITADDR").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("ORGCODE").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("UNITCHAR").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("UNITKIND").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("SALARYDATE").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("UNITPHONE").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("UNITLINKMAN").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("UNITAGENTPAPNO").getBytes("iso-8859-1"), "utf-8"),
                    "0",
                    "0.00",
                    "0.00",
                    "0.000",
                    "0.000",
                    "0.00",
                    "0.00",
                    "0",
                    "1899-12-01",
                    "0110",
                    "111111",
                    new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                    new String(request.getParameter("REMARK").getBytes("iso-8859-1"), "utf-8")
            );
            if(dao.selectCompanyBySomething("ORGCODE",company.getORGCODE()).size()>0){
                Company company1=dao.selectCompanyBySomething("ORGCODE",company.getORGCODE()).get(0);
                company.setUNITACCNUM(company1.getUNITACCNUM());
                if(company1.getACCSTATE().equals("9")){
                    dao.updateOneCompany(company);
                }
            }else {
                dao.insertOneCompany(company);
            }
            Business business=new Business();
            business.setBusinessMessage("添加了单位:"+company.getUNITACCNAME());
            business.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            business.setUser("ROOT");
            BusinessDao businessDao=new BusinessDao();
            businessDao.insertOneBusiness(business);
            request.setAttribute("cid", "1");
            request.setAttribute("msg", "添加成功,您的单位账号为:" + company.getUNITACCNUM());
            request.getRequestDispatcher("company/showCompany.jsp").forward(request, response);
        } else {
            Company company = new Company(new String(request.getParameter("UNITACCNUM").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("UNITACCNAME").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("UNITADDR").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("ORGCODE").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("UNITCHAR").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("UNITKIND").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("SALARYDATE").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("UNITPHONE").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("UNITLINKMAN").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("UNITAGENTPAPNO").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("ACCSTATE").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("BALANCE").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("BASENUMBER").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("UNITPROP").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("PERPROP").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("UNITPAYSUM").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("PERPAYSUM").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("PERSNUM").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("LASTPAYDATE").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("INSTCODE").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("OP").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("CREATDATE").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("REMARK").getBytes("iso-8859-1"), "utf-8")
            );
            dao.updateOneCompany(company);
            Business business=new Business();
            business.setBusinessMessage("修改了单位:"+company.getUNITACCNAME());
            business.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            business.setUser("ROOT");
            BusinessDao businessDao=new BusinessDao();
            businessDao.insertOneBusiness(business);
            request.setAttribute("msg", "修改成功,您的单位账号为:" + company.getUNITACCNUM());
            request.getRequestDispatcher("company/showAllCompany.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
