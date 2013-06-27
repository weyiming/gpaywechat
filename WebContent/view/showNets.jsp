<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="include/meta.html" %>
<title>微信智惠卡--网点查询</title>
<%@ include file="include/header.html" %>
<div id="label">
    <span>网点查询</span>
</div>
<div id="main">
    <ul data-role="listview" data-inset="true">
        <li data-role="list-divider">网点列表</li>
        <c:forEach items="${nets}" var="net">
        <li data-icon="false"><a href="#" onclick="alert('你点击了我！')">
            <h2>${net.netName}</h2>
            <p><strong>地址：</strong>${net.netAddr}</p>
            <p><strong>电话：</strong>${net.netTel}</p>
            <span class="ui-li-count">详细...</span>
        </a></li>
        </c:forEach>
    </ul>
</div>
<div class="center">
    <form id="pageForm" action="/NetsGetter" method="get">
        <input type="hidden" value="${currentPage}" id="currentPage"/>
        <input type="hidden" value="" id="page"/>
    </form>
    <jsp:include page="include/page.jsp"/>
</div>
<jsp:include page="include/footer.jsp"/>