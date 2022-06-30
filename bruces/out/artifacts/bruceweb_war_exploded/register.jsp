
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>社区注册</title>
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

    </style>
</head>
<body>
<div id="roy">
    <div id="joy">社区—注册</div>
    <form method="post" action="${pageContext.request.contextPath}/RegisterServlet"  enctype="multipart/form-data">
    <table >
        <tr>
            <td >
                <span class="iconfont icon-yonghuming"></span>
                <input class="lii" name="username" type="text" id="username" placeholder="请输入用户名" pattern="^[a-zA-Z]{3,6}$"
                       title="请输入3-6个字母作为用户名" />
            </td>
        </tr>
        <tr>
            <td >
                <span class="iconfont icon-mima"></span>
                <input class="lii" type="password" name="password" id="password" placeholder="请输入密码" pattern="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$"
                       title="请输入6-20个字母、数字、下划线 作为密码" onblur="isPassword(this.value)"/>
            </td>
        </tr>
        <tr>
            <td >
                <span class="iconfont icon-yonghuming"></span>
                <input type="file" name="file" id="txfile" >
            </td>
        </tr>
        <tr style="position: relative">
            <td >
                <span class="iconfont icon-yanzhengyanzhengma"></span>
                <input id="checkcodes" type="text" name="check" class="lii" style="width: 105px" autofocus>
            </td>
            <td >
                <a href="javascript:(0)"  onclick="check()"><img src="${pageContext.request.contextPath}/CodeServlet" id="checkcode1"
                                                                                style="border:1px #111111 solid;position: absolute;left: 135px;bottom:0px "></a>
            </td>
        </tr>
        <tr>
            <td >
                <input type="submit" onclick="reconfirm()" value="注册" class="login" style="margin-left: 22px">
            </td>
        </tr>
        <tr>
            <td><div style="width: 100px; height: 30px;color: red;text-align: center" >${requestScope.Message}</div></td></tr>
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

        var uname = document.getElementById("username");
        var upwd = document.getElementById("password");
        var txfile = document.getElementById("txfile");
        var check=document.getElementById("checkcodes");

        if(uname.value == ""){
            alert("请输入用户名")
        }else if(upwd.value == ""){
            alert("请输入密码")
        }else if(txfile.value == ""){
            alert("请上传头像");
        }else if(check.value == ""){
            alert("请输入验证码");
        }else
        {
            document.forms[0].submit()
        }
    }
    //验证密码的格式是否符合要求
    function isPassword(strPwd){
        var passwordReg=/^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$).{6,20}$/;
        if(strPwd != "" && strPwd.search(passwordReg) != -1)
        {
        }else{
            alert("密码6-20位，只允许字母、数字、下划线其中两项!!!");
            document.getElementById("password").value="";
        }
    }
    function isusername(struser) {
        var usernameReg=/^[a-zA-Z].{3,6}$/;
        if(struser != "" && struser.search(usernameReg) != -1)
        {
        }else{
            alert("用户名只能为字母，长度为3-6位");
            document.getElementById("password").value="";
            document.getElementById("username").value="";
        }


    }
</script>
</html>
