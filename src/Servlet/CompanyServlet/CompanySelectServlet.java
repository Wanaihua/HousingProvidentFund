package Servlet.CompanyServlet;

import Bean.SelectValue;
import com.github.pagehelper.PageHelper;
import Dao.CompanyDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CompanySelectServlet", urlPatterns = "/companySelectServlet")
public class CompanySelectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CompanyDao dao = new CompanyDao();
        SelectValue companySelectValue = (SelectValue) session.getAttribute("companySelectValue");
        if (companySelectValue != null) {
            if (request.getParameter("typeName") != null) {
                companySelectValue.setTypename(request.getParameter("typeName"));
                if (companySelectValue.getTypename().equals("UNITACCNUM") ||
                        companySelectValue.getTypename().equals("UNITACCNAME") ||
                        companySelectValue.getTypename().equals("UNITADDR") ||
                        companySelectValue.getTypename().equals("ORGCODE") ||
                        companySelectValue.getTypename().equals("UNITCHAR") ||
                        companySelectValue.getTypename().equals("UNITKIND") ||
                        companySelectValue.getTypename().equals("SALARYDATE") ||
                        companySelectValue.getTypename().equals("UNITPHONE") ||
                        companySelectValue.getTypename().equals("UNITLINKMAN") ||
                        companySelectValue.getTypename().equals("UNITAGENTPAPNO") ||
                        companySelectValue.getTypename().equals("ACCSTATE") ||
                        companySelectValue.getTypename().equals("REMARK")) {
                    String t = "text1";
                    if (companySelectValue.getTypename().equals("UNITCHAR")) {
                        t = "text2";
                    }
                    if (companySelectValue.getTypename().equals("UNITKIND")) {
                        t = "text3";
                    }
                    if (companySelectValue.getTypename().equals("ACCSTATE")) {
                        t = "text4";
                    }
                    companySelectValue.setValue1(new String(request.getParameter(t).getBytes("iso-8859-1"), "utf-8"));

                } else if (companySelectValue.getTypename().equals("LASTPAYDATE") || companySelectValue.getTypename().equals("CREATDATE")) {
                    companySelectValue.setValue1(new String(request.getParameter("date1").getBytes("iso-8859-1"), "utf-8"));
                    companySelectValue.setValue2(new String(request.getParameter("date2").getBytes("iso-8859-1"), "utf-8"));
                } else {
                    companySelectValue.setValue1(new String(request.getParameter("double1").getBytes("iso-8859-1"), "utf-8"));
                    companySelectValue.setValue2(new String(request.getParameter("double2").getBytes("iso-8859-1"), "utf-8"));
                }
            }
        } else {
            companySelectValue = new SelectValue();
            companySelectValue.setTypename("UNITACCNUM");
            companySelectValue.setValue1("");
            companySelectValue.setValue2("");
            companySelectValue.setPageNumber("1");
        }
        if (request.getParameter("pageNumber") != null) {
            companySelectValue.setPageNumber(
                    String.valueOf(
                            Integer.parseInt(
                                    request.getParameter("pageNumber")
                            )
                    )
            );
            if (Integer.parseInt(companySelectValue.getPageNumber()) < 0) {
                companySelectValue.setPageNumber("0");
            }
            if (Integer.parseInt(companySelectValue.getPageNumber()) > Integer.parseInt(companySelectValue.getAllPageNumber())) {
                companySelectValue.setPageNumber(companySelectValue.getAllPageNumber());
            }
        }
        if (companySelectValue.getTypename().equals("UNITACCNUM") || companySelectValue.getTypename().equals("UNITACCNAME") || companySelectValue.getTypename().equals("UNITADDR") || companySelectValue.getTypename().equals("ORGCODE") || companySelectValue.getTypename().equals("UNITCHAR") || companySelectValue.getTypename().equals("UNITKIND") || companySelectValue.getTypename().equals("SALARYDATE") || companySelectValue.getTypename().equals("UNITPHONE") || companySelectValue.getTypename().equals("UNITLINKMAN") || companySelectValue.getTypename().equals("UNITAGENTPAPNO") || companySelectValue.getTypename().equals("ACCSTATE") || companySelectValue.getTypename().equals("REMARK")) {
            companySelectValue.setAllPageNumber(String.valueOf(
                    (dao.selectCompanyByLike(companySelectValue).size() +
                            (dao.selectCompanyByLike(companySelectValue).size() % Integer.parseInt(companySelectValue.getEachpage()) != 0 ? Integer.parseInt(companySelectValue.getEachpage()) : 0)) / Integer.parseInt(companySelectValue.getEachpage())
            ));
            PageHelper.startPage(Integer.parseInt(companySelectValue.getPageNumber()), Integer.parseInt(companySelectValue.getEachpage()));

            session.setAttribute("companySelectValue", companySelectValue);
            session.setAttribute("companylist", dao.selectCompanyByLike(companySelectValue));
        } else {
            companySelectValue.setAllPageNumber(String.valueOf(
                    (dao.selectCompanyByBetween(companySelectValue).size() +
                            (dao.selectCompanyByBetween(companySelectValue).size() % Integer.parseInt(companySelectValue.getEachpage()) != 0 ? Integer.parseInt(companySelectValue.getEachpage()) : 0)) / Integer.parseInt(companySelectValue.getEachpage())
            ));
            PageHelper.startPage(Integer.parseInt(companySelectValue.getPageNumber()), Integer.parseInt(companySelectValue.getEachpage()));

            session.setAttribute("companySelectValue", companySelectValue);
            session.setAttribute("companylist", dao.selectCompanyByBetween(companySelectValue));
        }

        response.sendRedirect("Detail/all company information.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
