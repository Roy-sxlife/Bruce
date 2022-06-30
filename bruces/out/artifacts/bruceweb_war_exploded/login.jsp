<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>社区登录</title>
    <link href="${pageContext.request.contextPath}/resources/aliyun/iconfont.css" rel="stylesheet">
    <style>
        *{
            margin: 0 auto;
            padding: 0;
        }
        body{
            background: url("${pageContext.request.contextPath}/resources/img/p0.jpg")  no-repeat;
            background-size: 1280px 768px;
            margin-top: 80px;
        }
        table{
            border-collapse: inherit;
            border-spacing: 10px;
            align-content: center;
        }
        .login{
            height: 30px;
            width: 105px;
            background: #004B97;
            color: #FAFAFA;
            border: 1px #FFFFFF solid;
        }
        #roy{
            height: 300px;
            width: 450px;
            align-content: center;
        }
        #joy{
            height: 60px;
            width: max-content;
            color: #FFFFFF;
            font-size: 18px;
            line-height: 60px;
            align-content: center;
        }
        .lii{
            width: 220px;
            height: 30px;
            border: 1px #FFFFFF solid;
        }
        #findback{
            text-decoration: none;
            color: #111111;
        }

    </style>
</head>
<body>
<div id="roy">
    <div id="joy">社区—登录</div>
        <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
            <table >
                <tr>
                    <td >
                        <span class="iconfont icon-yonghuming"></span>
                        <input class="lii" name="username" id="username" value="${cookie.username.value}"/>
                    </td>
                </tr>
                <tr>
                    <td >
                        <span class="iconfont icon-mima"></span>
                        <input class="lii" type="password" name="password" id="password" value="${cookie.password.value}"/>
                    </td>
                </tr>
                <tr style="position: relative">
                    <td >
                        <span class="iconfont icon-yanzhengyanzhengma"></span>
                        <input type="text" name="check" class="lii" style="width: 105px" id="checkcodes" autofocus>
                    </td>
                    <td >
                        <a href="javascript:(0)"  onclick="check()"><img src="${pageContext.request.contextPath}/CodeServlet" id="checkcode1"
                                                                                        style="border:1px #111111 solid;position: absolute;left: 135px;bottom:0px "></a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label style="margin-left: 20px">
                            <input type="checkbox" name="remember" />记住密码
                        </label>
                    </td>
                    <td>
                        <a href="#" id="findback">
                            <div style="margin-left: -85px">找回密码</div>
                        </a>
                    </td>
                </tr>
                <tr>
                    <td >
                        <input type="submit" onclick="reconfirm()" value="登录" class="login" style="margin-left: 22px">
                        <button class="login" style="margin-left: 5px"><a href="register.jsp" style="text-decoration: none;color: #FAFAFA;">注册</a></button>
                    </td>

                </tr>
                <tr>
                    <td><div style="width: 150px; height: 30px;color: red;text-align: center" >${requestScope.Message}</div></td></tr>
            </table>


        </form>
</div>
</body>
<script>
    function check() {
        var check2 = document.getElementById("checkcode1");
        check2.setAttribute("src","${pageContext.request.contextPath}/CodeServlet?a="+new Date().getTime())
    }
    function reconfirm(){
        var username = document.getElementById("username")
        var password = document.getElementById("password")
        var code = document.getElementById("checkcodes")
        if(username.value == ""){
            alert("请输入用户名")
        }else if(password.value == ""){
            alert("请输入密码")
        }else if(code.value == ""){
            alert("请输入验证码");
        }else{
            document.forms[0].submit()
        }
    }
</script>
</html>
