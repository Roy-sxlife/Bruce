<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>我的关注</title>
    <link rel="stylesheet" type="text/css">
    <style>
        body{
            margin: 0px;
            min-width: 1280px;
            background-color: #f4f5f7;
        }

        #topnav li{
            padding-top: 5px;
            width: 50px;
            float: left;
            position: relative;
        }
        #shezhi{
            display: none;
            list-style: none;
            margin: 0px;
            padding-left: 0px;
            width: 120px;
            height: 60px;
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
        .bakegroud{
            width: 1280px;
            margin: 0 auto;
            background-color: #f4f5f7;
        }
        .nav{
            width: 1280px;
            height: 200px;
            background-image: linear-gradient(to top,#333 0%,rgba(255,255,255,0)30%),url("${pageContext.request.contextPath}/resources/img/bilibili-navback.png");
            position: relative;
        }
        /*头像*/
        #myhead{
            width: 60px;
            height: 60px;
            background-image: url(${pageContext.request.contextPath}/resources/picture/${User2.photo});
            border-radius: 50px;
            background-size: 100% 100%;
            position: absolute;
            bottom: 20px;
            left: 25px;
        }
        /*名字*/
        #myname{
            margin: 0px;
            padding: 0px;
            position: absolute;
            font-size: 18px;
            color: #FFFFFF;
            font-family: 微软雅黑;
            bottom: 50px;
            left: 110px;
            font-weight: 700;
        }
        /*个性签名*/
        #mywrite{
            margin: 0px;
            padding: 0px;
            position: absolute;
            font-size: 12px;
            color: hsla(0,0%,100%,.8);
            font-family: Microsoft Yahei;
            bottom: 25px;
            left: 110px;
        }
        /*等级*/
        #level{
            width: 28px;
            height: 16px;
            background-image: url(${pageContext.request.contextPath}/resources/img/level.png);
            background-position: -21px -190px;
            position: absolute;
            right: -28px;
            bottom: 53px;
            left: 250px;
        }
        .wdguanzhu{
            width: 1280px;
            height: 66px;
            background-color: #FFFFFF;
        }
        .guanzhu{
            margin: 10px 20px;
            padding: 0;
            list-style: none;
            float: left;
            width: 45px;
            font-size: 12px;
            text-align: center;
            line-height: 20px;
            color: #99a2aa;

        }
        .guanzhu a{
            text-decoration: none;
            color: #99a2aa;
        }
        .guanzhu>a:hover{
            color: #00a1d6 !important;
        }
        .bottom{
            width: 1280px;
            height: 900px;
            margin-top: 20px;
            margin-bottom: 20px;
            background-color: #fff;
        }
        .bottom-leftnav{
            width: 220px;
            height: 900px;
            background-color: #fff;
            float: left;
            list-style: none;
            margin: 0px;
            padding: 0px;
            padding-left: 20px;
            border-right: 1px solid #eee;
            position: relative;
        }
        #img-qbgz{
            width: 20px;
            height: 17px;
            background-image: url(${pageContext.request.contextPath}/resources/img/icons.png);
            background-position: -86px -856px;
            position: absolute;
            left: 0px;
            transition:all 0.3s;
        }
        #img-tbgz{
            width: 20px;
            height: 17px;
            background-image: url(${pageContext.request.contextPath}/resources/img/icons.png);
            background-position: -150px -856px;
            position: absolute;
            left: 0px;
            transition:all 0.3s;
        }
        #img-qqgz{
            width: 20px;
            height: 17px;
            background-image: url(${pageContext.request.contextPath}/resources/img/icons.png);
            background-position: -278px -983px;
            position: absolute;
            left: 0px;
            transition:all 0.3s;
        }
        #img-mrfz{
            width: 20px;
            height: 17px;
            background-image: url(${pageContext.request.contextPath}/resources/img/icons.png);
            background-position: -22px -1112px;
            position: absolute;
            left: 0px;
            transition:all 0.3s;
        }
        #img-wdfs{
            width: 20px;
            height: 17px;
            background-image: url(${pageContext.request.contextPath}/resources/img/icons.png);
            background-position: -21px -984px;
            position: absolute;
            left: 0px;
            transition:all 0.3s;
        }
        .bottom-leftnav li p{
            width: 100px;
            margin: 0px;
            margin-left: 30px;
            font-size: 14px;
            transition:all 0.5s;
        }
        .bottom-leftnav li a{
            text-decoration: none;
            color: #222;
            transition:all 0.5s;
        }
        .bottom-leftnav li{
            position: relative;
            padding-top: 15px;
            padding-bottom: 15px;
            z-index: 2;
        }
        .nav-num{
            position: absolute;
            right: 20px;
            top: 17px;
            font-size: 12px;
            color: #99a2aa;
            text-align: center;
        }
        .bottom-right{
            width: 1000px;
            height: 900px;
            margin: 0px 19px;
            background-color: #FFFFFF;
            float: right;
        }
        #bottom-rightnav{
            width: 1000px;
            padding-bottom: 20px;
            margin-top: 20px;
            border-bottom: 1px solid #eee;
            color: #222;
            font-size: 18px;
        }
        .iframe-wrap{
            position: relative;
            border-top: 1px solid #ccc;
            overflow: hidden;
            width: 1000px;
            height: 835px;
            float: left;
        }
        #gundong{
            width: 100%;
            height: 855px;
            border: none;
            position: absolute;
            right: -20px;
            top: 0px;
            bottom: 0;
            overflow-x: hidden;
            overflow-y: scroll;
            background-color: #fff;
        }
        #xuanzeWindow{
            width: 240px;
            height: 50px;
            position: absolute;
            background-color: #00a1d6;
            left: 0px;
            padding: 0px;
            top: 50px;
            z-index: 1;
            transition:all 0.5s;
        }
        #qbgz{
            color: #FFFFFF;
        }
        #num-qbgz{
            color: #FFFFFF;
        }
    </style>
