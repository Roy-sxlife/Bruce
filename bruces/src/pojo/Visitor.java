package pojo;


import java.util.*;

/**
 * 访客记录
 */
public class Visitor {
    private static List<Map<Integer, User>> visitor =new Vector<>();


    public static void addVisitor(Map<Integer,User> visitormap){
        visitor.add(visitormap);
    }

    //移除该访客记录
    public static void removeVisitor(Map<Integer,User> visitormap){
        visitor.remove(visitormap);
    }

    //获取某一个用户的访客记录
    public static List<User> visitorlist(int id){
        List<User> visitoruser = new ArrayList<>();
        for (int i = 0; i < visitor.size(); i++) {
            Map<Integer, User> mapuser=visitor.get(i);
            if(mapuser.containsKey(id)) {
                visitoruser.add(mapuser.get(id));
            }
        }
        return visitoruser;
    }
    //返回某个用户的访客数量
    public static int getVisitorCount(int id){
        int size = visitorlist(id).size();
        return size;
    }

}
