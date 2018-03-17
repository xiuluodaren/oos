<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单明细查询</title>
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
		
		 //绑定回车
		 $(document).keydown(function(event){
			if(event.keyCode==13){
				search();
			}
		});
		 
		$('#datagrid').datagrid({
			title:'收入明细查询',
			width:500,
			height:250,
			singleSelect:true,
			remoteSort:false,
			nowrap:false,
			fitColumns:true,
			pagination:true,
			fit:true,
			toolbar:'#tb',
			url:'${pageContext.request.contextPath }/order/findOrderEasyUI',
			columns:[[
				{field:'id',title:'订单编号',width:15,sortable:'true',
					sorter:function(a,b){  
						return parseInt(a) > parseInt(b) ? 1 : -1; 
				}},
				{field:'username',title:'用户名',width:10},
				{field:'shopnum',title:'商品数目',width:5,sortable:'true',
					sorter:function(a,b){  
						return parseInt(a) > parseInt(b) ? 1 : -1; 
				}},
				{field:'totalprice',title:'总价(元)',width:5,sortable:'true',
					sorter:function(a,b){  
						return parseInt(a) > parseInt(b) ? 1 : -1; 
				}},
				{field:'discount',title:'折扣(元)',width:5,sortable:'true',
					sorter:function(a,b){  
						return parseInt(a) > parseInt(b) ? 1 : -1; 
				},formatter:function(value,row,index){
					if (value == null)
					{
						return "无";
					}else{
						return value;
					}
				}},
				{field:'receivable',title:'应收(元)',width:5,sortable:'true',
					sorter:function(a,b){  
						return parseInt(a) > parseInt(b) ? 1 : -1; 
				},formatter:function(value,row,index){
					return "<font color='red'>" + value + "</font>";
				}},
				{field:'realprice',title:'实收(元)',width:5,sortable:'true',
					sorter:function(a,b){  
						return parseInt(a) > parseInt(b) ? 1 : -1; 
				}},
				{field:'changemoney',title:'找零(元)',width:5,sortable:'true',
					sorter:function(a,b){  
						return parseInt(a) > parseInt(b) ? 1 : -1; 
				}},
				{field:'operatorid',title:'操作人',width:10,sortable:'true',
					sorter:function(a,b){  
						return parseInt(a) > parseInt(b) ? 1 : -1; 
				},formatter:function(value,row,index){
					return row.operator.username;
				}},
				{field:'createtime',title:'创建时间',width:10,sortable:'true',
					sorter:function(a,b){  
						return parseInt(a) > parseInt(b) ? 1 : -1; 
					},formatter: function(value,row,index){
						return timestampToTime(value);
					},
				},
				{field:'updatetime',title:'修改时间',width:10,sortable:'true',
					sorter:function(a,b){  
						return parseInt(a) > parseInt(b) ? 1 : -1; 
					},formatter: function(value,row,index){
						return timestampToTime(value);
					},
				}
			]],
			onDblClickRow:function(index,data){
				$("#datagrid").datagrid("expandRow",index);
			},
			view: detailview,
			detailFormatter: function(rowIndex, rowData){
				
				var details = rowData.detailList;
				
				var html = "<table style=\"border:0;width:100%;\"><tr><th>菜品名称</th><th>菜品数量</th>"
				+"<th>原价</th><th>现价</th><th>是否打折</th><th>小计(元)</th></tr>";
				
				details.forEach(function(value,index){
					html += "<tr><td style=\"border:0;\">" + value.name + "</td><td style=\"border:0;\">" 
					+ value.shopnum + "</td><td style=\"border:0;\">"
					+ value.originalprice + "</td><td style=\"border:0;\">"
					+ value.price + "</td><td style=\"border:0;\">"
					+ (value.isvip == "1" ? "是" : "否") + "</td><td style=\"border:0;\">"
					+ value.xiaoji + "</td></tr>";
				});
				
				html += "</table>";
				return html;
			}
		});
	});
	
	//查询
	function search()
	{
		var json = getFormJson("#search-form");
		$('#datagrid').datagrid({
			queryParams:json
		});
		
	}
	
	//重置
	function reset()
	{
		$("#search-form").form("clear");
		
		$('#datagrid').datagrid({
			queryParams:null
		});
		
	}
	
</script>

</head>
<body>
	<table id="datagrid"></table>
	
    <div id="tb">
    		<form id="search-form">
			<table style="height: 100%;width: 730px;">
				<tr>
					<td style="text-align: right;width: 100px;">查询:</td>
					<td style="text-align: right;width: 60px;">
						订单号:
					</td>
					<td>
						<input id="search-orderId" name="orderId" style="width:150px;" />
					</td>
					<td style="text-align: right;width: 60px;">
						用户名:
					</td>
					<td>
						<input id="search-username" name="username" style="width:150px;" />
					</td>
					<td style="text-align: right;width: 60px;">
						操作人:
					</td>
					<td>
						<input id="search-operator" name="operator" style="width:150px;" />
					</td>
					</tr>
					<tr>
					<td style="text-align: right;">
						创建时间:
					</td>
					<td style="text-align: left;" colspan="2">
						<input id="search-createtimestart" class="easyui-datetimebox" name="createtimestart" data-options="width:220" />
					</td>
					<td style="text-align: right;">
						到
					</td>
					<td>
						<input id="search-createtimeend" class="easyui-datetimebox" name="createtimeend"  data-options="width:160" />
					</td>
					<td style="text-align: center;" colspan="2" style="width:210px;">
						<a class="easyui-linkbutton" icon="icon-search" href="javascript:search();" >查询</a> 
						<a class="easyui-linkbutton" icon="icon-reload" href="javascript:reset();" >重置</a> 
					</td>
				</tr>
			</table>
		</form>
	</div>
    
</body>
</html>