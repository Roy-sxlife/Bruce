package servlet;

import com.alibaba.fastjson.JSON;
import pojo.*;
import service.*;
import util.IsFollowOrStar;

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

@WebServlet("/StatusServlet")
public class StatusServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();
    StarService starService=new StarServiceImpl();
    StatusService statusService=new StatusServiceImpl();
    RemarkService remarkService=new RemarkServiceImpl();
    ReplyService replyService=new ReplyServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        User user =(User) request.getSession().getAttribute("User");



        String status_id=request.getParameter("status_id"); //收藏的Id
        String user_id=request.getParameter("user_id"); //关注的Id
        String enjoy_id=request.getParameter("enjoy_id"); //点赞的Id
        String method = request.getParameter("method");//需要进行的操作

        /**
         * 分页定义
         */

        Page page = new Page();
        int currentPage =1 ;
        //当前要显示的页面---超链接里面的请求数据
        String currentPageStr = request.getParameter("currentPage");
        //第一页--默认的页面
        if(currentPageStr == null || "".equals(currentPageStr))
        {
            currentPage = 1;
        }
        else
        {
            //当前页--用户选择的页面数
            currentPage = Integer.parseInt(currentPageStr);
        }
        //一页的大小
        int pageSize = 3;

        System.out.println(method);//输出method需要进行的操作

        switch (method) {
                case "ShowAll": //显示该页的所有文章
                    List<Status> statuses=statusService.findStatusList();//所有的文章
                    List<Status> statusList=statusService.queryStatusByPage(currentPage, pageSize); //这一页的文章
                    request.setAttribute("statuslist",statusList); //保存这一页的文章

                    Map<Integer,List<Remark>> theRemarks = new HashMap<>();
                    for(Status s:statusList){
                        theRemarks.put(s.getStatus_id(),remarkService.findAllRemark(s.getStatus_id()));
                    }
                    request.setAttribute("theRemarks",theRemarks);

                    //总数据数
                    int totalCount =statuses.size();
                    //组装page对象
                    //先set totalCount属性 作为分子
                    page.setTotalCount(totalCount);
                    page.setCurrentPage(currentPage);
                    page.setPageSize(pageSize);

                    page.setObjects(statusList);
                    //将数据传给request
                    request.setAttribute("p3",page);



                    List<Integer> allfollows2 = userService.findAllfollows(user.getId());
                    List<Integer> allstar2 = starService.findAllstars(user.getId());
                /**
                 * Map判断是否关注与是否收藏，改变前端界面图标显示
                 */
                    Map<Integer,Boolean> list=new HashMap<>();
                    Map<Integer,Boolean> starlist=new HashMap<>();
                    for(int i=0;i<statusList.size();i++){
                        list.put(statusList.get(i).getStatus_id(),IsFollowOrStar.isfollows(statusList.get(i).getUser().getId(),allfollows2));
                        starlist.put(statusList.get(i).getStatus_id(),IsFollowOrStar.ifstars(statusList.get(i).getStatus_id(),allstar2));

                    }

                    request.setAttribute("list",list);
                    request.setAttribute("starlist",starlist);



                    request.getRequestDispatcher("/public.jsp").forward(request,response); //返回最新动态页面
                    return;
                case "enjoy": { //进行点赞
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    starService.addEnjoy(Integer.parseInt(enjoy_id)); //点赞加一
                    int enjoyCount=statusService.enjoyCount(Integer.parseInt(enjoy_id));
                    String enjoyCountJson="{\"enjoyCount\":"+enjoyCount+"}";
                    writer.write(enjoyCountJson);
                    return;
                }
                case "star": { //收藏与取消收藏
                    List<Integer> allstar = starService.findAllstars(user.getId());
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    String ifstar="";
                    /**
                     * 判断是否收藏
                     */
                    if(IsFollowOrStar.ifstars(Integer.parseInt(status_id),allstar)) {
                        starService.removeStar(user.getId(), Integer.parseInt(status_id)); //移除收藏表
                        statusService.decrease(Integer.parseInt(status_id)); //该文章总收藏减一
                        starService.cancelStar(user.getId()); //该用户的总收藏减一
                        ifstar="false";
                    }
                    else {
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
                case "attention": {//关注与取消关注
                    List<Integer> allfollows = userService.findAllfollows(user.getId());
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    /**
                     * 判断是否关注
                     */
                    if(IsFollowOrStar.isfollows(Integer.parseInt(user_id),allfollows)){
                        userService.removeFriend(user.getId(), Integer.parseInt(user_id)); //关注者移除该关注，被关注者移除该粉丝
                        userService.cancelFollow(user.getId(), Integer.parseInt(user_id)); //关注减一，粉丝减一
                        writer.write("false");
                        return;
                    }else {
                        userService.addFriend(user.getId(), Integer.parseInt(user_id));//关注者关注该用户，被关注者添加粉丝
                        userService.startFollow(user.getId(), Integer.parseInt(user_id));//关注加一，粉丝加一
                        writer.write("true");
                        return;
                    }
                }
                case "addRemark":{ //将新的评论写入数据库，再查询所有的评论
                    /**
                     * 评论列表与发布评论传输返回的文章的Id，根据Id找到哪篇文章
                     */
                    String status_Id = request.getParameter("status_Id");
                    Status status = null;
                    if (status_Id != null) {
                        status = statusService.findStatusById(Integer.parseInt(status_Id));
                        request.getSession().setAttribute("status", status);
                    } else {
                        status = (Status) request.getSession().getAttribute("status");
                    }

                    String remark_content = request.getParameter("remark_content");//新的评论


                        Remark remark = new Remark(); //评论实体类
                        remark.setRemarker(user);
                        remark.setReply_count(0);
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        doPost(request, response);
    }


}