</head>
<body>

<div class="bakegroud">
    <!--页面顶部背景栏-->
    <div class="nav">
        <!--头像-->
        <div id="myhead"></div>
        <p id="myname">${User2.username}</p>
        <p id="mywrite">${User2.signature}</p>
        <div id="level"></div>
    </div>
    <!--顶部关注数粉丝数-->
    <div class="wdguanzhu">
        <ul class="guanzhu"><a href="javascript:void(0)" onclick="qbgz()">
            <li>关注数</li>
            <li id="n-gz">${User2.follow}</li>
        </a></ul>
        <ul class="guanzhu"><a href="javascript:void(0)"  onclick="wdfs()">
            <li>粉丝数</li>
            <li id="n-fs">${User2.fan}</li>
        </a></ul>
        <ul class="guanzhu">
            <li>获赞数</li>
            <li id="n-hz">0</li>
        </ul>
        <ul class="guanzhu">
            <li>播放数</li>
            <li id="n-bf">0</li>
        </ul>
        <ul class="guanzhu">
            <li>阅读数</li>
            <li id="n-yd">0</li>
        </ul>
    </div>
    <!--底部内容框-->
    <div class="bottom">
        <!--底部左边导航栏-->
        <ul class="bottom-leftnav">
            <li style="color: #99a2aa;font-size: 14px;">我的关注</li>
            <li><div id="img-qbgz"></div><a href="javascript:void(0)" id="qbgz" onclick="qbgz()"><p>全部关注</p></a><div class="nav-num" id="num-qbgz">${User2.follow}</div></li>
            <li><div id="img-tbgz"></div><a href="javascript:void(0)" id="tbgz" onclick="tbgz()"><p>特别关注</p></a><div class="nav-num" id="num-tbgz">0</div></li>
            <li><div id="img-qqgz"></div><a href="javascript:void(0)" id="qqgz" onclick="qqgz()"><p>悄悄关注</p></a><div class="nav-num" id="num-qqgz">0</div></li>
            <li><div id="img-mrfz"></div><a href="javascript:void(0)" id="mrfz" onclick="mrfz()"><p>默认分组</p></a><div class="nav-num" id="num-mrfz">0</div></li>
            <li style="color: #99a2aa;font-size: 14px;border-top: 1px solid #f4f5f7;">我的粉丝</li>
            <li style="border-bottom: 1px solid #eee;"><div id="img-wdfs"></div><a href="javascript:void(0)" id="wdfs" onclick="wdfs()"><p>我的粉丝</p></a><div class="nav-num" id="num-wdfs">${User2.fan}</div></li>
            <li id="xuanzeWindow"></li>
        </ul>
        <!--底部右边内容框-->
        <div class="bottom-right">
            <!--底部右边内容顶部导航栏-->
            <div id="bottom-rightnav">
                全部关注
            </div>
            <div class="iframe-wrap">
                <iframe id="gundong" src="${pageContext.request.contextPath}/FriendListServlet?method=follow">
                </iframe>
            </div>
        </div>
    </div>
