package servlet;

import pojo.Music;
import pojo.User;
import service.MusicService;
import service.MusicServiceImpl;
import service.UserService;
import service.UserServiceImpl;
import util.MD5Util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String jmpassword = MD5Util.MD5Encode(password, "UTF-8");
        String check=request.getParameter("check");
        String code=request.getSession().getAttribute("code")+"";
        HttpSession session=request.getSession();

        UserService userService=new UserServiceImpl();

        MusicService musicService=new MusicServiceImpl();
        List<Music> bruceAlbum=null;

        bruceAlbum=musicService.findAlbums();
        request.getSession().setAttribute("bruceAlbum",bruceAlbum);

        int size = bruceAlbum.size()+1;
        int albumsize=size%4!=0 ? size/4+1:size/4;
        request.getSession().setAttribute("albumsize",albumsize);


        if(check.equalsIgnoreCase(code)){
            User user=userService.finduser(username,jmpassword);

            if(userService.IfExist(username)){
                request.setAttribute("Message","用户名不存在！");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }

            if(user!=null){

                Integer olderUserId= (Integer) getServletContext().getAttribute("olderUserId");
                if(olderUserId==null){
                    olderUserId=0;
                }

                if(olderUserId.equals(user.getId())){
                    request.setAttribute("Message","用户已经登录！");
                    request.getRequestDispatcher("/login.jsp").forward(request,response);
                    System.out.println("用户已经登录！");
                    return;
                }else {

                }
                session.setAttribute("username",username);
                session.setAttribute("User",user);
                getServletContext().setAttribute("olderUserId",(Integer)user.getId());


                //记住密码---放在cookie中
                if(request.getParameter("remember")!=null)
                {

                    Cookie cookie1 = new Cookie("password",jmpassword);
                    //七天的时长
                    cookie1.setMaxAge(7*60*60*24);
                    //放在响应中
                    response.addCookie(cookie1);
                    System.out.println("记住密码");
                }
                else if(request.getParameter("remember")==null)
                {
                    Cookie cookie1 = new Cookie("password","");
                    cookie1.setMaxAge(7*60*60*24);
                    //放在响应中
                    response.addCookie(cookie1);
                    System.out.println("未记住密码");
                }
                // 使用cookie实现回写用户名
                Cookie cookie = new Cookie("username",username);
                //设置cookie七天失效
                cookie.setMaxAge(7*60*60*24);

                // 通过响应头发送cookie
                response.addCookie(cookie);

                response.sendRedirect("index.jsp");

            }else {
                System.out.println("加密前："+password);
                System.out.println("加密后："+jmpassword);
                request.setAttribute("Message","密码错误！");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
                System.out.println("输入的密码错误！");
            }
        }else{
            request.setAttribute("Message","验证码错误！");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            System.out.println("输入的验证码错误！");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}