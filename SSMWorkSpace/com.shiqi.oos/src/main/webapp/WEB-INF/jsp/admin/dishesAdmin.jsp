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
			title:'菜品管理',
			width:500,
			height:250,
			remoteSort:false,
			nowrap:false,
			fitColumns:true,
			pagination:true,
			fit:true,
			url:'${pageContext.request.contextPath }/dishes/findEasyUI',
			columns:[[
				{field:'selected',checkbox:'true'},
				{field:'id',title:'菜品编号',width:10,sortable:'true',
					formatter: function(value,row,index){
						return row.id;
				},sorter:function(a,b){  
					return parseInt(a) > parseInt(b) ? 1 : -1; 
				}},
				{field:'image',title:'图片',width:10,align:'center',formatter:function(value,row,index){
					var content = "<img src=\"${pageContext.request.contextPath }/uploadImages/"+ row.pic +"\" style=\"height:50px;width:50px\" />";
					return content;
				}},
				{field:'name',title:'菜品名称',width:10},
				{field:'typeid',title:'菜品类型',width:10,
					formatter:function(value,row,index){
					return row.dishestype.name;
				}},
				{field:'originalprice',title:'原价(<font color=\'red\'>元</font>)',width:10},
				{field:'price',title:'现价(<font color=\'red\'>元</font>)',width:10},
				{field:'isvip',title:'是否打折',width:10,formatter:function(value,row,index){
					if (value == "1") {
						return "是";
					}else{
						return "否";
					}
				}},
			]],
			toolbar:'#tb',
			onDblClickRow:function(index,data){
				$("#dishesForm").form("load",data);
				$("#edit-dishestype").val(data.dishestype.id);
				$("#picImage").attr("src", "${pageContext.request.contextPath }/uploadImages/" + data.pic);
				$('#editDiningTable').window('open');
				
			}
		});
		
		$(document).keydown(function(event){
			if(event.keyCode==13){
				search();
			}
		});
		
	});

	//显示图片
	function showImageWindow()
	{
		var url = $("#picImage").attr("src");
		window.parent.showImages(url);
	}
	
	//查询菜品
	function search()
	{
		var searchName = $("#search-name").val();
		var searchDishesType = $("#search-dishesType").val();
		
		searchDishesType = searchDishesType == "-1" ? null : searchDishesType;
		
		var url = "${pageContext.request.contextPath }/dishes/findEasyUI"
		
		$('#datagrid').datagrid({
			url:url,
			queryParams:{
				name:searchName,
				dishesType:searchDishesType
			}
		});
		
	}
	
	//重置查询条件
	function resetSearch()
	{
		$("#search-name").val("");
		$("#search-dishesType").val("-1");
		
		search();
	}
	
	//删除菜品
	function deleteDiningTable()
	{
		var rows = $('#datagrid').datagrid("getSelections");
		
		if (rows == null || rows.length == 0) {
			$.messager.confirm("提示","请选择要删除的菜品",function(data){
				
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
					$.post("${pageContext.request.contextPath }/dishes/deleteByIds",{"ids":ids},function(data){
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
			$.messager.confirm("提示","请选择一个要修改的菜品",function(data){
				
			});
		}else{
			$("#dishesForm").form("load",rows[0]);
			$("#edit-dishestype").val(rows[0].dishestype.id);
			$("#picImage").attr("src", "${pageContext.request.contextPath }/uploadImages/" + rows[0].pic);
			$('#editDiningTable').window('open');
		}
		
	}
	
	//修改菜品提交
	function edit()
	{
		var path = document.getElementById("editUploadFile").value;
		
		if (path == null || path == "") {
			//图片未修改
			$.post("${pageContext.request.contextPath }/dishes/editDisheById",$("#dishesForm").serialize(),function(data){
				$.messager.alert("提示",data.message);
				if (data.success) {
					location.reload();
				}
			});
		}else{
			//图片修改
			var formData = new FormData();

			//验证后缀名
			var ret = /\.(?:jpg|jpeg|png)$/i;
			if(ret.test(path) == false){
				$.messager.alert('提示','格式不符'); 
				return;
			}
			
            formData.append("file", document.getElementById("editUploadFile").files[0]);   
	            $.ajax({
	                url: "${pageContext.request.contextPath }/upload/uploadFile",
	                type: "POST",
	                data: formData,
	                /**
	                *必须false才会自动加上正确的Content-Type
	                */
	                contentType: false,
	                /**
	                * 必须false才会避开jQuery对 formdata 的默认处理
	                * XMLHttpRequest会对 formdata 进行正确的处理
	                */
	                processData: false,
	                success: function (data) {
	                		$("#editPicUrl").val(data.url);
	                		$.post("${pageContext.request.contextPath }/dishes/editDisheById",$("#dishesForm").serialize(),function(data1){
	            				$.messager.alert("提示",data1.message);
	            				if (data1.success) {
	            					location.reload();
	            				}
	            			});
	                }
            });
			
		}
		
	}
	
	//关闭
	function closeEditWindow()
	{
		$('#editDiningTable').window('close');
	}
	
	//关闭
	function closeAddWindow()
	{
		$('#addDishes').window('close');
	}
	
	//添加菜品
	function addDishes()
	{
		$('#addDishesWindow').window('open');
	}
	
	//添加菜品提交
	function add()
	{
		if ($("#addDishesForm").form("validate"))
		{
			var formData = new FormData();
			var path = document.getElementById("uploadFile").value;

			//验证后缀名
			var ret = /\.(?:jpg|jpeg|png)$/i;
			if(ret.test(path) == false){
				$.messager.alert('提示','格式不符'); 
				return;
			}
			
            formData.append("file", document.getElementById("uploadFile").files[0]);   
	            $.ajax({
	                url: "${pageContext.request.contextPath }/upload/uploadFile",
	                type: "POST",
	                data: formData,
	                /**
	                *必须false才会自动加上正确的Content-Type
	                */
	                contentType: false,
	                /**
	                * 必须false才会避开jQuery对 formdata 的默认处理
	                * XMLHttpRequest会对 formdata 进行正确的处理
	                */
	                processData: false,
	                success: function (data) {
	                		$("#picUrl").val(data.url);
	                		$.post("${pageContext.request.contextPath }/dishes/addDishes",$("#addDishesForm").serialize(),function(data1){
	                			$.messager.alert("提示",data1.message);
	                			if (data1.success == "true")
	                			{
	                				window.location.reload();	
	                			}
	                		});
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
						菜品名称:
					</td>
					<td style="width:200px;text-align: right;">
						<input id="search-name" name="name" style="width:100%;" />
					</td>
					<td style="width:80px;text-align: right;">
						菜品类型:
					</td>
					<td style="width:100px;text-align: right;">
						<select id="search-dishesType" name="status" style="width:100%">
									<option value="-1">不限</option>
								<c:forEach items="${list }" var="item">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>
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
		
	<!--修改菜品窗口-->
    <div id="editDiningTable" class="easyui-window" title="修改菜品" collapsible="false" minimizable="false" modal="true" closed="true" resizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 380px; padding: 5px;
        background: #fafafa">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            		<form id="dishesForm" action="${pageContext.request.contextPath }/dining/addDishes">
		                <table cellpadding=3>
		                		<tr>
		                        <td style="width:80px">菜品编号：</td>
		                        <td style="width: 200px">
		                        		<input id="edit-dishesId" readonly="readonly" class="easyui-validatebox" data-options="required:true"  name="id" style="width:100%;" />
		                        </td>
		                    </tr>
		                    <tr>
		                        <td>菜品名称：</td>
		                        <td>
		                        		<input id="edit-name" name="name" style="width:100%;" class="easyui-validatebox" data-options="required:true"></input>  
		                        </td>
		                    </tr>
		                    <tr>
		                        <td>菜品类型：</td>
		                        <td>
		                        		<select id="edit-dishestype" name="dishestype" style="width:100%">
										<c:forEach items="${list }" var="item">
											<option value="${item.id}">${item.name}</option>
										</c:forEach>
		                 			</select>
							   </td>
		                    </tr>
		                    <tr>
		                        <td>原价(元)：</td>
		                        <td>
		                        		<input id="edit-originalPrice" name="originalprice" style="width:100%;" class="easyui-numberbox" data-options="required:true"></input>  
		                        </td>
		                    </tr>
		                    <tr>
		                        <td>现价(元)：</td>
		                        <td>
		                        		<input id="edit-originalPrice" name="price" style="width:100%;" class="easyui-numberbox" data-options="required:true"></input>  
		                        </td>
		                    </tr>
		                    <tr>
		                    		<td>是否打折：</td>
		                    		<td>
		                    			<select id="edit-isvip" name="isvip" style="width: 100%">
										<option value="1" selected="selected">是</option>		                    			
										<option value="0">否</option>		                    			
		                    			</select>
		                    		</td>
		                    </tr>
		                    <tr>
		                    		<td>图片：</td>
		                    		<td>
			                    			<input id="editUploadFile" type="file" name="file" style="width:100%" />
			                    			<input id="editPicUrl" type="hidden" name="pic" style="width:100%" />
		                    		</td>
		                    </tr>
		                    <tr>
		                    		<td>图片预览：</td>
		                    		<td><img id="picImage" onclick="showImageWindow();" style="width:50px;height: 50px;" />
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
    
    <!--添加菜品窗口-->
    <div id="addDishesWindow" class="easyui-window" title="添加菜品" collapsible="false" minimizable="false" modal="true" closed="true" resizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 320px; padding: 5px;
        background: #fafafa">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            		<form id="addDishesForm" method="post">
		                <table cellpadding=3>
		                		<tr>
		                        <td style="width:80px">菜品编号：</td>
		                        <td style="width: 200px">
		                        		<input id="add-dishesId" class="easyui-validatebox" data-options="required:true"  name="id" style="width:100%;" />
		                        </td>
		                    </tr>
		                    <tr>
		                        <td>菜品名称：</td>
		                        <td>
		                        		<input id="add-name" name="name" style="width:100%;" class="easyui-validatebox" data-options="required:true"></input>  
		                        </td>
		                    </tr>
		                    <tr>
		                        <td>菜品类型：</td>
		                        <td>
		                        		<select id="add-dishesType" name="dishesType" style="width:100%">
										<c:forEach items="${list }" var="item">
											<option value="${item.id}">${item.name}</option>
										</c:forEach>
		                 			</select>
							   </td>
		                    </tr>
		                    <tr>
		                        <td>原价(元)：</td>
		                        <td>
		                        		<input id="add-originalPrice" name="originalPrice" style="width:100%;" class="easyui-numberbox" data-options="required:true"></input>  
		                        </td>
		                    </tr>
		                    <tr>
		                        <td>现价(元)：</td>
		                        <td>
		                        		<input id="add-originalPrice" name="price" style="width:100%;" class="easyui-numberbox" data-options="required:true"></input>  
		                        </td>
		                    </tr>
		                    <tr>
		                    		<td>是否打折：</td>
		                    		<td>
		                    			<select id="add-isvip" name="isvip" style="width: 100%">
										<option value="1" selected="selected">是</option>		                    			
										<option value="0">否</option>		                    			
		                    			</select>
		                    		</td>
		                    </tr>
		                    <tr>
		                    		<td>图片：</td>
		                    		<td>
			                    			<input id="uploadFile" type="file" name="file" style="width:100%" />
			                    			<input id="picUrl" type="hidden" name="picUrl" style="width:100%" />
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