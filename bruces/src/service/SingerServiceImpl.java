package service;

import dao.SingerDao;
import dao.SingerDaoImpl;
import pojo.Singer;

public class SingerServiceImpl implements SingerService {
    SingerDao singerDao=new SingerDaoImpl();
    @Override
    public void insert(Singer singer) {
        singerDao.insert(singer);
    }
}
