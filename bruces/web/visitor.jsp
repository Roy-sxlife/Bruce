<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="pojo.Visitor" %>
<%@ page import="pojo.User" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>我的访客记录</title>
</head>
<body>

<%
    User user= (User) request.getSession().getAttribute("User");
    List<User> visitorList=Visitor.visitorlist(user.getId());
%>
<div style="text-align: center">
你的访客记录共有：<%=Visitor.getVisitorCount(user.getId())%>条
你的访客记录有：
<%
    for (int i = 0; i < visitorList.size(); i++) {
        out.write("<p>"+visitorList.get(i).getUsername()+"</p>");
    }
%>
</div>
</body>
</html>
