package service;

import dao.UserDao;
import dao.UserDaolmpl;
import pojo.Follow;
import pojo.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao userDao=new UserDaolmpl();
    @Override
    public User finduser(String username,String password) {

        User user=userDao.finduser(username,password);
        if(user!=null){
            return user;
        }else
        return null;
    }

    @Override
    public int adduser(User user) {
        int i=userDao.adduser(user);
        if (i!=0){
            return i;
        }else
        return 0;
    }

    @Override
    public Boolean findfollowsOrnot(int id,int id2) {
        return userDao.findfollowOrnot(id,id2);
    }

    @Override
    public List<Integer> findAllfollows(int id) {
        List<Integer> follows =userDao.findAllfollows(id);
        if (follows!=null) return follows;
        else return null;
    }

    @Override
    public List<User> findAllfollowsUser(int id) {
        List<User> userList=userDao.findAllfollowsUser(id);
        if (userList!=null) {
            return userList;
        }
        else return null;
    }

    @Override
    public List<User> findAllfansUser(int id) {
        List<User> userList=userDao.findAllfansUser(id);
        if(userList!=null){
            return userList;
        }
        else return null;
    }

    @Override
    public void removeFriend(int id, int follow_id) {
        userDao.removeFriend(id,follow_id);
    }

    @Override
    public void addFriend(int id, int follow_id) {
        userDao.addFriend(id,follow_id);
    }

    @Override
    public void startFollow(int user_id,int follow_id) {
        userDao.startFollow(user_id,follow_id);
    }

    @Override
    public void cancelFollow(int user_id,int follow_id) {
        userDao.cancelFollow(user_id,follow_id);
    }

    @Override
    public User finduserbyId(int userId) {
        User user=userDao.finduserbyId(userId);
        return user;
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public boolean IfExist(String username) {
       return userDao.IfExist(username);
    }

    @Override
    public boolean repetition(String username1) {
        return userDao.repetition(username1);
    }

    @Override
    public List<User> queryFriendByPage(int id, int currentPage, int pageSize) {
        return userDao.queryFriendByPage(id,currentPage,pageSize);
    }

    @Override
    public List<User> queryFanByPage(int id, int currentPage, int pageSize) {
        return userDao.queryFanByPage(id,currentPage,pageSize);
    }

    @Override
    public void changePassword(String newpassword, int id) {
        userDao.changePassword(newpassword,id);
    }


}
