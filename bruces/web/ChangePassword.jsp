
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
</head>
<style>
</style>
<body>
<div style="text-align: center">
    <h3>修改您的密码</h3>
</div>
<div style="text-align: center">
    <form action="${pageContext.request.contextPath}/ChangePasswordServlet?method=change" method="post">
    原密码：<input type="password" id="password" name="password" placeholder="请输入原密码"><br>
    新密码：<input type="password" id="password2" name="newpassword" placeholder="请输入新密码" pattern="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$"
               title="请输入6-20个字母、数字、下划线 作为密码" onblur="isPassword(this.value)"><br>
    确认密码：<input type="password" id="password3" name="confirmpassword" placeholder="请确认新密码" onblur="isRepeat()"><br>
    <input type="submit" value="确认修改" onclick="return check()" >
    </form>
</div>
</body>
<script type="text/javascript">

    var password = document.getElementById("password");
    var upwd = document.getElementById("passwor2");
    var upwd1 = document.getElementById("password3");

    //表单提交之后调用的函数--由它调用其他判断的函数
    //检查用户输入是否为空
    function check(){

        if(password.value == ""){
            alert("请输入原密码");
        }else if(upwd.value == ""){
            alert("请输入新密码");
        }else if(upwd1.value == ""){
            alert("请再次输入密码");
        }else
        {
            document.forms[0].submit();
        }
    }

    //验证密码的格式是否符合要求
    function isPassword(strPwd){
        var passwordReg=/^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$).{6,20}$/;
        if(strPwd != "" && strPwd.search(passwordReg) != -1)
        {
        }else{
            alert("密码6-20位，只允许字母、数字、下划线其中两项!!!");
            return false;
        }
    }

    //验证用户再次输入的密码是否一致
    function isRepeat() {

        var pw=upwd.value;
        var pw2=upwd1.value;

       if (pw!=pw2){
           alert("两次输入的密码不相等");
           pw="";
           pw2="";
       }

    }

</script>
</html>
