package servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import pojo.User;
import service.UserService;
import service.UserServiceImpl;
import util.MD5Util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        String code=request.getSession().getAttribute("code")+"";
        RequestDispatcher rd=null;
        UserService userService = new UserServiceImpl();


            if (ServletFileUpload.isMultipartContent(request)) {
                FileItemFactory fileItemFactory = new DiskFileItemFactory();//创建本地文件系统
                ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);

                try {
                    List<FileItem> list = servletFileUpload.parseRequest(request);//解析多段数据
                    String filePath = "D:\\java\\bruces\\web\\resources\\picture\\";
                    String username = "";
                    String password = "";
                    String check = "";
                    String fileName = "";
                    String ext = "";
                    String photo = "";
                    for (FileItem fileItem : list) {
                        if (fileItem.isFormField()) {//非文件

                            String uname = fileItem.getFieldName();
                            String value = fileItem.getString("utf-8");
                            if (uname != null && uname.equals("username")) {
                                username = value;
                            }
                            if (uname != null && uname.equals("password")) {
                                password = value;
                            }
                            if (uname != null && uname.equals("check")) {
                                check = value;
                            }
                        }

                    }
                    if(check.equalsIgnoreCase(code)){
                        if(userService.repetition(username)){
                            request.setAttribute("Message", "用户名已存在");
                            rd = request.getRequestDispatcher("register.jsp");
                            rd.forward(request, response);
                            return;
                        }
                        for (FileItem fileItem : list) {
                            if (!fileItem.isFormField()){//文件
                                fileName = fileItem.getName();
                                ext = fileName.substring(fileName.lastIndexOf("\\") + 1);
                                photo = UUID.randomUUID() + ext;
                                fileItem.write(new File(filePath + photo));
                            }
                        }
                        User user = new User();
                        user.setUsername(username);
                        user.setPassword(MD5Util.MD5Encode(password,"UTF-8"));
                        user.setPhoto(photo);
                        user.setFan(0);
                        user.setFollow(0);
                        user.setStarcount(0);
                        user.setStatu(0);
                        user.setBgimg("MoRen.jpg");

                        int i = userService.adduser(user);

                        if (i != 0) {
                            rd = request.getRequestDispatcher("login.jsp");
                            rd.forward(request, response);
                        } else {
                            request.setAttribute("Message", "注册失败");
                            rd = request.getRequestDispatcher("register.jsp");
                            rd.forward(request, response);
                        }

                    }else {
                        request.setAttribute("Message", "验证码错误");
                        rd = request.getRequestDispatcher("register.jsp");
                        rd.forward(request, response);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }








    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}