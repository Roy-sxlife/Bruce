
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>动态发布</title>
    <style>
        body{
            margin: 0px;
            min-width: 1000px;
        }
        .headportrait{
            width: 23px;
            height: 23px;
            background-image: url(${pageContext.request.contextPath}/resources/picture/${User.photo});
            background-size: 100% 100%;
            top:4px;
            left:20px;
            border-radius: 50%;
            position: absolute;
        }
        #topnav{
            list-style: none;
            width: 100%;
            height: 30px;
            background-color: #666;
            padding-left: 0px;
            margin: 0px;
            font-size: 15px;
            color: #FFFFFF;
            display: block;
        }
        #topnav li{
            padding-top: 5px;
            width: 50px;
            float: left;
            position: relative;
        }
        .backgroud{
            width: 600px;
            margin: 0 auto;
        }
        .kejianfanwei p{
            float: left;
        }
        .neirong{
            margin-top: 10px;
            width: 600px;
            height: 400px;
            resize: none;
            border: 0px solid #000;
            border-radius: 1.5%;
            font-size: 20px;
        }
        .nav{
            background-image: linear-gradient(to top,#fff 0%,rgba(255,255,255,0)30%),url("${pageContext.request.contextPath}/resources/picture/${User.bgimg}");
            background-size: 100% 100%;
            width: 100%;
            height: 900px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
<div class="nav">
    <ul id="topnav">
        <div class="headportrait">
        </div>
        <li style="padding-left: 50px;"><a href="#" style="text-decoration: none;color: #FFFFFF;">${User.username}</a></li>
        <li style="padding-left: 80px;"><a href="${pageContext.request.contextPath}/index.jsp" style="text-decoration: none;color: #FFFFFF;">首 页</a></li>
        <li style="padding-left: 30px;"><a href="#" style="text-decoration: none;color: #FFFFFF;">关 注</a></li>
        <li style="padding-left: 30px;"><a href="#" style="text-decoration: none;color: #FFFFFF;">收 藏</a></li>
        <li style="padding-left: 30px;"><a href="#" style="text-decoration: none;color: #FFFFFF;">发 布</a></li>
        <li style="float: right;padding-right: 60px;"><a href="#" style="text-decoration: none;color: #FFFFFF;">设 置</a></li>
    </ul>
    <div class="backgroud">

        <form action="${pageContext.request.contextPath}/PublishServlet" method="post" enctype="multipart/form-data">
            <div>
                <div style="margin-top: 100px">标签:<input type="text" name="tag"></div>
                <textarea class="neirong"  name="description" rows="8" cols="40"></textarea>
                <input type="file" name="file"  >
                <div class="kejianfanwei">
                    <p style="font-size: 15px;"><input id="selfsee" type="radio" name="fanwei" value="1" />仅自己可见</p>
                    <p style="margin-left: 50px;font-size: 15px;"><input id="friendsee" type="radio" name="fanwei" value="2" />仅朋友可见</p>
                    <p style="margin-left: 50px;font-size: 15px;"><input id="allsee" type="radio" name="fanwei" value="3" />全部人可见</p>
                </div>
                <div style="clear:both"></div>
                <input type="reset" value="重置" style="opacity: 0.7;"/>
                <input type="submit" value="确认发布" style="margin-left: 30px;opacity: 0.7;"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>
