package servlet;

import pojo.Status;
import service.StatusService;
import service.StatusServiceImpl;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    StatusService statusService=new StatusServiceImpl();
    UserService userService=new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");


        String method = request.getParameter("method");
        System.out.println(method);

        switch (method){
            case "update":{

                Status status = (Status)request.getAttribute("status");


            }
            case "status":{

                String status_id = request.getParameter("status_id");

                Status status=statusService.findStatusById(Integer.parseInt(status_id));
                System.out.println(status);

                request.setAttribute("status",status);
                request.getRequestDispatcher("/updateStatus.jsp").forward(request,response);
                return;
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}