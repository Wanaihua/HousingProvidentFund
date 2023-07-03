package Servlet.UserServlet;

import Bean.Business;
import Bean.Company;
import Bean.User;
import Dao.CompanyDao;
import Dao.SystemDao;
import Dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "UserInAndUpServlet", urlPatterns = "/userInAndUpServlet")
public class UserInAndUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao dao = new UserDao();
        CompanyDao dao1 = new CompanyDao();
        String uid = request.getParameter("uid");
        String nextNumber = dao.selectUserNextNumber();
        SystemDao systemDao = new SystemDao();
        if ("1".equals(uid)) {
            if(!systemDao.checkNumber("PERACCNUM",nextNumber)){
                request.setAttribute("msg", "添加失败,账号已达上限");
                request.getRequestDispatcher("user/RegisterUser.jsp").forward(request, response);
            }
            User user = new User(
                    nextNumber,
                    new String(request.getParameter("UNITACCNUM").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("NAME").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("TYPE").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("NUM").getBytes("iso-8859-1"), "utf-8"),
                    new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                    "0.00",
                    "0",
                    new String(request.getParameter("BASENUMBER").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("UNITPROP").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("INDIPROP").getBytes("iso-8859-1"), "utf-8"),
                    new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                    new String(request.getParameter("UNITMONPAYSUM").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("PERMONPAYSUM").getBytes("iso-8859-1"), "utf-8"),
                    "0.00",
                    "0.00",
                    "0.00",
                    "0110",
                    "1111",
                    new String(request.getParameter("REMARK").getBytes("iso-8859-1"), "utf-8")
            );
            String UNITACCNUM=user.getUNITACCNUM();
            Company company = dao1.selectCompanyByUNITACCNUM(Integer.parseInt(UNITACCNUM));
            if(company==null){
                request.setAttribute("msg", "添加失败,公司不存在");
                request.getRequestDispatcher("user/RegisterUser.jsp").forward(request, response);
                return;
            }
            int PERSNUM = Integer.parseInt(company.getPERSNUM())+1;
            company.setPERSNUM(String.valueOf(PERSNUM));
            dao1.updateCompanyPERSNUM(company);
            dao.insertOneUser(user);
            Business business = new Business();
            business.setBusinessMessage("用户开户:" + user.getNAME());
            business.setTime(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
            business.setUser("Root");
            Dao.BusinessDao businessDao = new Dao.BusinessDao();
            businessDao.insertOneBusiness(business);
            request.setAttribute("msg", "添加成功");
            request.getRequestDispatcher("user/RegisterUser.jsp").forward(request, response);

        } else {

            User user = new User(
                    new String(request.getParameter("ACCNUM").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("UNITACCNUM").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("NAME").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("TYPE").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("NUM").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("OPENDATE").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("BALANCE").getBytes("iso-8859-1"), "utf-8"),
                    "0",
                    new String(request.getParameter("BASENUMBER").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("UNITPROP").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("INDIPROP").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("LASTPAYDATE").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("UNITMONPAYSUM").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("PERMONPAYSUM").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("YPAYAMT").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("YDRAWAMT").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("YINTERESTBAL").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("INSTCODE").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("OP").getBytes("iso-8859-1"), "utf-8"),
                    new String(request.getParameter("REMARK").getBytes("iso-8859-1"), "utf-8")
            );
            String UNITACCNUM=user.getUNITACCNUM();
            String OldUnitAccnum=dao.selectOldUnitAccnumByACCNUM(user.getACCNUM());
            Company company = dao1.selectCompanyByUNITACCNUM(Integer.parseInt(UNITACCNUM));
            if(company==null){
                request.setAttribute("msg", "添加失败,公司不存在");
                request.getRequestDispatcher("user/showAllUser.jsp").forward(request, response);
                return;
            }else{
                dao1.updateOldCompanyPERSNUM(OldUnitAccnum);
                dao1.updateNewCompanyPERSNUM(UNITACCNUM);
            }
            Business business = new Business();
            business.setBusinessMessage("用户数据修改:" + user.getNAME());
            business.setTime(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
            business.setUser("Root");
            Dao.BusinessDao businessDao = new Dao.BusinessDao();
            businessDao.insertOneBusiness(business);
            request.setAttribute("msg", "添加成功");
            dao.updateOneUser(user);
            response.sendRedirect("user/showAllUser.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
