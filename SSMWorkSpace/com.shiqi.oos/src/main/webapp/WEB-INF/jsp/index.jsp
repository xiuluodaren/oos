<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<!-- 导入easyui类库 -->
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">

	//初始化
	$(function()
	{
		//选项卡
		$('#tabs').tabs({
			 fit:true,
			 border:false,
			 toolPosition:'right'
		});
		
		
		
		//var topMenu = [{"id":"baseFunction","parentId":"1"},{"id":"systemMarger","parentId":"2"}];
		var topMenu = [];
		
		<c:forEach items="${topList }" var="menu">
			topMenu.push({"id":"topMenuId${menu.id }","parentId":"${menu.id}"});
		</c:forEach>
		
		//基础功能 系统管理
		for (var i = 0;i < topMenu.length;i++)
		{
			var idSelect = '#'+topMenu[i].id;
			var url = '${pageContext.request.contextPath }/menu/menuListByParentId?parentId=' + topMenu[i].parentId;
		    $(idSelect).tree({
		        url:url,    
		        formatter: function(data){
			    		return data.menuname;
		        },onClick: function(node){
					if($(idSelect).tree("isLeaf",node.target)){
						var tabs = $("#tabs");
						var tab = tabs.tabs("getTab",node.menuname);
						if(tab){
							tabs.tabs("select",node.menuname);
							// 更新选择的面板的新标题和内容
							$('#tabs').tabs('update', {
								tab: tab,
								options: {
									content:"<iframe src='${pageContext.request.contextPath }"+node.url+"' style='width:100%;height:100%;border:0;'''></iframe>",
								}
							});
						}else{
							tabs.tabs('add',{
							    title:node.menuname,
							    closable:true,
							    bodyCls:"content",
							    content:"<iframe src='${pageContext.request.contextPath }"+node.url+"' style='width:100%;height:100%;border:0;'''></iframe>",
							    tools:[
									{
										iconCls:'icon-reload',
										handler:function()
											{
												// 更新选择的面板的新标题和内容
												var tab = $('#tabs').tabs('getSelected');
												$('#tabs').tabs('update', {
													tab: tab,
													options: {
														content:"<iframe src='${pageContext.request.contextPath }"+node.url+"' style='width:100%;height:100%;border:0;'''></iframe>",
													}
												});
											}
									}]
							});
						}
					}
				},onDblClick:function(node){
					if (node.state == "open")
					{
						$(idSelect).tree("collapse",node.target);
					}else{
						$(idSelect).tree("expand",node.target);
					}
				}
		    });
		}
	});
	
	/*******顶部特效 *******/
	/**
	 * 更换EasyUI主题的方法
	 * @param themeName
	 * 主题名称
	 */
	changeTheme = function(themeName) {
		var $easyuiTheme = $('#easyuiTheme');
		var url = $easyuiTheme.attr('href');
		var href = url.substring(0, url.indexOf('themes')) + 'themes/'
				+ themeName + '/easyui.css';
		$easyuiTheme.attr('href', href);
		var $iframe = $('iframe');
		if ($iframe.length > 0) {
			for ( var i = 0; i < $iframe.length; i++) {
				var ifr = $iframe[i];
				$(ifr).contents().find('#easyuiTheme').attr('href', href);
			}
		}
	};
	// 退出登录
	function logoutFun() {
		$.messager
		.confirm('系统提示','您确定要退出本次登录吗?',function(isConfirm) {
			if (isConfirm) {
				location.href = '${pageContext.request.contextPath }/login/logout';
			}
		});
	}
	// 修改密码
	function editPassword() {
		$('#editPwdWindow').window('open');
	}
	
	//提交修改
	function submitPassword() {
		var oldPwd = $('#txtOldPass').val();
		var newPwd = $('#txtNewPass').val();
		var rePwd = $('#txtRePass').val();
		
		$.post("${pageContext.request.contextPath }/user/updatePassword",{"txtOldPass":oldPwd,"txtNewPass":newPwd,"txtRePass":rePwd},function(data){
			alert(data.message);
			if (data.success == "true"){
				window.location.href = "/";
			}
		});
		
	}
	
	// 版权信息
	function showAbout(){
		$.messager.alert("诗奇自助点餐系统","<a href='mailto:923318726@qq.com'>923318726@qq.com</a>");
	}
	
	//自定义密码验证
	$.extend($.fn.validatebox.defaults.rules, {    
	    equals: {    
	        validator: function(value,param){    
	        	 var rules = $.fn.validatebox.defaults.rules;
	             if (value != $(param[0]).val()) {
	                 rules.equals.message = "两次输入密码不一致";
	                 return false;
	             }
	             
	             return true;   
	        },    
	        message: ''   
	    }    
	}); 
	
	function showImages(picUrl)
	{
		$("#windowImage").attr("src", picUrl);
		$('#imageWindows').window('open');
	}
	
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false"
		style="height:80px;padding:10px;background:url('./images/header_bg.png') no-repeat right;">
		<div id="sessionInfoDiv"
			style="position: absolute;right: 5px;top:10px;">
			<font  size="5">[</font><strong><font color="red" size="5">${user.username }</font></strong><font  size="5">]，欢迎你！</font>
		</div>
		<div style="position: absolute; right: 5px; bottom: 10px; ">
			<a href="javascript:void(0);" class="easyui-menubutton"
				data-options="menu:'#layout_north_pfMenu',iconCls:'icon-ok'">更换皮肤</a>
			<a href="javascript:void(0);" class="easyui-menubutton"
				data-options="menu:'#layout_north_kzmbMenu',iconCls:'icon-help'">控制面板</a>
		</div>
		<div id="layout_north_pfMenu" style="width: 120px; display: none;">
			<div onclick="changeTheme('default');">default</div>
			<div onclick="changeTheme('gray');">gray</div>
			<div onclick="changeTheme('black');">black</div>
			<div onclick="changeTheme('bootstrap');">bootstrap</div>
			<div onclick="changeTheme('metro');">metro</div>
		</div>
		<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
			<div onclick="editPassword();">修改密码</div>
			<div onclick="showAbout();">联系管理员</div>
			<div class="menu-sep"></div>
			<div onclick="logoutFun();">退出系统</div>
		</div>
	</div>
	<div data-options="region:'west',split:true,title:'菜单导航'"
		style="width:150px">
		<div class="easyui-accordion" fit="true" border="false">
			<c:forEach items="${topList }" var="menu">
				<div title="${menu.menuname}" data-options="iconCls:'icon-mini-add'" style="overflow:auto">
					<ul id="topMenuId${menu.id }"></ul>
				</div>
			</c:forEach>
		</div>
	</div>
	<div data-options="region:'center'">
		<div id="tabs">
			<div title="首页" id="subWarp"
				style="width:100%;height:100%;overflow:hidden">
				<iframe src="page/home"
					style="width:100%;height:100%;border:0;">
				</iframe>
			</div>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="height:50px;padding:10px;background:url('./images/header_bg.png') no-repeat right;">
		<table style="width: 100%;">
			<tbody>
				<tr>
					<td style="width: 300px;">
						<div style="color: #999; font-size: 8pt;">
							诗奇自助点餐系统 by <a href="http://xiuluo.site/" target="block">王诗奇</a>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<!--修改密码窗口-->
    <div id="editPwdWindow" class="easyui-window" title="修改密码" collapsible="false" minimizable="false" modal="true" closed="true" resizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 200px; padding: 5px;
        background: #fafafa">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
	                <table cellpadding=3>
	                		<tr>
	                        <td>原密码：</td>
	                        <td><input id="txtOldPass" type="Password" class="txt01 easyui-validatebox" data-options="required:true,validType:'length[4,12]'" /></td>
	                    </tr>
	                    <tr>
	                        <td>新密码：</td>
	                        <td><input id="txtNewPass" type="Password" class="txt01 easyui-validatebox" data-options="required:true,validType:'length[4,12]'" /></td>
	                    </tr>
	                    <tr>
	                        <td>确认密码：</td>
	                        <td><input id="txtRePass" type="Password" class="txt01 easyui-validatebox" data-options="required:true,validType:['equals[\'#txtNewPass\']','length[4,12]']"  /></td>
	                    </tr>
    	            </table>
            </div>
            <div region="south" border="false" style="text-align: right; height: 50px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:submitPassword();" >确定</a> 
                <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
            </div>
        </div>
    </div>

	<!--图片窗口-->
    <div id="imageWindows" class="easyui-window" title="修改菜品" collapsible="false" minimizable="false" modal="true" closed="true" resizable="false"
        maximizable="false" icon="icon-save"  style="width: 500px; height: 500px; padding: 5px;
        background: #fafafa">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            		<img id="windowImage" alt="图片" style="width: 100%;height: 100%" />
            </div>
        </div>
    </div>
	

</body>
</html>