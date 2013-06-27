<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="include/meta.html" %>
<title>微信智惠卡--商户查询</title>
<%@ include file="include/header.html" %>
<div id="label">
    <span>商户查询</span>
</div>
<div id="main">
        <ul data-role="listview" data-inset="true">
            <li data-role="list-divider">商户列表</li>
            <c:forEach items="${merchants}" var="merchant">
                <li data-icon="false"><a href="#" onclick="alert('你点击了我！')">
                    <h2>${merchant.name}</h2>
                    <p><strong>地址：</strong>${merchant.address}</p>
                    <p><strong>电话：</strong>${merchant.tel}</p>
                    <span class="ui-li-count">详细...</span>
                </a></li>
            </c:forEach>
        </ul>
</div>
<div class="center">
    <form id="pageForm" action="/MerchantsGetter" method="get">
        <input type="hidden" value="${currentPage}" id="currentPage"/>
        <input type="hidden" value="bj" name="city"/>
        <input type="hidden" name="page" id="page"/>
    </form>
    <jsp:include page="include/page.jsp"/>
</div>
<jsp:include page="include/footer.jsp"/>