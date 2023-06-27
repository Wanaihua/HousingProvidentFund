package Servlet.UserServlet;

import Bean.Company;
import Bean.SelectValue;
import com.github.pagehelper.PageHelper;
import Dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserSelectServlet", urlPatterns = "/userSelectServlet")
public class UserSelectServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDao dao = new UserDao();
        Company company=(Company)session.getAttribute("company");
        SelectValue userSelectValue = null;
        if (session.getAttribute("userSelectValue") != null) {
            userSelectValue = (SelectValue) session.getAttribute("userSelectValue");
            if (request.getParameter("typeName") != null) {
                userSelectValue.setTypename(request.getParameter("typeName"));
                if (userSelectValue.getTypename().equals("ACCNUM") ||
                        userSelectValue.getTypename().equals("UNITACCNUM") ||
                        userSelectValue.getTypename().equals("NAME") ||
                        userSelectValue.getTypename().equals("TYPE") ||
                        userSelectValue.getTypename().equals("NUM") ||
                        userSelectValue.getTypename().equals("PERACCSTATE") ||
                        userSelectValue.getTypename().equals("INSTCODE") ||
                        userSelectValue.getTypename().equals("OP") ||
                        userSelectValue.getTypename().equals("REMARK")) {
                    String t = "text1";
                    if (userSelectValue.getTypename().equals("UNITCHAR")) {
                        t = "text2";
                    }
                    if (userSelectValue.getTypename().equals("ACCSTATE")) {
                        t = "text3";
                    }
                    userSelectValue.setValue1(new String(request.getParameter(t).getBytes("iso-8859-1"), "utf-8"));

                } else if (userSelectValue.getTypename().equals("LASTPAYDATE") || userSelectValue.getTypename().equals("OPENDATE")) {
                    userSelectValue.setValue1(new String(request.getParameter("date1").getBytes("iso-8859-1"), "utf-8"));
                    userSelectValue.setValue2(new String(request.getParameter("date2").getBytes("iso-8859-1"), "utf-8"));
                } else {
                    userSelectValue.setValue1(new String(request.getParameter("double1").getBytes("iso-8859-1"), "utf-8"));
                    userSelectValue.setValue2(new String(request.getParameter("double2").getBytes("iso-8859-1"), "utf-8"));
                }
            }
        } else {
            userSelectValue = new SelectValue();
            userSelectValue.setTypename("ACCNUM");
            userSelectValue.setValue1("");
            userSelectValue.setValue2("");
            userSelectValue.setPageNumber("1");
        }
        if (request.getParameter("pageNumber") != null) {
            userSelectValue.setPageNumber(
                    String.valueOf(
                            Integer.parseInt(
                                    request.getParameter("pageNumber")
                            )
                    )
            );
            if (Integer.parseInt(userSelectValue.getPageNumber()) < 0) {
                userSelectValue.setPageNumber("0");
            }
            if (Integer.parseInt(userSelectValue.getPageNumber()) > Integer.parseInt(userSelectValue.getAllPageNumber())) {
                userSelectValue.setPageNumber(userSelectValue.getAllPageNumber());
            }
        }

        if (userSelectValue.getTypename().equals("ACCNUM") ||
                userSelectValue.getTypename().equals("UNITACCNUM") ||
                userSelectValue.getTypename().equals("NAME") ||
                userSelectValue.getTypename().equals("TYPE") ||
                userSelectValue.getTypename().equals("NUM") ||
                userSelectValue.getTypename().equals("PERACCSTATE") ||
                userSelectValue.getTypename().equals("INSTCODE") ||
                userSelectValue.getTypename().equals("OP") ||
                userSelectValue.getTypename().equals("REMARK")) {
                userSelectValue.setAllPageNumber(String.valueOf(
                    (dao.selectUserByLike(userSelectValue,company).size() +
                            (dao.selectUserByLike(userSelectValue,company).size() % Integer.parseInt(userSelectValue.getEachpage()) != 0 ? Integer.parseInt(userSelectValue.getEachpage()) : 0)) / Integer.parseInt(userSelectValue.getEachpage())
            ));
            PageHelper.startPage(Integer.parseInt(userSelectValue.getPageNumber()), Integer.parseInt(userSelectValue.getEachpage()));

            session.setAttribute("userSelectValue", userSelectValue);
            session.setAttribute("userlist", dao.selectUserByLike(userSelectValue,company));
        } else {
            userSelectValue.setAllPageNumber(String.valueOf(
                    (dao.selectUserByBetween(userSelectValue,company).size() +
                            (dao.selectUserByBetween(userSelectValue,company).size() % Integer.parseInt(userSelectValue.getEachpage()) != 0 ? Integer.parseInt(userSelectValue.getEachpage()) : 0)) / Integer.parseInt(userSelectValue.getEachpage())
            ));
            PageHelper.startPage(Integer.parseInt(userSelectValue.getPageNumber()), Integer.parseInt(userSelectValue.getEachpage()));

            session.setAttribute("userSelectValue", userSelectValue);
            session.setAttribute("userlist", dao.selectUserByBetween(userSelectValue,company));
        }
        response.sendRedirect("Detail/all personal information.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
