
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>梁博</title>
    <style>
        html {
            font-size: 50px;
        }
        body {
            margin: 0;
            padding: 0;
        }
        .backgroudbody{
            width: 100%;
            min-width: 1500px;
            height: 800px;
            background-color: #fff;
            margin: 0 auto;
        }
        .topnav{
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
        .topnav li{
            padding-top: 5px;
            width: 50px;
            float: left;
            position: relative;
        }
        .nav{
            background-image: linear-gradient(to top,#fff 0%,rgba(255,255,255,0)30%),url("${pageContext.request.contextPath}/resources/img/back2.jpg");
            background-size: 100% 100%;
            width: 100%;
            height: 900px;
            margin: 0 auto;
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
        .mp3Box {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 500px;
            height: 40px;
            padding: 0 0.4rem;
            background-color: #222;
            opacity: 0.7;
            margin: 0 auto;
            position: absolute;
            bottom: 25px;
            left: 80px;
        }
        .radioBox {
            width: 25px;
            height: 25px;
            /* border: 1px solid red; */
            border-radius: 50%;
            background: url(${pageContext.request.contextPath}/resources/img/btn.png);
            background-position: -18px -18px;
        }

        .voice {
            width: 18px;
            height: 18px;
            background: url(${pageContext.request.contextPath}/resources/img/btn.png);
            background-position: -62px -195px;
        }
        #yinliang {
            display: none;
            position: relative;
            top: 0.2rem;
            left: -2.14rem;
        }
        .time {
            font-size: 15px;
            color: white;
            -moz-user-select: none;
            -webkit-user-select: none;
            user-select: none;
        }
        .progress {
            position: relative;
            width: 50%;
            height: 0.08rem;
            background-color: #ccc;
        }
        .slide {
            position: absolute;
            top: -0.1rem;
            width: 0.3rem;
            height: 0.3rem;
            border-radius: 50%;
            background-color: #3299cc;
            z-index: 9;
        }
        .fill{
            position: absolute;
            top: 0;
            height: 0.08rem;
            background-color: #3299cc;
        }
        #jinzhi{
            width: 250px;
            height: 250px;
            border-radius: 50%;
            position: absolute;
            top: 125px;
            display: block;
            opacity: 1;
            left: 225px;
        }
        #xuanzhuan{
            -webkit-animation:rotateImg 15s linear infinite;
            width: 250px;
            height: 250px;
            border-radius: 50%;
            position: absolute;
            top: 125px;
            display: none;
            opacity: 1;
            left: 225px;
        }

        @keyframes rotateImg {
            0% {transform : rotate(0deg);}
            100% {transform : rotate(360deg);}
        }

        @-webkit-keyframes rotateImg {
            0%{-webkit-transform : rotate(0deg);}
            100%{-webkit-transform : rotate(360deg);}
        }
        #songName{
            position: absolute;
            top: 50px;
            font-size: 30px;
            margin: 0px;
            width: 400px;
            left: 150px;
            text-align: center;
        }
        .songnav{
            width: 700px;
            height: 30px;
            background-color: #1b1c1e;
            list-style: none;
            margin: 0px;
            position: relative;
            padding: 0px;
        }
        .songnav li{
            float: left;
            font-size: 15px;
        }
        .window{
            position: absolute;
            overflow: hidden;
            width: 200px;
            height: 500px;
            left: -260px;
            top: 0px;
        }
        .huadong-window{
            width: 220px;
            height: 500px;
            overflow-x: hidden;
            overflow-y: scroll;
            background-color: #FFFFFF;
            font-size: 15px;
            text-align: center;
        }
        .huadong-window p{
            margin: 0;
            padding: 13px 0px;
            border-bottom: 1px solid #aaa;
            right: 20px;
            background-color: #FFFFFF;
            width: 140px;
        }
        .huadong-window a{
            text-decoration: none;
            font-size: 15px;color: #333;
            display: block;
            width: 140px;
            margin-left: 15px;
            margin-right: 15px;
        }
        .huadong-window img{
            margin: 0;
            padding: 13px 0px;
            height: 20px;
            width: 20px;
        }
    </style>
