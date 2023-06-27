<%@ page import="Bean.Company" %>
<%@ page import="jdk.nashorn.internal.objects.Global" %><%--
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
                <form class="layui-form layui-form-pane" action="../findCompanyByNUMServlet">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">单位账号</label>
                            <div class="layui-input-inline">
                                <!--注意此处input标签里的id-->
                                <input type="hidden" name="id" value="1">
                                <input class="layui-input" name="UNITACCNUM" id="demoReload" autocomplete="off">
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
    <form action="../goUpdateCompanyServlet" method="post" class="layui-form">
        <input type="hidden" name="id" value="1">
    <table class="layui-table" id="test" lay-filter="test">
        <%
            Company company = null;
            if(request.getAttribute("company")!=null) {
                company = (Company) request.getAttribute("company");
            }
        %>
        <c:if test="${company!=null}">
            <input type="hidden" name="UNITACCNUM" value="<%=company.getUNITACCNUM()%>">
            <tr>
                <td>单位账号</td>
                <td><%=company.getUNITACCNUM()%></td>
            </tr>
            <tr>
                <td>单位名称</td>
                <td><%=company.getUNITACCNAME()%></td>
            </tr>
            <tr>
                <td>单位地址</td>
                <td><%=company.getUNITADDR()%></td>
            </tr>
            <tr>
                <td>组织机构代码</td>
                <td><%=company.getORGCODE()%></td>
            </tr>
            <tr>
                <td>单位类别</td>
                <td><%=company.getUNITCHAR()%></td>
            </tr>
            <tr>
                <td>企业类型</td>
                <td><%=company.getUNITKIND()%></td>
            </tr>
            <tr>
                <td>发薪日期</td>
                <td><%=company.getSALARYDATE()%>号</td>
            </tr>
            <tr>
                <td>单位联系人</td>
                <td><%=company.getUNITLINKMAN()%></td>
            </tr>
            <tr>
                <td>联系人电话</td>
                <td><%=company.getUNITPHONE()%></td>
            </tr>
            <tr>
                <td>经办人身份证号码</td>
                <td><%=company.getUNITAGENTPAPNO()%></td>
            </tr>
            <tr>
                <td>单位比例</td>
                <td><%=company.getUNITPROP()%></td>
            </tr>
            <tr>
                <td>个人比例</td>
                <td><%=company.getPERPROP()%></td>
            </tr>
            <tr>
                <td>最后汇缴月</td>
                <td><%=company.getLASTPAYDATE()%></td>
            </tr>
            <tr>
                <td colspan="2"><button type="submit" class="layui-btn layui-btn-fluid" ><i class="layui-icon layui-icon-auz"></i>  汇 缴 </button></td>
            </tr>
        </c:if>
    </table>
</form>
</div>
</body>
</html>