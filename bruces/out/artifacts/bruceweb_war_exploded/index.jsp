<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="pojo.OnlineUsers" %>
<%@ page import="java.util.Vector" %>
<html>
<head>
    <meta charset="utf-8">
    <title>梁博歌迷交流社区</title>
    <link rel="stylesheet" type="text/css" >
    <style>
        body{
            margin: 0px;
            min-width: 1050px;
        }
        .headportrait{
            width: 23px;
            height: 23px;
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
            width: 60px;
            float: left;
            position: relative;
        }
        .search{
            width: 100%;
            height: 300px;
            background-color: #f0f3f5;
        }
        #search{
            outline:none;
            box-shadow:0 0 2px 1.5px #eee;
            border:none;
            width:451px;
            height:30px;
            padding:0px;
            color:#767676;
            font-size:13px;
        }
        #sousuo{
            background:url(${pageContext.request.contextPath}/resources/img/nav_mv_bg.png);
            width:33px;
            height:30px;
            border:0;
            box-shadow:1px 1px 1px 1px #9099a1;
            background-position:0px -40px;
            background-repeat:no-repeat;
            text-align:center;
        }
        .inner-nav{
            margin-left: 0px;
            margin-right: 0px;
            margin-top: 10px;
            margin-bottom: 10px;
            color: #555;
            float: left;
        }
        .number{
            margin-top: 13px;
            margin-bottom: 10px;
            margin-right: 10px;
            color: #555555;
            float: right;
        }
        #chuangenumber1{
            color: #555555;
            float: left;
        }
        #chuangenumber2{
            color: #555555;
            float: left;
        }
        .btn1{
            background-image:url(${pageContext.request.contextPath}/resources/img/slide_swithc_2.png);
            width:18px;
            height:18px;
            border-radius:50%;
            float: right;
            margin-top: 15px;
            margin-right: 15px;
            background-position: -18px 0px;
        }
        .btn2{
            background-image:url(${pageContext.request.contextPath}/resources/img/slide_swithc_2.png);
            width:18px;
            height:18px;
            border-radius:50%;
            float: right;
            margin-top: 15px;
            margin-right: 5px;
        }
        .innner{
            border-top: 1px solid #ccc;
            width: 700px;
            height: 260px;
        }
        .window{
            width: 680px;
            height: 225px;
            margin: 20px auto;
            overflow:hidden;
            position: relative;
        }
        #huadong-window1{
            width: 3680px;
            height: 225px;
            position: absolute;
            left: 0px;
            transition:all 2s;
        }
        #huadong-window2{
            width: 3000px;
            height: 225px;
            position: absolute;
            left: 0px;
            transition:all 2s;
        }
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
        #dongtai{
            min-width: 700px;
            min-height: 1000px;
            width: 700px;
            height: 1000px;
            border: none;
            position: absolute;
            right: -20px;
            top: 0px;
            bottom: 0px;
            overflow-x: hidden;
            overflow-y: scroll;
        }
        .new{
            width: 18px;
            height: 18px;
            background-image: url(${pageContext.request.contextPath}/resources/img/shuaxin.png);
            float: right;
        }
        #shuaxin{
            float: right;
            text-decoration: none;
            color: #666666;
            font-size: 15px;
            margin-top: 15px;
            margin-right: 10px;
        }
        .iframe-wrap{
            position: relative;
            border-top: 1px solid #ccc;
            overflow: hidden;
            width: 700px;
            height: 1000px;
            min-width: 700px;
            min-height: 1000px;
            float: left;
        }
        .right-nav{
            font-size:20px;
            background-color:#eee;
            width:220px;
            height:50px;
            margin-left: 30px;
            margin-top: 50px;
            margin-bottom: 20px;
            color:#555;
            list-style: none;
            text-align: center;
            padding-left: 0px;
            font-family: YouYuan;
        }
        .resoubang{
            margin: 0;
            padding-left: 0px;
            width:220px;
            margin-left: 30px;
            height:25px;
            list-style: none;
            padding-bottom: 10px;
            border-bottom: 1px solid #ccc;
        }
        .resoubang-inner{
            margin: 0;
            padding-left: 0;
            margin-left: 30px;
            margin-top: 15px;
        }
        .resoubang-inner li{
            list-style: none;
            font-size:10px;
            width:220px;
            height:21px;
            padding-top:7px;
            padding-bottom:7px;
            border-bottom: 1px solid #CCCCCC;
        }
        .resoubang-inner a{
            text-decoration:none;
            color:#3377aa;
            font-size:13px;
        }
        .right-img{
            width: 220px;
            height: 150px;
            float: left;
            margin-left: 30px;
            margin-top: 30px;
            opacity: 0.85;
            border-radius: 1.5%;
        }
        .yanchanghui{
            margin: 0;
            padding-left: 0px;
            width:220px;
            margin-left: 30px;
            margin-top: 30px;
            height:25px;
            list-style: none;
            padding-bottom: 10px;
            border-bottom: 1px solid #ccc;
            float: left;
        }
        .yanchanghui-inner{
            margin: 0;
            padding-left: 0;
            margin-left: 30px;
            margin-top: 15px;
            float: left;
        }
        .yanchanghui-inner li{
            list-style: none;
            font-size:10px;
            width:220px;
            height:21px;
            padding-top:7px;
            padding-bottom:7px;
            border-bottom: 1px solid #CCCCCC;
        }
        .yanchanghui-inner a{
            text-decoration:none;
            color:#3377aa;
            font-size:13px;
        }
        #shezhi{
            display: none;
            list-style: none;
            margin: 0px;
            padding-left: 0px;
            width: 120px;
            height: 90px;
            background-color: #666666;
            position: absolute;
            left: -40px;
        }
        #shezhi li{
            width: 100px;
            height: 25px;
            padding-top: 0px;
            text-align: center;
            font-size: 15px;
            margin-top: 5px;
            margin-left: 10px;
            border-bottom: 1px solid #FFFFFF;
        }
        #shezhi a{
            text-decoration: none;
            color: #FFFFFF;
        }


        #idcard li{
            margin-top: 10px;
        }



    </style>
