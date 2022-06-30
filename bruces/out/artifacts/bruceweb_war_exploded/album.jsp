<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2022/4/14
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<style>
    .zhuanji{
        width: 150px;
        height: 225px;
        margin-left: 10px;
        margin-right: 10px;
        display: inline-block;
    }
    .zhuanji a{
        text-decoration: none;
    }
    .zhuanji img{
        width: 150px;
        height: 178px;
    }
    .zhuanji p{
        width: 150px;
        margin-top: 10px;
        margin-bottom: 10px;
        text-align: center;
        font-size: 15px;
        color: #666666;
    }
</style>
<body>
<c:forEach items="" var="">
    <div class="zhuanji">--%>
        <a href="${pageContext.request.contextPath}/SongsServlet" target="_blank"><img src="${pageContext.request.contextPath}/resources/img/1.jpg"></a>
        <a href="" target="_blank"><p>梁博同名专辑</p></a>
    </div>
</c:forEach>
</body>
</html>
