<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="include/meta.html" %>
<title>微信智惠卡绑定结果</title>
<%@ include file="include/header.html" %>
<div id="label">
		<span>绑定<c:if test="${result==1}">成功</c:if><c:if test="${result==0}">失败</c:if></span>
</div>
<div id="main">
	<div class="signin">
	<div id="bindImg"></div>
	<ul>
		<li>
			<c:if test="${result==1}">
				<h4>恭喜,您已经成功绑定微信账户与智慧卡账户！</h4>
			</c:if>
			<c:if test="${result==0}">
				<h4>很遗憾,您的此次绑定失败了！您可以选择继续绑定。</h4>
			</c:if>	
		</li>
		<li>
			<input type="button" value="继续绑定" onclick="window.location='view/accountBind.jsp'"/>
		</li>
	</ul>
	</div>
</div>
<jsp:include page="include/footer.html"/>