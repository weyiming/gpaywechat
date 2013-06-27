<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="include/meta.html" %>
<title>微信智惠卡--建议投诉</title>
<%@ include file="include/header.html" %>
<div id="label">
    <span>建议投诉</span>
</div>
<div id="main">
    <div class="maincontent">
        <div class="contentImg adviceImg"></div>
        <form action="/AdvicePost" method="post">
            <input type="hidden" name="openID" value="${openID}"/>
            <div class="mt5">
                <label for="title">主题：</label>
                <input type="text" name="title" id="title" placeholder="您所要投诉的问题或建议"/>
            </div>
            <div class="mt10">
                <label for="text">描述：</label>
                <textarea name="text" id="text" cols="40" rows="8" placeholder="255字以内的问题描述"></textarea>
            </div>
            <div class="mt10">
                <label for="phone">手机：</label>
                <input type="tel" name="phone" id="phone" placeholder="留下您的手机号方便我们联系您"/>
            </div>
            <div class="mt10">
                <input type="submit" value="提  交"/>
            </div>
        </form>
    </div>
</div>
<jsp:include page="include/footer.jsp"/>