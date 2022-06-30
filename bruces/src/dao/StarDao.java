package dao;

import pojo.Status;

import java.util.List;

public interface StarDao {
    Boolean findStarOrnot(int status_id, int id);

    List<Integer> findAllstar(int id);

    List<Status> findAllStatus(int id);

    void removeStar(int id, int status_id);

    void addStar(int id, int status_id);

    void startStar(int user_id);

    void cancelStar(int user_id);

    void addEnjoy(int enjoy_id);

    int starCount(int status_id);
}
