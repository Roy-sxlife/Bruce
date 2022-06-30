package servlet;

import com.alibaba.fastjson.JSON;
import pojo.Music;
import service.MusicService;
import service.MusicServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/SongsServlet")
public class SongsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String album=request.getParameter("album");
        String song = request.getParameter("song");
        HttpSession session=request.getSession();


        MusicService musicService=new MusicServiceImpl();
        List<Music> music=null;
        if (album != null & song == null) {
            music=musicService.findSongsByAlbum(album);
        }
        if(album==null&&song!=null){
            music=musicService.findSongsByAllSongName(song);
        }

        String[] musiclocation = new String[music.size()];
        String[] musicimg = new String[music.size()];
        String[] musicsong = new String[music.size()];
        int[] musicid = new int[music.size()];

        Map<Integer, String> locationmap =new HashMap<>();
        Map<Integer, String> songmap =new HashMap<>();
        Map<Integer, String> imgmap =new HashMap<>();

        for (int i=0;i<music.size();i++){

            musiclocation[i]=music.get(i).getSonglocation();
            musicimg[i]=music.get(i).getSongimg();
            musicsong[i]=music.get(i).getSong();
            musicid[i]=music.get(i).getId();
        }
        for (int i=0;i<music.size();i++){
            locationmap.put(musicid[i],musiclocation[i]);
            songmap.put(musicid[i],musicsong[i]);
            imgmap.put(musicid[i],musicimg[i]);
        }

        String imgjson = JSON.toJSONString(imgmap);
        String locationjson = JSON.toJSONString(locationmap);
        String songjson = JSON.toJSONString(songmap);


        request.setAttribute("Brucesong",music);
        request.setAttribute("locationmap",locationjson);
        request.setAttribute("songmap",songjson);
        request.setAttribute("imgmap",imgjson);
        request.getRequestDispatcher("bruceMusic.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}