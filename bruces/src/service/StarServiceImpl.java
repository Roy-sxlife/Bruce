package service;

import dao.StarDao;
import dao.StarDaoImpl;
import pojo.Status;

import java.util.List;

public class StarServiceImpl implements StarService {
    StarDao starDao=new StarDaoImpl();
    @Override
    public Boolean findStarOrnot(int status_id, int id) {

        return starDao.findStarOrnot(status_id,id);
    }

    @Override
    public List<Integer> findAllstars(int id) {

        List<Integer> allstar=starDao.findAllstar(id);
        if (allstar!=null) return allstar;
        else return null;
    }

    @Override
    public List<Status> findAllStatus(int id) {
        List<Status> statuses=starDao.findAllStatus(id);
        if(statuses!=null){
            return statuses;
        }
        else return null;
    }

    @Override
    public void removeStar(int id, int status_id) {

        starDao.removeStar(id,status_id);
    }

    @Override
    public void addStar(int id, int status_id) {
        starDao.addStar(id,status_id);
    }

    @Override
    public void startStar(int user_id) {
        starDao.startStar(user_id);
    }

    @Override
    public void cancelStar(int user_id) {
        starDao.cancelStar(user_id);
    }

    @Override
    public void addEnjoy(int enjoy_id) {
        starDao.addEnjoy(enjoy_id);
    }

    @Override
    public int starCount(int status_id) {
        return starDao.starCount(status_id);
    }
}
