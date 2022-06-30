package servlet;

import com.alibaba.fastjson.JSON;
import javafx.application.Application;
import pojo.Remark;
import pojo.Reply;
import pojo.Status;
import pojo.User;
import service.*;
import util.IsFollowOrStar;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/CenterServlet")
public class CenterServlet extends HttpServlet {
    StatusService statusService=new StatusServiceImpl();
    UserService userService=new UserServiceImpl();
    StarService starService=new StarServiceImpl();
    RemarkService remarkService=new RemarkServiceImpl();
    ReplyService replyService=new ReplyServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");


        String user_Id = request.getParameter("user_Id");//传进来哪个用户的Id，从而进入不同的个人中心
        User otherUser = null;
        /**
         * 判断是否第一次进入个人中心
         */
        if (user_Id != null) {//第一次
            otherUser = userService.finduserbyId(Integer.parseInt(user_Id));
            request.getSession().setAttribute("otherUser", otherUser);
        } else {//其他次
            otherUser = (User) request.getSession().getAttribute("otherUser");
        }
        User user = (User) request.getSession().getAttribute("User");//找到登录用户的User session


        String delete_id = request.getParameter("delete_id");//删除
        String enjoy_id = request.getParameter("enjoy_id");//点赞
        String status_id = request.getParameter("status_id");//收藏
        String user_id = request.getParameter("user_id");//关注
        String method = request.getParameter("method");

        String status_Id = request.getParameter("status_Id");
        Status status = null;
        if (status_Id != null) {
            status = statusService.findStatusById(Integer.parseInt(status_Id));
            request.setAttribute("status", status);
        } else {
            status = (Status) request.getAttribute("status");
        }


