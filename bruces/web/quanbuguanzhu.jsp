
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>全部关注</title>
    <style>
        body{
            margin: 0px;
            width: 1000px;
        }
        .people{
            height: 100px;
            width: 1000px;
            margin: 0px;
            border-bottom: 1px solid #eee;
            position: relative;
        }
        #head{
            background-image: url(${pageContext.request.contextPath}/p4.png);
            width: 60px;
            height: 60px;
            border-radius: 50%;
            background-size: 100% 100%;
            position: absolute;
            top: 20px;
            left: 0px;
        }
        #name{
            position: absolute;
            top: 30px;
            left: 80px;
            font-size: 16px;
        }
        #mywrite{
            position: absolute;
            top: 60px;
            left: 80px;
            font-size: 13px;
            color: #888;
        }
        /*#guanzhu{*/
        /*    position: absolute;*/
        /*    width: 60px;*/
        /*    height: 20px;*/
        /*    right: 60px;*/
        /*    top: 45px;*/
        /*    background-color: #ccc;*/
        /*    border-radius: 2px;*/
        /*    text-decoration: none;*/
        /*    color: #666666;*/
        /*    text-align: center;*/
        /*    font-size: 14px;*/
        /*    padding: 5px;*/
        /*}*/
    </style>
    <script>
        function AddTogo(id) {
            var type = id;
            var guanzhu = document.getElementById(type);
            if(guanzhu.innerHTML == "已关注"){
                guanzhu.innerHTML = "关注";
                guanzhu.style.backgroundColor = "#FFF";
                guanzhu.style.border = "1px solid #eee";
            }
            else{
                guanzhu.innerHTML = "已关注";
                guanzhu.style.backgroundColor = "#ccc";
                guanzhu.style.border = "1px solid #ccc";
            }
        }


    </script>
</head>
<body>

<c:forEach items="${follows}" var="follow">

<div class="people">
    <!--头像-->
    <div id="head" style="background-image: url(${pageContext.request.contextPath}/resources/picture/${follow.photo});"></div>
    <!--名字-->
    <div id="name">${follow.username}</div>
    <!--个性签名-->
    <div id="mywrite">${follow.signature}</div>
    <!--关注取消关注按钮-->
    <c:choose>
        <c:when test="${list1.get(follow.id)}">
    <a href="${pageContext.request.contextPath}/FriendListServlet?method=follow&follow_id=${follow.id}" onclick="AddTogo(${follow.id}) " ><div id="${follow.id}"  style="position: absolute;
            width: 60px;
            height: 20px;
            right: 60px;
            top: 45px;
            background-color: #ccc;
            border-radius: 2px;
            text-decoration: none;
            color: #666666;
            text-align: center;
            font-size: 14px;
            padding: 5px;">已关注</div></a>
        </c:when>
        <c:otherwise>
    <a href="${pageContext.request.contextPath}/FriendListServlet?method=follow&follow_id=${follow.id}" onclick="AddTogo(${follow.id}) " ><div id="${follow.id}"  style="position: absolute;
            width: 60px;
            height: 20px;
            right: 60px;
            top: 45px;
            background-color: #FFF;
            border-radius: 2px;
            text-decoration: none;
            color: #666666;
            text-align: center;
            font-size: 14px;
            border: 1px solid #eee;
            padding: 5px;">关注</div></a>
        </c:otherwise>
    </c:choose>

</div>

</c:forEach>
<p style="font-size: 12px;color: #666666;text-align: center;">
    <!-- 分页选择链接 -->
    <c:if test="${requestScope.p1.totalPage!=0}">
<div >
    <span >当前第 ${requestScope.p1.currentPage} 页，总共 ${requestScope.p1.totalPage} 页
    </span>
    <a href="FriendListServlet?method=${requestScope.method}&currentPage=1" >首页</a>
    <!-- 利用el表达式的三目运算符进行判断 -->
    <a href="FriendListServlet?method=${requestScope.method}&currentPage=${(requestScope.p1.currentPage==1)?1:requestScope.p1.currentPage-1}" >上一页</a>
    <a href="FriendListServlet?method=${requestScope.method}&currentPage=${(requestScope.p1.currentPage==requestScope.p1.totalPage)?requestScope.p1.totalPage:requestScope.p1.currentPage+1}" >下一页</a>
    <a href="FriendListServlet?method=${requestScope.method}&currentPage=${requestScope.p1.totalPage}" >尾页</a>
</div>
</c:if></p>
</body>

</html>
