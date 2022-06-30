package service;

import dao.MusicDao;
import dao.MusicDaoImpl;
import pojo.Music;

import java.util.List;

public class MusicServiceImpl implements MusicService {
    MusicDao musicDao=new MusicDaoImpl();
    @Override
    public List<Music> findAlbums() {
        return musicDao.findAlbums();
    }

    @Override
    public List<Music> findSongsByAlbum(String album) {
        return musicDao.findSongsByAlbum(album);
    }

    @Override
    public List<Music> findAllMusic() {

        return musicDao.findAllMusic();
    }

    @Override
    public List<Music> findSongsByAllSongName(String song) {

        return musicDao.findSongsBySongName(song);
    }

    @Override
    public void addMusic(Music music) {
        musicDao.addMusic(music);
    }
}
