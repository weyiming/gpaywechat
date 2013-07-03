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
    <script>

        function changeCity()
        {
            //alert($('#selectcity').val());
            var city = $('#city');
            city.val('bj');
            $('#area').val('#');
            $('#type').val('#');
            $('#page').val(1);
            $('#pageForm').submit();
        }
    </script>
    <form id="pageForm" action="/MerchantsGetter" method="get">
        <div data-role="fieldcontain">
            <label for="city">选择城市:</label>
            <select name="city" id="city" data-role="slider" onchange="changeCity()">
                <option value="bj" >北京</option>
                <option value="sh" selected="selected">上海</option>
            </select>
        </div>

        <fieldset data-role="controlgroup" data-type="horizontal">
            <legend>选择商户区域和类型：</legend>
            <label for="area">请选择商户区域</label>
            <select name="area" id="area">
                <option value="#">全上海</option>
                <option value="闵行区">闵行区</option>
                <option value="南汇区">南汇区</option>
                <option value="普陀区">普陀区</option>
                <option value="崇明县">崇明县</option>
                <option value="静安区">静安区</option>
                <option value="卢湾区">卢湾区</option>
                <option value="金山区">金山区</option>
                <option value="虹口区">虹口区</option>
                <option value="徐汇区">徐汇区</option>
                <option value="黄浦区">黄浦区</option>
                <option value="杨浦区">杨浦区</option>
                <option value="奉贤区">奉贤区</option>
                <option value="闸北区">闸北区</option>
                <option value="长宁区">长宁区</option>
                <option value="嘉定区">嘉定区</option>
                <option value="青浦区">青浦区</option>
                <option value="宝山区">宝山区</option>
                <option value="松江区">松江区</option>
                <option value="浦东新区">浦东新区</option>
                <option value="崇明区">崇明区</option>
            </select>
            <label for="type">商户类型</label>
            <select name="type" id="type">
                <option value="#">全部</option>
                <option value="百货商城">百货商城</option>
                <option value="餐饮美食">餐饮美食</option>
                <option value="食品西点">食品西点</option>
                <option value="酒店宾馆">酒店宾馆</option>
                <option value="家电家居">家电家居</option>
                <option value="生活服务">生活服务</option>
                <option value="休闲娱乐">休闲娱乐</option>
                <option value="票务旅游">票务旅游</option>
                <option value="医疗药房">医疗药房</option>
                <option value="教育培训">教育培训</option>
                <option value="汽车用品">汽车用品</option>
                <option value="各类专卖">各类专卖</option>
            </select>
        </fieldset>
        <input type="hidden" name="page" id="page" value="1"/>
        <input type="submit" value="查询"/>
    </form>
    <div data-role="collapsible-set" data-theme="c" data-content-theme="d" data-collapsed-icon="arrow-r" data-expanded-icon="arrow-d">
        <c:forEach items="${merchants}" var="merchant">
            <div data-role="collapsible" data-icon="false">
                <h2>${merchant.name}</h2>
                <ul data-role="listview">
                    <li data-icon="false">
                        <h2>${merchant.name}</h2>
                        <p><strong>区域：</strong>${merchant.area}</p>
                        <p><strong>类型：</strong>${merchant.type}</p>
                        <p><strong>电话：</strong>${merchant.tel}</p>
                        <p><strong>地址：</strong>${merchant.address}</p>
                    </li>
                </ul>
            </div>
        </c:forEach>
    </div>
    <div class="center">
        <jsp:include page="include/page.jsp"/>
    </div>
    <jsp:include page="include/footer.jsp"/>