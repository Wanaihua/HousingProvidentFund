<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<script src="jquery.min.js" type="text/javascript" ></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#userLogin").addClass("bor");
    		$("#userLogin").click(function(){
    			$("#userLogin").addClass("bor");
    			$("#rootLogin").removeClass("bor");
				$("#type").val("user");
    		});
    		$("#rootLogin").click(function(){
    			$("#userLogin").removeClass("bor");
    			$("#rootLogin").addClass("bor");
				$("#type").val("root");
    			
    		});
		});
	</script>
	<style type="text/css">
		.bor{
			border-bottom: 5px solid pink;
            padding-bottom: 10px;
            color: pink;
		}
		.txt2{
			margin-top: 400px;
			margin-right: -100px;
		}
	</style>
  </head>
  <%
	  if(request.getAttribute("msg")!=null){
		  %>
		  <script type="text/javascript">
		  		alert("不能为空或用户名或密码错误");
		  </script>
		  <%
	  }else if(request.getAttribute("rootMsg")!=null){
		  %>
		  <script type="text/javascript">
		  		alert("不能为空或用户名或密码错误");
				$("#userLogin").removeClass("bor");
				$("#rootLogin").addClass("bor");
		  </script>
		  <%
	  }
  %>
  <body>
  <div class="login">
	<div class="bg">
		<div class="body">
			<div class="img">
					<img src="img/img-01.png" alt="IMG">
			</div>
		    <form action="loginServlet" method="post" name="form1" id="form1" >
				<input type="hidden" name="type" id="type">
				<div class="title">
						<span class="pad1" id="userLogin">柜员登录</span>
						<span class="pad1" id="rootLogin">管理登录</span>
				</div>

				<div class="inputt">
					<input type="text" name="username" class="inputtt" placeholder="用户名"/>
				</div>

				<div class="inputt">
					<input type="password" name="userpass"  class="inputtt" placeholder="密码"/>
				</div>

				<div class="">
					<input type="submit" value='登录' class="btn">
				</div>
		    </form>
    	</div>
    </div>
   </div>
  </body>
</html>
