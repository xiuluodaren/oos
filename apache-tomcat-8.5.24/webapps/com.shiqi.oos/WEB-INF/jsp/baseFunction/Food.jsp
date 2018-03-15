<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>配餐</title>
<!-- 导入easyui类库 -->
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/ext/datagrid-detailview.js" ></script>
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
		//ajax轮询刷新数据
		 window.setInterval(function(){
			 $('#datagrid').datagrid({
				 url:'${pageContext.request.contextPath }/food/findEasyUI'
			 });
		 },AjaxTime);
		
		$('#datagrid').datagrid({
			title:'配餐',
			width:500,
			height:250,
			remoteSort:false,
			nowrap:false,
			fitColumns:true,
			pagination:true,
			fit:true,
			url:'${pageContext.request.contextPath }/food/findEasyUI',
			columns:[[
				{field:'diningtableid',title:'桌号',width:10,sortable:'true',
					sorter:function(a,b){  
						return parseInt(a) > parseInt(b) ? 1 : -1; 
				}},
				{field:'userid',title:'用户编号',width:10},
				{field:'shopnum',title:'商品数目',width:10,sortable:'true',
					sorter:function(a,b){  
						return parseInt(a) > parseInt(b) ? 1 : -1; 
				}}
			]],
			onDblClickRow:function(index,data){
				$("#datagrid").datagrid("expandRow",index);
			},
			view: detailview,
			detailFormatter: function(rowIndex, rowData){
				
				var details = rowData.shoppingcardetails;
				
				var html = "<table style=\"border:0;width:100%;\"><tr><th>菜品名称</th><th>菜品类型</th><th>菜品数量</th></tr>";
				
				details.forEach(function(value,index){
					html += "<tr><td style=\"border:0;\">" + value.dishes.name + "</td><td style=\"border:0;\">"
						+ value.dishes.dishestype.name + "</td><td style=\"border:0;\">" + value.shopnum + "</td><td style=\"border:0;\">"
						+ "<input type='button' value='配餐' onclick=\"food(\'" + value.id + "\');\" />" + "</td></tr>";
				});
				
				html += "</table>";
				return html;
			}
		});
	});

	//配餐
	function food(id)
	{
		$.post("${pageContext.request.contextPath }/food/foodByCarDetailId",{"id":id},function(data){
			$.messager.alert("提示",data.message);
			if (data.success == "true"){
				window.location.reload();
			}
		});
	}
</script>

</head>
<body>
	<table id="datagrid"></table>
</body>
</html>