</head>
<body>
<div class="backgroudbody">
    <ul class="topnav">
        <div class="headportrait">
        </div>
        <li style="padding-left: 50px;"><a href="#" style="text-decoration: none;color: #FFFFFF;">${User.username}</a></li>
        <li style="padding-left: 80px;"><a href="#" style="text-decoration: none;color: #FFFFFF;">首 页</a></li>
        <li style="padding-left: 30px;"><a href="#" style="text-decoration: none;color: #FFFFFF;">关 注</a></li>
        <li style="padding-left: 30px;"><a href="#" style="text-decoration: none;color: #FFFFFF;">收 藏</a></li>
        <li style="padding-left: 30px;"><a href="#" style="text-decoration: none;color: #FFFFFF;">发 布</a></li>
        <li style="float: right;padding-right: 60px;"><a href="#" style="text-decoration: none;color: #FFFFFF;">设 置</a></li>
    </ul>
    <div class="nav">
        <div style="margin: 0 auto;width: 700px;height: 500px;position: relative;background-color: #888;opacity: 0.9;top: 150px;">
            <ul class="songnav">
            </ul>
            <!--歌曲选择-->

            <div class="window">

                <div class="huadong-window">
                    <c:forEach items="${Brucesong}" var="bruce">


                       <a href="javascript:songselect(${bruce.id}) " ><p>${bruce.song}</p></a>
                       <a onclick="tishi(${bruce.id})" href="${pageContext.request.contextPath}/UpAndDownloadServlet?method=download&songlocation=${bruce.songlocation}"><img  src="resources/img/download.png"></a>

                    </c:forEach>
                </div>

            </div>

            <p id="songName">默认</p>
            <img src="${pageContext.request.contextPath}/resources/img/p4.png" id="xuanzhuan">
            <img src="${pageContext.request.contextPath}/resources/img/p4.png" id="jinzhi">



            <div class="mp3Box">
                <!-- 播放开关 -->
                <div class="radioBox" onclick="bofan()">
                    <audio id="ado"></audio>
                </div>
                <!-- 进度条 -->
                <div class="progress">
                    <div class="slide"></div>
                    <div class="fill"></div>
                </div>
                <!-- 时间 -->
                <div class="time">
                    <span class="currentTime">00:00</span> /
                    <span class="duraTime">00:00</span>
                </div>
                <!-- 是否静音 -->
                <div class="voice"></div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function tishi(x) {
        alert("下载成功！");
    }
    //选歌
    function songselect(id){
        var id=id;

        var imgmap=eval('(' + '${imgmap}' + ')');
        var img1=imgmap[id];

        var locationmap=eval('(' + '${locationmap}' + ')');
        var location1=locationmap[id];


        var songmap=eval('(' + '${songmap}' + ')');
        var song1=songmap[id];


        document.getElementById("ado").src = "${pageContext.request.contextPath}"+"/"+"resources"+"/"+"picture"+"/"+location1;
        document.getElementById("jinzhi").src = "${pageContext.request.contextPath}"+"/"+"resources"+"/"+"picture"+"/"+img1;
        document.getElementById("xuanzhuan").src = "${pageContext.request.contextPath}"+"/"+"resources"+"/"+"picture"+"/"+img1;
        document.getElementById("songName").innerHTML = song1;
        document.querySelector(".radioBox").style.backgroundPosition = "-18px -18px";
        document.getElementById("jinzhi").style.display="block";
        document.getElementById("xuanzhuan").style.display="none";
    }


    var audio = document.querySelector("#ado");
    audio.controls = false;
    audio.loop = true;
    audio.volume = 0.3;
    var rBox = document.querySelector(".radioBox");
    rBox.appendChild(audio);
    //   调整声音是否静音
    var voice = document.querySelector(".voice");
    voice.addEventListener("click", function () {
        if (audio.muted) {
            audio.muted = false;
            voice.style.backgroundPosition = "-62px -195px";
        } else {
            audio.muted = true;
            voice.style.backgroundPosition = "-126px -195px";
        }
    });
    //   播放暂停
    function bofan() {
        if (audio.paused) {
            audio.play();
            rBox.style.backgroundPosition= "-18px -78px";
            document.getElementById("jinzhi").style.display="none";
            document.getElementById("xuanzhuan").style.display="block";
        } else {
            audio.pause();
            rBox.style.backgroundPosition = "-18px -18px";
            document.getElementById("jinzhi").style.display="block";
            document.getElementById("xuanzhuan").style.display="none";
        }
    }

    //   获取音频总时长
    if (audio != null) {
        var duration;
        audio.load();
        audio.oncanplay = function () {
            var duraTime = document.querySelector(".duraTime");
            duraTime.innerHTML = transTime(audio.duration);
        };
    }
    //   格式化时间格式
    function transTime(time) {
        let duration = parseInt(time);
        let minute = parseInt(duration / 60);
        let sec = (duration % 60) + "";
        let isM0 = ":";
        if (minute == 0) {
            minute = "00";
        } else if (minute < 10) {
            minute = "0" + minute;
        }
        if (sec.length == 1) {
            sec = "0" + sec;
        }
        return minute + isM0 + sec;
    }
    // 时长进度条
    var progress = document.querySelector(".progress");
    var slide = document.querySelector(".slide");
    var fill = document.querySelector(".fill")
    audio.ontimeupdate = function () {
        var l = (audio.currentTime / audio.duration) * 100;
        slide.style.left = l + "%";
        fill.style.width = l + "%";
        if (audio.currentTime == 0) {
            slide.style.left = "0%";
        }
        var currentTime = document.querySelector(".currentTime");
        currentTime.innerHTML = transTime(parseInt(audio.currentTime));
        var duraTime = document.querySelector(".duraTime");
        duraTime.innerHTML = transTime(audio.duration);
    };
    // 点击进度条
    progress.onmousedown = function (e) {
        var rate = (e.clientX - progress.offsetLeft) / this.clientWidth*audio.duration
        audio.currentTime = rate-(progress.clientWidth*0.005)
    };
    //进度条拖动
    slide.onmousedown = function (e) {
        var x = e.clientX - this.offsetLeft; //240
        document.onmousemove = function (e) {
            var jlx = ((e.clientX - x) / progress.clientWidth) * 100;
            if (jlx <= 100 && jlx >= 0) {
                slide.style.left = jlx + "%";
            }
            audio.currentTime = (jlx / 100) * audio.duration;
        };
        document.onmouseup = function (e) {
            document.onmousemove = null;
            document.onmouseup = null;
        };
    };
    slide.ontouchstart = function (e) {
        var x = e.targetTouches[0].clientX - this.offsetLeft; //240
        document.ontouchmove = function (e) {
            var jlx = ((e.targetTouches[0].clientX - x) / progress.clientWidth) * 100;
            if (jlx <= 100 && jlx >= 0) {
                slide.style.left = jlx + "%";
            }
            audio.currentTime = (jlx / 100) * audio.duration;
        };
        document.ontouchend = function (e) {
            document.ontouchmove = null;
            document.ontouchend = null;
        };
    };
</script>
</html>

