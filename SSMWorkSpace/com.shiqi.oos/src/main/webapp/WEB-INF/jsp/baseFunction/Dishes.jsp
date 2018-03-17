<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜品列表</title>
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
	
</head>
<body>
<script type="text/javascript">

function detailDishes(dishesId)
{
	var url = "${pageContext.request.contextPath }/shoppingCar/addToShopCar"
	$.post(url,{"shopId":dishesId},function(data){
		if (data.success)
		{
			
		}
		alert(data.message);
	});
	
}

</script>

<c:if test="${dishesList != null}">
	<c:forEach items="${dishesList }" var="item">
		<div class="itemDiv" style="width:25%;float:left;text-align: center;margin-bottom: 5px;">
			<image src="${pageContext.request.contextPath }/uploadImages/${item.pic }" style="width:100px;height:100px;"></image></br>
			<label>菜品名称:${item.name }</label></br>
			<label><s>原价:${item.originalprice}元</s></label></br>
			<label>会员价:<font color="red">${item.price}元</font></label></br>
			<button onclick="detailDishes('${item.id}');">加入购物车</button></br>
		</div>
	</c:forEach>
	<script type="text/javascript">
		$(function(){
			var divW = $(".itemDiv").width();
			$(".itemDiv").height(divW + 50);
		});
	</script>
</c:if>
</body>
</html>