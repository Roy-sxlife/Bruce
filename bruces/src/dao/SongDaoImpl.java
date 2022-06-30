package dao;

import com.alibaba.druid.util.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import pojo.Remark;
import pojo.Reply;
import pojo.Song;
import pojo.User;
import util.DbUtil;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class SongDaoImpl implements Songdao {
    @Override
    public void insert(Song song) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();

            String sql = "insert into song(song_id,singer_id,name,singer_name,pic,lyric,duration,album_name,url,is_download,play_count) values(?,?,?,?,?,?,?,?,?,?,?)";
            qr.update(ce, sql,song.getSongId(),song.getSingerId(),song.getName(),song.getSingerName(),song.getPic(),song.getLyric(),song.getDuration(),song.getAlbumName(),song.getUrl(),song.getIsDownload(),song.getPlayCount());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
    }

    @Override
    public  List<Map<String, Object>> findLatestSong() {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();

            String sql = "select name,song_id,singer_name,url from song limit 30;";
            MapListHandler mapListHandler = new MapListHandler();
            List<Map<String, Object>> songs = qr.query(ce, sql, mapListHandler);
            return songs;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> findSongById(String id) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();

            String sql = "select name,song_id,singer_name,url from song  where song_id=?";
            MapListHandler mapListHandler = new MapListHandler();
            List<Map<String, Object>> songs = qr.query(ce, sql, mapListHandler,id);
            return songs;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> findSongBySrc(String src) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();

            String sql = "select name,song_id,singer_name,url from song  where url=?";
            MapListHandler mapListHandler = new MapListHandler();
            List<Map<String, Object>> songs = qr.query(ce, sql, mapListHandler,src);
            return songs;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> findHotSong(String singer_name) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();

            String sql = "select name,song_id from song where singer_name=? limit 10;";
            MapListHandler mapListHandler = new MapListHandler();
            List<Map<String, Object>> songs = qr.query(ce, sql, mapListHandler,singer_name);
            return songs;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
        return null;
    }

    @Override
    public String findLyricById(String id) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "select lyric from song where song_id=?";
            String lyric=qr.query(ce,sql,new ScalarHandler<>("lyric"),id);
            return lyric;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
        return null;
    }
}
