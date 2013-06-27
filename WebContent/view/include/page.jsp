<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    function goToPage(test)
    {
        if (test == 0)
        {
            //alert(parseInt($('#currentPage').val()) - 1);
            $('#page').val(parseInt($('#currentPage').val()) - 1);
        }
        if (test == 1)
        {
            alert(parseInt($('#currentPage').val()) + 1);
            $('#page').val(parseInt($('#currentPage').val()) + 1);
        }

        $('#pageForm').submit();
    }
</script>
<p>
    <c:if test="${currentPage==1}">
        <a href="#" data-role="button" data-inline="true" class="ui-disabled">上一页</a>
    </c:if>
    <c:if test="${currentPage!=1}">
        <a href="#" data-role="button" data-inline="true" onclick="goToPage(0)">上一页</a>
    </c:if>
    <label>第${currentPage}页/共${totalPage}页</label>
    <c:if test="${currentPage==totalPage}">
        <a href="#" data-role="button" data-inline="true" class="ui-disabled">下一页</a>
    </c:if>
    <c:if test="${currentPage!=totalPage}">
        <a href="#" data-role="button" data-inline="true" onclick="goToPage(1)">下一页</a>
    </c:if>
</p>