</head>
<body>
<%
    Vector online=OnlineUsers.getVector();
%>
<!--顶部导航栏-->
<ul id="topnav">
    <c:choose>
        <c:when test="${User != null}">
    <div class="headportrait" style=" background-image: url(${pageContext.request.contextPath}/resources/picture/${User.photo});"></div>
    <li style="padding-left: 50px;"><a href="${pageContext.request.contextPath}/CenterServlet?method=ShowAll&user_Id=${User.id}" target="_blank" style="text-decoration: none;color: #FFFFFF;">${User.username}</a></li>
        </c:when>
        <c:otherwise>
            <li style="padding-left: 50px;"><a href="${pageContext.request.contextPath}/login.jsp" target="_blank" style="text-decoration: none;color: #FFFFFF;">登录/注册</a></li>
        </c:otherwise>
    </c:choose>
    <li style="padding-left: 80px;"><a href="${pageContext.request.contextPath}/index.jsp" style="text-decoration: none;color: #FFFFFF;">首 页</a></li>
    <li style="padding-left: 30px;"><a href="${pageContext.request.contextPath}/FriendServlet" target="_blank" style="text-decoration: none;color: #FFFFFF;">关 注</a></li>
    <li style="padding-left: 30px;"><a href="${pageContext.request.contextPath}/StarServlet?method=ShowAll" target="_blank" style="text-decoration: none;color: #FFFFFF;">收 藏</a></li>
    <li style="padding-left: 30px;"><a href="${pageContext.request.contextPath}/publish.jsp" style="text-decoration: none;color: #FFFFFF;">发 布</a></li>
    <li style="padding-left: 30px;"><a href="${pageContext.request.contextPath}/music/WEB-INF/view/index.jsp" target="_blank" style="text-decoration: none;color: #FFFFFF;">音 乐</a></li>
    <li style="float: right;padding-right: 60px;position: relative;"><a href="javascript:void(0)" onclick="shezhi()" style="text-decoration: none;color: #FFFFFF;">设 置</a>
        <!--设置二级页面-->
        <ul id="shezhi">
            <a href="${pageContext.request.contextPath}/MyInformServlet" target="_blank"><li>个人资料</li></a>
            <a href="${pageContext.request.contextPath}/ChangePasswordServlet?method=start" target="_blank"><li>修改密码</li></a>
            <a href="${pageContext.request.contextPath}/LogoutServlet"><li>退出登录</li></a>
        </ul>
    </li>
