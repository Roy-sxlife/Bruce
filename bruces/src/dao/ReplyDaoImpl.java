package dao;

import com.alibaba.druid.util.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import pojo.Remark;
import pojo.Reply;
import pojo.User;
import util.DbUtil;

import java.sql.Connection;
import java.util.List;

public class ReplyDaoImpl implements ReplyDao {
    @Override
    public List<Reply> findTheReply(int id) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();

            String sql = "select * from replys where remark_id=? order by reply_time desc";
            BeanListHandler<Reply> beanListHandler = new BeanListHandler<>(Reply.class);
            List<Reply> replies = qr.query(ce, sql, beanListHandler,id);

            for(Reply reply:replies){
                sql="select users.* from replys,users where replys.id=? and replys.from_user_id=users.id";
                BeanHandler<User> beanHandler = new BeanHandler<>(User.class);
                User user=qr.query(ce, sql, beanHandler,reply.getId());
                reply.setFrom_user(user);

                sql="select users.* from replys,users where replys.id=? and replys.to_user_id=users.id";
                BeanHandler<User> beanHandler2 = new BeanHandler<>(User.class);
                User user1=qr.query(ce, sql, beanHandler,reply.getId());
                reply.setTo_user(user1);

                sql="select remarks.* from replys,remarks where replys.id=? and replys.remark_id=remarks.id";
                BeanHandler<Remark> beanHandler3 = new BeanHandler<>(Remark.class);
                Remark remark=qr.query(ce, sql, beanHandler3,reply.getId());
                reply.setRemark(remark);

            }

            return replies;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
        return null;
    }

    @Override
    public void addnewreply(int parseInt, int parseInt1, int parseInt2, String reply_content) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "insert into replys(remark_id,from_user_id,to_user_id,reply_content,reply_number) values(?,?,?,?,?)";
            qr.update(ce,sql,parseInt,parseInt1,parseInt2,reply_content,0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
    }

    @Override
    public Reply findNewReply(int remark_id) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();

            String sql = "select * from (select * from replys where remark_id=? order by reply_time desc)as b limit 1";
            BeanHandler<Reply> beanHandler = new BeanHandler<>(Reply.class);
            Reply reply = qr.query(ce, sql, beanHandler,remark_id);

            sql="select users.* from replys,users where replys.id=? and replys.from_user_id=users.id";
            BeanHandler<User> beanHandler2 = new BeanHandler<>(User.class);
            User user=qr.query(ce, sql, beanHandler2,reply.getId());
            reply.setFrom_user(user);

            sql="select users.* from replys,users where replys.id=? and replys.to_user_id=users.id";
            BeanHandler<User> beanHandler3 = new BeanHandler<>(User.class);
            User user1=qr.query(ce, sql, beanHandler3,reply.getId());
            reply.setTo_user(user1);

            sql="select remarks.* from replys,remarks where replys.id=? and replys.remark_id=remarks.id";
            BeanHandler<Remark> beanHandler4 = new BeanHandler<>(Remark.class);
            Remark remark=qr.query(ce, sql, beanHandler4,reply.getId());
            reply.setRemark(remark);

            return reply;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
        return null;
    }

    @Override
    public int findReplyCount(int remark_id) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "select reply_count from remarks where id=?";
            int i=qr.query(ce,sql,new ScalarHandler<>(),remark_id);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
        return 0;
    }

    @Override
    public void addRemark_reply(int remark_id) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "update remarks set reply_count=reply_count+1 where id=?";
            qr.update(ce,sql,remark_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
    }
}
