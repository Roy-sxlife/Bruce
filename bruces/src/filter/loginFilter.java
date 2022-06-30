package filter;

import pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "loginFilter",value = "/*")
public class loginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse) resp;

        User user=(User)request.getSession().getAttribute("User");
        String requestURI = request.getRequestURI();


        if(requestURI.indexOf("login.jsp")!=-1||requestURI.indexOf("CodeServlet")!=-1||requestURI.indexOf("LoginServlet")!=-1||requestURI.indexOf("register.jsp")!=-1||requestURI.indexOf("RegisterServlet")!=-1||requestURI.equals("/bruceweb/")||requestURI.indexOf("LogoutServlet")!=-1){
            chain.doFilter(req, resp);
            return;
        }else {
            if (user == null) {
                System.out.println("成功拦截！");
                request.setAttribute("Message","未登录！");
                response.sendRedirect("login.jsp");
            } else {
                System.out.println("成功通过！");
                chain.doFilter(req, resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
