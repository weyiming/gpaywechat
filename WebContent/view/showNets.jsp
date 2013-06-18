<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="include/meta.html" %>
<title>微信智惠卡绑定</title>
<%@ include file="include/header.html" %>
<div id="label">
    <span>账户绑定</span>
</div>
<div id="main">
    <div class="signin">
        <div id="bindImg"></div>
        <form action="/NetsGetter" method="get">
            <input type="hidden" name="openID" value="${openID}"/>
            <ul>
                <li>智慧支付账户：<input type="text" name="gpayAccount"/></li>
                <li>智慧支付密码：<input type="password" name="gpayPassword"/></li>
                <li><input type="submit" value="绑  定"/></li>
            </ul>
        </form>
    </div>
</div>
<jsp:include page="include/footer.html"/>