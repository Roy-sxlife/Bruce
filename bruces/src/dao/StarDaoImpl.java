package dao;

import com.alibaba.druid.util.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import pojo.*;
import util.DbUtil;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class StarDaoImpl implements StarDao {
    @Override
    public Boolean findStarOrnot(int status_id, int id) { //需要查询多次浪费资源
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "select stars.* from stars where stars.status_id=? and stars.user_id=?";
            BeanHandler<Star> beanHandler = new BeanHandler<>(Star.class);
            Star star = qr.query(ce, sql, beanHandler,status_id,id);
            if (star!=null) {
                return true;
            }else return false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
        return null;
    }

    @Override
    public List<Integer> findAllstar(int id) { //查询该用户所有的收藏的动态ID
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "select stars.* from stars where user_id=?";
            ColumnListHandler<Integer> columnListHandler = new ColumnListHandler<>("status_id");
            List<Integer> statuses= qr.query(ce, sql, columnListHandler,id);

            return statuses;


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }

        return null;
    }

    @Override
    public List<Status> findAllStatus(int id) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "select status.* from status,stars where stars.user_id=? and status.status_id=stars.status_id order by stars.star_time desc";
            BeanListHandler<Status> beanListHandler = new BeanListHandler<>(Status.class);
            List<Status> statuses= qr.query(ce, sql, beanListHandler,id);

            for(Status status:statuses){
                sql="select users.* from status,users where status.status_id=? and status.user_id=users.id";
                BeanHandler<User> beanHandler = new BeanHandler<>(User.class);
                User user=qr.query(ce, sql, beanHandler,status.getStatus_id());
                status.setUser(user);

                sql="select labels.* from status,labels where status.status_id=? and status.label_id=labels.label_id";
                BeanHandler<Label> beanHandler2 = new BeanHandler<>(Label.class);
                Label label=qr.query(ce, sql, beanHandler2,status.getStatus_id());
                status.setLabel(label);
            }

            if (statuses!=null){
                return statuses;
            }else return null;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
        return null;
    }

    @Override
    public void removeStar(int id, int status_id) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "delete from stars where user_id=? and status_id=?";
            qr.update(ce,sql,id,status_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }


    }

    @Override
    public void addStar(int id, int status_id) {
        Connection ce = null;
        Star star=new Star();
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "insert into stars(user_id,status_id,star_time) values(?,?,?)";
            qr.update(ce,sql,id,status_id,star.getStar_time());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
    }

    @Override
    public void startStar(int user_id) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "update users set starcount=starcount+1 where id=?;";
            qr.update(ce,sql,user_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
    }

    @Override
    public void cancelStar(int user_id) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "update users set starcount=starcount-1 where id=?;";
            qr.update(ce,sql,user_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
    }

    @Override
    public void addEnjoy(int enjoy_id) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "update status set enjoy=enjoy+1 where status_id=?;";
            qr.update(ce,sql,enjoy_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
    }

    @Override
    public int starCount(int status_id) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "select star from status where status_id=?";
            int i=qr.query(ce,sql,new ScalarHandler<>(),status_id);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
        return 0;
    }

}
