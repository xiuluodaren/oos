<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
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
	
<!-- oldcss -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/oldcss/base.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/oldcss/animation1.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/oldcss/form.css" />

<script type="text/javascript">

	$(function(){
		$('#datagrid').datagrid({
			title:'用户',
			width:500,
			height:250,
			remoteSort:false,
			nowrap:false,
			fitColumns:true,
			pagination:true,
			fit:true,
			url:'${pageContext.request.contextPath }/user/findEasyUI',
			columns:[[
				{field:'selected',checkbox:'true'},
				{field:'id',title:'用户编号',width:10,sortable:'true',
					formatter: function(value,row,index){
						return row.id;
				},sorter:function(a,b){  
					return parseInt(a) > parseInt(b) ? 1 : -1; 
				}},
				{field:'username',title:'用户名',width:10,
					formatter: function(value,row,index){
						return row.username;
				}},
				{field:'isvip',title:'是否会员',width:10,
					formatter: function(value,row,index){
						//是否会员，1 是  0 否
						switch (row.isvip) {
						case "0":
							{
								return "否";
							}
						case "1":
							{
								return "是";
							}
						default:
							break;
						}
				}},
				{field:'op',title:'操作',width:10,
					formatter: function(value,row,index){
						var retStr = "<a href='#' class='btn_01_mini' onclick='editUserRoles(\""+row.id+"\")'>角色设置<b></b></a>";
					  	return retStr;	
					}
				}
			]],
			toolbar:'#tb',
			onDblClickRow:function(index,data){
				$("#userId").val(data.id);
				$("#userForm").form("load",data);
				$('#editUser').window('open');
			}
		});
		
		$(document).keydown(function(event){
			if(event.keyCode==13){
				search_user();
			}
		});
		
	});

	//查询用户
	function search_user()
	{
		var searchId = $("#search-id").val();
		var searchUsername = $("#search-username").val();
		var searchisvip = $("#search-isvip").val();
		var url = "${pageContext.request.contextPath }/user/search?id=" + searchId + "&username=" + searchUsername + "&isvip=";
		
		if (searchisvip == "0" || searchisvip == "1") {
			url += searchisvip;
		}
		
		$('#datagrid').datagrid({
			url:url
		});
		
	}
	
	//重置查询条件
	function resetSearch_user()
	{
		$("#search-id").val("");
		$("#search-username").val("");
		$("#search-isvip").val("2");
		
		$('#datagrid').datagrid({
			url:"${pageContext.request.contextPath }/user/findEasyUI"
		});
	}
	
	//删除用户
	function deleteUsers()
	{
		var rows = $('#datagrid').datagrid("getSelections");
		
		if (rows == null || rows.length == 0) {
			$.messager.confirm("提示","请选择要删除的用户",function(data){
				
			});
		}else{
			var ids = "";

			$.each( rows, function(index, value){
				  ids = ids + value.id + ",";
			});
			
			ids = ids.substring(0,ids.length - 1);
			
			$.messager.confirm("提示","是否确定删除" + ids + "用户",function(r){
				if(r)
				{
					$.post("${pageContext.request.contextPath }/user/deleteByIds",{"ids":ids},function(data){
						alert(data.message);
						
						if (data.success == 'true') {
							location.reload();
						}
					});
				}
			});

		}
	}
	
	//修改用户
	function editUser()
	{
		var rows = $('#datagrid').datagrid("getSelections");
		
		if (rows == null || rows.length == 0 || rows.length > 1) {
			$.messager.confirm("提示","请选择一个要修改的用户",function(data){
				
			});
		}else{
			$("#userId").val(rows[0].id);
			$("#userForm").form("load",rows[0]);
			$('#editUser').window('open');
		}
		
	}
	
	//重置密码
	function reset(){
		$.messager.confirm("提示","确定重置密码吗?",function(r){
			if (r){
				$.post("${pageContext.request.contextPath }/user/resetPasswrod",{"userId":$("#userId").val()},function(data){
					alert(data.message);
					$('#editUser').window('close');
				});
			}
		});
	}
	
	//修改用户提交
	function edit()
	{
		var json = $("#userForm").serialize();
		$.post("${pageContext.request.contextPath }/user/editById",json,function(data){
			alert(data.message);
			if (data.success) {
				location.reload();
			}
		});
	}
	
	//关闭
	function closeEditWindow()
	{
		$('#editUser').window('close');
	}
	
	//关闭
	function closeAddWindow()
	{
		$('#addUser').window('close');
	}
	
	//添加用户
	function addUser()
	{
		$("#addUserForm").form("clear");
		$("#addisvip").val("0");
		$('#addUser').window('open');
	}
	
	//添加用户提交
	function add()
	{
		if ($("#addUserForm").form("validate"))
		{
			var json = $("#addUserForm").serialize();
			
			$.post("${pageContext.request.contextPath }/user/addUser",json,function(data){
				alert(data.message);
				if (data.success == "true") {
					location.reload();
				}
			});
		}
	}
	
	//设置角色
	function editUserRoles(id){
		$('#setUserRoles').dialog('open');
		$('#setUserRoleId').val(id);
		var options = document.getElementById("roleIds").options; 

		for(var i = 0;i < options.length;i++){
			options[i] = null;
			i--;
		}

        $('#roleTree').tree({
             checkbox: true,
             url: '${pageContext.request.contextPath }/role/findAll',
         	 onCheck:function(node,checked){
	    	           var options = document.getElementById("roleIds").options;  
	    	           var id = node.id;
	    	     	  if(checked)
	    	     	  {
	    	               var i,bFlag;
					   for (i=0; i < options.length; i++){
						   if(options[i].value==id){
							   bFlag=true;
							   break;
					       }	
					   }
					   if(!bFlag){
						   options.add(new Option(node.rolename,id));
						}
	    	           }else {
		    	           var i;
		    	           for (i=0; i < options.length; i++) {
			    	            if(options[i].value==id){
				    	            options[i] = null;
		                        i--;
		                    }	
		                }
	    	           }
	    	           node.checked = checked;  
       		 },formatter:function(node){
         		return node.rolename;
         	},onLoadSuccess:function(node, data){
         		$.ajax({  
		            url: '${pageContext.request.contextPath }/user/findRolesByUserId?userId='+id,  
		            dataType: 'json', 
		            method:'POST',
		            contentType : 'application/json;charset=utf-8',
		            success: function (roles) {
		            	 	var options = document.getElementById("roleIds").options;
		                 var treeNodes = $('#roleTree').tree('getRoots');
		                 for(var i=0;i<roles.length;i++){
	         	            for (var j = 0;j < treeNodes.length;j++)
	                     	{
	         	            		if(treeNodes[j].id == roles[i].id)
	         					{
	         	            			options.add(new Option(roles[i].rolename,roles[i].id));
	         	            			$('#roleTree').tree('check',treeNodes[j].target);
	         					}
	                     	}
		                 }
		            }
         		});
         	}
         });   
     }
	
	function RemoveSelect(){

        var options = document.getElementById("roleIds").options;
        var treeNodes = $('#roleTree').tree('getRoots');
        for (var j = 0;j < treeNodes.length;j++)
        	{
        		$('#roleTree').tree('uncheck',treeNodes[j].target);
     	}
        //删除所有
        options.length=0;
    }

    function confirm_onclick(){

	    	var options = document.getElementById("roleIds").options; 
	    	/* if (options.length > 1) {
	    		$.messager.alert('提示','只能给用户分配一个角色！');
	    		return;
	    	} */
	    	var roleCodes = [];
	    	for(i=0;i<options.length;i++){
	    		roleCodes.push(options[i].value);
	    }
        $('#roleCodes').val(roleCodes.join(","));
        
        $('#setUserRolesForm').form('submit',{
            success: function(data){
	            	$('#setUserRoles').dialog('close');
	            	data = JSON.parse(data);
                    $.messager.show({
                        title: '提示',
                        msg: data.message
                    });
            }
        });
    }
	
