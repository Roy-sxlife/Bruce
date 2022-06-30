package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("username");
        request.getSession().setAttribute("User",null);
        request.getSession().setMaxInactiveInterval(0);

        //这里就是把username的cookie设置成0秒有效期，就是直接删除掉了
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {

                //找到密码相等的cookie
                if (URLDecoder.decode(cookie.getName(), "utf-8").equals("password")) { // 表明已经登陆过了，就直接跳转了
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }

            }
        }
        getServletContext().setAttribute("olderUserId",0);
        response.sendRedirect("login.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}