package servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import pojo.Status;
import pojo.User;
import service.StatusService;
import service.StatusServiceImpl;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/MyInformServlet")
public class MyInformServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        User user=(User) request.getSession().getAttribute("User");
        String userId = request.getParameter("userId");
        UserService userService = new UserServiceImpl();

        if(userId==null) {
            if (ServletFileUpload.isMultipartContent(request)) {
                FileItemFactory fileItemFactory = new DiskFileItemFactory();//创建本地文件系统
                ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);

                try {
                    List<FileItem> list = servletFileUpload.parseRequest(request);//解析多段数据
                    String filePath = "D:\\java\\bruces\\web\\resources\\picture\\";
                    String txfile = "";//头像
                    String bgfile = "";//背景
                    String username = "";//昵称
                    String sex = "";//性别
                    String birthday = "";//生日
                    String signature = "";//个性签名
                    String constellation = "";//星座
                    String files = "";
                    String fileName = "";
                    String ext = "";
                    for (FileItem fileItem : list) {
                        if (!fileItem.isFormField()) {//文件
                            files = fileItem.getFieldName();
                            fileName = fileItem.getName();
                            ext = fileName.substring(fileName.lastIndexOf("\\") + 1);
                            if (fileName != null) {
                                if (files != null && files.equals("txfile")) {
                                    if (ext != "") {
                                        txfile = UUID.randomUUID() + ext;
                                        fileItem.write(new File(filePath + txfile));
                                    }
                                }
                                if (files != null && files.equals("bgfile")) {
                                    if (ext != "") {
                                        bgfile = UUID.randomUUID() + ext;
                                        fileItem.write(new File(filePath + bgfile));
                                    }
                                }
                            }
                        } else {
                            String uname = fileItem.getFieldName();
                            String value = fileItem.getString("utf-8");
                            if (uname != null && uname.equals("username")) {
                                username = value;
                            }
                            if (uname != null && uname.equals("sex")) {
                                sex = value;
                            }
                            if (uname != null && uname.equals("birthday")) {
                                birthday = value;
                            }
                            if (uname != null && uname.equals("signature")) {
                                signature = value;
                            }
                            if (uname != null && uname.equals("constellation")) {
                                constellation = value;
                            }
                        }
                    }

                    if (bgfile != "") {
                        user.setBgimg(bgfile);
                    }
                    if (txfile != "") {
                        user.setPhoto(txfile);
                    }

                    user.setUsername(username);
                    user.setSex(sex);
                    user.setSignature(signature);
                    user.setConstellation(constellation);
                    user.setBirthday(birthday);


                    userService.updateUser(user);
                    request.getSession().setAttribute("User", user);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            request.getRequestDispatcher("/myinform.jsp").forward(request, response);
        }else {
            User user2=userService.finduserbyId(Integer.parseInt(userId));
            request.getSession().setAttribute("otherUserForm",user2);

            request.getRequestDispatcher("/otherinform.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}