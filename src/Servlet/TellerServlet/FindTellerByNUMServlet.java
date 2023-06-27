package Servlet.TellerServlet;

import Bean.Teller;
import Dao.TellerDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "FindTellerByNUMServlet", value = "/findTellerByNUMServlet")
public class FindTellerByNUMServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TellerDao tellerDao=new TellerDao();
        String TELLERACCNUM=request.getParameter("TELLERACCNUM");
        Teller teller=tellerDao.selectOneTellerByNUM(TELLERACCNUM);
        request.setAttribute("teller",teller);
        request.getRequestDispatcher("Teller/logout.jsp").forward(request,response);
    }
}
