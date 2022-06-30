package dao;

import com.alibaba.druid.util.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pojo.Label;
import pojo.Remark;
import pojo.Status;
import pojo.User;
import util.DbUtil;

import java.sql.Connection;
import java.util.List;

public class RemarkDaoImpl implements RemarkDao {
    @Override
    public List<Remark> findAllRemark(Integer status_id) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();

            String sql = "select * from remarks where status_id=? order by remark_time desc ";
            BeanListHandler<Remark> beanListHandler = new BeanListHandler<>(Remark.class);
            List<Remark> remarks = qr.query(ce, sql, beanListHandler,status_id);

            for(Remark remark:remarks){
                sql="select users.* from remarks,users where remarks.id=? and remarks.remarker_id=users.id";
                BeanHandler<User> beanHandler = new BeanHandler<>(User.class);
                User user=qr.query(ce, sql, beanHandler,remark.getId());
                remark.setRemarker(user);
            }

            return remarks;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
        return null;
    }

    @Override
    public void addRemark(Remark remark) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "insert into remarks(remark_content,remarker_id,status_id,reply_count) values(?,?,?,?)";
            qr.update(ce,sql,remark.getRemark_content(),remark.getRemarker().getId(),remark.getStatus().getStatus_id(),remark.getReply_count());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }


    }

    @Override
    public void addStatus_Remark(int status_id) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "update status set remark=remark+1 where status_id=?";
            qr.update(ce,sql,status_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }

    }

    @Override
    public Remark findNewRemark(int status_id) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();

            String sql = "select * from (select * from remarks where status_id=? order by remark_time desc)as a limit 1";
            BeanHandler<Remark> beanHandler = new BeanHandler<>(Remark.class);
            Remark remark = qr.query(ce, sql, beanHandler,status_id);

            sql="select users.* from remarks,users where remarks.id=? and remarks.remarker_id=users.id";
            BeanHandler<User> beanHandler2 = new BeanHandler<>(User.class);
            User user=qr.query(ce, sql, beanHandler2,remark.getId());
            remark.setRemarker(user);

            return remark;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
        return null;
    }
}
