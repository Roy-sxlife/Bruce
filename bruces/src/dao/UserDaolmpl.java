package dao;

import com.alibaba.druid.util.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import pojo.Follow;
import pojo.Star;
import pojo.User;
import util.DbUtil;

import java.sql.Connection;
import java.util.List;

public class UserDaolmpl implements UserDao{
    @Override
    public User finduser(String username,String password) {

        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "select * from users where username=?;";
            BeanHandler<User> beanHandler = new BeanHandler<>(User.class);
            User user = qr.query(ce, sql, beanHandler,username);
            System.out.println(user.getPassword());
            if(username.equals(user.getUsername())&&password.equals(user.getPassword())){
                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
        return null;


    }

    @Override
    public int adduser(User user) {

        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "insert into users(username,password,photo,fan,follow,statu,starcount,bgimg) values(?,?,?,?,?,?,?,?)";
            int i = qr.update(ce,sql,user.getUsername(),user.getPassword(),user.getPhoto(),user.getFan(),user.getFollow(),user.getStatu(),user.getStarcount(),user.getBgimg());
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }




        return 0;
    }

    @Override
    public Boolean findfollowOrnot(int id,int id2) { //数据库操作量过大，不宜使用
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "select * from follows where follow_id=? and user_id=?";
            BeanHandler<Follow> beanHandler = new BeanHandler<>(Follow.class);
            Follow follow = qr.query(ce, sql, beanHandler,id2,id);
            if (follow!=null){
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
    public List<Integer> findAllfollows(int id) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "select follows.* from follows where user_id=?";
            ColumnListHandler<Integer> columnListHandler = new ColumnListHandler<>("follow_id");
            List<Integer> follows= qr.query(ce, sql, columnListHandler,id);

            return follows;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }

        return null;
    }

    @Override
    public List<User> findAllfollowsUser(int id) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "select users.* from users,follows where follows.user_id=? and users.id=follows.follow_id";
            BeanListHandler<User> beanListHandler = new BeanListHandler<>(User.class);
            List<User> userList= qr.query(ce, sql, beanListHandler,id);
            return userList;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }

        return null;
    }

    @Override
    public List<User> findAllfansUser(int id) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "select users.* from users,fans where fans.user_id=? and users.id=fans.fan_id";
            BeanListHandler<User> beanListHandler = new BeanListHandler<>(User.class);
            List<User> userList= qr.query(ce, sql, beanListHandler,id);

            return userList;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }

        return null;
    }

    @Override
    public void removeFriend(int id, int follow_id) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "delete from follows where user_id=? and follow_id=?";
            qr.update(ce,sql,id,follow_id);
            String sql2="delete from fans where user_id=? and fan_id=?";
            qr.update(ce,sql2,follow_id,id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
    }

    @Override
    public void addFriend(int id, int follow_id) {
        Connection ce = null;
        Star star=new Star();
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "insert into follows(user_id,follow_id) values(?,?)";
            qr.update(ce,sql,id,follow_id);
            String sql2 = "insert into fans(user_id,fan_id) values(?,?)";
            qr.update(ce,sql2,follow_id,id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
    }

    @Override
    public void startFollow(int user_id, int follow_id) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "update users set users.follow=users.follow+1 where users.id=?;";
            int i=qr.update(ce,sql,user_id);
            System.out.println(i+"11");
            String sql2 = "update users set users.fan=users.fan+1 where users.id=?;";
            qr.update(ce,sql2,follow_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
    }

    @Override
    public void cancelFollow(int user_id, int follow_id) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "update users set users.follow=users.follow-1 where users.id=?;";
            int i=qr.update(ce,sql,user_id);
            System.out.println(i+"22");
            String sql2 = "update users set users.fan=users.fan-1 where users.id=?;";
            qr.update(ce,sql2,follow_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
    }

    @Override
    public User finduserbyId(int userId) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "select * from users where id=?;";
            BeanHandler<User> beanHandler = new BeanHandler<>(User.class);
            User user = qr.query(ce, sql, beanHandler,userId);
            return user;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
        return null;
    }

    @Override
    public void updateUser(User user) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "update users set photo=?,bgimg=?,username=?,sex=?,birthday=?,signature=?,constellation=? where users.id=?;";
            qr.update(ce,sql,user.getPhoto(),user.getBgimg(),user.getUsername(),user.getSex(),user.getBirthday(),user.getSignature(),user.getConstellation(),user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
    }

    @Override
    public boolean IfExist(String username) {
        Connection ce=null;
        try {
            QueryRunner qr=new QueryRunner();
            ce=DbUtil.getConnection();
            String sql="select * from users where username=?";
            BeanHandler<User> beanHandler = new BeanHandler<>(User.class);
            User user=qr.query(ce,sql,beanHandler,username);
            if(user==null){
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean repetition(String username1) {
        Connection ce=null;
        try {
            QueryRunner qr=new QueryRunner();
            ce=DbUtil.getConnection();
            String sql="select * from users where username=?";
            BeanHandler<User> beanHandler = new BeanHandler<>(User.class);
            User user=qr.query(ce,sql,beanHandler,username1);
            if(user!=null){
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> queryFriendByPage(int id, int currentPage, int pageSize) {
        Connection ce = null;
        try {
            //limit 查询的数目====pageSize
            //offset对应的记录开始数
            //记录第几页的第一条记录begin
            int begin = ( currentPage-1 )*pageSize;
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "select users.* from users,follows where follows.user_id=? and users.id=follows.follow_id limit ? offset ?";
            BeanListHandler<User> beanListHandler = new BeanListHandler<>(User.class);
            List<User> userList= qr.query(ce, sql, beanListHandler,id,pageSize,begin);
            return userList;


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }

        return null;
    }

    @Override
    public List<User> queryFanByPage(int id, int currentPage, int pageSize) {
        Connection ce = null;
        try {
            //limit 查询的数目====pageSize
            //offset对应的记录开始数
            //记录第几页的第一条记录begin
            int begin = ( currentPage-1 )*pageSize;
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "select users.* from users,fans where fans.user_id=? and users.id=fans.fan_id limit ? offset ?";
            BeanListHandler<User> beanListHandler = new BeanListHandler<>(User.class);
            List<User> userList= qr.query(ce, sql, beanListHandler,id,pageSize,begin);
            return userList;


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }

        return null;
    }

    @Override
    public void changePassword(String newpassword, int id) {
        Connection ce = null;
        try {
            QueryRunner qr = new QueryRunner();
            ce = DbUtil.getConnection();
            String sql = "update users set users.password=? where users.id=?;";
            qr.update(ce,sql,newpassword,id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ce);
        }
    }

}
