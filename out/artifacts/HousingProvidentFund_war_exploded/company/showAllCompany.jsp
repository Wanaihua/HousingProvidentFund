<%@ page import="Bean.Company" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 万爱华
  Date: 2023/6/23
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <title></title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="../layui/css/layui.css" rel="stylesheet">
</head>
<body>
<%
  List<Company> list = (List<Company>) request.getAttribute("company");
  int cid=2;
  if(request.getAttribute("msg")!=null){
    out.print("<script>alert('"+request.getAttribute("msg")+"')</script>");
  }
%>
<div style="padding: 16px;">
  <table class="layui-hide" id="test" lay-filter="test"></table>
</div>
<script type="text/html" id="toolbarDemo">
  <div class="layui-btn-container">
    <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
    <button class="layui-btn layui-btn-sm" lay-event="getData">获取当前页数据</button>
    <button class="layui-btn layui-btn-sm" lay-event="ExcelImport">EXCEL批量导入</button>
    <button class="layui-btn layui-btn-sm layui-bg-blue" id="reloadTest">
      重载测试
      <i class="layui-icon layui-icon-down layui-font-12"></i>
    </button>
    <button class="layui-btn layui-btn-sm layui-btn-primary" id="rowMode">
      <span>{{= d.lineStyle ? '多行' : '单行' }}模式</span>
      <i class="layui-icon layui-icon-down layui-font-12"></i>
    </button>
  </div>
</script>
<script type="text/html" id="cityTpl">
  <select id="demoCity1" class="layui-border" lay-ignore>
    <option value="1" {{= 1 == d.UNITCHAR ? 'selected' : ''  }}>企业</option>
    <option value="2" {{= 2 == d.UNITCHAR ? 'selected' : ''  }}>事业</option>
    <option value="3" {{= 3 == d.UNITCHAR ? 'selected' : ''  }}>机关</option>
    <option value="4" {{= 4 == d.UNITCHAR ? 'selected' : ''  }}>团体</option>
    <option value="5" {{= 5 == d.UNITCHAR ? 'selected' : ''  }}>其他</option>
  </select>
</script>

<script type="text/html" id="barDemo">
  <div class="layui-clear-space">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-xs" lay-event="more">
      更多
      <i class="layui-icon layui-icon-down"></i>
    </a>
  </div>
