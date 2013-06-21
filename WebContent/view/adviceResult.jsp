<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="include/meta.html" %>
<title>微信智惠卡--建议投诉结果</title>
<%@ include file="include/header.html" %>
<div id="label">
    <span>提交成功</span>
</div>
<div id="main">
    <div class="maincontent">
        <div class="contentImg adviceImg"></div>
        <div class="mt10">
            <h4>提交成功！感谢您的建议！我们的工作人员会尽快与您取得联系。</h4>
        </div>
        <div class="mt10">
            <form action="/Advice?openID=${openID}" method="get">
                <input type="submit" value="继 续 反 馈"/>
            </form>
        </div>
    </div>
</div>
<jsp:include page="include/footer.html"/>