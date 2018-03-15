<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>收银</title>
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
			title:'收银',
			width:500,
			height:250,
			singleSelect:true,
			remoteSort:false,
			nowrap:false,
			fitColumns:true,
			pagination:true,
			fit:true,
			toolbar:'#tb',
			url:'${pageContext.request.contextPath }/cashier/findEasyUI',
			columns:[[
				{field:'diningtableid',title:'桌号',width:10,sortable:'true',
					sorter:function(a,b){  
						return parseInt(a) > parseInt(b) ? 1 : -1; 
				}},
				{field:'userid',title:'用户编号',width:10},
				{field:'username',title:'用户名',width:10,
					formatter: function(value,row,index){
					if (row.user.username){
						return row.user.username;
					}},
				},
				{field:'isvip',title:'是否会员',width:10,
					formatter: function(value,row,index){
							switch (row.user.isvip){
							case '0':
							{
								return "否"
							}
							case '1':
							{
								return "是"
							}
						}},
					},
				{field:'shopnum',title:'商品数目',width:10,sortable:'true',
					sorter:function(a,b){  
						return parseInt(a) > parseInt(b) ? 1 : -1; 
				}},
				{field:'createtime',title:'创建时间',width:10,sortable:'true',
					sorter:function(a,b){  
						return parseInt(a) > parseInt(b) ? 1 : -1; 
					},formatter: function(value,row,index){
						return timestampToTime(value);
					},
				},
				{field:'totalprice',title:'总价',width:10,sortable:'true',
					sorter:function(a,b){  
						return parseInt(a) > parseInt(b) ? 1 : -1; 
				},formatter:function(value,row,index){
					return "<font color='red'>" + value + "</font>";
				}}
			]],
			onDblClickRow:function(index,data){
				$("#datagrid").datagrid("expandRow",index);
			},
			view: detailview,
			detailFormatter: function(rowIndex, rowData){
				
				var details = rowData.shoppingcardetails;
				
				var html = "<table style=\"border:0;width:100%;\"><tr><th>菜品名称</th><th>菜品数量</th>"
				+"<th>原价</th><th>现价</th><th>是否打折</th><th>小计(元)</th></tr>";
				
				details.forEach(function(value,index){
					html += "<tr><td style=\"border:0;\">" + value.dishes.name + "</td><td style=\"border:0;\">" 
					+ value.shopnum + "</td><td style=\"border:0;\">"
					+ value.dishes.originalprice + "</td><td style=\"border:0;\">"
					+ value.dishes.price + "</td><td style=\"border:0;\">"
					+ (value.dishes.isvip == "1" ? "是" : "否") + "</td><td style=\"border:0;\">"
					+ value.xiaoji + "</td></tr>";
				});
				
				html += "<tr><td colspan=\"6\" align=\"center\"><input type='button' style='color:red;font-size: 20px;' onclick='cashier(" + rowIndex + ");' value='结账' /></td></tr>";
				
				html += "</table>";
				return html;
			}
		});
	});

	//结账
	function cashier(rowIndex)
	{
		$("#cashierForm").form("clear");
		$('#datagrid').datagrid("selectRow",rowIndex);
		var rowData = $('#datagrid').datagrid("getSelected");
		
		//填充shopcarid
		$("#shopCarId").val(rowData.id);
		
		//计算原价
		var details = rowData.shoppingcardetails;
		
		var originalprice = 0;
		
		details.forEach(function(value,index){
			originalprice += value.dishes.originalprice * value.shopnum;
		});
		
		$("#totalprice").val(originalprice);
		
		//是否折扣
		$("#isdiscount").val(rowData.user.isvip);
		showDiscount();
		
		$("#cashierWindow").window("open");
	}
	
	//是否显示折扣
	function showDiscount()
	{
		var discount = $("#isdiscount").val();
		//获取信息
		var rowData = $('#datagrid').datagrid("getSelected");
		
		//计算原价
		var details = rowData.shoppingcardetails;
		
		var originalprice = 0;
		
		details.forEach(function(value,index){
			originalprice += value.dishes.originalprice * value.shopnum;
		});
		
		if (discount == '1') {
			$("#showDiscount").attr("hidden",null);
			$('#cashierWindow').panel('resize',{
				height: 290
			});
			
			$("#discount").val(originalprice - rowData.totalprice);
			$("input[name='discount']").val(originalprice - rowData.totalprice);
			$("#receivable").val(rowData.totalprice);
			$("#discount").validatebox("enableValidation");
		}else{
			$("#showDiscount").attr("hidden","hidden");
			$('#cashierWindow').panel('resize',{
				height: 270
			});
			
			$("#receivable").val(originalprice);
			$("#discount").validatebox("disableValidation");
		}
		
	}
	
	//折扣改变，用于手工输入折扣数
	function discountChange()
	{
		var discount = $("#discount").val();
		
		//获取信息
		var rowData = $('#datagrid').datagrid("getSelected");
		//计算原价
		var details = rowData.shoppingcardetails;
		
		var originalprice = 0;
		
		details.forEach(function(value,index){
			originalprice += value.dishes.originalprice * value.shopnum;
		});
		
		if (originalprice >= discount) {
			$("#receivable").val(originalprice - discount);
		}else{
			$("#receivable").val(0);
			$("#discount").val(originalprice);
			$("input[name='discount']").val(originalprice);
		}
		
		realPriceChange();
	}
	
	//实收
	function realPriceChange()
	{
		//应收
		var receivable = parseInt($("#receivable").val());
		//实收
		var realPrice = parseInt($("#realPrice").val());
		
		if (realPrice < receivable) {
			$.messager.alert("提示","请修改折扣");
		}else{
			//找零
			$("#change").val(realPrice - receivable);
			$("input[name='change']").val(realPrice - receivable);
		}
		
	}
	
	//关闭窗口
	function closeWindow()
	{
		$("#cashierWindow").window("close");
	}
	
	//确定收银
	function confirm()
	{
		var success = $("#cashierForm").form("validate");
		if (success)
		{
			$.messager.confirm("提示","请确定收银结果正确",function(r){
				if (r) {
					var json = $("#cashierForm").serialize();
					
					
					
				}
			});
		}
		
	}
	
	//查询
	function search()
	{
		var diningTableId = $("#search-diningtableid").val();
		var username = $("#search-username").val();
		
		$('#datagrid').datagrid({
			queryParams:{
				diningTableId:diningTableId,
				username:username
			}
		});
		
	}
	
	//重置
	function reset()
	{
		$("#search-diningtableid").val("");
		$("#search-username").val("");
		
		$('#datagrid').datagrid({
			queryParams:null
		});
		
	}
	
