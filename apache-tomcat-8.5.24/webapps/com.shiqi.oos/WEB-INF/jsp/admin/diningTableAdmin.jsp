<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>桌位管理</title>
<!-- 导入easyui类库 -->
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>

<!-- 自定义js -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/shiqi.js"></script>

<script type="text/javascript">

	$(function(){
		$('#datagrid').datagrid({
			title:'购物车',
			width:500,
			height:250,
			remoteSort:false,
			nowrap:false,
			fitColumns:true,
			pagination:true,
			fit:true,
			url:'${pageContext.request.contextPath }/dining/findEasyUI',
			columns:[[
				{field:'selected',checkbox:'true'},
				{field:'id',title:'桌号',width:10,sortable:'true',
					formatter: function(value,row,index){
						return row.id;
				},sorter:function(a,b){  
					return parseInt(a) > parseInt(b) ? 1 : -1; 
				}},
				{field:'renshu',title:'最大人数',width:10,
					formatter: function(value,row,index){
						return row.renshu;
				}},
				{field:'status',title:'状态',width:10,
					formatter: function(value,row,index){
						//状态，1 使用中  0 空闲
						switch (row.status) {
						case "0":
							{
								return "空闲";
							}
						case "1":
							{
								return "使用中";
							}
						default:
							break;
						}
				}}
			]],
			toolbar:'#tb',
			onDblClickRow:function(index,data){
				$("#status").val(data.status);
				$("#zhuohao").val(data.id);
				$("#diningTableForm").form("load",data);
				$('#editDiningTable').window('open');
			}
		});
		
		$(document).keydown(function(event){
			if(event.keyCode==13){
				search();
			}
		});
		
	});

	//查询餐桌
	function search()
	{
		var searchKey = $("#searchKey").val();
		var status = $("#search-status").val();
		var url = "${pageContext.request.contextPath }/dining/search?searchKey=" + searchKey + "&status="
		
		if (status == "0" || status == "1") {
			url += status;
		}
		
		$('#datagrid').datagrid({
			url:url
		});
		
	}
	
	//重置查询条件
	function resetSearch()
	{
		$("#searchKey").val("");
		$("#search-status").val("");
		$('#datagrid').datagrid({
			url:"${pageContext.request.contextPath }/dining/findEasyUI"
		});
	}
	
	//删除餐桌
	function deleteDiningTable()
	{
		var rows = $('#datagrid').datagrid("getSelections");
		
		if (rows == null || rows.length == 0) {
			$.messager.confirm("提示","请选择要删除的餐桌",function(data){
				
			});
		}else{
			var ids = "";

			$.each( rows, function(index, value){
				  ids = ids + value.id + ",";
			});
			
			ids = ids.substring(0,ids.length - 1);
			
			$.messager.confirm("提示","是否确定删除" + ids + "餐桌",function(r){
				if(r)
				{
					$.post("${pageContext.request.contextPath }/dining/deleteByIds",{"ids":ids},function(data){
						alert(data.message);
						
						if (data.success) {
							location.reload();
						}
					});
				}
			});

		}
	}
	
	//修改餐桌
	function editDiningTable()
	{
		var rows = $('#datagrid').datagrid("getSelections");
		
		if (rows == null || rows.length == 0 || rows.length > 1) {
			$.messager.confirm("提示","请选择一个要修改的餐桌",function(data){
				
			});
		}else{
			$("#status").val(rows[0].status);
			$("#zhuohao").val(rows[0].id);
			$("#diningTableForm").form("load",rows[0]);
			$('#editDiningTable').window('open');
		}
		
	}
	
	//修改餐桌提交
	function edit()
	{
		var zhuohao = $("#zhuohao").val();
		var maxNum = $("#maxNum").val();
		var status = $("#status").val();
		
		$.post("${pageContext.request.contextPath }/dining/editById",{"zhuohao":zhuohao,"renshu":maxNum,"status":status},function(data){
			alert(data.message);
			if (data.success) {
				location.reload();
			}
		});
	}
	
	//关闭
	function closeEditWindow()
	{
		$('#editDiningTable').window('close');
	}
	
	//关闭
	function closeAddWindow()
	{
		$('#addDiningTable').window('close');
	}
	
	//添加餐桌
	function addDiningTable()
	{
		$("#addDiningTableForm").form("clear");
		$("#add-status").val("0");
		$('#addDiningTable').window('open');
	}
	
	//添加餐桌提交
	function add()
	{
		if ($("#addDiningTableForm").form("validate"))
		{
			var json = $("#addDiningTableForm").serialize();
			
			$.post("${pageContext.request.contextPath }/dining/addDiningTable",json,function(data){
				alert(data.message);
				if (data.success == "true") {
					location.reload();
				}
			});
		}
		
	}
	
