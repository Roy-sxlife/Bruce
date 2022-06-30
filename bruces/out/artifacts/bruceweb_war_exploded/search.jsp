<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2022/4/10
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>搜索结果</title>
</head>
<body>
搜索结果：${search}
<button onclick="jBox()">点击</button>
</body>
<script type="text/javascript">
    function jBox() {
        var html=loginHyml;
        var content={
            state:{

            },
            buttonsFocus:0,
            submit:function (v,h,f) {

                if(v==0){
                    return true;

                }
                return false;
            }
        }
        $.jBox.open(content,'登录',650,550);
    }
</script>
</html>
