package dao;

import pojo.Music;

import java.util.List;

public interface MusicDao {
    List<Music> findAlbums();

    List<Music> findSongsByAlbum(String album);

    List<Music> findAllMusic();

    List<Music> findSongsBySongName(String song);

    void addMusic(Music music);
}
