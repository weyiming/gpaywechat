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
         city.val('sh');
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
                <option value="bj" selected="selected">北京</option>
                <option value="sh">上海</option>
            </select>
        </div>

        <fieldset data-role="controlgroup" data-type="horizontal">
            <legend>选择商户区域和类型：</legend>
            <label for="area">请选择商户区域</label>
            <select name="area" id="area">
                <option value="#">全北京</option>
                <option value="顺义区">顺义区</option>
                <option value="朝阳区">朝阳区</option>
                <option value="宣武区">宣武区</option>
                <option value="通州区">通州区</option>
                <option value="房山区">房山区</option>
                <option value="延庆县">延庆县</option>
                <option value="平谷区">平谷区</option>
                <option value="西城区">西城区</option>
                <option value="密云区">密云区</option>
                <option value="海淀区">海淀区</option>
                <option value="大兴区">大兴区</option>
                <option value="石景山区">石景山区</option>
                <option value="丰台区">丰台区</option>
                <option value="崇文区">崇文区</option>
                <option value="经济开发区">经济开发区</option>
                <option value="东城区">东城区</option>
                <option value="昌平区">昌平区</option>
                <option value="怀柔区">怀柔区</option>
                <option value="门头沟区">门头沟区</option>
            </select>
            <label for="type">商户类型</label>
            <select name="type" id="type">
                <option value="#">全部</option>
                <option value="百货">百货</option>
                <option value="超市">超市</option>
                <option value="电器">电器</option>
                <option value="家居">家居</option>
                <option value="餐饮">餐饮</option>
                <option value="摄影">摄影</option>
                <option value="美容美体">美容美体</option>
                <option value="医疗保健">医疗保健</option>
                <option value="酒店住宿">酒店住宿</option>
                <option value="服饰饰品">服饰饰品</option>
                <option value="眼镜店">眼镜店</option>
                <option value="奢侈品">奢侈品</option>
                <option value="西点甜品">西点甜品</option>
                <option value="生活休闲">生活休闲</option>
                <option value="教育培训">教育培训</option>
                <option value="即将上线">即将上线</option>
                <option value="缴费网点">缴费网点</option>
                <option value="网上商城">网上商城</option>
                <option value="其他">其他</option>
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