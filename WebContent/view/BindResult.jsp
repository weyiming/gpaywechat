<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>微信智惠卡绑定结果</title>
<link rel="stylesheet" href="../resources/css/style.css" style="text/css"/>
</head>
<body>
<div id="header">
	<div id="logo"></div>
	<div id="label">
		<span>账户绑定</span>
	</div>
</div>
<div id="main">
	<div class="signin">
	<div id="bindImg"></div>
	<ul>
		<li>
			<c:if test="">
				<h4>恭喜,您已经成功绑定微信账户与智慧卡账户！</h4>
			</c:if>
			<c:if test="1==1">
				<h4>很遗憾,您的此次失败了！您可以选择继续绑定。</h4>
			</c:if>	
		</li>
		<li>
			<input type="button" value="继续绑定" onclick="window.location='AccountBind.html'"/>
		</li>
	</ul>
	</div>
</div>
<div id="footer">
	版权所有©北京爱农驿站科技服务有限公司
</div>
</body>
</html>