</script>
<script src="../layui/layui.js"></script>
<script>

  function findChar(UNITCHAR) {
    if(UNITCHAR==1) return "企业";
    if(UNITCHAR==2) return "事业";
    if(UNITCHAR==3) return "机关";
    if(UNITCHAR==4) return "团体";
    if(UNITCHAR==5) return "其他";
  }

  function findKind(UNITKIND) {
    if(UNITKIND==110) return"110 国有经济"
    if(UNITKIND==120) return"120 集体经济"
    if(UNITKIND==130) return"130 股份合作企业"
    if(UNITKIND==140) return"140 联营企业"
    if(UNITKIND==150) return"150 有限责任公司"
    if(UNITKIND==160) return"160 股份有限公司"
    if(UNITKIND==170) return"170 私营企业"
    if(UNITKIND==190) return"190 其他企业"
    if(UNITKIND==200) return"200 港、澳、台商投资企业"
    if(UNITKIND==300) return"300 外商投资企业"
    if(UNITKIND==900) return"900 其他"
  }

  layui.use(['table', 'dropdown'], function(){
    var table = layui.table;
    var dropdown = layui.dropdown;

    // 创建渲染实例
    table.render({
      elem: '#test'
      ,url:'../companyShowServlet?cid=2' // 此处为静态模拟数据，实际使用时需换成真实接口
      ,toolbar: '#toolbarDemo'
      ,defaultToolbar: ['filter', 'exports', 'print', {
        title: '提示'
        ,layEvent: 'LAYTABLE_TIPS'
        ,icon: 'layui-icon-tips'
      }]
      ,height: 'full-35' // 最大高度减去其他容器已占有的高度差
      ,css: [ // 重设当前表格样式
        '.layui-table-tool-temp{padding-right: 145px;}'
      ].join('')
      ,cellMinWidth: 80
      ,totalRow: true // 开启合计行
      ,page: {
        layout: ['prev', 'page', 'next', 'skip', 'count', 'limit']
        ,curr:1
        ,page:true
        ,groups:5
        ,limit:10
        ,first:true
        ,last:true
      }
      ,cols: [[
        {type: 'checkbox', fixed: 'left'}
        ,{field:'UNITACCNUM', fixed: 'left', width:120, title: 'ID', sort: true, totalRowText: '合计：'}
        ,{field:'UNITACCNAME', width:200, title: '用户'}
        ,{field:'UNITADDR', minwidth:260, title: '地址'}
        ,{field:'UNITLINKMAN', width:120, title: '单位联系人'}
        ,{field:'UNITPHONE', width:120, title: '联系电话'}
        ,{field:'ORGCODE', width:120, title: '组织机构代码'}
        ,{field:'UNITCHAR', width:115, title: '单位类别', minWidth:115, templet: '#cityTpl', exportTemplet: function(d, obj){
            // console.log(obj)
            // 处理该字段的导出数据
            var SelectData ={1:"企业",2:"事业",3:"机关",4:"团体",5:"其他"};
            var td = obj.td(this.field); // 获取当前 td
            return SelectData[td.find('select').val()];
          }}
        ,{fixed: 'right', title:'操作', width: 134, minWidth: 125, toolbar: '#barDemo'}
      ]]
      ,done: function(){
        var id = this.id;
        // 下拉按钮测试
        dropdown.render({
          elem: '#dropdownButton' // 可绑定在任意元素中，此处以上述按钮为例
          ,data: [{
            id: 'add',
            title: '添加'
          },{
            id: 'update',
            title: '编辑'
          },{
            id: 'delete',
            title: '删除'
          }]
          // 菜单被点击的事件
          ,click: function(obj){
            var checkStatus = table.checkStatus(id)
            var data = checkStatus.data; // 获取选中的数据
            switch(obj.id){
              case 'add':
                layer.open({
                  title: '添加',
                  type: 1,
                  area: ['80%','80%'],
                  content: '<div style="padding: 16px;">自定义表单元素</div>'
                });
                break;
              case 'update':
                if(data.length !== 1) return layer.msg('请选择一行');
                layer.open({
                  title: '编辑',
                  type: 1,
                  area: ['80%','80%'],
                  content: '<div style="padding: 16px;">自定义表单元素</div>'
                });
                break;
              case 'delete':
                if(data.length === 0){
                  return layer.msg('请选择一行');
                }
                layer.msg('delete event');
                break;
            }
          }
        });

        // 重载测试
        dropdown.render({
          elem: '#reloadTest' // 可绑定在任意元素中，此处以上述按钮为例
          ,data: [{
            id: 'reload',
            title: '重载'
          },{
            id: 'reload-deep',
            title: '重载 - 参数叠加'
          },{
            id: 'reloadData',
            title: '仅重载数据'
          },{
            id: 'reloadData-deep',
            title: '仅重载数据 - 参数叠加'
          }]
          // 菜单被点击的事件
          ,click: function(obj){
            switch(obj.id){
              case 'reload':
                table.reload('test', {
                  where: {
                    abc: '123456'
                  }
                });
                break;
              case 'reload-deep':
                table.reload('test', {
                  where: {
                    abc: 123
                    ,test: '新的 test1'
                  }
                }, true);
                break;
              case 'reloadData':
                // 数据重载 - 参数重置
                table.reloadData('test', {
                  where: {
                    abc: '123456'
                  }
                  ,scrollPos: 'fixed'  // 保持滚动条位置不变 - v2.7.3 新增
                  ,height: 2000 // 测试无效参数（即与数据无关的参数设置无效，此处以 height 设置无效为例）
                });
                break;
              case 'reloadData-deep':
                // 数据重载 - 参数叠加
                table.reloadData('test', {
                  where: {
                    abc: 123
                    ,test: '新的 test1'
                  }
                }, true);
                break;
            }
            layer.msg('可观察 Network 请求参数的变化');
          }
        });

        // 行模式
        dropdown.render({
          elem: '#rowMode'
          ,data: [{
            id: 'default-row',
            title: '单行模式（默认）'
          },{
            id: 'multi-row',
            title: '多行模式'
          }]
          // 菜单被点击的事件
          ,click: function(obj){
            var checkStatus = table.checkStatus(id)
            var data = checkStatus.data; // 获取选中的数据
            switch(obj.id){
              case 'default-row':
                table.reload('test', {
                  lineStyle: null // 恢复单行
                });
                layer.msg('已设为单行');
                break;
              case 'multi-row':
                table.reload('test', {
                  // 设置行样式，此处以设置多行高度为例。若为单行，则没必要设置改参数 - 注：v2.7.0 新增
                  lineStyle: 'height: 95px;'
                });
                layer.msg('即通过设置 lineStyle 参数可开启多行');
                break;
            }
          }
        });
      }
      ,error: function(res, msg){
        console.log(res, msg)
      }
    });
    // 工具栏事件
    table.on('toolbar(test)', function(obj){
      var id = obj.config.id;
      var checkStatus = table.checkStatus(id);
      var othis = lay(this);
      switch(obj.event){
        case 'getCheckData':
          var data = checkStatus.data;
          layer.alert(layui.util.escape(JSON.stringify(data)));
          break;
        case 'getData':
          var getData = table.getData(id);
          console.log(getData);
          layer.alert(layui.util.escape(JSON.stringify(getData)));
          break;
        case 'ExcelImport':
            layer.open({
                title: 'Excel导入',
                type: 1,
                area: ['30%','30%'],
                content: '<div style="padding: 16px;"><form class="layui-form" action="../companyInByExcelServlet" method="post"><div class="layui-form-block">请选择Excel文件<input type="file" class="layui-btn layui-btn-primary" id="filePath" name="CompanyfilePath" accept=".xls" onchange="file()"></div><div class="layui-input-block" style="margin-top: 20px"> <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button> <button type="reset" class="layui-btn layui-btn-primary">重置</button> </div></form></div>'
            });
            break;
        case 'LAYTABLE_TIPS':
          layer.alert('自定义工具栏图标按钮');
          break;
      };
    });
    // 触发单元格工具事件
    table.on('tool(test)', function(obj){ // 双击 toolDouble
      var data = obj.data; // 获得当前行数据
      // console.log(obj)
      if(obj.event === 'edit'){
        layer.open({
          title: '编辑 - id:'+ data.UNITACCNUM,
          type: 1,
          area: ['80%','80%'],
          content: '<div style="padding: 16px;"><form class="layui-form" action="../companyInAndUpServlet" method="post" style="margin-top: 0px;" >'
                  +'<input type="hidden" name="cid" value="2">'
                  +'<input type="hidden" name="UNITACCNUM" value="'+data.UNITACCNUM+'">'
                  +'<div class="layui-form-item"><label class="layui-form-label">单位名称</label><div class="layui-input-block"> <input type="text" name="UNITACCNAME" required  lay-verify="required" value="'+data.UNITACCNAME+'" autocomplete="off" class="layui-input"> </div></div>'
                  +'<div class="layui-form-item"><label class="layui-form-label">单位地址</label><div class="layui-input-block"> <input type="text" name="UNITADDR" required  lay-verify="required" value="'+data.UNITADDR+'" autocomplete="off" class="layui-input"> </div></div>'
                  +'<div class="layui-form-item"><label class="layui-form-label">组织机构代码</label> <div class="layui-input-block"> <input type="text" name="ORGCODE" required  lay-verify="required" value="'+data.ORGCODE+'" autocomplete="off" class="layui-input"> </div></div>'
                  +'<div class="layui-form-item"><label class="layui-form-label">单位类别</label> <div class="layui-input-block"> <select name="UNITCHAR" lay-verify="required" > <option value="1" >企业</option> <option value="2">事业</option> <option value="3">机关</option> <option value="4">团体</option> <option value="5">其他</option> </select> </div> </div>'
                  +'<div class="layui-form-item"><label class="layui-form-label">企业类型</label> <div class="layui-input-block"> <select name="UNITKIND" lay-verify="required"> <option value="110">110 国有经济</option> <option value="120">120 集体经济</option> <option value="130">130 股份合作企业</option> <option value="140">140 联营企业</option> <option value="150">150 有限责任公司</option> <option value="160">160 股份有限公司</option> <option value="170">170 私营企业</option> <option value="190">190 其他企业</option> <option value="200">200 港、澳、台商投资企业</option> <option value="300">300 外商投资企业</option> <option value="900">900 其他</option> </select> </div> </div>'
                  +'<div class="layui-form-item"><label class="layui-form-label">发薪日期</label> <div class="layui-input-block"> <select name="SALARYDATE" lay-verify="required"> <option value="1"  >1号</option> <option value="2"  >2号</option> <option value="3"  >3号</option> <option value="4"  >4号</option> <option value="5"  >5号</option> <option value="6"  >6号</option> <option value="7"  >7号</option> <option value="8"  >8号</option> <option value="9"  >9号</option> <option value="10"  >10号</option> <option value="11"  >11号</option> <option value="12"  >12号</option> <option value="13"  >13号</option> <option value="14"  >14号</option> <option value="15"  >15号</option> <option value="16"  >16号</option> <option value="17"  >17号</option> <option value="18"  >18号</option> <option value="19"  >19号</option> <option value="20"  >20号</option> <option value="21"  >21号</option> <option value="22"  >22号</option> <option value="23"  >23号</option> <option value="24"  >24号</option> <option value="25"  >25号</option> <option value="26"  >26号</option> <option value="27"  >27号</option> <option value="28"  >28号</option> </select> </div> </div>'
                  +'<div class="layui-form-item"><label class="layui-form-label">联系电话</label> <div class="layui-input-block"> <input type="text" name="UNITPHONE" required  lay-verify="required" value="'+data.UNITPHONE+'" " autocomplete="off" class="layui-input"> </div> </div>'
                  +'<div class="layui-form-item"><label class="layui-form-label">单位联系人</label> <div class="layui-input-block"> <input type="text" name="UNITLINKMAN" required  lay-verify="required" value="'+data.UNITLINKMAN+'" autocomplete="off" class="layui-input"> </div> </div>'
                  +'<div class="layui-form-item"><label class="layui-form-label">经办人身份证号码</label> <div class="layui-input-block"> <input type="text" name="UNITAGENTPAPNO" required  lay-verify="required" value="'+data.UNITAGENTPAPNO+'" autocomplete="off" class="layui-input"> </div> </div>'
                  +'<div class="layui-form-item"><label class="layui-form-label">账户状态</label> <div class="layui-input-block"> <input type="text" name="ACCSTATE" required  lay-verify="required" value="'+data.ACCSTATE+'" autocomplete="off" class="layui-input"> </div> </div>'
                  +'<div class="layui-form-item"><label class="layui-form-label">公积金余额</label> <div class="layui-input-block"> <input type="text" name="BALANCE" required  lay-verify="required" value="'+data.BALANCE+'" autocomplete="off" class="layui-input"> </div> </div>'
                  +'<div class="layui-form-item"><label class="layui-form-label">缴存基数</label> <div class="layui-input-block"> <input type="text" name="BASENUMBER" required  lay-verify="required" value="'+data.BASENUMBER+'" autocomplete="off" class="layui-input"> </div> </div>'
                  +'<div class="layui-form-item"><label class="layui-form-label">单位比例</label><div class="layui-input-block"> <input type="text" name="UNITPROP" required  lay-verify="required" value="'+data.UNITPROP+'" autocomplete="off" class="layui-input"> </div> </div>'
                  +'<div class="layui-form-item"><label class="layui-form-label">个人比例</label><div class="layui-input-block"> <input type="text" name="PERPROP" required  lay-verify="required" value="'+data.PERPROP+'" autocomplete="off" class="layui-input"> </div> </div>'
                  +'<div class="layui-form-item"><label class="layui-form-label">单位月应缴额</label><div class="layui-input-block"> <input type="text" name="UNITPAYSUM" required  lay-verify="required" value="'+data.UNITPAYSUM+'" autocomplete="off" class="layui-input"> </div></div>'
                  +'<div class="layui-form-item"><label class="layui-form-label">个人月应缴额</label><div class="layui-input-block"> <input type="text" name="PERPAYSUM" required  lay-verify="required" value="'+data.PERPAYSUM+'" autocomplete="off" class="layui-input"> </div></div>'
                  +'<div class="layui-form-item"><label class="layui-form-label">单位人数</label><div class="layui-input-block"> <input type="text" name="PERSNUM" required  lay-verify="required" value="'+data.PERSNUM+'" autocomplete="off" class="layui-input"> </div></div>'
                  +'<div class="layui-form-item"><label class="layui-form-label">最后汇缴月</label><div class="layui-input-block"> <input type="text" name="LASTPAYDATE" required  lay-verify="required" value="'+data.LASTPAYDATE+'" autocomplete="off" class="layui-input"> </div></div>'
                  +'<div class="layui-form-item"><label class="layui-form-label">公积金中心机构代码</label><div class="layui-input-block"> <input type="text" name="INSTCODE" required  lay-verify="required" value="'+data.INSTCODE+'" autocomplete="off" class="layui-input"> </div></div>'
                  +'<div class="layui-form-item"><label class="layui-form-label">柜员</label><div class="layui-input-block"> <input type="text" name="OP" required  lay-verify="required" value="'+data.OP+'" autocomplete="off" class="layui-input"> </div></div>'
                  +'<div class="layui-form-item"><label class="layui-form-label">建立日期</label><div class="layui-input-block"> <input type="text" name="CREATDATE" required  lay-verify="required" value="'+data.CREATDATE+'" autocomplete="off" class="layui-input"> </div></div>'
                  +'<div class="layui-form-item layui-form-text"> <label class="layui-form-label">备注</label> <div class="layui-input-block"> <textarea name="REMARK" placeholder="请输入内容" class="layui-textarea"></textarea> </div> </div>'
                  +'<div class="layui-form-item"><div class="layui-input-block"> <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button> <button type="reset" class="layui-btn layui-btn-primary">重置</button> </div> </div>'
                  +'</form></div>'
                  +'<script>'
                  +'layui.use("form", function(){var form = layui.form;form.render();});'
                  +'<'
                  +'/script>'
        });
      } else if(obj.event === 'more'){
        // 更多 - 下拉菜单
        dropdown.render({
          elem: this, // 触发事件的 DOM 对象
          show: true, // 外部事件触发即显示
          data: [{
            title: '查看',
            id: 'detail'
          },{
            title: '删除',
            id: 'del'
          }],
          click: function(menudata){
            if(menudata.id === 'detail'){
              layer.open({
                title: '查看 - id:'+ data.UNITACCNUM,
                type: 1,
                area: ['80%','80%'],
                content: '<div style="padding: 16px;"><table class="layui-table">  '
                        +'<thead> <tr><th>属性名</th> <th>属性值</th> </tr> </thead>'
                        +'<tbody><tr><td>单位名称</td> <td>'+data.UNITACCNAME+'</td> </tr>  <tr><td>单位地址</td><td>'+data.UNITADDR+'</td></tr>  <tr><td>组织机构代码</td> <td>'+data.ORGCODE+'</td> </tr> <tr><td>单位类别</td> <td>'+findChar(data.UNITCHAR)+'</td> </tr> <tr><td>企业类型</td> <td>'+findKind(data.UNITKIND)+'</td> </tr> <tr><td>发薪日期</td> <td>'+data.SALARYDATE+'日</td> </tr> <tr><td>联系电话</td> <td>'+data.UNITPHONE+'</td> </tr> <tr><td>单位联系人</td> <td>'+data.UNITLINKMAN+'</td> </tr> <tr><td>经办人身份证号码</td> <td>'+data.UNITAGENTPAPNO+'</td> </tr> <tr><td>账户状态</td> <td>'+data.ACCSTATE+'</td> </tr><tr><td>公积金余额</td> <td>'+data.BALANCE+'</td> </tr><tr><td>缴存基数</td> <td>'+data.BASENUMBER+'</td> </tr><tr><td>单位比例</td> <td>'+data.UNITPROP+'</td> </tr> <tr><td>个人比例</td> <td>'+data.PERPROP+'</td> </tr> <tr><td>单位月应缴额</td> <td>'+data.UNITPAYSUM+'</td> </tr> <tr><td>个人月应缴额</td> <td>'+data.PERPAYSUM+'</td> </tr><tr><td>单位人数</td> <td>'+data.PERSNUM+'</td> </tr><tr><td>最后汇缴月</td> <td>'+data.LASTPAYDATE+'</td> </tr><tr><td>公积金中心机构代码</td> <td>'+data.INSTCODE+'</td> </tr><tr><td>柜员</td> <td>'+data.OP+'</td> </tr><tr><td>建立日期</td> <td>'+data.CREATDATE+'</td> </tr><tr><td>备注</td> <td>'+data.REMARK+'</td> </tr> '
                        +'</tbody></table></div>'
              })
            } else if(menudata.id === 'del'){
              layer.confirm('真的删除行 [id: '+ data.UNITACCNUM +'] 么', function(index){
                obj.del(); // 删除对应行（tr）的DOM结构
                layer.close(index);
                // 向服务端发送删除指令
                window.location.href = "http://localhost:8080/companyDeleteServlet?UNITACCNUM="+data.UNITACCNUM;
              });
            }
          },
          align: 'right', // 右对齐弹出
          style: 'box-shadow: 1px 1px 10px rgb(0 0 0 / 12%);' // 设置额外样式
        })
      }
    });

    // 触发表格复选框选择
    table.on('checkbox(test)', function(obj){
      console.log(obj)
    });
    // 行单击事件
    table.on('row(test)', function(obj){
      //console.log(obj);
      //layer.closeAll('tips');
    });
    // 行双击事件
    table.on('rowDouble(test)', function(obj){
      console.log(obj);
    });
    // 单元格编辑事件
    table.on('edit(test)', function(obj){
      var field = obj.field; // 得到字段
      var value = obj.value; // 得到修改后的值
      var data = obj.data; // 得到所在行所有键值
      // 值的校验
      if(field === 'email'){
        if(!/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(obj.value)){
          layer.tips('输入的邮箱格式不正确，请重新编辑', this, {tips: 1});
          return obj.reedit(); // 重新编辑 -- v2.8.0 新增
        }
      }
      // 编辑后续操作，如提交更新请求，以完成真实的数据更新
      // …
      layer.msg('编辑成功', {icon: 1});

      // 其他更新操作
      var update = {};
      update[field] = value;
      obj.update(update);
    });
  });

</script>
</body>
</html>
