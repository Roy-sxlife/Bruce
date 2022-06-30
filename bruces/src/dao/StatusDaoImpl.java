package dao;

import com.alibaba.druid.util.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import pojo.Label;
import pojo.Status;
import pojo.User;
import util.DbUtil;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

public class StatusDaoImpl implements StatusDao {
    @Override
    public int addStatus(Status status,String tag) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();

            String sql2 = "insert into labels(label_name) values(?)";
            int j = qr.update(ce, sql2, tag);

            String sql3 = "select * from labels where label_name=?";
            BeanHandler<Label> beanHandler = new BeanHandler<>(Label.class);
            Label label = qr.query(ce, sql3, beanHandler, tag);
            status.setLabel(label);

            String sql = "insert into status(user_id,label_id,description,mood,remark,enjoy,star) values(?,?,?,?,?,?,?)";
            int i = qr.update(ce, sql, status.getUser().getId(), status.getLabel().getLabel_id(), status.getDescription(), status.getMood(),status.getRemark(),status.getEnjoy(),status.getStar());

            return i;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }

        return 0;
    }

    @Override
    public List<Status> findStatusList() {

        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();

            String sql = "select * from status order by create_time desc ";
            BeanListHandler<Status> beanListHandler = new BeanListHandler<>(Status.class);
            List<Status> statusList = qr.query(ce, sql, beanListHandler);

//            for (int i = 0; i < statusList.size(); i++) {
//
//                sql="select users.* from status,users where status.status_id=? and status.user_id=users.id";
//                BeanHandler<User> beanHandler = new BeanHandler<>(User.class);
//                User user=qr.query(ce, sql, beanHandler,statusList.get(i).getStatus_id());
//                statusList.get(i).setUser(user);
//
//                sql="select labels.* from status,labels where status.status_id=? and status.label_id=labels.label_id";
//                BeanHandler<Label> beanHandler2 = new BeanHandler<>(Label.class);
//                Label label=qr.query(ce, sql, beanHandler2,statusList.get(i).getStatus_id());
//                statusList.get(i).setLabel(label);
//            }


            return statusList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
        return null;
    }

    @Override
    public void decrease(int status_id) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "update status set star=star-1 where status_id=?";
            qr.update(ce,sql,status_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
    }

    @Override
    public void increase(int status_id) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "update status set star=star+1 where status_id=?";
            qr.update(ce,sql,status_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
    }

    @Override
    public List<Status> findUserStatusList(int user_id) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();

            String sql = "select status.* from status where user_id=? order by create_time desc ";
            BeanListHandler<Status> beanListHandler = new BeanListHandler<>(Status.class);
            List<Status> statusList = qr.query(ce, sql, beanListHandler,user_id);

            for(Status status:statusList){
                sql="select users.* from status,users where status.status_id=? and status.user_id=users.id";
                BeanHandler<User> beanHandler = new BeanHandler<>(User.class);
                User user=qr.query(ce, sql, beanHandler,status.getStatus_id());
                status.setUser(user);

                sql="select labels.* from status,labels where status.status_id=? and status.label_id=labels.label_id";
                BeanHandler<Label> beanHandler2 = new BeanHandler<>(Label.class);
                Label label=qr.query(ce, sql, beanHandler2,status.getStatus_id());
                status.setLabel(label);
            }


            return statusList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
        return null;
    }

    @Override
    public void deleteStatus(int delete_id) {

        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql1 = "SET FOREIGN_KEY_CHECKS =0;";
            String sql2 = "delete from status where status_id=?;";
            String sql3 = "SET FOREIGN_KEY_CHECKS =1;";
            qr.update(ce,sql1);
            qr.update(ce,sql2,delete_id);
            qr.update(ce,sql3);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }

    }

    @Override
    public Status findStatusById(Integer status_id) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "select * from status where status_id=?;";
            BeanHandler<Status> beanHandler = new BeanHandler<>(Status.class);
            Status status = qr.query(ce, sql, beanHandler,status_id);


            sql="select users.* from status,users where status.status_id=? and status.user_id=users.id";
            BeanHandler<User> beanHandler1 = new BeanHandler<>(User.class);
            User user=qr.query(ce, sql, beanHandler1,status.getStatus_id());
            status.setUser(user);

            sql="select labels.* from status,labels where status.status_id=? and status.label_id=labels.label_id";
            BeanHandler<Label> beanHandler2 = new BeanHandler<>(Label.class);
            Label label=qr.query(ce, sql, beanHandler2,status.getStatus_id());
            status.setLabel(label);


            return status;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
        return null;
    }

    @Override
    public List<Status> queryStatusByPage(int currentPage, int pageSize) {
        Connection ce = null;
        try {
            //limit 查询的数目====pageSize
            //offset对应的记录开始数
            //记录第几页的第一条记录begin
            int begin = ( currentPage-1 )*pageSize;
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "select * from status order by create_time desc limit ? offset ?";
            BeanListHandler<Status> beanListHandler = new BeanListHandler<>(Status.class);
            List<Status> statusList= qr.query(ce, sql, beanListHandler,pageSize,begin);

            for (int i = 0; i < statusList.size(); i++) {

                sql="select users.* from status,users where status.status_id=? and status.user_id=users.id";
                BeanHandler<User> beanHandler = new BeanHandler<>(User.class);
                User user=qr.query(ce, sql, beanHandler,statusList.get(i).getStatus_id());
                statusList.get(i).setUser(user);

                sql="select labels.* from status,labels where status.status_id=? and status.label_id=labels.label_id";
                BeanHandler<Label> beanHandler2 = new BeanHandler<>(Label.class);
                Label label=qr.query(ce, sql, beanHandler2,statusList.get(i).getStatus_id());
                statusList.get(i).setLabel(label);
            }


            return statusList;


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }

        return null;
    }

    @Override
    public int enjoyCount(int enjoy_id) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "select enjoy from status where status_id=?";
            int i=qr.query(ce,sql,new ScalarHandler<>(),enjoy_id);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
        return 0;
    }

    @Override
    public int findRemarkCount(int status_id) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "select remark from status where status_id=?";
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
