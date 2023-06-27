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
<%--        <button class="layui-btn layui-btn-sm" lay-event="ExcelImport">EXCEL批量导入</button>--%>
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
        <option value="UNITACCNUM" {{= 'UNITACCNUM' == d.SEQNAME ? 'selected' : ''  }}>UNITACCNUM</option>
        <option value="PERACCNUM" {{= 'PERACCNUM' == d.SEQNAME ? 'selected' : ''  }}>PERACCNUM</option>
    </select>
</script>

<script type="text/html" id="barDemo">
    <div class="layui-clear-space">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
<%--        <a class="layui-btn layui-btn-xs" lay-event="more">--%>
<%--            更多--%>
<%--            <i class="layui-icon layui-icon-down"></i>--%>
<%--        </a>--%>
    </div>
</script>
<script src="../layui/layui.js"></script>
<script>

    function findChar(UNITCHAR) {
        if(UNITCHAR=='UNITACCNUM') return "UNITACCNUM";
        if(UNITCHAR=='PERACCNUM') return "PERACCNUM";
    }


    layui.use(['table', 'dropdown'], function(){
        var table = layui.table;
        var dropdown = layui.dropdown;

        // 创建渲染实例
        table.render({
            elem: '#test'
            ,url:'../systemShowServlet' // 此处为静态模拟数据，实际使用时需换成真实接口
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
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'SEQNAME', fixed: 'left', width:200, title: '键值信息', sort: true, totalRowText: '合计：'}
                ,{field:'SEQ', width:120, title: '当前序号', sort: true}
                ,{field:'MAXSEQ', width:120, title: '最大序号'}
                ,{field:'DESC', minwidth:260, title: '描述'}
                ,{field:'FREEUSE1', width:120, title: '备用1'}
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
                        content: '<div style="padding: 16px;"><form class="layui-form" action="../SystemInByExcelServlet" method="post"><div class="layui-form-block">请选择Excel文件<input type="file" class="layui-btn layui-btn-primary" id="filePath" name="CompanyfilePath" accept=".xls" onchange="file()"></div><div class="layui-input-block" style="margin-top: 20px"> <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button> <button type="reset" class="layui-btn layui-btn-primary">重置</button> </div></form></div>'
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
                    title: '编辑 - :'+data.SEQNAME,
                    type: 1,
                    area: ['80%','80%'],
                    content: '<div style="padding: 16px;"><form class="layui-form" action="../SystemInAndUpServlet" method="post" style="margin-top: 0px;" >'
                        +'<input type="hidden" name="cid" value="2">'
                        +'<div class="layui-form-item"><label class="layui-form-label">键值信息</label><div class="layui-input-block"> <input type="text" name="SEQNAME" required  lay-verify="required" value="'+data.SEQNAME+'" autocomplete="off" class="layui-input"> </div></div>'
                        +'<div class="layui-form-item"><label class="layui-form-label">当前序号</label><div class="layui-input-block"> <input type="text" name="SEQ" required  lay-verify="required" value="'+data.SEQ+'" autocomplete="off" class="layui-input"> </div></div>'
                        +'<div class="layui-form-item"><label class="layui-form-label">最大序号</label><div class="layui-input-block"> <input type="text" name="MAXSEQ" required  lay-verify="required" value="'+data.MAXSEQ+'" autocomplete="off" class="layui-input"> </div></div>'
                        +'<div class="layui-form-item"><label class="layui-form-label">描述</label><div class="layui-input-block"> <input type="text" name="DESC" required  lay-verify="required" value="'+data.DESC+'" autocomplete="off" class="layui-input"> </div></div>'
                        +'<div class="layui-form-item"><label class="layui-form-label">备用1</label><div class="layui-input-block"> <input type="text" name="FREEUSE1" required  lay-verify="required" value="'+data.FREEUSE1+'" autocomplete="off" class="layui-input"> </div></div>'
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
                            })
                        } else if(menudata.id === 'del'){
                            layer.confirm('真的删除行 [id: '+ data.ACCNUM +'] 么', function(index){
                                obj.del(); // 删除对应行（tr）的DOM结构
                                layer.close(index);
                                // 向服务端发送删除指令
                                window.location.href = "http://localhost:8080/userDeleteServlet?ACCNUM="+data.ACCNUM;
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
