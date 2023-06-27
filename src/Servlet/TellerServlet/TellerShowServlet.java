package Servlet.TellerServlet;

import Bean.Teller;
import Dao.TellerDao;
import Servlet.GsonUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TellerShowServlet", value = "/tellerShowServlet")
public class TellerShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TellerDao tellerDao=new TellerDao();
        List<Teller> teller;
        int count=0;
        teller=tellerDao.selectAllTeller();
        count = teller.size();
        if(request.getParameter("page")!=null){
            int page = Integer.parseInt(request.getParameter("page"));
            int limit = Integer.parseInt(request.getParameter("limit"));
            teller=tellerDao.selectAllTellerByPage((page-1)*limit,limit);
        }else{
            teller=tellerDao.selectAllTellerByPage(0,10);
        }
        String data = GsonUtil.simpleTellerListToJson(teller);
        String json = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+data+"}";
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
