package service;

import pojo.Status;
import pojo.User;

import java.sql.Timestamp;
import java.util.List;

public interface StatusService {
    int addStatus(Status status,String tag);

    List<Status> findStatusList();

    void decrease(int status_id);

    void increase(int status_id);

    List<Status> findUserStatusList(int user_id);

    void deleteStatus(int delete_id);

    Status findStatusById(Integer status_id);

    List<Status> queryStatusByPage(int currentPage, int pageSize);

    int enjoyCount(int enjoy_id);

    int findRemarkCount(int status_id);
}
