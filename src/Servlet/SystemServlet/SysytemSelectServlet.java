package Servlet.SystemServlet;

import Bean.SelectValue;
import com.github.pagehelper.PageHelper;
import Dao.SystemDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SysytemSelectServlet", urlPatterns = "/sysytemSelectServlet")
public class SysytemSelectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        SystemDao dao = new SystemDao();
        SelectValue systemSelectValue = (SelectValue) session.getAttribute("systemSelectValue");
        if(systemSelectValue!=null){
        if (request.getParameter("typeName") != null) {
            systemSelectValue.setTypename(request.getParameter("typeName"));
            if (request.getParameter("value1") != null) {
                systemSelectValue.setValue1(request.getParameter("value1"));
            }
        }}else {
            systemSelectValue =new SelectValue();
            systemSelectValue.setTypename("SEQNAME");
            systemSelectValue.setValue1("");
            systemSelectValue.setValue2("");
            systemSelectValue.setPageNumber("1");
        }
        if (request.getParameter("pageNumber") != null) {
            systemSelectValue.setPageNumber(
                    String.valueOf(
                            Integer.parseInt(
                                    request.getParameter("pageNumber")
                            )
                    )
            );
            if (Integer.parseInt(systemSelectValue.getPageNumber()) < 0) {
                systemSelectValue.setPageNumber("0");
            }
            if (Integer.parseInt(systemSelectValue.getPageNumber()) > Integer.parseInt(systemSelectValue.getAllPageNumber())) {
                systemSelectValue.setPageNumber(systemSelectValue.getAllPageNumber());
            }
        }
            systemSelectValue.setAllPageNumber(String.valueOf(
                    (dao.selectSystemBySomething(systemSelectValue).size()+
                            (dao.selectSystemBySomething(systemSelectValue).size()%Integer.parseInt(systemSelectValue.getEachpage())!=0?Integer.parseInt(systemSelectValue.getEachpage()):0))/Integer.parseInt(systemSelectValue.getEachpage())
            ));PageHelper.startPage(Integer.parseInt(systemSelectValue.getPageNumber()), Integer.parseInt(systemSelectValue.getEachpage()));

            session.setAttribute("systemSelectValue", systemSelectValue);
            session.setAttribute("list", dao.selectSystemBySomething(systemSelectValue));
            response.sendRedirect("Detail/system%20parameter%20management.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
