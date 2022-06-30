
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>${otherUserForm.username}的个人资料</title>
    <style type="text/css">
        body{margin: 0px;}
        .backgroudbody{
            width: 100%;
            min-width: 1000px;
            height: 900px;
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
        .headportrait{
            width: 23px;
            height: 23px;
            background-image: url(${pageContext.request.contextPath}/resources/picture/${otherUserForm.photo});
            background-size: 100% 100%;
            top:4px;
            left:20px;
            border-radius: 50%;
            position: absolute;
        }
        .selftitle{
            font-size: 40px;
            margin: 0 auto;
            width: 400px;
        }
        .publish-content{
            background-image: linear-gradient(to top,#fff 0%,rgba(255,255,255,0)30%),url("${pageContext.request.contextPath}/resources/picture/${otherUserForm.bgimg}");
            background-size: 100% 100%;
            width: 100%;
            height: 900px;
            margin: 0 auto;
        }
        .thefunction{
            list-style: none;
            padding: 0px;
            margin: 0px;
        }
        .thefunction li{
            float: left;
            margin-top: 20px;
            font-size: 15px;
            margin-bottom: 20px;
        }
        .myhead{
            width: 40px;
            height: 40px;
            background-image: url(${pageContext.request.contextPath}/resources/picture/${otherUserForm.photo});
            background-size: 100% 100%;
            border-radius: 50%;
            margin-top: 30px;
            float: left;
        }
        #xiugaixingxi{
            background-image: linear-gradient(to left,#f4f5f7 40%,rgba(255,255,255,0)100%),url("${pageContext.request.contextPath}/resources/picture/${otherUserForm.bgimg}");
            background-size: 100% 100%;
            position: fixed;
            width: 500px;
            height: 350px;
            left: 0;
            right: 0;
            top: 0;
            bottom: 0;
            margin-top: auto;
            margin-bottom: auto;
            margin-left: auto;
            margin-right: auto;
            display: block;
        }
        #wodetouxiang{
            background-image: url(${pageContext.request.contextPath}/resources/picture/${otherUserForm.photo});
            width: 50px;
            height: 50px;
            border-radius: 50%;
            background-size: 100% 100%;
            margin-top: 30px;
            margin-left: 60px;
            z-index: 1;
        }
        #idcard{
            list-style: none;
            margin: 0;
            padding: 0;
            font-size: 14px;
            color: #333;
        }
        #idcard li{
            margin-top: 7px;
        }
        #name{
            top: 1px;
            left: 35px;
            width: 100px;
            border: 0px;
            color: #333333;
            font-size: 14px;
            background-color: #f4f5f7;
            display: inline;
        }
        #birthday{
            width: 100px;
            left: 0px;
            top: 1px;
            border: 0px;
            color: #333;
            font-size: 14px;
            background-color: #f4f5f7;
            display: inline;
        }
        #boy{
            width: 100px;
            left: 0px;
            top: 1px;
            border: 0px;
            color: #333;
            font-size: 14px;
            background-color: #f4f5f7;
            display: inline;
        }
        #xingzuo{
            width: 100px;
            left: 0px;
            top: 1px;
            border: 0px;
            color: #333;
            font-size: 14px;
            background-color: #f4f5f7;
            display: inline;
        }
        #mywrite{
            width: 150px;
            left: 0px;
            top: 1px;
            border: 0px;
            color: #333;
            font-size: 14px;
            height: 35px;
            background-color: #f4f5f7;
            display: inline;
        }
        #chongzhiicon{
            width: 20px;
            height: 20px;
            background-image: url(${pageContext.request.contextPath}/resources/img/a20.png);
            background-size: 100% 100%;
            display: block;
            position: absolute;
            right: 6px;
            top: 6px;
        }
    </style>
</head>
<body>
<div class="backgroudbody">
    <ul class="topnav">
        <div class="headportrait">
        </div>
        <li style="padding-left: 50px;"><a href="#" style="text-decoration: none;color: #FFFFFF;">${otherUserForm.username}</a></li>
        <li style="padding-left: 80px;"><a href="index.jsp" style="text-decoration: none;color: #FFFFFF;">首 页</a></li>
        <li style="padding-left: 30px;"><a href="#" style="text-decoration: none;color: #FFFFFF;">关 注</a></li>
        <li style="padding-left: 30px;"><a href="#" style="text-decoration: none;color: #FFFFFF;">收 藏</a></li>
        <li style="padding-left: 30px;"><a href="#" style="text-decoration: none;color: #FFFFFF;">发 布</a></li>
        <li style="float: right;padding-right: 60px;"><a href="#" style="text-decoration: none;color: #FFFFFF;">设 置</a></li>
    </ul>

    <div class="publish-content">

<form  id="xiugaixingxi" >
            <div id="wodetouxiang">

            </div>
            <div style="width: 200px;height: 350px;position: absolute;right: 0;top: 0;">
                <div style="margin: 0 auto;width: 170px; height: 350px;">
                    <ul id="idcard">
                        <li>昵称：<div type="text" id="name" >${otherUserForm.username}</div></li>
                        <li>性别：<div  id="boy" >${otherUserForm.sex}</div></li>
                        <li>生日：<div id="birthday"> ${otherUserForm.birthday}</div></li>
                        <li>星座：<div  id="xingzuo" >${otherUserForm.constellation}</div></li>
                        <li>个性签名：<div id="mywrite"> ${otherUserForm.signature}</div></li>
                        <input id="chongzhi" type="reset" value="重置" style="display: none;"/>
                        <label for="chongzhi" id="chongzhiicon" onclick="seexiugai()"></label>
                    </ul>
                </div>
            </div>
        </form>
    </div>
</body>
<script>
    function seexiugai(){
        document.getElementById("xiugaixingxi").style.display = "none";
    }
    function shezhisee(){
        document.getElementById("shezhi").style.display = "none";
        document.getElementById("xiugaixingxi").style.display = "block";
    }
</script>
</html>
