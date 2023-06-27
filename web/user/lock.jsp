<%@ page import="Bean.Company" %>
<%@ page import="jdk.nashorn.internal.objects.Global" %>
<%@ page import="Bean.User" %><%--
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
                <form class="layui-form layui-form-pane" action="../findUserByNUMServlet">
                    <input name="id" type="hidden" value="2">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">个人账号</label>
                            <div class="layui-input-inline">
                                <!--注意此处input标签里的id-->
                                <input class="layui-input" name="ACCNUM" id="demoReload" autocomplete="off">
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
    <form action="../lockUserServlet" method="post" class="layui-form">
        <table class="layui-table" id="test" lay-filter="test">
            <%
                User user=null;
                if(request.getAttribute("user")!=null) {
                    user = (User) request.getAttribute("user");
                }
            %>
            <c:if test="${user!=null}">
                <input type="hidden" name="ACCNUM" value="<%=user.getACCNUM()%>">
                <tr>
                    <td>个人公积金账号</td>
                    <td><%=user.getACCNUM()%></td>
                </tr>
                <tr>
                    <td>单位公积金账号</td>
                    <td><%=user.getUNITACCNUM()%></td>
                </tr>
                <tr>
                    <td>开户日期</td>
                    <td><%=user.getOPENDATE()%></td>
                </tr>
                <tr>
                    <td>用户名</td>
                    <td><%=user.getNAME()%></td>
                </tr>
                <tr>
                    <td>证件号码</td>
                    <td><%=user.getNUM()%></td>
                </tr>
                <tr>
                    <td>公积金余额</td>
                    <td><%=user.getBALANCE()%></td>
                </tr>
                <tr>
                    <td>缴存基数</td>
                    <td><%=user.getBASENUMBER()%>号</td>
                </tr>
                <tr>
                    <td>最后汇缴月</td>
                    <td><%=user.getLASTPAYDATE()%></td>
                </tr>
                <tr>
                    <td>单位月应缴额</td>
                    <td><%=user.getUNITMONPAYSUM()%></td>
                </tr>
                <tr>
                    <td>个人月应缴额</td>
                    <td><%=user.getPERMONPAYSUM()%></td>
                </tr>
                <tr>
                    <td>单位比例</td>
                    <td><%=user.getUNITPROP()%></td>
                </tr>
                <tr>
                    <td>个人比例</td>
                    <td><%=user.getINDIPROP()%></td>
                </tr>
                <tr>
                    <td>本年汇补缴额</td>
                    <td><%=user.getYPAYAMT()%></td>
                </tr>
                <tr>
                    <td>年提取额</td>
                    <td><%=user.getYDRAWAMT()%></td>
                </tr>
                <tr>
                    <td>年度结息</td>
                    <td><%=user.getYINTERESTBAL()%></td>
                </tr>
                <tr>
                    <td>公积金中心机构代码</td>
                    <td><%=user.getINSTCODE()%></td>
                </tr>
                <tr>
                    <td>柜员</td>
                    <td><%=user.getOP()%></td>
                </tr>
                <tr>
                    <td>备注</td>
                    <td><%=user.getREMARK()%></td>
                </tr>
                <tr>
                    <td colspan="2"><button type="submit" class="layui-btn layui-btn-fluid" ><i class="layui-icon layui-icon-auz"></i>  封  存 </button></td>
                </tr>
            </c:if>
        </table>
    </form>
</div>
</body>
</html>