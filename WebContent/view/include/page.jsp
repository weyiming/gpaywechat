<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    function goToPage(test,currentPage,area,type)
    {
        if (test == 0)
        {
            //alert(parseInt($('#currentPage').val()) - 1);
            $('#page').val(currentPage - 1);
            $('#area').val(area);
            $('#type').val(type);
        }
        if (test == 1)
        {
            //alert(currentPage);
            $('#page').val(currentPage + 1);
            $('#area').val(area);
            $('#type').val(type);
        }

        $('#pageForm').submit();
    }
</script>
    <div class="ui-grid-solo">
    <div class="ui-block-a">
        <p>第${currentPage}页/共${totalPage}页</p>
    </div>
    </div>
    <div class="ui-grid-a">
    <div class="ui-block-a">
    <c:if test="${currentPage==1}">
        <button disabled="">上一页</button>
    </c:if>
    <c:if test="${currentPage!=1}">
        <button onclick="goToPage(0,${currentPage},'${area}','${type}')">上一页</button>
    </c:if>
    </div>
    <div class="ui-block-b">
    <c:if test="${currentPage==totalPage || totalPage==0}">
        <button disabled="">下一页</button>
    </c:if>
    <c:if test="${currentPage!=totalPage && totalPage!=0}">
        <button onclick="goToPage(1,${currentPage},'${area}','${type}')">下一页</button>
    </c:if>
    </div>
    </div>