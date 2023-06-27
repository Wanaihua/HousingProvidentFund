package Servlet.TellerServlet;

import Bean.Business;
import Bean.Teller;
import Dao.TellerDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "TellerInAndUpServlet", value = "/tellerInAndUpServlet")
public class TellerInAndUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Teller teller=new Teller();
        TellerDao tellerDao=new TellerDao();
        int cid=Integer.parseInt(request.getParameter("cid"));
        if(cid==2){
            String TELLERACCNUM=request.getParameter("TELLERACCNUM");
            String NAME=new String(request.getParameter("TELLERNAME").getBytes("iso-8859-1"),"utf-8");
            teller.setTELLERNAME(NAME);
            teller.setTELLERCARD(request.getParameter("TELLERCARD"));
            teller.setTELLERPHONE(request.getParameter("TELLERPHONE"));
            teller.setTELLERREMAKE(request.getParameter("TELLERREMAKE"));
            teller.setTELLERACCNUM(TELLERACCNUM);
            tellerDao.updateOneTeller(teller);
            Business business=new Business();
            business.setBusinessMessage("修改了柜员:"+teller.getTELLERNAME());
            business.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            business.setUser("Root");
            request.setAttribute("business",business);
            request.setAttribute("msg","修改成功");
            request.getRequestDispatcher("Teller/showAllTeller.jsp").forward(request,response);
        }else{
            String pass=request.getParameter("TELLERPASS");
            String andpass=request.getParameter("ANDTELLERPASS");
            if(!pass.equals(andpass)){
                request.setAttribute("msg","两次密码不一致");
                request.getRequestDispatcher("Teller/RegisterTeller.jsp").forward(request,response);
                return;
            }
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            String TELLERACCNUM=tellerDao.selectTellerNextNumber();
            teller.setTELLERACCNUM(TELLERACCNUM);
            String NAME=new String(request.getParameter("TELLERNAME").getBytes("iso-8859-1"),"utf-8");
            teller.setTELLERNAME(NAME);
            teller.setTELLERPASS(pass);
            teller.setTELLERCARD(request.getParameter("TELLERCARD"));
            teller.setTELLERPHONE(request.getParameter("TELLERPHONE"));
            teller.setTELLERREMAKE(request.getParameter("TELLERREMAKE"));
            tellerDao.insertOneTeller(teller);
            Business business=new Business();
            business.setBusinessMessage("添加了柜员:"+teller.getTELLERNAME());
            business.setTime(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
            business.setUser("Root");
            Dao.BusinessDao businessDao=new Dao.BusinessDao();
            businessDao.insertOneBusiness(business);
            request.setAttribute("msg","添加成功,您的柜员账号为:"+TELLERACCNUM);
            request.getRequestDispatcher("Teller/RegisterTeller.jsp").forward(request,response);
        }
    }
}
