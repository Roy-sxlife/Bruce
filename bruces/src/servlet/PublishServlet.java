package servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import pojo.Label;
import pojo.Status;
import pojo.User;
import service.StatusService;
import service.StatusServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/PublishServlet")
public class PublishServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user=(User) request.getSession().getAttribute("User");


        if(ServletFileUpload.isMultipartContent(request)){
            FileItemFactory fileItemFactory=new DiskFileItemFactory();//创建本地文件系统
            ServletFileUpload servletFileUpload=new ServletFileUpload(fileItemFactory);

            try {
                List<FileItem> list=servletFileUpload.parseRequest(request);//解析多段数据
                String filePath="D:\\java\\bruces\\web\\resources\\picture\\";
                String tag="";//标签
                String description="";//内容
                String fileName="";
                String ext="";
                String mood="";
                for(FileItem fileItem:list){
                    if(!fileItem.isFormField()){//文件
                        fileName = fileItem.getName();
                        ext = fileName.substring(fileName.lastIndexOf("\\")+1);
                        mood = UUID.randomUUID()+ext;
                        fileItem.write(new File(filePath+mood));
                    }else {
                        String uname=fileItem.getFieldName();
                        String value=fileItem.getString("utf-8");
                        if(uname!=null && uname.equals("tag")) {
                            tag = value;
                        }if (uname!=null && uname.equals("description")){
                            description=value;
                        }
                    }
                }
                StatusService statusService=new StatusServiceImpl();
                Status status=new Status();
                status.setUser(user);
                status.setDescription(description);
                status.setMood(mood);

                int i=statusService.addStatus(status,tag);
                if(i!=0){
                    response.sendRedirect("index.jsp");
                }else {
                    response.sendRedirect("publish.jsp");
                    request.getSession().setAttribute("error","您发布的内容有误");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}