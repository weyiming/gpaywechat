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
    <div data-role="collapsible-set" data-theme="c" data-content-theme="d" data-collapsed-icon="arrow-r" data-expanded-icon="arrow-d">
        <c:forEach items="${nets}" var="net">
            <div data-role="collapsible">
                <h2>${net.netName}</h2>
                <ul data-role="listview">
                    <li data-icon="false">
                        <h2>${net.netName}</h2>
                        <p><strong>网点电话：</strong>${net.netTel}</p>
                        <p><strong>网点地址：</strong>${net.netAddr}</p>
                        <p><strong>公司名：</strong>${net.companyName}</p>
                        <p><strong>所属公司：</strong>${net.company}</p>
                        <p><strong>公司电话：</strong>${net.companyTel}</p>
                        <p><strong>公司地址：</strong>${net.companyAddr}</p>
                    </li>
                </ul>
            </div>
        </c:forEach>
    </div>
</div>
<div class="center">
    <form id="pageForm" action="NetsGetter" method="get">
        <input type="hidden" value="${currentPage}" id="currentPage"/>
        <input type="hidden" value="" id="page"/>
    </form>
    <jsp:include page="include/netPage.jsp"/>
</div>
<jsp:include page="include/footer.jsp"/>