</ul>
<!--顶部搜索框-->
<div class="search">
    <div style="width: 950px;margin: 0 auto;">
        <div style="width: 700px;height: 104px;margin: 0;float: left;margin-top: 90px;">
            <form action="${pageContext.request.contextPath}/SearchServlet" target="_blank">
            <div style="margin: 0 auto;margin-top: 35px; width: 500px;height: 30px;">
                <input id="search" type="text" name="search" placeholder="   搜索专辑、博主、热评" autofocus/>
                <input id="sousuo" type="submit" value=" ">
            </div>
            </form>
        </div>
        <div style="width: 250px;margin: 0;float: left;margin-top: 90px;">
            <p style="width: 250px;margin: 0;text-align: center;color: #666666;font-size: 40px;font-family: YouYuan;">
                欢迎来到</br>梁博交流社区
            </p>
            <p style="text-align: center">当前在线人数：<%=online.size()%>人</p>
            <p style="text-align: center">在线用户名单：

                    <%
                        for (int i = 0; i < online.size(); i++) {
                            out.write(online.get(i)+" ");
                        }
                        %>

            </p>
        </div>
    </div>
</div>
<div style="width: 950px;margin: 0 auto;">
    <div style="width: 700px;margin: 0;float: left;">
        <!--最新专辑栏-->
        <p class="inner-nav">最新专辑</p>
        <a href="javascript:void(0)" onclick="move1(1)"><div class="btn1"> </div></a>
        <a href="javascript:void(0)" onclick="move1(0)"><div class="btn2"> </div></a>
        <p class="number"><span id="chuangenumber1">1</span>/${sessionScope.albumsize}</p>
        <div style="clear:both"></div>
        <div class="innner">
            <div class="window">
                <div id="huadong-window1">

                    <c:forEach items="${bruceAlbum}" var="bruce">
                        <div class="zhuanji">
                            <a href="${pageContext.request.contextPath}/SongsServlet?album=${bruce.album}" target="_blank"><img src="${pageContext.request.contextPath}/resources/picture/${bruce.songimg}"></a>
                            <a href="${pageContext.request.contextPath}/SongsServlet?album=${bruce.album}" target="_blank"><p>${bruce.album}</p></a>
                        </div>
                    </c:forEach>
                    <div class="zhuanji">
                        <a href="addAlbum.jsp" ><img src="${pageContext.request.contextPath}/resources/img/add.png"></a>
                        <a href="#" ><p>添加专辑</p></a>
                    </div>
                </div>
            </div>
        </div>
        <!--推荐歌曲栏-->
        <p class="inner-nav">现场合集</p>
        <a href="javascript:void(0)" onclick="move2(1)"><div class="btn1"> </div></a>
        <a href="javascript:void(0)" onclick="move2(0)"><div class="btn2"> </div></a>
        <p class="number"><span id="chuangenumber2">1</span>/3</p>
        <div style="clear:both"></div>
        <div class="innner">
            <div class="window">
                <div id="huadong-window2">
                    <div class="zhuanji">
                        <a href="https://www.bilibili.com/video/BV1e741147Qb?share_source=copy_web" target="_blank"><img src="${pageContext.request.contextPath}/resources/img/lb1.jpg"></a>
                        <a href="https://www.bilibili.com/video/BV1e741147Qb?share_source=copy_web" target="_blank"><p>【梁博】20段乐器solo集锦！！</p></a>
                    </div>
                    <div class="zhuanji">
                        <a href="https://www.bilibili.com/video/BV1264y1C7xg?share_source=copy_web" target="_blank"><img src="${pageContext.request.contextPath}/resources/img/lb2.jpg"></a>
                        <a href="https://www.bilibili.com/video/BV1264y1C7xg?share_source=copy_web" target="_blank"><p>【梁博】《大事发声 第3季》完整版</p></a>
                    </div>
                    <div class="zhuanji">
                        <a href="https://www.bilibili.com/video/BV1t4411u785?share_source=copy_web" target="_blank"><img src="${pageContext.request.contextPath}/resources/img/lb3.jpg"></a>
                        <a href="https://www.bilibili.com/video/BV1t4411u785?share_source=copy_web" target="_blank"><p>【梁博】长春消夏艺术节</p></a>
                    </div>
                    <div class="zhuanji">
                        <a href="https://www.bilibili.com/video/BV1E4411e71N?share_source=copy_web" target="_blank"><img src="${pageContext.request.contextPath}/resources/img/lb4.jpg"></a>
                        <a href="https://www.bilibili.com/video/BV1E4411e71N?share_source=copy_web" target="_blank"><p>梁博巡回LIVE SHOW“5”上海专场</p></a>
                    </div>
                </div>
            </div>
        </div>
        <!--最新动态-->
        <div>
            <p class="inner-nav">最新动态</p>
            <a href="#" onclick="shuaxin()" id="shuaxin"><div class="new"> </div>刷新</a>
            <div style="clear:both"></div>
            <div class="iframe-wrap">
                <iframe id="dongtai" src="${pageContext.request.contextPath}/StatusServlet?method=ShowAll">
                </iframe>
            </div>
        </div>
    </div>
    <div style="margin: 0;width: 250px;float: right;">
        <ul class="right-nav">
            <li style="padding-top: 13px;">梁博歌迷应援会</li>
        </ul>
        <!--热搜榜-->
        <ul class="resoubang">
            <li>热搜榜</li>
        </ul>
        <ul class="resoubang-inner">
            <li>1 <a href="#" target="_blank">梁博“凉”了吗？</a></li>
            <li>2 <a href="#" target="_blank">粉丝音乐节</a></li>
            <li>3 <a href="#" target="_blank">音乐</a></li>
            <li>4 <a href="#" target="_blank">梁博最新专辑</a></li>
            <li>5 <a href="#" target="_blank">演唱会最新消息</a></li>
            <li>6 <a href="#" target="_blank">购买演唱会票</a></li>
            <li>7 <a href="#" target="_blank">演唱会举办地点</a></li>
            <li>8 <a href="#" target="_blank">有粉丝见面会吗？</a></li>
            <li>9 <a href="#" target="_blank">秀梁博的亲笔签名</a></li>
            <li>10 <a href="#" target="_blank">粉丝群</a></li>
        </ul>
    </div>
    <img src="${pageContext.request.contextPath}/resources/img/lb.png" class="right-img">
    <!--演唱会信息-->
    <ul class="yanchanghui">
        <li>演唱会信息</li>
    </ul>
    <ul class="yanchanghui-inner">
        <li><a href="#" target="_blank">广州天河体育场演唱会</a></li>
        <li><a href="#" target="_blank">北京鸟巢演唱会</a></li>

    </ul>

