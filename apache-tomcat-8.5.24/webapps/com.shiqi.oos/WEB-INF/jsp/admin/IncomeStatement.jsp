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

	//全局变量，是否已插入统计
	var isTotal = false;
	
	$(function(){
		
		 //绑定回车
		 $(document).keydown(function(event){
			if(event.keyCode==13){
				search();
			}
		});
		 
		$('#datagrid').datagrid({
			title:'收入明细查询',
			singleSelect:true,
			remoteSort:false,
			fit:true,
			nowrap:true,
			fitColumns:true,
			pagination:true,
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
						if (value == '0') return "----";
						return timestampToTime(value);
					},
				},
				{field:'updatetime',title:'修改时间',width:10,sortable:'true',
					sorter:function(a,b){  
						return parseInt(a) > parseInt(b) ? 1 : -1; 
					},formatter: function(value,row,index){
						if (value == '0') return "----";
						return timestampToTime(value);
					},
				}
			]],
			onDblClickRow:function(index,data){
				$("#datagrid").datagrid("expandRow",index);
			},onLoadSuccess:function(data){
				$('#datagrid').datagrid("resize");
				var totalRow;
				if (isTotal == false){
					totalRow = calculationTotal(data.rows);
					isTotal = true;
				}
				
				$('#datagrid').datagrid("insertRow",{
					row:totalRow
				});
			}
		});
	});
	
	//遍历计算合计
	function calculationTotal(rows)
	{
		var json = {
			"id" : "合计",
			"username":"----",
			"shopnum" : 0, //商品数
			"totalprice" : 0,	//总价
			"discount" : 0,	//折扣
			"receivable" : 0,	//应收
			"realprice" : 0,	//实收
			"changemoney" : 0,	//找零
			"operator" : {
				"username":"----"
			},
			"createtime" : "0",
			"updatetime" : "0"
		};
		
		rows.forEach(function(value,index){
			json.shopnum += parseInt(value.shopnum);
			json.totalprice += parseInt(value.totalprice);
			
			if (value.discount != null)
				json.discount += parseInt(value.discount);
			
			json.receivable += parseInt(value.receivable);
			json.realprice += parseInt(value.realprice);
			json.changemoney += parseInt(value.changemoney);
		});

		
		//遍历加上红色字体
	    for(var name in json){       
	       	var value = json[name];
	       	if (name != "createtime" && name != "updatetime" && name != "operator" && name != "username")
	       		json[name] = "<font color='red'>" + value + "</font>";
	    }
		
		return json;
	}
	
	//查询
	function search()
	{
		var json = getFormJson("#search-form");
		isTotal = false;
		$('#datagrid').datagrid({
			queryParams:json
		});
		
	}
	
	//重置
	function reset()
	{
		$("#search-form").form("clear");
		isTotal = false;
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
			<table style="height: 100%;width: 620px;">
				<tr>
					<td style="text-align: right;width: 50px;">查询:</td>
					<td style="text-align: right;width: 60px;">
						创建时间:
					</td>
					<td style="text-align: left;width: 120px;">
						<input id="search-createtimestart" class="easyui-datetimebox" name="createtimestart" data-options="width:120" />
					</td>
					<td style="text-align: right;width: 20px;">
						到
					</td>
					<td style="text-align: left;width: 120px;">
						<input id="search-createtimeend" class="easyui-datetimebox" name="createtimeend"  data-options="width:120" />
					</td>
					<td style="text-align: center;width:210px;">
						<a style="top:5px;" class="easyui-linkbutton" icon="icon-search" href="javascript:search();" >查询</a> 
						<a style="top:5px;" class="easyui-linkbutton" icon="icon-reload" href="javascript:reset();" >重置</a> 
					</td>
				</tr>
			</table>
		</form>
	</div>
    
</body>
</html>