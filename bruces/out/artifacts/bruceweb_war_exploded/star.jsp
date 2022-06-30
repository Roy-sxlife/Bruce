
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${User.username}的收藏列表</title>
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="js/jquery.validate.js"></script>
    <style type="text/css">
        body{margin: 0px;}
        .backgroudbody{
            width: 100%;
            min-width: 1000px;
            height: 2000px;
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
            width: 1100px;
            margin: 0 auto;
            border-bottom: 1px solid #ccc;
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
            background-image: url(${pageContext.request.contextPath}/resources/picture/${User.photo});
            background-size: 100% 100%;
            border-radius: 50%;
            margin-top: 30px;
            float: left;
        }
        .backgroud{
            width: 900px;
            margin: 0 auto;
            text-align: center;
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


    <div style="margin-top: 10px;text-align: center">收藏列表:</div>


    <c:forEach items="${statuses}" var="status">
    <div class="publish-content">
        <div class="myhead"  style=" background-image: url(${pageContext.request.contextPath}/resources/picture/${status.user.photo})"></div>
        <p style="margin-top: 38px;float: left;margin-left: 20px;"> <a  style="text-decoration: none" href="${status.user.id}">${status.user.username}<a/></p>
        <p style="margin-top: 42px;float: left;margin-left: 30px;font-size: 13px;color: #666;">发布时间:${status.create_time}</p>
        <div style="clear:both"></div>
        <p style="width: 900px;margin: 30px auto;line-height: 30px;font-size: 16px;color: #333;">
                    <span style="margin-left: 50px" >${status.description}</span>
                    <span style="color: #000000;font-weight: bold">${status.label.label_name}</span>
                    <br><br>

                    <video style="width: 200px;height: 250px;" controls  onerror="javascript:this.style.display = 'none'" loop src="${pageContext.request.contextPath}/resources/picture/${status.mood}"></video>
                    <img style="width: 200px;height: 250px;" controls onerror="javascript:this.style.display = 'none'" loop src="${pageContext.request.contextPath}/resources/picture/${status.mood}">

        </p>

        <ul class="thefunction">
            <a href="javascript:;" onclick="enjoy(${status.status_id})" style="text-decoration: none;color: #666666;"><li  style="margin-left: 50px;position: relative;"><img  src="${pageContext.request.contextPath}/resources/img/aie.png" style="width: 25px;height: 25px;position: absolute;right:20px;top: -2px;margin-right: 10px">(<span id="enjoy${status.status_id}">${status.enjoy}</span>)</li></a>
            <c:choose>
                <c:when test="${starlist.get(status.status_id)}">
                    <a href="javascript:;" onclick="star(${status.status_id})" style="text-decoration: none;color: #666666;" ><li style="margin-left: 70px;position: relative;"><img id="collect${status.status_id}" src="${pageContext.request.contextPath}/resources/img/star.jpeg" style="width: 25px;height: 25px;position: absolute;right:20px;top: -2px;margin-right: 10px">(<span id="star${status.status_id}">${status.star}</span>)</li></a>
                </c:when>
                <c:otherwise> <a href="javascript:;" onclick="star(${status.status_id})" style="text-decoration: none;color: #666666;"><li style="margin-left: 70px;position: relative;"><img id="collect${status.status_id}" src="${pageContext.request.contextPath}/resources/img/abd.png" style="width: 25px;height: 25px;position: absolute;right:20px;top: -2px;margin-right: 10px">(<span id="star${status.status_id}">${status.star}</span>)</li></a>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${userlist.get(status.status_id)}"><a href="javascript:;" onclick="follow(${status.user.id})" style="text-decoration: none;color: #666666;"><li style="margin-left: 95px;position: relative;"><img id="attention${status.user.id}" src="${pageContext.request.contextPath}/resources/img/love.png" style="width: 25px;height: 25px;position: absolute;right:20px;top: -2px;"></li></a> </c:when>
                <c:otherwise><a href="javascript:;" onclick="follow(${status.user.id})" style="text-decoration: none;color: #666666;"><li style="margin-left: 95px;position: relative;"><img id="attention${status.user.id}" src="${pageContext.request.contextPath}/resources/img/afv.png" style="width: 25px;height: 25px;position: absolute;right:20px;top: -2px;"></li></a></c:otherwise>
            </c:choose>
            <a  href="javascript:;" style="text-decoration: none;color: #666666;" onclick="comment(${status.status_id})"><li style="margin-left: 60px;position: relative;"><img src="${pageContext.request.contextPath}/resources/img/aii.png" style="width: 25px;height: 25px;position: absolute;right:20px;top: -2px;margin-right: 10px" >(<span id="remark${status.status_id}">${status.remark}</span>)</li></a>
            <a href="javascript:;" href="#" style="text-decoration: none;color: #666666;"><li style="margin-right: 50px;float: right;position: relative;"><img  src="${pageContext.request.contextPath}/resources/img/aev.png" style="width: 25px;height: 25px;position: absolute;top: -2px;"></li></a>
        </ul>

        <div style="clear:both"></div>
    </div>

        <div id="Remarksection${status.status_id}" style="margin-left: 30px;display: none"  >
            <c:forEach items="${StarRemark.get(status.status_id)}" var="remark">
                <div class="publish-content">
                    <div style="width: 40px;
                            height: 40px;
                            background-image: url(${pageContext.request.contextPath}/resources/picture/${remark.remarker.photo});
                            background-size: 100% 100%;
                            border-radius: 50%;
                            margin-top: 30px;
                            float: left;"></div>
                    <p style="margin-top: 38px;float: left;margin-left: 20px;">${remark.remarker.username}</p>
                    <p style="margin-top: 42px;float: left;margin-left: 30px;font-size: 13px;color: #666;">评论时间:${remark.remark_time}</p>
                    <div style="clear:both"></div>
                    <p style="width: 900px;margin: 30px auto;line-height: 30px;font-size: 16px;color: #333;">
                            ${remark.remark_content}
                    </p>
                    <span onclick="ShowReply('${remark.remarker.username}',${remark.id})" style="font-size:8px;color:#000000;margin-top: 20px;">查看回复（<span id="remark_reply${remark.id}">${remark.reply_count}</span>）</span>
                    <div id="replyShow${remark.id}" style="margin-left: 80px">

                    </div>
                    <div id="replyadd${remark.id}" style="display: none;margin-top: 20px;margin-left: 80px">
                        <form action="" >
                            <div>
                                <textarea id="reply_content_id${remark.id}" rows="6" cols="30" placeholder=""></textarea>
                                <div style="clear:both"></div>
                                <input type="reset" value="重置" style="opacity: 0.7;"/>
                                <button href="javascript:;" type="button" id="${remark.remarker.id}" onclick="addReply(${remark.id},${sessionScope.User.id})" style="margin-left: 30px;opacity: 0.7;" >确认回复</button>
                            </div>
                        </form>
                    </div>
                    <div style="clear:both"></div>
                </div>
            </c:forEach>
        </div>
        <div id="Replycomment${status.status_id}" style="display: none;">
            <div class="backgroud">
                <form action="" id="form${status.status_id}">
                    <div>
                        <textarea class="neirong"  id="remark_content_id${status.status_id}" rows="6" cols="30"></textarea>
                        <div style="clear:both"></div>
                        <input type="reset" value="重置" style="opacity: 0.7;"/>
                        <button href="javascript:;" type="button" onclick="addRemark(${status.status_id})" style="margin-left: 30px;opacity: 0.7;" >确认评论</button>
                    </div>
                </form>
            </div>
        </div>


    </c:forEach>


    <p style="margin-bottom: 30px;text-align: center;color: #666;">没有更多内容啦~</p>
</div>
</body>
<script>

    function comment(j){ //显示评论列表

        var c=j;

        document.getElementById("Replycomment"+c).style.display="block"; //显示填写评论的form
        document.getElementById("Remarksection"+c).style.display="block";//显示评论列表


    }

    function ShowReply(name,a) {
        var remark_id=a;

        document.getElementById("reply_content_id"+remark_id).setAttribute("placeholder","回复 @"+name);
        document.getElementById("replyadd"+remark_id).style.display="block";
        $("#"+"replyShow"+remark_id).empty();

        var xmlHttpRequest = new XMLHttpRequest();

        xmlHttpRequest.onreadystatechange=function(){
            if(xmlHttpRequest.readyState == 4&&xmlHttpRequest.status == 200) {
                var reply= xmlHttpRequest.responseText;
                var obj =eval('(' +reply+ ')');
                for(var i in obj){
                    var list=obj[i];

                    $reply =
                        '						<div >' +
                        '						    <div style=\"width: 40px;\n' +
                        '                                    height: 40px;\n' +
                        '                                    background-image: url(' + '${pageContext.request.contextPath}'+'/'+'resources'+ '/'+'picture'+'/'+list.from_user.photo + ');\n' +
                        '                                    background-size: 100% 100%;\n' +
                        '                                    border-radius: 50%;\n' +
                        '                                    margin-top: 30px;\n' +
                        '                                    float: left;\">' +
                        '                                </div>'+
                        '						            <p style=\"margin-top: 38px;float: left;margin-left: 20px;\">' + list.from_user.username +' 回复 @'+list.to_user.username+'</p>' +
                        '						            <p style=\"margin-top: 42px;float: left;margin-left: 30px;font-size: 13px;color: #666;\">' + '回复时间:'+list.reply_time + '</p>' +
                        '                       <div style=\"clear:both\">'+
                        '                       </div>'+
                        '						        <p style=\"width: 900px;margin: 30px auto;line-height: 30px;font-size: 16px;color: #333;\">' + list.reply_content +
                        '						</p>' +
                        '        <span onclick=\"A_reply('+'\''+list.from_user.username+'\''+','+list.remark.id+','+list.from_user.id +')\" style=\"font-size:8px;color:#000000;margin-top: 20px;\">回复（'+ list.reply_number+'）</span>'+
                        '                       <div style=\"clear:both\">'+
                        '                       </div>'+
                        '						</div>' +
                        '';
                    $("#"+"replyShow"+remark_id).append($reply);
                }

            }

        }
        xmlHttpRequest.open("post","${pageContext.request.contextPath}/StarServlet",true);
        xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        xmlHttpRequest.send("method=reply&remark_id="+remark_id);
    }

    function addReply(a,userId) {
        var remark_id=a;
        var from_user_id=userId;
        var to_user_id= document.getElementById("replyadd"+remark_id).getElementsByTagName("button")[0].id;
        var reply_content=document.getElementById("reply_content_id"+remark_id).value;

        var xmlHttpRequest = new XMLHttpRequest();

        xmlHttpRequest.onreadystatechange=function(){
            if(xmlHttpRequest.readyState == 4&&xmlHttpRequest.status == 200) {
                document.getElementById("reply_content_id"+remark_id).value="";
                var reply= xmlHttpRequest.responseText;
                var obj =eval('(' +reply+ ')');
                document.getElementById("remark_reply"+remark_id).innerText=obj.replyCount;

                var list=obj.reply;


                $reply =
                    '						<div >' +
                    '						    <div style=\"width: 40px;\n' +
                    '                                    height: 40px;\n' +
                    '                                    background-image: url(' + '${pageContext.request.contextPath}'+'/'+'resources'+ '/'+'picture'+'/'+list.from_user.photo + ');\n' +
                    '                                    background-size: 100% 100%;\n' +
                    '                                    border-radius: 50%;\n' +
                    '                                    margin-top: 30px;\n' +
                    '                                    float: left;\">' +
                    '                                </div>'+
                    '						            <p style=\"margin-top: 38px;float: left;margin-left: 20px;\">' + list.from_user.username +' 回复 @'+list.to_user.username+'</p>' +
                    '						            <p style=\"margin-top: 42px;float: left;margin-left: 30px;font-size: 13px;color: #666;\">' + '回复时间:'+list.reply_time + '</p>' +
                    '                       <div style=\"clear:both\">'+
                    '                       </div>'+
                    '						        <p style=\"width: 900px;margin: 30px auto;line-height: 30px;font-size: 16px;color: #333;\">' + list.reply_content +
                    '						</p>' +
                    '        <span onclick=\"A_reply('+'\''+list.from_user.username+'\''+','+list.remark.id+','+list.from_user.id +')\" style=\"font-size:8px;color:#000000;margin-top: 20px;\">回复（'+ list.reply_number+'）</span>'+
                    '                       <div style=\"clear:both\">'+
                    '                       </div>'+
                    '						</div>' +
                    '';
                $("#"+"replyShow"+remark_id).append($reply);


            }

        }
        xmlHttpRequest.open("post","${pageContext.request.contextPath}/StarServlet",true);
        xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        xmlHttpRequest.send("method=addreply&remarkId="+remark_id+"&from_user_id="+from_user_id+"&to_user_id="+to_user_id+"&reply_content="+reply_content);

    }

    function A_reply(name,id,from_user_id){
        var username=name;
        var remark_id=id;
        document.getElementById("replyadd"+remark_id).getElementsByTagName("button")[0].id=from_user_id;
        document.getElementById("reply_content_id"+remark_id).setAttribute("placeholder","回复 @"+username);
    }

    function addRemark(statusId) { //填写评论再重新显示
        var statusId=statusId;

        var remarkcontent=document.getElementById("remark_content_id"+statusId).value;
        //清空上一次的评论内容---用empty清空原来的评论区
        // $("#"+"Remarksection"+statusId).empty();


        var xmlHttpRequest = new XMLHttpRequest();

        xmlHttpRequest.onreadystatechange=function(){

            if(xmlHttpRequest.readyState == 4&&xmlHttpRequest.status == 200) {
                document.getElementById("remark_content_id"+statusId).value="";
                var data=xmlHttpRequest.responseText;
                var obj =eval('(' +data+ ')');
                document.getElementById("remark"+statusId).innerText=obj.remarkCount;

                var list=obj.remarks;

                    $comments =
                        '						<div class=\"publish-content\" >' +
                        '						    <div style=\"width: 40px;\n' +
                        '                                    height: 40px;\n' +
                        '                                    background-image: url(' + '${pageContext.request.contextPath}'+'/'+'resources'+ '/'+'picture'+'/'+list.remarker.photo + ');\n' +
                        '                                    background-size: 100% 100%;\n' +
                        '                                    border-radius: 50%;\n' +
                        '                                    margin-top: 30px;\n' +
                        '                                    float: left;\">' +
                        '                                </div>'+
                        '						            <p style=\"margin-top: 38px;float: left;margin-left: 20px;\">' + list.remarker.username + '</p>' +
                        '						            <p style=\"margin-top: 42px;float: left;margin-left: 30px;font-size: 13px;color: #666;\">' + '评论时间:'+list.remark_time + '</p>' +
                        '                       <div style=\"clear:both\">'+
                        '                       </div>'+
                        '						        <p style=\"width: 900px;margin: 30px auto;line-height: 30px;font-size: 16px;color: #333;\">' + list.remark_content +
                        '						</p>' +
                        '        <span onclick=\"ShowReply('+'\''+list.remarker.username+'\''+','+list.id+')\" style=\"font-size:8px;color:#000000;margin-top: 20px;\">查看回复（'+'<span id=\"remark_reply'+list.id +'\">'+ list.reply_count+'</span>'+'）</span>'+
                        ' <div id=\"replyShow'+list.id+'\" style=\"margin-left: 80px\">'+
                        '</div>'+
                        ' <div id=\"replyadd'+list.id+'\" style=\"display: none;margin-top: 20px;margin-left: 80px\">'+
                        ' <form action=\"\" >'+
                        ' <div>'+
                        ' <textarea id=\"reply_content_id'+list.id+'\" rows=\"6\" cols=\"30\" placeholder=\"\"></textarea>'+
                        '<div style="clear:both">'+
                        '</div>'+
                        ' <input type=\"reset\" value=\"重置\" style=\"opacity: 0.7;\"/>'+
                        ' <button type=\"button\" id=\"'+list.remarker.id+'\" onclick=\"addReply('+list.id+','+'${sessionScope.User.id})\" style=\"margin-left: 30px;opacity: 0.7;\" >确认回复</button>'+
                        '</div>'+
                        '</form>'+
                        '</div>'+
                        '     <div style=\"clear:both\">'+
                        '   </div>'+
                        '						</div>' +
                        '';
                    $("#"+"Remarksection"+statusId).append($comments);

            }
        }
        xmlHttpRequest.open("post","${pageContext.request.contextPath}/StarServlet",true);
        xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        xmlHttpRequest.send("method=addRemark&remark_content="+remarkcontent+"&status_Id="+statusId);

    }


    function star(s) { //收藏与取消收藏
        var starId=s;
        var xmlHttpRequest = new XMLHttpRequest();

        xmlHttpRequest.onreadystatechange=function(){
            if(xmlHttpRequest.readyState == 4&&xmlHttpRequest.status == 200) {

                var data=xmlHttpRequest.responseText;
                var obj=eval('(' +data+ ')');


                if (obj.ifstar==true){
                    document.getElementById("collect"+starId).src="${pageContext.request.contextPath}"+"/"+"resources"+"/"+"img"+"/"+"star.jpeg";
                    document.getElementById("star"+starId).innerText=obj.starCount;
                }else if(obj.ifstar==false){
                    document.getElementById("collect"+starId).src="${pageContext.request.contextPath}"+"/"+"resources"+"/"+"img"+"/"+"abd.png";
                    document.getElementById("star"+starId).innerText=obj.starCount;
                }

            }
        }


        xmlHttpRequest.open("post","${pageContext.request.contextPath}/StarServlet",true);
        xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        xmlHttpRequest.send("method=star&status_id="+starId);

    }
    function enjoy(e) { //点赞，暂未有取消点赞
        var enjoy_id=e;

        var xmlHttpRequest = new XMLHttpRequest();

        xmlHttpRequest.onreadystatechange=function(){
            if(xmlHttpRequest.readyState == 4&&xmlHttpRequest.status == 200) {
                var enjoy= xmlHttpRequest.responseText;
                var obj =eval('(' +enjoy+ ')');
                document.getElementById("enjoy"+enjoy_id).innerText=obj.enjoyCount;
            }

        }
        xmlHttpRequest.open("post","${pageContext.request.contextPath}/StarServlet",true);
        xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        xmlHttpRequest.send("method=enjoy&enjoy_id="+enjoy_id);
    }
    function follow(f) { //关注和取消关注

        var follow_id=f;
        var xmlHttpRequest = new XMLHttpRequest();

        xmlHttpRequest.onreadystatechange=function(){
            if(xmlHttpRequest.readyState == 4&&xmlHttpRequest.status == 200) {
                var ifFollow= xmlHttpRequest.responseText;
                if (ifFollow=="true"){
                    document.getElementById("attention"+follow_id).src="${pageContext.request.contextPath}"+"/"+"resources"+"/"+"img"+"/"+"love.png";
                }else if(ifFollow=="false"){
                    document.getElementById("attention"+follow_id).src="${pageContext.request.contextPath}"+"/"+"resources"+"/"+"img"+"/"+"afv.png";
                }
            }

        }
        xmlHttpRequest.open("post","${pageContext.request.contextPath}/StarServlet",true);
        xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        xmlHttpRequest.send("method=attention&user_id="+follow_id);
    }





</script>

</html>
