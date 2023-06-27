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
    String cid = request.getAttribute("cid").toString();
    Company company=(Company)session.getAttribute("company");
    if(request.getAttribute("msg")!=null){
        out.print("<script>alert('"+request.getAttribute("msg")+"')</script>");
    }
%>

<form class="layui-form" action="../companyInAndUpServlet" method="post" style="margin-top: 0px;" >
    <input type="hidden" name="cid" value="<%=cid%>">
    <div class="layui-form-item">
        <label class="layui-form-label">单位名称</label>
        <div class="layui-input-block">
            <input type="text" name="UNITACCNAME" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">单位地址</label>
        <div class="layui-input-block">
            <input type="text" name="UNITADDR" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">组织机构代码</label>
        <div class="layui-input-block">
            <input type="text" name="ORGCODE" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">单位类别</label>
        <div class="layui-input-block">
            <select name="UNITCHAR" lay-verify="required">
                <option value="1">企业</option>
                <option value="2">事业</option>
                <option value="3">机关</option>
                <option value="4">团体</option>
                <option value="5">其他</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">企业类型</label>
        <div class="layui-input-block">
            <select name="UNITKIND" lay-verify="required">
                <option value="110">110 国有经济</option>
                <option value="120">120 集体经济</option>
                <option value="130">130 股份合作企业</option>
                <option value="140">140 联营企业</option>
                <option value="150">150 有限责任公司</option>
                <option value="160">160 股份有限公司</option>
                <option value="170">170 私营企业</option>
                <option value="190">190 其他企业</option>
                <option value="200">200 港、澳、台商投资企业</option>
                <option value="300">300 外商投资企业</option>
                <option value="900">900 其他</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">发薪日期</label>
        <div class="layui-input-block">
            <select name="SALARYDATE" lay-verify="required">
                <option value="1"  >1号</option>
                <option value="2"  >2号</option>
                <option value="3"  >3号</option>
                <option value="4"  >4号</option>
                <option value="5"  >5号</option>
                <option value="6"  >6号</option>
                <option value="7"  >7号</option>
                <option value="8"  >8号</option>
                <option value="9"  >9号</option>
                <option value="10"  >10号</option>
                <option value="11"  >11号</option>
                <option value="12"  >12号</option>
                <option value="13"  >13号</option>
                <option value="14"  >14号</option>
                <option value="15"  >15号</option>
                <option value="16"  >16号</option>
                <option value="17"  >17号</option>
                <option value="18"  >18号</option>
                <option value="19"  >19号</option>
                <option value="20"  >20号</option>
                <option value="21"  >21号</option>
                <option value="22"  >22号</option>
                <option value="23"  >23号</option>
                <option value="24"  >24号</option>
                <option value="25"  >25号</option>
                <option value="26"  >26号</option>
                <option value="27"  >27号</option>
                <option value="28"  >28号</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">联系电话</label>
        <div class="layui-input-block">
            <input type="text" name="UNITPHONE" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">单位联系人</label>
        <div class="layui-input-block">
            <input type="text" name="UNITLINKMAN" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">经办人身份证号码</label>
        <div class="layui-input-block">
            <input type="text" name="UNITAGENTPAPNO" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
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
            <input type="text" name="PERPROP" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
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
