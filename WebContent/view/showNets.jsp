<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="include/meta.html" %>
<title>微信智惠卡--网点查询</title>
<%@ include file="include/header.html" %>
<div id="label">
    <span>购卡网点</span>
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
    <p>
        <a href="#" data-role="button" data-inline="true">上一页</a>
        <label>第1页/共10页</label>
        <a href="#" data-role="button" data-inline="true">下一页</a>
    </p>

</div>
<jsp:include page="include/footer.html"/>