        if (user.getId()==otherUser.getId()) {

            switch (method) {
                case "ShowAll":
                    List<Status> statusList = statusService.findUserStatusList(otherUser.getId());
                    request.setAttribute("UserStatus", statusList); //保存这一页的文章

                    Map<Integer,List<Remark>> theRemarks = new HashMap<>();
                    for(Status s:statusList){
                        theRemarks.put(s.getStatus_id(),remarkService.findAllRemark(s.getStatus_id()));
                    }
                    request.setAttribute("CenterRemarks",theRemarks);


                    List<Integer> allfollows2 = userService.findAllfollows(user.getId());
                    List<Integer> allstar2 = starService.findAllstars(user.getId());
/**
 * Map判断是否关注与是否收藏，改变前端界面图标显示
 */
                    Map<Integer, Boolean> userlist = new HashMap<>();
                    Map<Integer, Boolean> starlist = new HashMap<>();
                    for (int i = 0; i < statusList.size(); i++) {
                        userlist.put(statusList.get(i).getStatus_id(), IsFollowOrStar.isfollows(statusList.get(i).getUser().getId(), allfollows2));
                        starlist.put(statusList.get(i).getStatus_id(), IsFollowOrStar.ifstars(statusList.get(i).getStatus_id(), allstar2));

                    }
                    request.setAttribute("userlist", userlist);
                    request.setAttribute("starlist", starlist);


                    request.getRequestDispatcher("/mycenter.jsp").forward(request, response);
                    return;
                case "enjoy": {
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    starService.addEnjoy(Integer.parseInt(enjoy_id)); //点赞加一
                    int enjoyCount=statusService.enjoyCount(Integer.parseInt(enjoy_id));
                    String enjoyCountJson="{\"enjoyCount\":"+enjoyCount+"}";
                    writer.write(enjoyCountJson);
                    return;
                }
                case "delete":{
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    statusService.deleteStatus(Integer.parseInt(delete_id));
                    writer.write("true");

                    return;

                }
                case "star": {
                    List<Integer> allstar = starService.findAllstars(user.getId());
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    String ifstar="";
                    if (IsFollowOrStar.ifstars(Integer.parseInt(status_id), allstar)) {
                        starService.removeStar(user.getId(), Integer.parseInt(status_id)); //移除收藏表
                        statusService.decrease(Integer.parseInt(status_id)); //该文章总收藏减一
                        starService.cancelStar(user.getId()); //该用户的总收藏减一
                        ifstar="false";
                    } else {
                        starService.addStar(user.getId(), Integer.parseInt(status_id)); //添加到收藏表
                        statusService.increase(Integer.parseInt(status_id)); //该文章总收藏量加一
                        starService.startStar(user.getId()); //该用户的总收藏量加一
                        ifstar="true";
                    }
                    int starCount=starService.starCount(Integer.parseInt(status_id));
                    String starJson="{\"starCount\":"+starCount+",\"ifstar\":"+ifstar+"}";
                    writer.write(starJson);
                    return;

                }
                case "attention": {
                    List<Integer> allfollows = userService.findAllfollows(user.getId());
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    if (IsFollowOrStar.isfollows(Integer.parseInt(user_id), allfollows)) {
                        userService.removeFriend(user.getId(), Integer.parseInt(user_id)); //关注者移除该关注，被关注者移除该粉丝
                        userService.cancelFollow(user.getId(), Integer.parseInt(user_id)); //关注减一，粉丝减一
                        writer.write("false");
                        return;
                    } else {
                        userService.addFriend(user.getId(), Integer.parseInt(user_id));//关注者关注该用户，被关注者添加粉丝
                        userService.startFollow(user.getId(), Integer.parseInt(user_id));//关注加一，粉丝加一
                        writer.write("true");
                        return;
                    }
                }
                case "addRemark":{ //将新的评论写入数据库，再查询所有的评论
                    String remark_content = request.getParameter("remark_content");//新的评论


                    Remark remark = new Remark(); //评论实体类
                    remark.setRemarker(user);
                    remark.setRemark_content(remark_content);
                    remark.setStatus(status);

                    remarkService.addRemark(remark);//添加评论
                    remarkService.addStatus_Remark(status.getStatus_id());//文章评论数加一
                    System.out.println("评论添加成功");

                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    Remark remarks = remarkService.findNewRemark(status.getStatus_id()); //查询最新一条评论
                    int remarkCount =statusService.findRemarkCount(status.getStatus_id());
                    /**
                     * 以json形式返回
                     */
                    String strJson = JSON.toJSONString(remarks);
                    String remarkJson="{\"remarkCount\":"+remarkCount+",\"remarks\":"+strJson+"}";
                    writer.write(remarkJson);
                    return;

                }
                case "reply":{

                    String remark_id = request.getParameter("remark_id");
                    List<Reply> replies=replyService.findTheReply(Integer.parseInt(remark_id));
                    String strJson = JSON.toJSONString(replies);
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter out = response.getWriter();
                    out.write(strJson);
                    return;

                }
                case "addreply":{

                    String remarkId = request.getParameter("remarkId");
                    String from_user_id = request.getParameter("from_user_id");
                    String to_user_id = request.getParameter("to_user_id");
                    String reply_content = request.getParameter("reply_content");

                    System.out.println(remarkId+"  "+from_user_id+"  "+to_user_id+"  "+reply_content);

                    replyService.addnewreply(Integer.parseInt(remarkId),Integer.parseInt(from_user_id),Integer.parseInt(to_user_id),reply_content);
                    replyService.addRemark_reply(Integer.parseInt(remarkId));
                    System.out.println("回复成功");

                    Reply reply=replyService.findNewReply(Integer.parseInt(remarkId));

                    int replyCount =replyService.findReplyCount(Integer.parseInt(remarkId));

                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    /**
                     * 以json形式返回
                     */
                    String strJson = JSON.toJSONString(reply);

                    String replyJson="{\"replyCount\":"+replyCount+",\"reply\":"+strJson+"}";
                    writer.write(replyJson);
                    return;
                }

            }
        } else  {


            switch (method) {
                case "ShowAll":
                    List<Status> statusList = statusService.findUserStatusList(otherUser.getId());
                    request.setAttribute("UserStatus", statusList); //保存这一页的文章

                    Map<Integer,List<Remark>> theRemarks = new HashMap<>();
                    for(Status s:statusList){
                        theRemarks.put(s.getStatus_id(),remarkService.findAllRemark(s.getStatus_id()));
                    }
                    request.setAttribute("CenterRemarks",theRemarks);

                    List<Integer> allfollows2 = userService.findAllfollows(user.getId());
                    List<Integer> allstar2 = starService.findAllstars(user.getId());
            /**
             * Map判断是否关注与是否收藏，改变前端界面图标显示
             */
                    Map<Integer, Boolean> userlist = new HashMap<>();
                    Map<Integer, Boolean> starlist = new HashMap<>();
                    for (int i = 0; i < statusList.size(); i++) {
                        userlist.put(statusList.get(i).getStatus_id(), IsFollowOrStar.isfollows(statusList.get(i).getUser().getId(), allfollows2));
                        starlist.put(statusList.get(i).getStatus_id(), IsFollowOrStar.ifstars(statusList.get(i).getStatus_id(), allstar2));

                    }
                    request.setAttribute("userlist", userlist);
                    request.setAttribute("starlist", starlist);

                    Map<Integer,User> map=new HashMap<>();
                    map.put(otherUser.getId(),user);
                    getServletContext().setAttribute("visitor",map);

                    request.getRequestDispatcher("/selfCenter.jsp").forward(request, response);
                    return;
                case "enjoy": {
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    starService.addEnjoy(Integer.parseInt(enjoy_id)); //点赞加一
                    int enjoyCount=statusService.enjoyCount(Integer.parseInt(enjoy_id));
                    String enjoyCountJson="{\"enjoyCount\":"+enjoyCount+"}";
                    writer.write(enjoyCountJson);
                    return;
                }
                case "star": {
                    List<Integer> allstar = starService.findAllstars(user.getId());
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    String ifstar="";
                    if (IsFollowOrStar.ifstars(Integer.parseInt(status_id), allstar)) {
                        starService.removeStar(user.getId(), Integer.parseInt(status_id)); //移除收藏表
                        statusService.decrease(Integer.parseInt(status_id)); //该文章总收藏减一
                        starService.cancelStar(user.getId()); //该用户的总收藏减一
                        ifstar="false";
                    } else {
                        starService.addStar(user.getId(), Integer.parseInt(status_id)); //添加到收藏表
                        statusService.increase(Integer.parseInt(status_id)); //该文章总收藏量加一
                        starService.startStar(user.getId()); //该用户的总收藏量加一
                        ifstar="true";
                    }
                    int starCount=starService.starCount(Integer.parseInt(status_id));
                    String starJson="{\"starCount\":"+starCount+",\"ifstar\":"+ifstar+"}";
                    writer.write(starJson);
                    return;

                }
                case "attention": {
                    List<Integer> allfollows = userService.findAllfollows(user.getId());
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    if (IsFollowOrStar.isfollows(Integer.parseInt(user_id), allfollows)) {
                        userService.removeFriend(user.getId(), Integer.parseInt(user_id)); //关注者移除该关注，被关注者移除该粉丝
                        userService.cancelFollow(user.getId(), Integer.parseInt(user_id)); //关注减一，粉丝减一
                        writer.write("false");
                        return;
                    } else {
                        userService.addFriend(user.getId(), Integer.parseInt(user_id));//关注者关注该用户，被关注者添加粉丝
                        userService.startFollow(user.getId(), Integer.parseInt(user_id));//关注加一，粉丝加一
                        writer.write("true");
                        return;
                    }
                }
                case "addRemark":{ //将新的评论写入数据库，再查询所有的评论

                    String remark_content = request.getParameter("remark_content");//新的评论

                    Remark remark = new Remark(); //评论实体类
                    remark.setRemarker(user);
                    remark.setRemark_content(remark_content);
                    remark.setStatus(status);

                    remarkService.addRemark(remark);//添加评论
                    remarkService.addStatus_Remark(status.getStatus_id());//文章评论数加一
                    System.out.println("评论添加成功");

                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    Remark remarks = remarkService.findNewRemark(status.getStatus_id()); //查询最新一条评论
                    int remarkCount =statusService.findRemarkCount(status.getStatus_id());
                    /**
                     * 以json形式返回
                     */
                    String strJson = JSON.toJSONString(remarks);
                    String remarkJson="{\"remarkCount\":"+remarkCount+",\"remarks\":"+strJson+"}";
                    writer.write(remarkJson);
                    return;

                }
                case "reply":{

                    String remark_id = request.getParameter("remark_id");
                    List<Reply> replies=replyService.findTheReply(Integer.parseInt(remark_id));
                    String strJson = JSON.toJSONString(replies);
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter out = response.getWriter();
                    out.write(strJson);
                    return;

                }
                case "addreply":{

                    String remarkId = request.getParameter("remarkId");
                    String from_user_id = request.getParameter("from_user_id");
                    String to_user_id = request.getParameter("to_user_id");
                    String reply_content = request.getParameter("reply_content");

                    System.out.println(remarkId+"  "+from_user_id+"  "+to_user_id+"  "+reply_content);

                    replyService.addnewreply(Integer.parseInt(remarkId),Integer.parseInt(from_user_id),Integer.parseInt(to_user_id),reply_content);
                    replyService.addRemark_reply(Integer.parseInt(remarkId));
                    System.out.println("回复成功");

                    Reply reply=replyService.findNewReply(Integer.parseInt(remarkId));

                    int replyCount =replyService.findReplyCount(Integer.parseInt(remarkId));

                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    /**
                     * 以json形式返回
                     */
                    String strJson = JSON.toJSONString(reply);

                    String replyJson="{\"replyCount\":"+replyCount+",\"reply\":"+strJson+"}";
                    writer.write(replyJson);
                    return;
                }
            }
        }
    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}