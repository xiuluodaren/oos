<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜品管理</title>
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
			title:'菜品类型管理',
			width:500,
			height:250,
			remoteSort:false,
			nowrap:false,
			fitColumns:true,
			pagination:true,
			fit:true,
			url:'${pageContext.request.contextPath }/dishesType/findEasyUI',
			columns:[[
				{field:'selected',checkbox:'true'},
				{field:'id',title:'类型id',width:10,sortable:'true',
					formatter: function(value,row,index){
						return row.id;
				},sorter:function(a,b){  
					return parseInt(a) > parseInt(b) ? 1 : -1; 
				}},
				{field:'name',title:'类型名称',width:10},
				{field:'isshowonhome',title:'是否在首页显示',width:10,
					formatter:function(value,row,index){
						if (value == '0') {
							return "否";
						}else{
							return "是";
						}
				}},
   				{field:'zindex',title:'同级排序',width:10,sortable:'true',
				 	  	sorter:function(a,b){  
						return parseInt(a) > parseInt(b) ? 1 : -1; 
				}},
			]],
			toolbar:'#tb',
			onDblClickRow:function(index,data){
				$("#editDishesTypeForm").form("load",data);
				$('#editDishesTypeWindow').window('open');
			}
		});
		
		$(document).keydown(function(event){
			if(event.keyCode==13){
				search();
			}
		});
		
	});

	//查询菜品
	function search()
	{
		var searchTypeName = $("#search-dishestypename").val();
		var searchIsShowOnHome = $("#search-isshowonhome").val();
		
		searchIsShowOnHome = searchIsShowOnHome == "-1" ? null : searchIsShowOnHome;
		
		var url = "${pageContext.request.contextPath }/dishesType/findEasyUI"
		
		$('#datagrid').datagrid({
			url:url,
			queryParams:{
				dishestypename:searchTypeName,
				isshowonhome:searchIsShowOnHome
			}
		});
		
	}
	
	//重置查询条件
	function resetSearch()
	{
		$("#search-dishestypename").val("");
		$("#search-isshowonhome").val("-1");
		
		search();
	}
	
	//删除菜品
	function deleteDiningTable()
	{
		var rows = $('#datagrid').datagrid("getSelections");
		
		if (rows == null || rows.length == 0) {
			$.messager.confirm("提示","请选择要删除的菜品类型",function(data){
				
			});
		}else{
			var ids = "";

			$.each( rows, function(index, value){
				  ids = ids + value.id + ",";
			});
			
			ids = ids.substring(0,ids.length - 1);
			
			$.messager.confirm("提示","是否确定删除" + ids + "菜品类型",function(r){
				if(r)
				{
					$.post("${pageContext.request.contextPath }/dishesType/deleteByIds",{"ids":ids},function(data){
						alert(data.message);
						
						if (data.success) {
							location.reload();
						}
					});
				}
			});

		}
	}
	
	//修改菜品
	function editDiningTable()
	{
		var rows = $('#datagrid').datagrid("getSelections");
		
		if (rows == null || rows.length == 0 || rows.length > 1) {
			$.messager.confirm("提示","请选择一个要修改的菜品类型",function(data){
				
			});
		}else{
			$("#editDishesTypeForm").form("load",rows[0]);
			$('#editDishesTypeWindow').window('open');
		}
		
	}
	
	//修改菜品提交
	function edit()
	{
		if ($("#editDishesTypeForm").form("validate"))
		{
            		$.post("${pageContext.request.contextPath }/dishesType/updateDishesType",$("#editDishesTypeForm").serialize(),function(data1){
        				$.messager.alert("提示",data1.message);
        				if (data1.success) {
        					location.reload();
        				}
        			});
		}
	}
	
	//关闭
	function closeEditWindow()
	{
		$('#editDishesTypeWindow').window('close');
	}
	
	//关闭
	function closeAddWindow()
	{
		$('#addDishesTypeWindow').window('close');
	}
	
	//添加菜品
	function addDishes()
	{
		$("#addDishesTypeForm").form("clear");
		$("#add-isshowonhome").val("1");
		$('#addDishesTypeWindow').window('open');
	}
	
	//添加菜品提交
	function add()
	{
		if ($("#addDishesTypeForm").form("validate"))
		{
            		$.post("${pageContext.request.contextPath }/dishesType/addDishesType",$("#addDishesTypeForm").serialize(),function(data1){
            			$.messager.alert("提示",data1.message);
            			if (data1.success == "true")
            			{
            				window.location.reload();	
            			}
            		});
		}
		
	}
	
