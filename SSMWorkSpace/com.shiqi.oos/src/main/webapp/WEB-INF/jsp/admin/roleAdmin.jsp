<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--  
 * 作者:修罗大人
 * 时间:Feb 6, 2017 5:01:29 PM
 -->
<meta charset="UTF-8" />
<title>角色管理</title>

<!-- 导入easyui类库 -->
<link id="easyuiTheme" rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>

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

</head>
<body>
	<div class="layout_overflow">
		<div class="inner6px submitdata">
			<div class="boxBmargin12 cell">
				<form id="searchform" name="searchform" method="post">
					<table>
						<tr>
							<th width="15%">角色名称</th>
							<td width="20%"><input name="roleName" id="roleNameSearch"
								value="" style="width: 90%" /></td>
							<td><a href="###" class="btn_01"
								onclick="javascript:$('#roleNameSearch').val('');search_role();">清空<b></b></a>
								<a href="###" class="btn_01" onclick="search_role();">查询<b></b></a>
							</td>
						</tr>
					</table>
				</form>
			</div>

			<div id="pagination"></div>

			<div id="ddRole" class="easyui-dialog" title="添加/修改角色信息"
				style="width: 300px; height: 200px;"
				data-options="resizable:true,modal:true,top:50" closed="true">

				<div class="inner6px">
					<div class="cell">
						<form id="roleform" name="roleform" method="post">
							<table>
								<tr>
									<th>角色编号</th>
									<td><input id="roleId" name="roleId"
										class="easyui-validatebox tb"
										data-options="required:true,validType:['charts','length[2,30]'],invalidMessage:'编号至少2个字符,最多30个字符！'" />
									</td>
								</tr>
								<tr>
									<th>角色名称</th>
									<td><input id="roleName" name="roleName"
										class="easyui-validatebox tb"
										data-options="required:true,validType:['charts','length[2,30]'],invalidMessage:'名称至少2个字符,最多30个字符！'" />
									</td>
								</tr>
								<tr>
									<th>同级排序</th>
									<td><input id="zIndex" name="zIndex"
										class="easyui-validatebox tb"
										data-options="required:true,validType:'numRange[0,1000]',invalidMessage:'必须填写0~1000之间的数字'" /></td>
								</tr>
								<tr>
									<td colspan="4">
										<div class="btn_area_setc">
											<a href="###" class="btn_01_mini"
												onclick="javascript:submitrole();" type="submit">保存<b></b></a>
											<a href="###" class="btn_01_mini"
												onclick="javascript:$('#ddRole').dialog('close');">关闭<b></b></a>
										</div>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>

		<div id="ddRoleView" class="easyui-dialog" title="查看角色详细信息"
			style="width: 300px; height: 220px;"
			data-options="resizable:true,modal:true" closed="true"></div>

		<div id="ddRoleUsersView" class="easyui-dialog" title="用户设置"
			style="width: 500px; height: 430px;"
			data-options="resizable:true,modal:true" closed="true">
			<form id="setUsersForm" method="get" action="/role/updateRoleUsers">
				<input id="userIds" type="hidden" name="userIds" /> 
				<input id="roleIdSetUsers" type="hidden" name="roleId" />
			</form>
			<div class="inner6px">
				<div class="popwin_layer_02">
					<div class="btn_area_setc btn_area_bg popwin_layer_02_btnarea"
						style="z-index: 10;">
						<a href="#" class="btn_01" onclick="conflick();" id="confirm">确认<b></b></a>
					</div>

					<div class="popwin_layer_02_left">
						<div class="box_01">
							<div class="caption_01">待选择用户</div>
							<div class="inner6px popwin_layer_02_height"
								style="overflow-y: hidden;">
								<select id="waitSelect" style="height: 100%; width: 100%;"
									multiple="multiple">

								</select>
							</div>
						</div>
					</div>
					<div class="popwin_layer_02_btn">
						<a href="javascript:insertOption_task();" class="user_adbtn_01"></a>
						<a href="javascript:delOption_task();" class="user_adbtn_02"></a>
					</div>
					<div class="popwin_layer_02_right">
						<div class="box_01">
							<div class="caption_01">已选择用户</div>
							<div class="inner6px popwin_layer_02_height"
								style="overflow-y: hidden;">
								<select id="selectedUsers" style="height: 100%; width: 100%;"
									multiple="multiple">

								</select>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div id="ddSetModulesView" class="easyui-dialog" title="模块设置"
			style="width: 200px; height: 370px;"
			data-options="resizable:true,modal:true" closed="true">
			<div style="height: 90%">
				<ul id="setModules"></ul>
			</div>
			<div class="btn_area_setc btn_area_bg popwin_layer_02_btnarea"
				style="z-index: 10; height: 10%">
				<a href="#" class="btn_01" onclick="updateModules();" id="confirm">确认<b></b></a>
			</div>
		</div>

	</div>

