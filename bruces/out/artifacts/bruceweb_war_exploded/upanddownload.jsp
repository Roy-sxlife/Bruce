
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>音 乐</title>
</head>
<body>

<div><a href="${pageContext.request.contextPath}/UpAndDownloadServlet?method=addupload">上传你的音乐</a></div>
<br>
    <form>

        <table>

            <tr>
                <td>序号</td>
                <td>歌曲名称</td>
                <td>歌手</td>
                <td>专辑</td>
            </tr>
            <c:forEach items="${AllMusic}" var="music">
                <tr>
                    <td>${music.id}</td>
                    <td><a target="_blank" href="${pageContext.request.contextPath}/SongsServlet?song=${music.song}">${music.song}</a></td>
                    <td>${music.singer}</td>
                    <td>《${music.album}》</td>
                </tr>
            </c:forEach>
        </table>
    </form>

<audio autoplay controls  src="http://music.163.com/song/media/outer/url?id=1401482684"></audio>

</body>
</html>
