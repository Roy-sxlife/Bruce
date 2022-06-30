package service;

import dao.SongDaoImpl;
import dao.Songdao;
import pojo.Song;

import java.util.List;
import java.util.Map;

public class SongServiceImpl implements SongService {
    Songdao songdao=new SongDaoImpl();
    @Override
    public void insert(Song song) {
        songdao.insert(song);
    }

    @Override
    public  List<Map<String, Object>> findLatestSong() {
        return songdao.findLatestSong();
    }

    @Override
    public List<Map<String, Object>> findSongById(String id) {
        return songdao.findSongById(id);
    }

    @Override
    public List<Map<String, Object>> findSongBySrc(String src) {
        return songdao.findSongBySrc(src);
    }

    @Override
    public List<Map<String, Object>> findHotSong(String singer_name) {
        return songdao.findHotSong(singer_name);
    }

    @Override
    public String findLyricById(String id) {
        return songdao.findLyricById(id);
    }
}