</body>
<script type="text/javascript" th:inline="javascript">
	/*<![CDATA[*///这句坚决不能删
	           
	var updateRoleId;
	           
	//自定义验证
	 $.extend($.fn.validatebox.defaults.rules, {    
	    numRange: {    
	        validator: function(value, param){
	            return (value >= param[0] && value <= param[1]);    
	        },
    		message: ''
	    },
	    userName:{
	        validator:function(value,param){
	            var reg = /^\w+$/;
	            if(value.length < 4 || value.length > 30) return false;
	            return reg.test(value);
	        },
	        message:  ''
	    },
	    phoneNum: {
			validator: function(value){
				var rex=/^1[3-8]+\d{9}$/;
				
				if (value.length > 11) return false;
				return rex.test(value);
			},
			message: ''
		}
	});  
	           
	$(function(){
		$("#roleNameSearch").keydown(function(event){
			if (event.keyCode == 13) {
				search_role();
			}
		});
	})
	
	//新增/修改角色提交
	function submitrole()
	{
		$('#roleform').form('submit',{
			url:'${pageContext.request.contextPath }/role/saveOrUpdateRole',
            onSubmit: function(){
                return $(this).form('validate');
            },
            success: function(data){
            		$('#ddRole').dialog('close');
            		var result = JSON.parse(data);
				alert(result.message);				
                if (result.success == "true"){
                    $('#pagination').datagrid('reload');    // reload the user data
                    $('#roleTree').tree('reload');
                }
            }
        });
	}
	
	//修改角色信息
	function edit_role(id)
	{
		$.ajax({
			 url: '${pageContext.request.contextPath }/role/findById?roleId='+id,  
	            dataType: 'json', 
	            method:'GET',
	            contentType : 'application/json;charset=utf-8',
	            success: function (data) {
					$('#roleId').val(data.id);
					$('#roleId').attr("readonly","readonly");
					$('#roleName').val(data.rolename);
					$('#zIndex').val(data.zindex);

					$('#ddRole').dialog('open');
	            }
		});
	}
	
	//查询角色
	function search_role()
	{
		var roleName = $('#roleNameSearch').val();

		var url = "${pageContext.request.contextPath }/role/findRoles";
		
		$('#pagination').datagrid({
			url:url,
			method:'POST',
			queryParams: {
				roleName:roleName
			}
		});
	}
	
	//角色列表
	$('#pagination').datagrid({
		toolbar:[{
			text:'新增',
			iconCls:'icon-add',
			handler:function()
			{
				$('#roleform').form('clear');
				$('#ddRole').dialog('open');
			}
		},'-',{
			text:'删除',
			iconCls:'icon-remove',
			handler:function()
			{
				var selectionsroles = $('#pagination').datagrid('getSelections');
				
				if (selectionsroles.length == 0) {
					$.messager.alert("提示","请选择要删除的角色");
					return;
				}
				
				$.messager.confirm('删除角色', '确定要删除这些角色吗？', function(result){
					if(result)
					{
						var roleIds = [];
						selectionsroles.forEach(function(value,index,array){
							roleIds.push(value.id);
						});
								
						$.ajax({  
				            url: '${pageContext.request.contextPath }/role/deleteRolesByIds',  
				            dataType: 'json', 
				            method:'GET',
				            contentType : 'application/json;charset=utf-8',
				            data:{"roleIds":roleIds.join(",")},
				            success: function (data) {
				            		alert(data.message);
					            	if(data.success == "true"){
					            		$('#pagination').datagrid('reload');
					            	}
				            }  
					    });
					}
				});
			}
		}],
		title:'角色列表',
		pagination:true,
		rownumbers:true,
		fitColumns:true,
		nowrap:false,
		remoteSort:false,
		height:400,
		method:'GET',
		url:'${pageContext.request.contextPath }/role/findEasyUI',
		columns:[[
						  {field:'select-id',checkbox:true},
				          {field:'id',title:'角色编号',width:20},
				          {field:'rolename',title:'角色名',width:20},
						  {field:'zindex',title:'排序',width:20,sortable:'true',
					    	  	sorter:function(a,b){  
									return parseInt(a) > parseInt(b) ? 1 : -1; 
							  }},
						  {field:'op',title:'操作',width:20,formatter:function(value,rec){
			          			return "<a href='#' class='btn_01_mini' onclick='edit_role(\""+rec.id+"\")'>修改<b></b></a>"
		          				  +"<a href='#' class='btn_01_mini' onclick='module_set(\""+rec.id+"\")'>模块设置<b></b></a>"
		          				  +"<a href='#' class='btn_01_mini' onclick='userrole_set(\""+rec.id+"\")'>用户设置<b></b></a>";
					  }}
		        ]],
		onDblClickRow:function(index,data){
			edit_role(data.id);
		}
	});
	
	//模块设置
	function module_set(id)
	{
		updateRoleId = id;
		$('#setModules').tree({
			url: '${pageContext.request.contextPath }/menu/findTree',
			checkbox:true,
			cascadeCheck:true,
			formatter:function(node){
	    			return node.menuname;
	    		},onLoadSuccess:function(node,data){
		    		$.ajax({  
			            url: "${pageContext.request.contextPath }/role/findMenuByRoleId?roleId="+id,
			            dataType: 'json', 
			            method:'GET',
			            contentType : 'application/json;charset=utf-8',
			            success: function (modules) {
			            	var rootNode = $('#setModules').tree('getRoot');
			            	var treeNodes =$('#setModules').tree('getChildren',rootNode.target) ;
			                 for(var i=0;i<modules.length;i++){
		         	            for (var j = 0;j < treeNodes.length;j++)
		                     	{
		         	            	if(treeNodes[j].id == modules[i].id)
		         					{
		         	            		$('#setModules').tree('check',treeNodes[j].target);
		         					}
		                     	}
			                 }
			            }  
				    });
		    	}
		});
		
		$('#ddSetModulesView').dialog('open');
	}
	
	//模块设置提交
	function updateModules()
	{
		var selecteds = $('#setModules').tree('getChecked');
		var selectedIds = [];
		selecteds.forEach(function(value,index,array){
			selectedIds.push(value.id);
		});

		// 创建一个 form  
	    var form1 = document.createElement("form");  
	    form1.id = "formTemp";  
	    form1.name = "form";  
	  
	    // 添加到 body 中  
	    document.body.appendChild(form1);  
	  
	    // 创建一个输入  
	    var input = document.createElement("input");  
	    // 设置相应参数  
	    input.type = "hidden";  
	    input.name = "roleId";  
	    input.value = updateRoleId;
	  
	    // 将该输入框插入到 form 中  
	    form1.appendChild(input);  
	  
		 // 创建一个输入  
	    var input1 = document.createElement("input");  
	    // 设置相应参数  
	    input1.type = "hidden";  
	    input1.name = "menuIds";  
	    input1.value = selectedIds.join(",");
	  
	    // 将该输入框插入到 form 中  
	    form1.appendChild(input1); 
	    
	    // form 的提交方式  
	    form1.method = "GET";  
	    // form 提交路径  
	    form1.action = "${pageContext.request.contextPath }/role/updateRoleMenus";
	  
	    // 对该 form 执行提交  
	    $('#formTemp').form('submit',{
            success: function(data){
				data = JSON.parse(data);
	            	alert(data.message);
				if(data.success == "true"){
	            		$('#ddSetModulesView').dialog('close');
	            	}
            }
        }); 
	    // 删除该 form  
	    document.body.removeChild(form1);
	}
	
	//设置用户
	function userrole_set(id)
	{
		$('#ddRoleUsersView').dialog('open');
		$('#roleIdSetUsers').val(id);
 		$.ajax({  
		       url: '${pageContext.request.contextPath }/user/findAll',
		       dataType: 'json', 
		       method:'GET',
		       contentType : 'application/json;charset=utf-8',
		       success: function (users) {
		       	var options = document.getElementById("waitSelect").options;	
		       	for (var i = 0;i < options.length;i++){
		       		options[i] = null;
		       		i--;
		       	}
		       	users.forEach(function(value,index,array){
		       		options.add(new Option(value.username,value.id));
		       	});
		       }  
		});
		
		//已选择用户
		$.ajax({  
            url: '${pageContext.request.contextPath }/role/findUsersByRoleId?roleId='+id,
            dataType: 'json', 
            method:'GET',
            contentType : 'application/json;charset=utf-8',
            success: function (users) {
	            	var options = document.getElementById("selectedUsers").options;	
	            	for (var i = 0;i < options.length;i++){
	            		options[i] = null;
	            		i--;
	            	}
	            	users.forEach(function(value,index,array){
	            		options.add(new Option(value.username,value.id));
	            	});
            }  
	    });
	}
	
    function conflick(){
	    	var selectedUsers = document.getElementById("selectedUsers").options;	
	    	var userIds = [];
	    	for(i=0;i<selectedUsers.length;i++)
	    	{
	    		userIds.push(selectedUsers[i].value);
		}
	    	
        $('#userIds').val(userIds);
        
        $('#setUsersForm').form('submit',{
            success: function(data){
            	$('#ddRoleUsersView').dialog('close');
				data = JSON.parse(data);
				$.messager.show({
                    title: '提示',
                    msg: data.message
                });
            }
        });
    }

    function insertOption_task() {
    	
	    	var waitSelect = document.getElementById("waitSelect").options;	
	    	var selectedUsers = document.getElementById("selectedUsers").options;	
	    	for (var i = 0;i < waitSelect.length;i++){
	    		if(waitSelect[i].selected)
			{
	    			var aFlag = false;
	    			for (var j = 0;j < selectedUsers.length;j++)
	    			{
		    	    		if (selectedUsers[j].value == waitSelect[i].value)
	   	    			{
	   	    				aFlag = true;
	   	    				break;
	   	    			}
		    	    	}
		    	    	if(aFlag == false)
				{
    	    				selectedUsers.add(new Option(waitSelect[i].text,waitSelect[i].value));
				}
	    	    	
	    	    			waitSelect[i].selected = false;
				}
	    	}
    	
	}
	function delOption_task() {
		var selectedUsers = document.getElementById("selectedUsers").options;	
	    	for (var i = 0;i < selectedUsers.length;i++){
	    		if(selectedUsers[i].selected)
   			{
	    			selectedUsers[i] = null;
	    			i--;
   			}
	    	}
	}
	
	/*]]>*///这句坚决不能删
</script>
</html>