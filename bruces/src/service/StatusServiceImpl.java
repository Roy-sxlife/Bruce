package service;

import dao.StatusDao;
import dao.StatusDaoImpl;
import pojo.Status;
import pojo.User;

import java.sql.Timestamp;
import java.util.List;

public class StatusServiceImpl implements StatusService {
    StatusDao statusDao=new StatusDaoImpl();
    @Override
    public int addStatus(Status status,String tag) {

        int i=statusDao.addStatus(status,tag);
        if(i!=0) return i;
        else return 0;
    }

    @Override
    public List<Status> findStatusList() {
        List<Status> statusList=statusDao.findStatusList();
        return statusList;
    }

    @Override
    public void decrease(int status_id) {
        statusDao.decrease(status_id);
    }

    @Override
    public void increase(int status_id) {
        statusDao.increase(status_id);
    }

    @Override
    public List<Status> findUserStatusList(int user_id) {
        List<Status> statuses=statusDao.findUserStatusList(user_id);
        return statuses;
    }

    @Override
    public void deleteStatus(int delete_id) {
        statusDao.deleteStatus(delete_id);
    }

    @Override
    public Status findStatusById(Integer status_id) {
        Status status=statusDao.findStatusById(status_id);
        return status;
    }

    @Override
    public List<Status> queryStatusByPage(int currentPage, int pageSize) {
        return statusDao.queryStatusByPage(currentPage,pageSize);
    }

    @Override
    public int enjoyCount(int enjoy_id) {
        return statusDao.enjoyCount(enjoy_id);
    }

    @Override
    public int findRemarkCount(int status_id) {
        return statusDao.findRemarkCount(status_id);
    }


}