</script>

</head>
<body>
		<table id="datagrid"></table>
		
		<div id="tb">
			<table style="height: 100%;width: 900px;">
				<tr>
					<td style="width:50px;text-align: right;">查询:</td>
					<td style="width:80px;text-align: right;">
						用户编号:
					</td>
					<td style="width:200px;text-align: right;">
						<input id="search-id" name="id" style="width:100%;" />
					</td>
					<td style="width:50px;text-align: right;">
						用户名:
					</td>
					<td style="width:200px;text-align: right;">
						<input id="search-username" name="username" style="width:100%;" />
					</td>
					<td style="width:80px;text-align: right;">
						是否会员:
					</td>
					<td style="width:70px;text-align: right;">
						<select id="search-isvip" name="isvip">
								<option value="2">不限</option>
                     			<option value="0">否</option>
                     			<option value="1">是</option>
	                 	</select>
					</td>
					<td style="width:120px;text-align: right;">
						<a href="###" class="btn_01" onclick="search_user();">查询<b></b></a>
					</td>
					<td style="width:120px;text-align: right;">
						<a href="###" class="btn_01" onclick="resetSearch_user();">重置<b></b></a
					</td>
				</tr>
			</table>
			<a href="javascript:addUser();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
			<a href="javascript:editUser();" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
			<a href="javascript:deleteUsers();" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
		</div>
		
	<!--修改用户窗口-->
    <div id="editUser" class="easyui-window" title="修改用户" collapsible="false" minimizable="false" modal="true" closed="true" resizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 180px; padding: 5px;
        background: #fafafa">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            		<form id="userForm">
		                <table cellpadding=3>
		                		<tr>
		                        <td>用户编号：</td>
		                        <td><input id="userId" readonly="readonly" name="userId" style="width:150px;" /></td>
		                    </tr>
		                    <tr>
		                        <td>用户名：</td>
		                        <td>
		                        		<input id="username" name="username" style="width:150px;" class="easyui-validatebox" data-options="required:true,min:0,max:20"></input>  
		                        </td>
		                    </tr>
		                    <tr>
		                        <td>是否会员：</td>
		                        <td>
		                        		<select id="isvip" name="isvip" style="width:150px;">
			                     		<option value="0">否</option>
			                     		<option value="1">是</option>
				                 	</select>
							   </td>
		                    </tr>
	    	            </table>
    	            </form>
            </div>
            <div region="south" border="false" style="text-align: right; height: 50px; line-height: 30px;">
                <a class="easyui-linkbutton" icon="icon-ok" href="javascript:reset();" >重置密码</a> 
                <a class="easyui-linkbutton" icon="icon-ok" href="javascript:edit();" >确定</a> 
                <a class="easyui-linkbutton" icon="icon-cancel" href="javascript:closeEditWindow();">取消</a>
            </div>
        </div>
    </div>
    
	<!--添加用户窗口-->
    <div id="addUser" class="easyui-window" title="添加用户" collapsible="false" minimizable="false" modal="true" closed="true" resizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 160px; padding: 5px;
        background: #fafafa">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            		<form id="addUserForm">
		                <table cellpadding=3>
		                    <tr>
		                        <td>用户名：</td>
		                        <td>
		                        		<input id="addusername" name="username" style="width:150px;" class="easyui-validatebox" data-options="required:true,min:0,max:20"></input>  
		                        </td>
		                    </tr>
		                    <tr>
		                        <td>是否会员：</td>
		                        <td>
		                        		<select id="addisvip" name="isvip" style="width:150px;">
			                     		<option value="0">否</option>
			                     		<option value="1">是</option>
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

	<!-- 设置角色 -->
	<div id="setUserRoles" class="easyui-dialog" title="设置用户角色"
		style="width: 700px; height: 400px;"
		data-options="resizable:true,modal:true" closed="true">
		<div class="inner6px">
			<form id="setUserRolesForm" method="get"
				action="${pageContext.request.contextPath }/user/updateUserRoles">
				<input type="hidden" id="roleCodes" name="roleIds" /> <input
					type="hidden" id="setUserRoleId" name="userId" />
				<div class="popwin_layer_01">
					<div class="popwin_layer_01_left">
						<div class="box_01">
							<div class="caption_01">待选择角色</div>
							<div class="inner6px popwin_layer_01_height">
								<ul id="roleTree"></ul>
							</div>
						</div>
					</div>
					<div class="popwin_layer_01_right">
						<div class="box_01">
							<div class="caption_01">
								已选择角色
								<div class="right">
									<a href="javascript:RemoveSelect();" class="btn_01_ultra_mini"
										style="width: 40px"><b></b>×</a> 
									<a href="javascript:confirm_onclick();" class="btn_01_ultra_mini"
										style="width: 40px"><b></b>√</a>
								</div>
							</div>
							<div class="inner6px popwin_layer_01_height">
								<select id="roleIds" style="width: 100%; height: 100%;"
									multiple="multiple" name="roleIds">

								</select>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>


</body>
</html>