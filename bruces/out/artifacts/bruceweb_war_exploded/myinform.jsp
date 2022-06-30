<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2021/12/21
  Time: 8:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>${User.username}的个人资料</title>
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
            background-image: url(${pageContext.request.contextPath}/resources/picture/${User.photo});
            background-size: 100% 100%;
            top:4px;
            left:20px;
            border-radius: 50%;
            position: absolute;
        }




        .publish-content{
            background-image: linear-gradient(to top,#fff 0%,rgba(255,255,255,0)30%),url("${pageContext.request.contextPath}/resources/picture/${User.bgimg}");
            background-size: 100% 100%;
            width: 100%;
            height: 900px;
            margin: 0 auto;
        }

        .thefunction li{
            float: left;
            margin-top: 20px;
            font-size: 15px;
            margin-bottom: 20px;
        }



        #xiugaixingxi{
            background-image: linear-gradient(to left,#f4f5f7 40%,rgba(255,255,255,0)100%),url("${pageContext.request.contextPath}/resources/picture/${User.bgimg}");
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
            background-image: url(${pageContext.request.contextPath}/resources/picture/${User.photo});
            width: 50px;
            height: 50px;
            border-radius: 50%;
            background-size: 100% 100%;
            margin-top: 30px;
            margin-left: 60px;
            z-index: 1;
        }
        #genggaitouxiang{
            width: 50px;
            height: 50px;
            border-radius: 50%;
            background-color: #CCCCCC;
            text-align: center;
            font-size: 10px;
            position: relative;
            opacity: 0;
            font-family: YouYuan;
            display: block;
        }
        #genggaitouxiang:hover{
            opacity: 0.7;
        }
        #idcard{
            list-style: none;
            margin: 0;
            padding: 0;
            font-size: 14px;
            color: #333;
        }
        #idcard li{
            margin-top: 10px;
        }
        #birthday{
            width: 100px;
            left: 0px;
            top: 1px;
            border: 0px;
            color: #333;
            font-size: 14px;
            background-color: #f4f5f7;
        }
        #xingzuo{
            width: 100px;
            left: 0px;
            top: 1px;
            border: 0px;
            color: #333;
            font-size: 14px;
            background-color: #f4f5f7;
        }
        #hobbit{
            width: 150px;
            left: 0px;
            top: 1px;
            border: 0px;
            color: #333;
            font-size: 14px;
            height: 35px;
            background-color: #f4f5f7;
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
        }
        #location{
            width: 100px;
            left: 0px;
            top: 1px;
            border: 0px;
            color: #333;
            font-size: 14px;
            background-color: #f4f5f7;
        }
        #xiugai{
            position: absolute;
            left: 65px;
            top: 300px;
            background-color: #CCCCCC;
            border: 1px solid #CCCCCC;
            color: #666666;
            font-size: 14px;
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
        <li style="padding-left: 50px;"><a href="#" style="text-decoration: none;color: #FFFFFF;">${User.username}</a></li>
        <li style="padding-left: 80px;"><a href="index.jsp" style="text-decoration: none;color: #FFFFFF;">首 页</a></li>
        <li style="padding-left: 30px;"><a href="#" style="text-decoration: none;color: #FFFFFF;">关 注</a></li>
        <li style="padding-left: 30px;"><a href="#" style="text-decoration: none;color: #FFFFFF;">收 藏</a></li>
        <li style="padding-left: 30px;"><a href="#" style="text-decoration: none;color: #FFFFFF;">发 布</a></li>
        <li style="float: right;padding-right: 60px;"><a href="#" style="text-decoration: none;color: #FFFFFF;">设 置</a></li>
    </ul>

    <div class="publish-content">

    <!--个人资料背景-->
<form action="${pageContext.request.contextPath}/MyInformServlet" id="xiugaixingxi" enctype="multipart/form-data" method="post">
            <div id="wodetouxiang">
                <input style="display: none;" id="touxiang" type="file" name="txfile" value="${pageContext.request.contextPath}/resources/picture/${User.photo}"/>
                <label id="genggaitouxiang" for="touxiang"><span style="position: absolute;top: 20px;left: 0px;font-size: 12px;">更改头像</span></label>
                <input type="file" id="genggaibeijing" style="display: none" name="bgfile">
                <label for="genggaibeijing" id="huanbeijing" style="font-size: 12px;color: #222222;position: absolute;top: 10px;left: 60px;">更换背景</label>
            </div>
            <div style="width: 200px;height: 350px;position: absolute;right: 0;top: 0;">
                <div style="margin: 0 auto;width: 170px; height: 350px;">
                    <ul id="idcard">
                        <li>昵称：<input type="text" id="names"  name="username" value="${User.username}"/></li>
                        <li>性别：<c:choose><c:when test="${User.sex eq '男'}">
                        <select name="sex">
                            <option value="男" selected>男</option>
                            <option value="女">女</option>
                        </select></c:when>
                            <c:otherwise> <select name="sex">
                                <option value="男" >男</option>
                                <option value="女" selected>女</option>
                            </select></c:otherwise>
                        </c:choose></li>
                        <li>生日：<input type="text" id="birthday" name="birthday" value="${User.birthday}"/></li>
                        <li>星座：<input type="text" id="xingzuo" name="constellation" value="${User.constellation}"/></li>
                        <li>个性签名：<input type="text" id="mywrite" name="signature" value="${User.signature}"/></li>
                        <li><input type="submit" value="确认修改" id="xiugai" onclick="seexiugai()"/></li>
                        <input id="chongzhi" type="reset" value="重置" style="display: none;"/>
                        <label for="chongzhi" id="chongzhiicon" onclick="seexiugai()"></label>
                    </ul>
                </div>
            </div>
        </form>
    </div>
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