</div>


</body>
<script>



    var a=0;
    var b=0;
    var size=${sessionScope.albumsize};

    window.onload=function(){
        document.getElementById("huadong-window1").style.width=size*700+"px";
    }

    function move1(num){
        if(num==1){
            a--;
        }
        if(num==0){
            a++;
        }
        if(a<-(size-1)){
            a=0;
        }
        if(a>0){
            a=-(size-1);
        }
        document.getElementById("huadong-window1").style.left=a*700+"px";
        if(a==0){
            document.getElementById("chuangenumber1").innerHTML = "1";
        }
        if(a==-1){
            document.getElementById("chuangenumber1").innerHTML = "2";
        }
        if(a==-2){
            document.getElementById("chuangenumber1").innerHTML = "3";
        }
    }
    function move2(num){
        if(num==1){
            b--;
        }
        if(num==0){
            b++;
        }
        if(b<-2){
            b=0;
        }
        if(b>0){
            b=-2;
        }
        document.getElementById("huadong-window2").style.left=b*700+"px";
        if(b==0){
            document.getElementById("chuangenumber2").innerHTML = "1";
        }
        if(b==-1){
            document.getElementById("chuangenumber2").innerHTML = "2";
        }
        if(b==-2){
            document.getElementById("chuangenumber2").innerHTML = "3";
        }
    }
    function shuaxin(){
        window.location.hash = '#';
        location.reload();
    }
    function shezhi(){
        if(document.getElementById("shezhi").style.display == "none"){
            document.getElementById("shezhi").style.display = "block";
        }
        else{
            document.getElementById("shezhi").style.display = "none";
        }
    }


</script>
</html>

