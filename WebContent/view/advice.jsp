<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="include/meta.html" %>
<title>微信智惠卡--建议投诉</title>
<%@ include file="include/header.html" %>
<div id="label">
    <span>建议投诉</span>
</div>
<script>
    //返回val的字节长度
    function getByteLen(val) {
        var len = 0;
        for (var i = 0; i < val.length; i++) {
            if (val[i].match(/[^\x00-\xff]/ig) != null) //全角
                len += 2;
            else
                len += 1;
        }
        return len;
    }
    //返回val在规定字节长度max内的值
    function getByteVal(val, max) {
        var returnValue = '';
        var byteValLen = 0;
        for (var i = 0; i < val.length; i++) {
            if (val[i].match(/[^\x00-\xff]/ig) != null)
                byteValLen += 2;
            else
                byteValLen += 1;
            if (byteValLen > max)
                break;
            returnValue += val[i];
        }
        return returnValue;
    }
    $(function() {
        var _text = $('#text');
        var _title = $('#title');
        var _titleMsg = $('#titleMsg');
        var _titleMax = _title.attr('maxlength');
        var _textMsg = $('#textMsg');
        var _textMax = _text.attr('maxlength');
        var _val1,_val2;
        _title.bind('keyup change', function() { //绑定keyup和change事件
            if (_titleMsg.find('span').size() < 1) {//避免每次弹起都会插入一条提示信息
                _titleMsg.append(_titleMax);
            }
            _val1 = $(this).val();
            var _cur = getByteLen(_val1);
            if (_cur == 0) {//当默认值长度为0时,可输入数为默认maxlength值
                _titleMsg.text(_titleMax);
            } else if (_cur < _titleMax) {//当默认值小于限制数时,可输入数为max-cur
                _titleMsg.text(_titleMax - _cur);
            } else {//当默认值大于等于限制数时
                _titleMsg.text(0);
                $(this).val(getByteVal(_val1,_titleMax)); //截取指定字节长度内的值
            }
        });
        _text.bind('keyup change', function() { //绑定keyup和change事件
            if (_textMsg.find('span').size() < 1) {//避免每次弹起都会插入一条提示信息
                _textMsg.append(_textMax);
            }
            _val2 = $(this).val();
            var _cur = getByteLen(_val2);
            if (_cur == 0) {//当默认值长度为0时,可输入数为默认maxlength值
                _textMsg.text(_textMax);
            } else if (_cur < _textMax) {//当默认值小于限制数时,可输入数为max-cur
                _textMsg.text(_textMax - _cur);
            } else {//当默认值大于等于限制数时
                _textMsg.text(0);
                $(this).val(getByteVal(_val2,_textMax)); //截取指定字节长度内的值
            }
        });
    });
    function checkPhone(){
        if($("#phone").val()==""){
            $("#phoneMsg").html('手机号码不能为空！');
            $("#phone").focus();
            return false;
        }

        if(!$("#phone").val().match(/^1[3|4|5|8][0-9]\d{4,8}$/)){
            $("#phoneMsg").html('手机号码格式不正确！请重新输入！');
            $("#phone").focus();
            return false;
        }
        return true;
    }

    function checkAndSubmit(){
        var check = checkPhone();
        if (check)
            $('form').submit();
    }
</script>
<div id="main">
    <div class="maincontent">
        <div class="contentImg adviceImg"></div>
        <form action="AdvicePost" method="post">
            <input type="hidden" name="openID" value="${openID}"/>
            <div class="mt5">
                <label for="title">主题：</label><div style="color:#dcdcdc">剩余<span id="titleMsg" style="color:red" ></span>字</div>
                <input type="text" name="title" id="title" placeholder="您所要投诉的问题或建议,20字以内" maxlength="20"/>
            </div>
            <div class="mt10">
                <label for="text">描述：</label><div style="color:#dcdcdc">剩余<span id="textMsg" style="color:red" ></span>字</div>
                <textarea name="text" id="text" cols="40" rows="8" placeholder="255字以内的问题描述" maxlength="255"></textarea>
            </div>
            <div class="mt10">
                <label for="phone">手机：</label><div id="phoneMsg" style="color:red"></div>
                <input type="tel" name="phone" id="phone" placeholder="留下您的手机号方便我们联系您" />
            </div>
            <div class="mt10">
                <input type="button" value="提  交" onclick="checkAndSubmit()"/>
            </div>
        </form>
    </div>
</div>
<jsp:include page="include/footer.jsp"/>