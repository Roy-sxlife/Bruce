package dao;

import pojo.User;

import java.io.File;
import java.util.List;

public interface UserDao {
    User finduser(String username,String password);
    int adduser(User user);

    Boolean findfollowOrnot(int id,int id2);

    List<Integer> findAllfollows(int id);

    List<User> findAllfollowsUser(int id);

    List<User> findAllfansUser(int id);

    void removeFriend(int id, int follow_id);

    void addFriend(int id, int follow_id);

    void startFollow(int user_id, int follow_id);

    void cancelFollow(int user_id, int follow_id);

    User finduserbyId(int userId);

    void updateUser(User user);

    boolean IfExist(String username);

    boolean repetition(String username1);

    List<User> queryFriendByPage(int id, int currentPage, int pageSize);

    List<User> queryFanByPage(int id, int currentPage, int pageSize);

    void changePassword(String newpassword, int id);
}
