package service;

import pojo.Song;

import java.util.List;
import java.util.Map;

public interface SongService {
    void insert(Song song);

    List<Map<String, Object>> findLatestSong();

    List<Map<String, Object>> findSongById(String id);

    List<Map<String, Object>> findSongBySrc(String src);

    List<Map<String, Object>> findHotSong(String singer_name);

    String findLyricById(String id);
}
