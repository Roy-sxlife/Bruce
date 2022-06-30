package service;

import pojo.Music;

import java.util.List;

public interface MusicService {
    List<Music> findAlbums();

    List<Music> findSongsByAlbum(String album);

    List<Music> findAllMusic();

    List<Music> findSongsByAllSongName(String song);

    void addMusic(Music music);
}