</script>

</head>
<body>
		<table id="datagrid"></table>
		
		<div id="tb">
			<table style="height: 100%;width: 500px;">
				<tr>
					<td style="width:50px;text-align: right;">查询:</td>
					<td style="width:50px;text-align: right;">
						桌号:
					</td>
					<td style="width:200px;text-align: right;">
						<input id="searchKey" name="searchKey" style="width:100%;" />
					</td>
					<td style="width:50px;text-align: right;">
						状态:
					</td>
					<td style="width:80px;text-align: right;">
						<select id="search-status" name="status">
								<option value="2">不限</option>
                     			<option value="0">空闲</option>
                     			<option value="1">使用中</option>
	                 	</select>
					</td>
					<td style="width:70px;text-align: right;">
						<input type="button" onclick="search();" value="查询" />
					</td>
					<td style="width:70px;text-align: right;">
						<input type="button" onclick="resetSearch();" value="重置" />
					</td>
				</tr>
			</table>
			<a href="javascript:addDiningTable();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
			<a href="javascript:editDiningTable();" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
			<a href="javascript:deleteDiningTable();" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
		</div>
		
	<!--修改餐桌窗口-->
    <div id="editDiningTable" class="easyui-window" title="修改餐桌" collapsible="false" minimizable="false" modal="true" closed="true" resizable="false"
        maximizable="false" icon="icon-save"  style="width: 210px; height: 200px; padding: 5px;
        background: #fafafa">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            		<form id="diningTableForm">
		                <table cellpadding=3>
		                		<tr>
		                        <td>桌号：</td>
		                        <td><input id="zhuohao" disabled="disabled" name="zhuohao" style="width:60px;" /></td>
		                    </tr>
		                    <tr>
		                        <td>最大人数：</td>
		                        <td>
		                        		<input id="maxNum" name="renshu" style="width:60px;" class="easyui-numberbox" data-options="required:true,min:0,max:20"></input>  
		                        </td>
		                    </tr>
		                    <tr>
		                        <td>状态：</td>
		                        <td>
		                        		<select id="status" name="status">
		                        			<option value="0">空闲</option>
		                        			<option value="1">使用中</option>
		                        		</select>
							   </td>
		                    </tr>
	    	            </table>
    	            </form>
            </div>
            <div region="south" border="false" style="text-align: right; height: 50px; line-height: 30px;">
                <a class="easyui-linkbutton" icon="icon-ok" href="javascript:edit();" >确定</a> 
                <a class="easyui-linkbutton" icon="icon-cancel" href="javascript:closeEditWindow();">取消</a>
            </div>
        </div>
    </div>
    
    <!--添加餐桌窗口-->
    <div id="addDiningTable" class="easyui-window" title="添加餐桌" collapsible="false" minimizable="false" modal="true" closed="true" resizable="false"
        maximizable="false" icon="icon-save"  style="width: 210px; height: 200px; padding: 5px;
        background: #fafafa">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            		<form id="addDiningTableForm">
		                <table cellpadding=3>
		                		<tr>
		                        <td>桌号：</td>
		                        <td><input id="add-zhuohao" class="easyui-validatebox" data-options="required:true"  name="zhuohao" style="width:60px;" /></td>
		                    </tr>
		                    <tr>
		                        <td>最大人数：</td>
		                        <td>
		                        		<input id="add-maxNum" name="renshu" style="width:60px;" class="easyui-numberbox" data-options="required:true,min:0,max:20"></input>  
		                        </td>
		                    </tr>
		                    <tr>
		                        <td>状态：</td>
		                        <td>
		                        		<select id="add-status" name="status">
		                        			<option value="0">空闲</option>
		                        			<option value="1">使用中</option>
		                        		</select>
							   </td>
		                    </tr>
	    	            </table>
    	            </form>
            </div>
            <div region="south" border="false" style="text-align: right; height: 50px; line-height: 30px;">
                <a class="easyui-linkbutton" icon="icon-ok" href="javascript:add();" >确定</a> 
                <a class="easyui-linkbutton" icon="icon-cancel" href="javascript:closeAddWindow();">取消</a>
            </div>
        </div>
    </div>
		
</body>
</html>