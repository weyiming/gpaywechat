<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="include/meta.html" %>
<title>微信智惠卡--商户查询</title>
<%@ include file="include/header.html" %>
<div id="label">
    <span>商户查询</span>
</div>

<!-- jquery mobile content begin -->
<div data-role="content">
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
    <form id="pageForm" action="MerchantsGetter" method="get">
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
                <option value="minxingqu">闵行区</option>
                <option value="nanhuiqu">南汇区</option>
                <option value="putuoqu">普陀区</option>
                <option value="chongmingxian">崇明县</option>
                <option value="jinganqu">静安区</option>
                <option value="luwanqu">卢湾区</option>
                <option value="jinshanqu">金山区</option>
                <option value="hongkouqu">虹口区</option>
                <option value="xuhuiqu">徐汇区</option>
                <option value="huangpuqu">黄浦区</option>
                <option value="yangpuqu">杨浦区</option>
                <option value="fengxianqu">奉贤区</option>
                <option value="zhabeiqu">闸北区</option>
                <option value="changningqu">长宁区</option>
                <option value="jiadingqu">嘉定区</option>
                <option value="qingpuqu">青浦区</option>
                <option value="baoshanqu">宝山区</option>
                <option value="songjiangqu">松江区</option>
                <option value="pudongxinqu">浦东新区</option>
                <option value="chongmingqu">崇明区</option>
            </select>
            <label for="type">商户类型</label>
            <select name="type" id="type">
                <option value="#">全部</option>
                <option value="bihuoshangcheng">百货商城</option>
                <option value="canyinmeishi">餐饮美食</option>
                <option value="shipinxidian">食品西点</option>
                <option value="jiudianbinguan">酒店宾馆</option>
                <option value="jiadianjiaju">家电家居</option>
                <option value="shenghuofuwu">生活服务</option>
                <option value="xiuxianyule">休闲娱乐</option>
                <option value="piaowulvyou">票务旅游</option>
                <option value="yiliaoyaofang">医疗药房</option>
                <option value="jiaoyupeixun">教育培训</option>
                <option value="qicheyongpin">汽车用品</option>
                <option value="geleizhuanmai">各类专卖</option>
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
    </div>
    <!-- jquery mobile content end -->
 <jsp:include page="include/footer.jsp"/>