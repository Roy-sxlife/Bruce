
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加专辑</title>
    <style>
        #upload{

        }
        ul{

        }
        li{
            text-decoration: none;
        }

    </style>
</head>
<body >
<div id="upload">
<form action="${pageContext.request.contextPath}/UpAndDownloadServlet?method=upload" method="post" enctype="multipart/form-data">
    <ul>
        <li>歌曲名称：<input type="text" name="songname"></li>
        <li>歌手：<input type="text" name="songer"></li>
        <li>专辑<input type="text" name="album"></li>
        <li>专辑图片<input type="file" name="songimg"></li>
        <li>歌曲文件：<input type="file" name="musicfile"></li>
        <input type="submit" value="上传">
    </ul>
</form>
</div>
</body>
</html>
