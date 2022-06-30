<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <style>
        body{
            padding: 0;
            margin: 0;
            width: 680px;

        }

        #topnav li{
            padding-top: 5px;
            width: 50px;
            float: left;
            position: relative;
        }
        .backgroud{
            width: 900px;
            margin: 0 auto;
        }
        .backgroudbody{
            width: 680px;
            min-width: 680px;
            height: 2000px;
            background-color: #fff;
            margin: 0 auto;
        }
        .kejianfanwei p{
            float: left;
        }
        .neirong{
            margin-top: 100px;
            width: 400px;
            height: 200px;
            resize: none;
            border: 1px solid #FFc1ed;
            opacity: 0.5;
            border-radius: 1.5%;
            placeholder:请输入评论内容;
            size: 20px;
            color: #111111;

        }

        .publish-content{
            width: 680px;
            float: left;
            border-bottom: 1px solid #ccc;
        }
        .myhead{
            width: 40px;
            height: 40px;
            background-image: url(${pageContext.request.contextPath}/resources/picture/${User.photo});
            background-size: 100% 100%;
            border-radius: 50%;
            margin-top: 30px;
            float: left;
        }

        .thefunction li{
            float: left;
            margin-top: 20px;
            font-size: 15px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="backgroudbody">



            <div>
                <div class="publish-content">
                    <div class="myhead" style=" background-image: url(${pageContext.request.contextPath}/resources/picture/${status.user.photo})"></div>
                    <p style="margin-top: 38px;float: left;margin-left: 20px;"> ${status.user.username}</p>
                    <p style="margin-top: 42px;float: left;margin-left: 30px;font-size: 13px;color: #666;">发布时间:${status.create_time}</p>
                    <div style="clear:both"></div>
                <p style="width: 900px;margin: 30px auto;line-height: 30px;font-size: 16px;color: #333;">
                    <span style="margin-left: 50px" >${status.description}</span>
                    <span>${status.label.label_name}</span>
                    <br><br>

                    <video style="width: 200px;height: 250px;" controls  onerror="javascript:this.style.display = 'none'" loop src="${pageContext.request.contextPath}/resources/picture/${status.mood}"></video>
                    <img style="width: 200px;height: 250px;" controls onerror="javascript:this.style.display = 'none'" loop src="${pageContext.request.contextPath}/resources/picture/${status.mood}"></img>
                    <%--        <img style="width: 150px;height: 150px;" id="img" src="${pageContext.request.contextPath}/${status.mood}">--%>

                </p>

                    <div style="clear:both"></div>

            </div>
        </div>



                    <c:forEach items="${commentlist}" var="comment">
                        <div class="publish-content">
                            <div style="width: 40px;
                                    height: 40px;
                                    background-image: url(${pageContext.request.contextPath}/resources/picture/${comment.remarker.photo});
                                    background-size: 100% 100%;
                                    border-radius: 50%;
                                    margin-top: 30px;
                                    float: left;"></div>
                        <p style="margin-top: 38px;float: left;margin-left: 20px;"> ${comment.remarker.username}</p>
                        <p style="margin-top: 42px;float: left;margin-left: 30px;font-size: 13px;color: #666;">评论时间:${comment.remark_time}</p>
                        <div style="clear:both"></div>
                        <p style="width: 900px;margin: 30px auto;line-height: 30px;font-size: 16px;color: #333;">
                                ${comment.remark_content}
                        </p>
                        <div style="clear:both"></div>
                        </div>
                    </c:forEach>


    <div class="backgroud">
        <form action="${pageContext.request.contextPath}/StatusServlet?method=remark" method="post">
            <div>
                <textarea class="neirong"  name="remark_content" rows="6" cols="30"></textarea>
                <div style="clear:both"></div>
                <input type="reset" value="重置" style="opacity: 0.7;"/>
                <input type="submit" value="确认评论"  style="margin-left: 30px;opacity: 0.7;"/>
            </div>
        </form>
    </div>

</div>

</body>
</html>
