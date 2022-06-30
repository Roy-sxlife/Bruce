package servlet;

import pojo.Page;
import pojo.User;
import service.StarService;
import service.StarServiceImpl;
import service.UserService;
import service.UserServiceImpl;
import util.IsFollowOrStar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/FriendListServlet")
public class FriendListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        User user=(User)request.getSession().getAttribute("User");
        String method = request.getParameter("method");
        String follow_id=request.getParameter("follow_id");
        String fan_id = request.getParameter("fan_id");

        UserService userService=new UserServiceImpl();
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

        request.setAttribute("method", method);
        switch (method){
            case "follow":{
                List<User> userList=null;
                List<Integer> allfollows=userService.findAllfollows(user.getId());


                if(follow_id!=null){
                    /**
                     * 判断是否关注
                     */
                    if(IsFollowOrStar.isfollows(Integer.parseInt(follow_id),allfollows)){
                        userService.removeFriend(user.getId(),Integer.parseInt(follow_id));
                        userService.cancelFollow(user.getId(),Integer.parseInt(follow_id));
                    }else {
                        userService.addFriend(user.getId(),Integer.parseInt(follow_id));
                        userService.startFollow(user.getId(),Integer.parseInt(follow_id));
                    }

                }

                //总数据数
                int totalCount =allfollows.size();
                //组装page对象
                //先set totalCount属性 作为分子
                page.setTotalCount(totalCount);
                page.setCurrentPage(currentPage);
                page.setPageSize(pageSize);
                userList=userService.queryFriendByPage(user.getId(),currentPage, pageSize);

                page.setObjects(userList);
                //将数据传给request
                request.setAttribute("p1",page);

                List<Integer> allfollows2=userService.findAllfollows(user.getId());//重新查找所关注的朋友id

                Map<Integer,Boolean> list=new HashMap<>();
                for(int i=0;i<userList.size();i++){
                    list.put(userList.get(i).getId(), IsFollowOrStar.isfollows(userList.get(i).getId(),allfollows2));
                }
                request.getSession().setAttribute("list1",list);
                request.getSession().setAttribute("follows",userList);
                request.getRequestDispatcher("/quanbuguanzhu.jsp").forward(request,response);
                return;
            }
            case "fan":{
                List<User> fans =null;
                List<Integer> allfollows=userService.findAllfollows(user.getId());//查看这个粉丝是否有关注


                if(fan_id!=null){
                    /**
                     * 判断是否关注
                     */
                    if(IsFollowOrStar.isfollows(Integer.parseInt(fan_id),allfollows)){
                        userService.removeFriend(user.getId(),Integer.parseInt(fan_id));
                        userService.cancelFollow(user.getId(),Integer.parseInt(fan_id));
                    }else {
                        userService.addFriend(user.getId(),Integer.parseInt(fan_id));
                        userService.startFollow(user.getId(),Integer.parseInt(fan_id));
                    }

                }
                //总数据数
                int totalCount =userService.findAllfansUser(user.getId()).size();
                //组装page对象
                //先set totalCount属性 作为分子
                page.setTotalCount(totalCount);
                page.setCurrentPage(currentPage);
                page.setPageSize(pageSize);

                fans=userService.queryFanByPage(user.getId(),currentPage, pageSize);
                page.setObjects(fans);
                //将数据传给request
                request.setAttribute("p2", page);


                List<Integer> allfollows2=userService.findAllfollows(user.getId());


                Map<Integer,Boolean> list=new HashMap<>();
                for(int i=0;i<fans.size();i++){
                    list.put(fans.get(i).getId(), IsFollowOrStar.isfollows(fans.get(i).getId(),allfollows2));
                }

                request.getSession().setAttribute("list2",list);
                request.getSession().setAttribute("fans",fans);
                request.getRequestDispatcher("/wodefensi.jsp").forward(request,response);
                return;
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}