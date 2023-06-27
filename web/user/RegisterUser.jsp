<%@ page import="Bean.Company" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 万爱华
  Date: 2023/6/23
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="../layui/css/layui.css" rel="stylesheet">
    <style>
        .layui-form-label {
            width: 120px;
        }
        .layui-input-block {
            margin-left: 150px;
        }
    </style>
</head>
<script src="../layui/layui.js"></script>
<body>
<%
    if(request.getAttribute("msg")!=null){
        out.print("<script>alert('"+request.getAttribute("msg")+"')</script>");
    }
%>

<form class="layui-form" action="../userInAndUpServlet" method="post" style="margin-top: 0px;" >
    <input type="hidden" name="uid" value="1">
    <div class="layui-form-item">
        <label class="layui-form-label">单位公积金账号</label>
        <div class="layui-input-block">
            <input type="text" name="UNITACCNUM" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
            <input type="text" name="NAME" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">证件类型</label>
        <div class="layui-input-block">
            <select name="TYPE" lay-verify="required">
                <option value="1">身份证</option>
                <option value="2">军官证</option>
                <option value="3">护照</option>
                <option value="4">其他</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">证件号码</label>
        <div class="layui-input-block">
            <input type="text" name="NUM" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">缴存基数</label>
        <div class="layui-input-block">
            <input type="text" name="BASENUMBER" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">单位比例</label>
        <div class="layui-input-block">
            <input type="text" name="UNITPROP" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">个人比例</label>
        <div class="layui-input-block">
            <input type="text" name="INDIPROP" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">单位月应缴额</label>
        <div class="layui-input-block">
            <input type="text" name="UNITMONPAYSUM" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">个人月应缴额</label>
        <div class="layui-input-block">
            <input type="text" name="PERMONPAYSUM" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea name="REMARK" placeholder="请输入内容" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo" type="submit">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script>
    //Demo
    layui.use('form', function(){
        var form = layui.form;
    });
</script>


</body>
</html>
