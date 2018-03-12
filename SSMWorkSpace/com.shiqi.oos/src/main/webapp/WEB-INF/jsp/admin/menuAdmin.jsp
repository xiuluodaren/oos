<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!--  
 * 作者:修罗大人
 * 时间:Feb 6, 2017 5:01:29 PM
 -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模块管理</title>

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

</head>
<body>
	<div class="page_content">
		<div class="layout_01_left">
			<div class="layout_overflow">
				<ul id="mt"></ul>
			</div>
		</div>
		
		<div class="layout_01_right">
			<div class="layout_overflow">
				<div class="inner6px submitdata">
					<!-- 条件搜索表单 -->
					<div class="boxBmargin12 cell">
						<form id="searchform" name="searchform" method="post">
							<input id="parentId" type="hidden" name="parentId" value="" />
							<table>
								<tbody>
									<tr>
										<th width="15%">模块名</th>
										<td width="35%">
										<input class="input_text" name="menuName" id="menuName" value="" type="text" />
										</td>
										<td colspan="4">
											<div class="btn_area_setc">
												<a href="###" class="btn_01"
													onclick="javascript:$('#menuName').val('');search_menu();">清空<b></b></a> <a
													href="###" class="btn_01" onclick="search_menu();">查询<b></b></a>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</form>
					</div>
					
					<!-- 模块列表 -->
					<div id="dgmenu"></div>

					<div id="ddmenu" class="easyui-dialog"  title="新增模块信息"
						style="width: 440px;  height: 240px;"
						data-options="resizable:true,modal:true"
						closed="true">
						
							<div class="inner6px">
								<div class="cell">
									<form id="menuForm"  method="post" >
										<input id="menuNewId" type="hidden" name="id" />
										<table>
											<tr>
												<th width="15%">模块名称</th>
												<td width="35%">
												<input id="menuNameNew" name="menuName"  class="easyui-validatebox tb" 
												data-options="required:true,validType:['charts','length[1,30]'],invalidMessage:'模块名称最长30位'"  /></td>
											</tr>
											<tr>
												<th>链接地址</th>
												<td>
													<input id="url" name="url" style="width:90%;" class="easyui-validatebox tb" data-options="required:true"/>
												</td>
											</tr>
											<tr>
												<th>上级模块</th>
												<td>
													<input id="parentmenuNew" name="parentId" type="hidden" /> 
													<table>
														<tr style="border-width: 0px;">
															<td style="padding:0" ><input id="parentmenuName" readonly="readonly"/> </td>
															<td><a id="btn" onclick="search_parentmenu()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查找</a></td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<th>同级排序</th>
												<td><input id="zindex" name="zindex"  class="easyui-numberbox tb"  data-options="validType:'numRange[0,1000]',invalidMessage:'必须填写0~1000之间的数字'"  /></td>
											</tr>
											<tr>
												<td colspan="4">
													<div class="btn_area_setc">
														<a onclick="javascript:submitmenu();" class="btn_01"  type="submit">保存<b></b></a>
														<a href="###"  class="btn_01"
															onclick="javascript:$('#ddmenu').dialog('close');">关闭<b></b></a>
													</div>
												</td>
											</tr>
										</table>
									</form>
								</div>
							</div>
					</div>

					<div id="parentmenuDd" class="easyui-dialog" title="查找上级模块" 
						style="width: 200px;  height: 350px;" data-options="resizable:true,modal:true,top:50"
						closed="true">
						<ul id="parentmenuTree"></ul>
					</div>
					 
					 <div id="setmenuRoles" class="easyui-dialog" title="设置用户角色" 
						style="width:680px;  height: 300px;" data-options="resizable:true,modal:true,top:50"
						closed="true">
						<div class="inner6px">
							<form id="setmenuRolesForm" method="get" action="${pageContext.request.contextPath }/menu/updateMenuRoles">
								<input type="hidden" id="roleCodes" name="roleIds" /> 
								<input type="hidden" id="setmenuRoleId" name="menuId" />
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
													<a href="javascript:RemoveSelect();"
														class="btn_01_ultra_mini" style="width: 40px"><b></b>×</a>
													<a href="javascript:confirm_onclick();"
														class="btn_01_ultra_mini" style="width: 40px"><b></b>√</a>
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
					 
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript" th:inline="javascript">
	/*<![CDATA[*///这句坚决不能删
	       
	//自定义数字范围验证
	 $.extend($.fn.validatebox.defaults.rules, {    
	    numRange: {    
	        validator: function(value, param){
	            return (value >= param[0] && value <= param[1]);    
	        },
    		message: ''
	    }    
	});           

	$('#dgmenu').datagrid({
		toolbar:[{
			text:'新增',
			iconCls:'icon-add',
			handler:function()
			{
				$('#ddmenu').dialog({
					title:"新增模块"
				});
				$('#ddmenu').dialog('open');
				
				$("#menuForm").form("clear");
			}
		},'-',{
			text:'删除',
			iconCls:'icon-remove',
			handler:function()
			{
				var rows = $('#dgmenu').datagrid("getSelections");
				
				if (rows == null || rows.length == 0) {
					$.messager.confirm("提示","请选择要删除的模块",function(data){
						
					});
					return;
				}
				
				$.messager.confirm('删除员工', '确定要删除这些模块吗？', function(result){
					if(result)
					{
						var selectionsmenus = $('#dgmenu').datagrid('getSelections');
						var menuIds = [];
						selectionsmenus.forEach(function(value,index,array){
							menuIds.push(value.id);
						});
						$.ajax({  
				            url: '${pageContext.request.contextPath }/menu/deleteMenusByIds',  
				            dataType: 'json', 
				            method:'GET',
				            contentType : 'application/json;charset=utf-8',
				            data:{"ids":menuIds.join(",")},
				            success: function (data) {
				            		alert(data.message);
					            	if(data.success == "true"){
					            		$('#dgmenu').datagrid('reload');
					            		$('#mt').tree('reload');
					            	}
				            }  
					    });
					}
				});
			}
		}],
		title:'模块列表',
		pagination:true,
		rownumbers:true,
		fitColumns:true,
		nowrap:false,
		height:380,
		remoteSort:false,
		method:'GET',
		url : '${pageContext.request.contextPath }/menu/findEasyUI',
		columns:[[
				          {field:'id',checkbox:true},
				          {field:'menuname',title:'模块名称',width:15},
					      {field:'url',title:'链接地址',width:40},
					      {field:'parentMenu',title:'上级模块',width:15,formatter:function(value,row){
					      	if(value != null)
							{
					    		return value.menuname;
							}
					      }},
					      {field:'zindex',title:'模块排序',width:10,sortable:'true',
					    	  	sorter:function(a,b){  
								return parseInt(a) > parseInt(b) ? 1 : -1; 
						  }},
				          {field:'op',title:'操作',width:20,formatter:function(value,rec){
								return "<a href='#' class='btn_01_mini' onclick='edit_menu(\""+rec.id+"\")'>修改<b></b></a>"
										  +"<a href='#' class='btn_01_mini' onclick='editmenuRoles(\""+rec.id+"\")'>角色设置<b></b></a>";
							}}
				        ]]
	});
	
	//查询
	function search_menu()
	{
		var parentId = $('#parentId').val() == "" ? null : $('#parentId').val() ;
		var menuName = $('#menuName').val() == "" ? null : $('#menuName').val();

		var url = "${pageContext.request.contextPath }/menu/findByMenuname?parentId="+(parentId == null ? "" : parentId)+"&menuname="+(menuName == null ? "" : menuName);
		
		if (parentId == null && menuName == null)
		{
			url = "${pageContext.request.contextPath }/menu/findEasyUI"	
		}
		
		$('#dgmenu').datagrid({
			url:url
		});
	}
	
	//角色设置
	function editmenuRoles(id)
	{
		$('#setmenuRoles').dialog('open');
		$('#setmenuRoleId').val(id);
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
	    	     	  if(checked){
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
		            url: '${pageContext.request.contextPath }/menu/findRolesByMenuId?menuId='+id,  
		            dataType: 'json', 
		            method:'GET',
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
	
	 function RemoveSelect()
	 {
        var options = document.getElementById("roleIds").options;
        var i;
        var treeNodes = $('#roleTree').tree('getRoots');
        
        for (var j = 0;j < treeNodes.length;j++)
		{
           		$('#roleTree').tree('uncheck',treeNodes[j].target);
		}
	}

    function confirm_onclick()
    {
	    	var options = document.getElementById("roleIds").options; 
	    	var roleCodes = [];
    		for(i=0;i<options.length;i++){
    			roleCodes.push(options[i].value);
        }
        $('#roleCodes').val(roleCodes.join(","));
        
        $('#setmenuRolesForm').form('submit',{
            success: function(data){
	            	$('#setmenuRoles').dialog('close');
	            	data = JSON.parse(data);
	            $.messager.show({
	                title: '提示',
	                msg: data.message
	           	});
       		 }
        });
    }
	
	//新增/修改模块提交
	function submitmenu()
	{
		$('#menuForm').form('submit',{
            url: '${pageContext.request.contextPath }/menu/save',
            onSubmit: function(){
                return $(this).form('validate');
            },
            success: function(resultJSON){
	            	$('#ddmenu').dialog('close');
	            	var result = JSON.parse(resultJSON);
	            	alert(result.message);
                if (result.success == "true"){
                    $('#dgmenu').datagrid('reload');    // reload the user data
                    $('#mt').tree('reload');
                }
            }
        });
	}
	
	//修改模块信息
	function edit_menu(id)
	{
		$.ajax({  
	            url: '${pageContext.request.contextPath }/menu/findById?menuId='+id,  
	            dataType: 'json', 
	            method:'GET',
	            contentType : 'application/json;charset=utf-8',
	            success: function (data) {
	            		$('#menuNewId').val(data.id);
	            		$('#menuNameNew').val(data.menuname);
					$('#url').val(data.url);
					$('#zindex').val(data.zindex);
					
					if (data.parentMenu != null)
					{
						$('#parentmenuNew').val(data.parentMenu.id);
						$('#parentmenuName').val(data.parentMenu.menuname); 
					}
					
					$('#ddmenu').dialog({
						title:"修改模块"
					});
					$('#ddmenu').dialog('open');
	            }  
	     });
	}
	
	//查找上级模块
	function search_parentmenu()
	{
		$('#parentmenuTree').tree({
			url: '${pageContext.request.contextPath }/menu/findTree',
	        onClick:function(node){
		        	$('#parentmenuNew').val(node.id);
		        	
		        	if(node.id == '001')
		    		{
		        		$('#parentmenuNew').val('');
		    		}
		        	
		        	$('#parentmenuName').val(node.menuname);
				$('#parentmenuDd').dialog('close');
		      	},formatter:function(node){
		    			return node.menuname;
		    	},onLoadSuccess:function(node, data){
		    		$('#parentmenuDd').dialog('open');
		    	}
		});
	}
	
	//模块
	$('#mt').tree({
		url: '${pageContext.request.contextPath }/menu/findTree',
        onClick:function(node){
        		$('#parentId').val(node.id);
	        	var url = '${pageContext.request.contextPath }/menu/menuListByParentId?parentId=';
	        	if (node.id != null) {
				url += node.id;
			}else{
				url = "${pageContext.request.contextPath }/menu/findEasyUI"
			}
	        	$('#dgmenu').datagrid({
	    			url:url
	    		});
      	},formatter:function(node){
	    		return node.menuname;
	    	},onDblClick:function(node){
	    		if (node.state == "open") {
		    		$('#mt').tree("collapse",node.target);
			}else{
		    		$('#mt').tree("expand",node.target);
			}
		}
	});
    
	$(function(){
		$("#menuName").keydown(function(event){
			if (event.keyCode==13) {
				search_menu();
			}
		});
	});
	
	
	/*]]>*///这句坚决不能删
</script>

</html>