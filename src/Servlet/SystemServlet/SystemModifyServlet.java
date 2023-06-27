package Servlet.SystemServlet;

import Bean.System;
import Dao.SystemDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SystemModifyServlet", urlPatterns = "/systemModifyServlet")
public class SystemModifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Bean.System system = new System(
                new String(request.getParameter("SEQNAME").getBytes("iso-8859-1"), "utf-8"),
                new String(request.getParameter("SEQ").getBytes("iso-8859-1"), "utf-8"),
                new String(request.getParameter("MAXSEQ").getBytes("iso-8859-1"), "utf-8"),
                new String(request.getParameter("DESC").getBytes("iso-8859-1"), "utf-8"),
                new String(request.getParameter("FREEUSE1").getBytes("iso-8859-1"), "utf-8")
        );
        SystemDao dao = new SystemDao();
        dao.updateOneSystem(system);
        request.getRequestDispatcher("/sysytemSelectServlet").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