</script>

</head>
<body>
	<table id="datagrid"></table>
	
	<!--收银窗口-->
    <div id="cashierWindow" class="easyui-window" title="收银" collapsible="false" minimizable="false" modal="true" closed="true" resizable="true"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 290px; padding: 5px;
        background: #fafafa">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            		<form id="cashierForm">
            			<input id="shopCarId" name="shopCarId" type="hidden" />
		                <table cellpadding=3>
		                    <tr>
		                        <th>总价(元)：</th>
		                        <td>
		                        		<input id="totalprice" name="totalprice" readonly="readonly" style="width:150px;" ></input>  
		                        </td>
		                    </tr>
		                    <tr>
		                        <th>是否打折：</th>
		                        <td>
		                        		<select id="isdiscount" name="isdiscount" style="width:150px;" onchange="showDiscount();">
			                     		<option value="1">是</option>
			                     		<option value="0">否</option>
				                 	</select>
							   </td>
		                    </tr>
		                    <tr id="showDiscount">
		                        <th>折扣(元)：</th>
		                        <td>
		                        		<input id="discount" name="discount" onchange="discountChange();" style="width:150px;" class="easyui-numberbox" data-options="required:true"  />  
		                        </td>
		                    </tr>
		                    <tr>
		                        <th>应收(元)：</th>
		                        <td>
		                        		<input id="receivable" name="receivable" readonly="readonly" style="width:150px;" />
		                        </td>
		                    </tr>
		                    <tr>
		                        <th>实收(元)：</th>
		                        <td>
		                        		<input id="realPrice" name="realPrice" onchange="realPriceChange();" style="width:150px;" class="easyui-numberbox" data-options="required:true" />  
		                        </td>
		                    </tr>
		                    <tr>
		                        <th>找零(元)：</th>
		                        <td>
		                        		<input id="change" name="change" style="width:150px;" class="easyui-numberbox" data-options="required:true" />
		                        </td>
		                    </tr>
	    	            </table>
    	            </form>
            </div>
            <div region="south" border="false" style="text-align: right; height: 50px; line-height: 30px;">
                <a class="easyui-linkbutton" icon="icon-ok" href="javascript:confirm();" >确定</a> 
                <a class="easyui-linkbutton" icon="icon-cancel" href="javascript:closeWindow();">取消</a>
            </div>
        </div>
    </div>
    
    <div id="tb">
			<table style="height: 100%;width: 850px;">
				<tr>
					<td style="width:50px;text-align: right;">查询:</td>
					<td style="width:80px;text-align: right;">
						桌号:
					</td>
					<td style="width:200px;text-align: right;">
						<input id="search-diningtableid" name="diningtableid" style="width:100%;" />
					</td>
					<td style="width:120px;text-align: right;">
						用户名:
					</td>
					<td style="width:200px;text-align: right;">
						<input id="search-username" name="username" style="width:100%;" />
					</td>
					<td style="width:100px;text-align: right;">
						<a class="easyui-linkbutton" icon="icon-search" href="javascript:search();" >查询</a> 
					</td>
					<td style="width:100px;text-align: right;">
						<a class="easyui-linkbutton" icon="icon-reload" href="javascript:reset();" >重置</a> 
					</td>
				</tr>
			</table>
		</div>
    
</body>
</html>