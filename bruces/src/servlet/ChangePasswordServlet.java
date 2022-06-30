package servlet;

import pojo.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        User user =(User) request.getSession().getAttribute("User");
        String method = request.getParameter("method");

        switch (method){
            case "start":{
                request.getRequestDispatcher("/ChangePassword.jsp").forward(request,response);
                return;
            }
            case "change":{
                String password = request.getParameter("password");
                String newpassword = request.getParameter("newpassword");
                String confirmpassword = request.getParameter("confirmpassword");

                UserService userService=new UserServiceImpl();
                if (confirmpassword.equals(newpassword)){
                    if (userService.finduser(user.getUsername(),password)!=null){
                        userService.changePassword(newpassword,user.getId());
                    }
                    else {
                        request.setAttribute("Error","原密码错误");
                    }
                }
                else {
                    request.setAttribute("Error","两次不相同");
                }
                request.getRequestDispatcher("/ChangePassword.jsp").forward(request,response);
                return;

            }
        }




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}