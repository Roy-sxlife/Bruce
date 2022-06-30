package util;

import java.util.List;

/**
 * 判断是否收藏、是否关注
 */

public class IsFollowOrStar {

    public static Boolean isfollows(int followId, List<Integer> allfollows) {
        for(Integer id:allfollows){
            if (id==followId) return true;
        }
        return false;
    }
    public static Boolean ifstars(int status_id,List<Integer> allstar) {
        for(Integer id:allstar){
            if (id==status_id) return true;
        }
        return false;
    }

}
