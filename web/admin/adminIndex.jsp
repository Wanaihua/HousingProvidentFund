<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="Bean.Admin" %>
<html>
<head>
    <meta charset="utf-8">
    <title>住房公积金管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../layui/css/layui.css" rel="stylesheet">
</head>
<body>
<%
    Admin admin = (Admin) session.getAttribute("admin");
    int id= admin.getId();
%>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo layui-hide-xs layui-bg-black">Welcome <%=admin.getUsername()%></div>
        <!-- 头部区域（可配合layui 已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <!-- 移动端显示 -->
            <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-header-event="menuLeft">
                <i class="layui-icon layui-icon-spread-left"></i>
            </li>

        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item layui-hide layui-show-sm-inline-block">
                <a href="javascript:;">
                    <img src="https://unpkg.com/outeres@0.0.10/img/layui/icon-v2.png" class="layui-nav-img">
                    <%=admin.getUsername()%>
                </a>
                <dl class="layui-nav-child">
                    <dd><a id="update" onclick="update()" >Change the password</a></dd>
                    <dd><a href="../login.jsp">Sign out</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item" lay-header-event="menuRight" lay-unselect>
                <a href="javascript:;">
                    <i class="layui-icon layui-icon-more-vertical"></i>
                </a>
            </li>
        </ul>
    </div>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">单位管理</a>
                    <dl class="layui-nav-child">
                        <dd><a data-src="../companyShowServlet?cid=1">单位开户</a></dd>
                        <dd><a data-src="../company/showAllCompany.jsp">单位资料</a></dd>
                        <dd><a data-src="../company/Remittance.jsp">汇缴</a></dd>
                        <dd><a data-src="../company/SupplementaryPayment.jsp">补缴</a></dd>
                        <dd><a data-src="../company/logout.jsp">注销</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">个人管理</a>
                    <dl class="layui-nav-child">
                        <dd><a data-src="../user/RegisterUser.jsp">个人开户</a></dd>
                        <dd><a data-src="../user/showAllUser.jsp">个人资料</a></dd>
                        <dd><a data-src="../user/lock.jsp">封存</a></dd>
                        <dd><a data-src="../user/open.jsp">启封</a></dd>
                        <dd><a data-src="../user/extract.jsp">提取</a></dd>
                        <dd><a data-src="../user/logout.jsp">注销</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">柜员管理</a>
                    <dl class="layui-nav-child">
                        <dd><a data-src="../Teller/RegisterTeller.jsp">柜员注册</a></dd>
                        <dd><a data-src="../Teller/showAllTeller.jsp">柜员资料</a></dd>
                        <dd><a data-src="../Teller/logout.jsp">注销</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a data-src="../system/business.jsp">业务查询</a></li>
                <li class="layui-nav-item"><a data-src="../system/SystemParameters.jsp">系统参数</a></li>
            </ul>
        </div>
    </div>
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <blockquote class="layui-elem-quote layui-text">欢迎使用住房公积金管理系统！
            </blockquote>
            <div class="layui-card layui-panel">
                <div class="layui-card-body" id="body">
                    <iframe frameborder="0" scrolling="yes" style="width: 100%" src="" id="aa"></iframe>
                </div>
            </div>
            <br><br>
        </div>
    </div>
    <div class="layui-footer">
        <!-- 底部固定区域 -->

    </div>

</div>
<script src="../layui/layui.js"></script>
<script src="../jquery.min.js"></script>
<script>
    //JS
    layui.use(['element', 'layer', 'util'], function(){
        var element = layui.element;
        var layer = layui.layer;
        var util = layui.util;
        var $ = layui.$;
        //头部事件
        util.event('lay-header-event', {
            menuLeft: function(othis){ // 左侧菜单事件
                layer.msg('展开左侧菜单的操作', {icon: 0});
            },
            menuRight: function(){  // 右侧菜单事件
                layer.open({
                    type: 1
                    ,title: '更多'
                    ,content: '<div style="padding: 15px;">处理右侧面板的操作</div>'
                    ,area: ['260px', '100%']
                    ,offset: 'rt' //右上角
                    ,anim: 'slideLeft'
                    ,shadeClose: true
                    ,scrollbar: false
                });
            }
        });
    });
    function update() {
        layer.open({
            type: 2,
            title: 'Change the password',
            shadeClose: true,
            area: ['500px', '30%'],
            content: 'Update.jsp?id=<%=id%>'
        });
    };
    //注意：导航 依赖 element 模块，否则无法进行功能性操作
    layui.use('element', function(){
        var element = layui.element;
    });
    $(function(){
        $(".layui-nav-item a").on("click",function(){
            var address =$(this).attr("data-src");
            $("iframe").attr("src",address);
        });
        var frame = $("#aa");
        var frameheight = $(window).height()-250;
        console.log(frameheight);
        frame.css("height",frameheight);
    });
</script>


</script>
</body>
</html>