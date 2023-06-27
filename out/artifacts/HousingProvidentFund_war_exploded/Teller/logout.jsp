<%@ page import="Bean.Company" %>
<%@ page import="jdk.nashorn.internal.objects.Global" %>
<%@ page import="Bean.User" %>
<%@ page import="Bean.Teller" %><%--
  Created by IntelliJ IDEA.
  User: 万爱华
  Date: 2023/6/25
  Time: 8:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link href="../layui/css/layui.css" rel="stylesheet">
</head>
<script src="../layui/layui.js"></script>
<%
    if(request.getAttribute("msg")!=null){
        out.print("<script>alert('"+request.getAttribute("msg")+"')</script>");
    }
%>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px" id="btn">
                <form class="layui-form layui-form-pane" action="../findTellerByNUMServlet">
                    <input name="id" type="hidden" value="1">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">柜员账号</label>
                            <div class="layui-input-inline">
                                <!--注意此处input标签里的id-->
                                <input class="layui-input" name="TELLERACCNUM" id="demoReload" autocomplete="off">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <!--注意此处button标签里的type属性-->
                            <button type="submit" class="layui-btn layui-btn-primary"  data-type="reload" lay-filter="data-search-btn"><i class="layui-icon"></i> 搜 索</button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>
    </div>
</div>
<div style="margin: 16px">
    <form action="../logoutTellerServlet" method="post" class="layui-form">
        <table class="layui-table" id="test" lay-filter="test">
            <%
                Teller teller=null;
                if(request.getAttribute("teller")!=null) {
                    teller = (Teller) request.getAttribute("teller");
                }
            %>
            <c:if test="${teller!=null}">
                <input type="hidden" name="TELLERACCNUM" value="<%=teller.getTELLERACCNUM()%>">
                <input type="hidden" name="TELLERNAME" value="<%=teller.getTELLERNAME()%>">
                <tr>
                    <td>柜员账号</td>
                    <td><%=teller.getTELLERACCNUM()%></td>
                </tr>
                <tr>
                    <td>柜员姓名</td>
                    <td><%=teller.getTELLERNAME()%></td>
                </tr>
                <tr>
                    <td>柜员身份证</td>
                    <td><%=teller.getTELLERCARD()%></td>
                </tr>
                <tr>
                    <td>柜员电话号</td>
                    <td><%=teller.getTELLERPHONE()%></td>
                </tr>
                <tr>
                    <td>备注</td>
                    <td><%=teller.getTELLERREMAKE()%></td>
                </tr>
                <tr>
                    <td colspan="2"><button type="submit" class="layui-btn layui-btn-fluid" ><i class="layui-icon layui-icon-auz"></i>  注  销 </button></td>
                </tr>
            </c:if>
        </table>
    </form>
</div>
</body>
</html>