</script>

</head>
<body>
	<table id="datagrid"></table>
		
		<div id="tb">
			<table style="height: 100%;width: 580px;">
				<tr>
					<td style="width:50px;text-align: right;">查询:</td>
					<td style="width:80px;text-align: right;">
						类型名称:
					</td>
					<td style="width:200px;text-align: right;">
						<input id="search-dishestypename" name="dishestypename" style="width:100%;" />
					</td>
					<td style="width:120px;text-align: right;">
						是否首页显示:
					</td>
					<td style="width:100px;text-align: right;">
						<select id="search-isshowonhome" name="isshowonhome" style="width:100%">
								<option value="-1">不限</option>
								<option value="1">是</option>
								<option value="0">否</option>
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
			<a href="javascript:addDishes();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
			<a href="javascript:editDiningTable();" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
			<a href="javascript:deleteDiningTable();" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
		</div>
		
	<!--修改菜品类型窗口-->
    <div id="editDishesTypeWindow" class="easyui-window" title="修改菜品" collapsible="false" minimizable="false" modal="true" closed="true" resizable="false"
        maximizable="false" icon="icon-save"  style="width: 320px; height: 240px; padding: 5px;
        background: #fafafa">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            		<form id="editDishesTypeForm" method="post">
		                <table cellpadding=3>
		                		<tr>
		                        <td style="width:100px">类型id：</td>
		                        <td style="width: 150px">
		                        		<input id="edit-dishesTypeId" class="easyui-validatebox" data-options="required:true"  name="id" style="width:100%;" />
		                        </td>
		                    </tr>
		                    <tr>
		                        <td>类型名称：</td>
		                        <td>
		                        		<input id="edit-name" name="name" style="width:100%;" class="easyui-validatebox" data-options="required:true"></input>  
		                        </td>
		                    </tr>
		                    <tr>
		                        <td>是否首页显示：</td>
		                        <td>
		                        		<select id="edit-isshowonhome" name="isshowonhome" style="width:100%">
										<option value="1">是</option>
										<option value="0">否</option>
		                 			</select>
							   </td>
		                    </tr>
		                    <tr>
								<th>同级排序</th>
								<td><input id="zindex" name="zindex"  class="easyui-numberbox tb" style="width:100%" data-options="validType:'numRange[0,1000]',invalidMessage:'必须填写0~1000之间的数字'"  /></td>
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
    
    <!--添加菜品类型窗口-->
    <div id="addDishesTypeWindow" class="easyui-window" title="添加菜品类型" collapsible="false" minimizable="false" modal="true" closed="true" resizable="false"
        maximizable="false" top="50" icon="icon-save"  style="width: 320px; height: 240px; padding: 5px;
        background: #fafafa" top:">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            		<form id="addDishesTypeForm" method="post">
		                <table cellpadding=3>
		                		<tr>
		                        <td style="width:100px">类型id：</td>
		                        <td style="width: 150px">
		                        		<input id="add-dishesTypeId" class="easyui-validatebox" data-options="required:true"  name="id" style="width:100%;" />
		                        </td>
		                    </tr>
		                    <tr>
		                        <td>类型名称：</td>
		                        <td>
		                        		<input id="add-name" name="name" style="width:100%;" class="easyui-validatebox" data-options="required:true"></input>  
		                        </td>
		                    </tr>
		                    <tr>
		                        <td>是否首页显示：</td>
		                        <td>
		                        		<select id="add-isshowonhome" name="isshowonhome" style="width:100%">
										<option value="1">是</option>
										<option value="0">否</option>
		                 			</select>
							   </td>
		                    </tr>
		                    <tr>
								<th>同级排序</th>
								<td><input id="zindex" name="zindex"  class="easyui-numberbox tb" style="width:100%" data-options="validType:'numRange[0,1000]',invalidMessage:'必须填写0~1000之间的数字'"  /></td>
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