</div>
</div>
</body>
<script >
    var xuanzeWindow = document.getElementById("xuanzeWindow");
    function src(url){
        document.getElementById("gundong").scr = "url";
    }
    function shezhi(){
        if(document.getElementById("shezhi").style.display == "none"){
            document.getElementById("shezhi").style.display = "block";
        }
        else{
            document.getElementById("shezhi").style.display = "none";
        }
    }
    function qbgzsee(){
        if(xuanzeWindow.style.top == "50px"){
            document.getElementById("qbgz").style.color = "#FFFFFF";
            document.getElementById("num-qbgz").style.color = "#FFFFFF";
            document.getElementById("img-qbgz").style.backgroundPosition = "-86px -856px";
        }
        else{
            document.getElementById("qbgz").style.color = "#222";
            document.getElementById("num-qbgz").style.color = "#99a2aa";
            document.getElementById("img-qbgz").style.backgroundPosition = "-150px -856px";
        }
    }
    function tbgzsee(){
        if(xuanzeWindow.style.top == "99px"){
            document.getElementById("tbgz").style.color = "#FFFFFF";
            document.getElementById("num-tbgz").style.color = "#FFFFFF";
            document.getElementById("img-tbgz").style.backgroundPosition = "-86px -791px";
        }
        else{
            document.getElementById("tbgz").style.color = "#222";
            document.getElementById("num-tbgz").style.color = "#99a2aa";
            document.getElementById("img-tbgz").style.backgroundPosition = "-150px -791px";
        }
    }
    function qqgzsee(){
        if(xuanzeWindow.style.top == "148px"){
            document.getElementById("qqgz").style.color = "#FFFFFF";
            document.getElementById("num-qqgz").style.color = "#FFFFFF";
            document.getElementById("img-qqgz").style.backgroundPosition = "-342px -983px";
        }
        else{
            document.getElementById("qqgz").style.color = "#222";
            document.getElementById("num-qqgz").style.color = "#99a2aa";
            document.getElementById("img-qqgz").style.backgroundPosition = "-278px -983px";
        }
    }
    function mrfzsee(){
        if(xuanzeWindow.style.top == "195px"){
            document.getElementById("mrfz").style.color = "#FFFFFF";
            document.getElementById("num-mrfz").style.color = "#FFFFFF";
            document.getElementById("img-mrfz").style.backgroundPosition = "-22px -1048px";
        }
        else{
            document.getElementById("mrfz").style.color = "#222";
            document.getElementById("num-mrfz").style.color = "#99a2aa";
            document.getElementById("img-mrfz").style.backgroundPosition = "-22px -1112px";
        }
    }
    function wdfssee(){
        if(xuanzeWindow.style.top == "294px"){
            document.getElementById("wdfs").style.color = "#FFFFFF";
            document.getElementById("num-wdfs").style.color = "#FFFFFF";
            document.getElementById("img-wdfs").style.backgroundPosition = "-86px -984px";
        }
        else{
            document.getElementById("wdfs").style.color = "#222";
            document.getElementById("num-wdfs").style.color = "#99a2aa";
            document.getElementById("img-wdfs").style.backgroundPosition = "-21px -984px";
        }
    }
    function qbgz(){
        document.getElementById("xuanzeWindow").style.top = "50px";
        document.getElementById("bottom-rightnav").innerHTML = "全部关注";
        gundong.src = "${pageContext.request.contextPath}/FriendListServlet?method=follow";
        qbgzsee();
        tbgzsee();
        qqgzsee();
        mrfzsee();
        wdfssee();
    }
    function tbgz(){
        document.getElementById("xuanzeWindow").style.top = "99px";
        document.getElementById("bottom-rightnav").innerHTML = "特别关注";
        gundong.src = "/friendship/tebeiguanzhu";
        qbgzsee();
        tbgzsee();
        qqgzsee();
        mrfzsee();
        wdfssee();
    }
    function qqgz(){
        document.getElementById("xuanzeWindow").style.top = "148px";
        document.getElementById("bottom-rightnav").innerHTML = "悄悄关注";
        gundong.src = "/friendship/qiaoqiaoguanzhu";
        qbgzsee();
        tbgzsee();
        qqgzsee();
        mrfzsee();
        wdfssee();
    }
    function mrfz(){
        document.getElementById("xuanzeWindow").style.top = "195px";
        document.getElementById("bottom-rightnav").innerHTML = "默认分组";
        gundong.src = "/friendship/morenfenzu";
        qbgzsee();
        tbgzsee();
        qqgzsee();
        mrfzsee();
        wdfssee();
    }
    function wdfs(){
        document.getElementById("xuanzeWindow").style.top = "294px";
        document.getElementById("bottom-rightnav").innerHTML = "我的粉丝";
        gundong.src = "${pageContext.request.contextPath}/FriendListServlet?method=fan";
        qbgzsee();
        tbgzsee();
        qqgzsee();
        mrfzsee();
        wdfssee();
    }

</script>
</html>
