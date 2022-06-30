package dao;

import com.alibaba.druid.util.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import pojo.Singer;
import util.DbUtil;

import java.sql.Connection;

public class SingerDaoImpl implements SingerDao {
    @Override
    public void insert(Singer singer) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();

            String sql = "insert into singer(singer_id,name,sex,pic,birth,location,introduction) values(?,?,?,?,?,?,?)";
            qr.update(ce, sql,singer.getSingerId(),singer.getName(),singer.getSex(),singer.getPic(),singer.getBirth(),singer.getLocation(),singer.getIntroduction());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }

    }
}
