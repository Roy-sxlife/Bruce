package dao;

import com.alibaba.druid.util.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pojo.Music;
import util.DbUtil;

import java.sql.Connection;
import java.util.List;

public class MusicDaoImpl implements MusicDao {
    @Override
    public List<Music> findAlbums() {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "select distinct songimg,album from music";
            BeanListHandler<Music> beanListHandler = new BeanListHandler<>(Music.class);
            List<Music> bruceAlbum = qr.query(ce, sql, beanListHandler);
            return bruceAlbum;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
        return null;
    }

    @Override
    public List<Music> findSongsByAlbum(String album) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "select * from Music where album=?";
            BeanListHandler<Music> beanListHandler = new BeanListHandler<>(Music.class);
            List<Music> musicList = qr.query(ce, sql, beanListHandler,album);
            return musicList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
        return null;
    }

    @Override
    public List<Music> findAllMusic() {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "select * from music";
            BeanListHandler<Music> beanListHandler = new BeanListHandler<>(Music.class);
            List<Music> musicList = qr.query(ce, sql, beanListHandler);
            return musicList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
        return null;
    }

    @Override
    public List<Music> findSongsBySongName(String song) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "select * from Music where song=?";
            BeanListHandler<Music> beanListHandler = new BeanListHandler<>(Music.class);
            List<Music> musicList = qr.query(ce, sql, beanListHandler,song);
            return musicList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
        return null;
    }

    @Override
    public void addMusic(Music music) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "insert into music(song,singer,album,songimg,songlocation) values(?,?,?,?,?)";
            qr.update(ce,sql,music.getSong(),music.getSinger(),music.getAlbum(),music.getSongimg(),music.getSonglocation());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }

    }
}
