package servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import pojo.Music;
import pojo.Song;
import service.MusicService;
import service.MusicServiceImpl;
import service.SongService;
import service.SongServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet("/UpAndDownloadServlet")
public class UpAndDownloadServlet extends HttpServlet {
    MusicService musicService=new MusicServiceImpl();
    SongService songService=new SongServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        String method = request.getParameter("method");
        System.out.println(method);


        switch (method){
//            case "Share":{
//                request.getRequestDispatcher("/music/WEB-INF/view/index.jsp").forward(request,response);
//                return;
//            }
            case "getmusic":{
                List<Map<String, Object>> songs=songService.findLatestSong();
                JSONArray array = new JSONArray();

                for (int i = 0; i < songs.size(); i++) {
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("name",songs.get(i).get("name"));
                    jsonObj.put("music_id",songs.get(i).get("song_id"));
                    jsonObj.put("singer_name",songs.get(i).get("singer_name"));
                    jsonObj.put("src",songs.get(i).get("url"));
                    array.add(jsonObj);
                }

                String songJson = JSON.toJSONString(array);
                response.getWriter().write(songJson);
                return;
            }
            case "getMInfo":{

                String id = request.getParameter("id");

                List<Map<String, Object>> songs=songService.findSongById(id);

                JSONArray array = new JSONArray();

                for (int i = 0; i < songs.size(); i++) {
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("name",songs.get(i).get("name"));
                    jsonObj.put("music_id",songs.get(i).get("song_id"));
                    jsonObj.put("singer_name",songs.get(i).get("singer_name"));
                    jsonObj.put("src",songs.get(i).get("url"));
                    array.add(jsonObj);
                }

                String songJson = JSON.toJSONString(array);
                response.getWriter().write(songJson);
                return;

            }
            case "getMSrc":{
                String src = request.getParameter("src");

                List<Map<String, Object>> songs=songService.findSongBySrc(src);

                JSONArray array = new JSONArray();

                for (int i = 0; i < songs.size(); i++) {
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("name",songs.get(i).get("name"));
                    jsonObj.put("music_id",songs.get(i).get("song_id"));
                    jsonObj.put("singer_name",songs.get(i).get("singer_name"));
                    jsonObj.put("src",songs.get(i).get("url"));
                    array.add(jsonObj);
                }

                String songJson = JSON.toJSONString(array);
                response.getWriter().write(songJson);
                return;

            }
            case "getRank":{

                String index = request.getParameter("singer_name");
                String singer_name="";
                if(index.equals("1")){
                    singer_name="徐秉龙";
                }else if(index.equals("2")){
                    singer_name="林俊杰";
                }else if(index.equals("0")){
                    singer_name="梁博";
                }
                List<Map<String, Object>> songs=songService.findHotSong(singer_name);

                JSONArray array = new JSONArray();

                for (int i = 0; i < songs.size(); i++) {
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("name",songs.get(i).get("name"));
                    jsonObj.put("music_id",songs.get(i).get("song_id"));
//                    jsonObj.put("singer_name",songs.get(i).get("singer_name"));
//                    jsonObj.put("src",songs.get(i).get("url"));
                    array.add(jsonObj);
                }

                String songJson = JSON.toJSONString(array);
                response.getWriter().write(songJson);
                return;
            }
//            case "search":{
//
//                String content = request.getParameter("content");
//                String Id = request.getParameter("Id");
//
//                System.out.println(Id+"  "+content);
//                request.getRequestDispatcher("/music/WEB-INF/view/result.jsp").forward(request,response);
//                return;
//            }
            case "getlyric":{
                String id = request.getParameter("id");
                String lyric=songService.findLyricById(id);
                response.getWriter().write(lyric);
                return;
            }
            case "addupload":{
                response.sendRedirect("upload.jsp");
                return;
            }
            case "upload":{
                Music music=new Music();
                if(ServletFileUpload.isMultipartContent(request)){
                    FileItemFactory fileItemFactory=new DiskFileItemFactory();//创建本地文件系统
                    ServletFileUpload servletFileUpload=new ServletFileUpload(fileItemFactory);

                    try {
                        List<FileItem> list=servletFileUpload.parseRequest(request);//解析多段数据
                        String filePath="D:\\java\\bruces\\web\\resources\\picture\\";
                        String songname="";//歌曲名称
                        String songer="";//歌手
                        String album="";//专辑
                        String songimg="";//专辑图片
                        String musicfile="";//歌曲文件
                        String files="";
                        String fileName="";
                        String ext="";
                        for(FileItem fileItem:list){
                            if(!fileItem.isFormField()){//文件
                                files=fileItem.getFieldName();
                                fileName = fileItem.getName();
                                ext = fileName.substring(fileName.lastIndexOf("\\")+1);
                                if(fileName!=null) {
                                    if (files != null && files.equals("songimg")) {
                                        if(ext!="") {
                                            songimg = UUID.randomUUID() + ext;
                                            fileItem.write(new File(filePath + songimg));
                                        }
                                    }
                                    if (files != null && files.equals("musicfile")) {
                                        if(ext!="") {
                                            musicfile = UUID.randomUUID() + ext;
                                            fileItem.write(new File(filePath + musicfile));
                                        }
                                    }
                                }
                            }else {
                                String uname=fileItem.getFieldName();
                                String value=fileItem.getString("utf-8");
                                if(uname!=null && uname.equals("songname")) {
                                    songname = value;
                                }if (uname!=null && uname.equals("songer")){
                                    songer=value;
                                }
                                if (uname!=null && uname.equals("album")){
                                    album=value;
                                }
                            }
                        }

                        music.setSong(songname);
                        music.setSinger(songer);
                        music.setAlbum(album);
                        music.setSongimg(songimg);
                        music.setSonglocation(musicfile);

                        musicService.addMusic(music);

                        List<Music> bruceAlbum=null;

                        bruceAlbum=musicService.findAlbums();
                        request.getSession().setAttribute("bruceAlbum",bruceAlbum);

                        int size = bruceAlbum.size()+1;
                        int albumsize=size%4!=0 ? size/4+1:size/4;
                        request.getSession().setAttribute("albumsize",albumsize);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                response.sendRedirect("index.jsp");
                return;
            }
            case "download":{
                String songlocation = request.getParameter("songlocation");
                System.out.println(songlocation);
                String path="D:\\java\\bruces\\web\\resources\\picture\\"+songlocation;

                ServletContext servletContext = getServletContext();
                String mimeType = servletContext.getMimeType(songlocation);
                System.out.println("下载的文件类型" + mimeType);
                response.setContentType(mimeType);
                response.setHeader("Content-Disposition","attachment;filename="+URLEncoder.encode(songlocation,"UTF-8"));

                InputStream in=null;
                OutputStream out=null;

                try{
                    in=new FileInputStream(path);
                    int len=0;
                    byte[] buffer=new byte[1024];
                    out=response.getOutputStream();
                    while ((len=in.read(buffer))>0){
//                        System.out.println(len);
//                        System.out.println(response.getOutputStream());
                        out.write(buffer,0,len);
                    }
                }catch (Exception e){
                    throw new RuntimeException();
                }finally {
                    in.close();
                    out.close();
                }


                response.sendRedirect(" .jsp");

                return;
            }
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}