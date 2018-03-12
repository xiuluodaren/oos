<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车</title>
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
			singleSelect:true,
			nowrap:false,
			fitColumns:true,
			pagination:true,
			fit:true,
			toolbar: '#tb',
			url:'${pageContext.request.contextPath }/shoppingCar/shopCarDetail',
			columns:[[
				{field:'image',title:'图片',width:10,align:'center',formatter:function(value,row,index){
					var content = "<img src=\"${pageContext.request.contextPath }/uploadImages/"+ row.dishes.pic +"\" style=\"height:50px;width:50px\" />";
					return content;
				}},
				{field:'createtime',title:'创建时间',width:10,
						formatter: function(value,row,index){
						return timestampToTime(value);
				}},
				{field:'updatetime',title:'修改时间',width:10,sortable:true,
						formatter: function(value,row,index){
						return timestampToTime(value);
				}},
				{field:'name',title:'菜品名称',width:10,sortable:true,
					formatter: function(value,row,index){
						if (row.dishes.name){
							return row.dishes.name;
						}
				}},
				{field:'originalprice',title:'原价(<font color=\'red\'>元</font>)',width:10,sortable:true,
					formatter: function(value,row,index){
						if (row.dishes.originalprice){
							return row.dishes.originalprice;
						}
				}},
				{field:'price',title:'现价(<font color=\'red\'>元</font>)',width:10,sortable:true,
					formatter: function(value,row,index){
						if (row.dishes.price){
							return row.dishes.price;
						}
				}},
				{field:'isvip',title:'是否特价',width:10,
					formatter: function(value,row,index){
						if (row.dishes.isvip == '1'){
							return "是";
						}else{
							return "否";
						}
				}},
				{field:'shopnum',title:'数量',width:10,
					formatter: function(value,row,index){
						return row.shopnum;
				}},
				{field:'xiaoji',title:'小计(<font color=\'red\'>元</font>)',width:10,
					formatter: function(value,row,index){
						return row.xiaoji;
				}},
				{field:'status',title:'状态',width:10,
					formatter: function(value,row,index){
						switch (row.status) {
							case "0":
							{
								return "未准备";
							}
							case "1":
							case "2":
							{
								return "准备中";
							}
							case "3":
							{
								return "已上菜";
							}
							default:
								break;
						}
				}},
				{field:'operation',title:'操作',width:10,
					formatter: function(value,row,index){
						var content = "<input type='button' style='color:red' onclick='javascript:removeFromShopCar(\""+ row.id +"\");' value='删除'/>";
						
						if (row.status == "0") {
							return content;
						}
						
				}}
			]],loadFilter: function(data){
				
				var originalprice = 0;	//原总价
				var price = 0;	//总价
				var shopnumber = 0;	//总数量
				
				for (var i = 0;i < data.rows.length;i++)
				{
					var detail = data.rows[i];
					originalprice += parseInt(detail.dishes.originalprice);
					price += parseInt(detail.xiaoji);
					shopnumber += detail.shopnum;
				}
				
				$("#originalprice").html(originalprice);
				$("#price").html(price+"元");
				$("#shopnumber").html(shopnumber);
				if (data.shoppingcardetails){
					return data.shoppingcardetails;
				} else {
					return data;
				}
			}
		});
		
		<c:if test="${empty zhuohao}">
		//查询全部餐桌
		$.get("${pageContext.request.contextPath }/dining/findEnablel",function(data){
			//<input id="1" name="zhuohao" type="radio"  /><label for="1">11111111111</label>
			
			for (var i = 0;i < data.length;i++){
				var dt = data[i];
				// disabled=\"disabled\" 
				var html = "<input id=\"" + dt.id +"\" value=\""+ dt.id +"\" name=\"zhuohao\" type=\"radio\" ";
				
				if (dt.status == '1') {
					html += " disabled=\"disabled\" ";
				}
				
				html += "/><label for=\""+ dt.id +"\">"+ dt.id +"</label>";
				
				$("#diningTable").append(html);
			}
			var button = "<input type=\"button\" value=\"确定点餐\" onclick=\"confirmOrder();\" />";
			$("#diningTable").append(button);
		});

		</c:if>
	});

	//移除购物车
	function removeFromShopCar(detailId)
	{
		$.get("${pageContext.request.contextPath }/shoppingCar/removeDetail",{"detailId":detailId},function(data){
			alert(data.message);
			if (data.success == 'true') {
				location.reload();
			}
		});
	}
	
	//确定点餐
	function confirmOrder()
	{
		var zhuohao = $('input[name="zhuohao"]:checked ').val();
		
		if (zhuohao == null) {
			alert("请选择桌号");
		}else{
			$.post("${pageContext.request.contextPath }/shoppingCar/confirmOrder",{"zhuohao":zhuohao},function(data){
				alert(data.message);
				location.reload();
			});
		}
		
	}
	
</script>

</head>
<body>
		<table id="datagrid"></table>
		
		<div id="tb">
			<table style="height: 100%;width: 100%">
				<tr>
					<td style="width:50px;text-align: right;">总价</td>
					<td style="width:50px;text-align: right;">原价:</td>
					<td id="originalprice" style="text-align: left;"></td>
					<td style="width:50px;text-align: right;">现价:</td>
					<td id="price" style="text-align: left;color: red"></td>
					<td style="width:50px;text-align: right;">总数量:</td>
					<td id="shopnumber"  style="text-align: left;"></td>
					<td></td>
				</tr>
				<tr>
					<td style="width:50px;text-align: right;">桌号:</td>
						<td id="diningTable" colspan="6" style="text-align: left;">
							<c:if test="${not empty zhuohao}">
								<input type="radio" name="zhuohao" value="${zhuohao }" checked="checked" />${zhuohao}
								<input type="button" value="确定点餐" onclick="confirmOrder();" />
							</c:if>
						</td>
				</tr>
			</table>
		</div>
</body>
</html>