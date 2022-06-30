package pojo;

import java.util.Vector;

/**
 * 在线用户储存表
 */
public class OnlineUsers {
    //储存当前在线用户
    private static Vector onlineuser=new Vector();
    /**
     * 添加在线用户
     */
    public static void addUser(String username){
        onlineuser.addElement(username);
    }
    /**
     * 移除在线用户
     */
    public static void removeUser(String username){
        onlineuser.removeElement(username);
    }
    /**
     * 获取在线用户数量
     */
    public static int getUserCount(){
        return onlineuser.size();
    }
    /**
     * 获取在线用户
     */
    public static Vector getVector(){
        return onlineuser;
    }
}
