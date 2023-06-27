<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="../layui/css/layui.css" rel="stylesheet">
</head>
<body>
<%
    int id=Integer.parseInt(request.getParameter("id"));
    if(request.getAttribute("msg")!=null){
        out.print("<script>alert('"+request.getAttribute("msg")+"')</script>");
    }
%>
<div class="layui-input-block layui-form" lay-filter="updateClass" style="margin-top: 30px">
    <form class="layui-form" id="form1" action="../adminUpdateServlet" lay-filter="example">
        <input name="id" value="<%=id %>" type="hidden">
        <div class="layui-col-md4">
            <div class="layui-input-wrap">
                <div class="layui-input-prefix layui-input-split">
                    <i class="layui-icon layui-icon-password"></i>
                </div>
                <input type="password" placeholder="旧密码" class="layui-input" id="password" name="oldPassword" style="width: 300px;">
            </div>
        </div>
        <div class="layui-col-md4">
            <div class="layui-input-wrap">
                <div class="layui-input-prefix layui-input-split">
                    <i class="layui-icon layui-icon-password"></i>
                </div>
                <input type="password" placeholder="新密码" class="layui-input" id="newPassword1" name="newPassword1" style="width: 300px;">
            </div>
        </div>
        <div class="layui-col-md4">
            <div class="layui-input-wrap">
                <div class="layui-input-prefix layui-input-split">
                    <i class="layui-icon layui-icon-password"></i>
                </div>
                <input type="password" placeholder="核实密码" class="layui-input" id="newPassword2" name="newPassword2" style="width: 300px;">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" type="button" id="but">确认修改</button>
            </div>
        </div>
    </form>
    <script src="../jquery.min.js"></script>
    <script type="text/javascript">
        $("#but").click(function () {
            var password=$("#password").val();
            var newPassword1=$("#newPassword1").val();
            var newPassword2=$("#newPassword2").val();
            if(password==""||newPassword1==""||newPassword2==""){
                alert("密码不能为空");
                return false;
            }else if(newPassword1!=newPassword2){
                alert("两次密码不一致");
                return false;
            }else{
                document.getElementById("form1").submit();
            }
        })
    </script>
</div>
</body>
</html>