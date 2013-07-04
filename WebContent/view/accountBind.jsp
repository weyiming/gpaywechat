<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include/meta.html" %>
<title>微信智惠卡--绑定</title>
<%@ include file="include/header.html" %>
<div id="label">
		<span>账户绑定</span>
</div>
<div id="main">
	<div class="maincontent">
	<div class="contentImg bindImg"></div>
	<form action="AccountBind" method="post">
        <input type="hidden" name="openID" value="${openID}"/>
        <div class="mt5" data-role="fieldcontain">
	        <label for="gpayAccount">智惠卡卡号：</label>
            <input type="text" name="gpayAccount" id="gpayAccount" placeholder="16位卡号"/>
        </div>
        <div class="mt10" data-role="fieldcontain">
	        <label for="gpayPassword">智惠卡密码：</label>
            <input type="password" name="gpayPassword" id="gpayPassword" placeholder="6位密码"/>
        </div>
        <div class="mt10">
            <input type="submit" value="绑  定" />
        </div>
	</form>
	</div>
</div>
<jsp:include page="include/footer.jsp"/>