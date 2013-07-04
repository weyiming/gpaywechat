<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="include/meta.html" %>
<title>微信智惠卡--绑定结果</title>
<%@ include file="include/header.html" %>
<div id="label">
		<span>绑定<c:if test="${result==1}">成功</c:if><c:if test="${result==0}">失败</c:if></span>
</div>
<div data-role="content">
<div id="main">
	<div class="maincontent">
        <div class="contentImg bindImg"></div>
	    <div class="mt10">
        <c:if test="${result==1}">
			<h4>恭喜,您已经成功绑定微信账户与智惠卡账户！</h4>
		</c:if>
		<c:if test="${result==0}">
			<h4>很遗憾,您的此次绑定失败了！您可以选择继续绑定。</h4>
		</c:if>
        </div>
        <div class="mt10">
		    <form action="/AccountBind?openID=${openID}" method="get">
                <input type="submit" value="继 续 绑 定"/>
            </form>
        </div>
	</div>
</div>
</div>
<jsp:include page="include/footer.jsp"/>