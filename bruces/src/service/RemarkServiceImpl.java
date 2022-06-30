package service;

import dao.RemarkDao;
import dao.RemarkDaoImpl;
import pojo.Remark;
import pojo.Status;
import pojo.User;

import java.util.List;

public class RemarkServiceImpl implements RemarkService {
    RemarkDao remarkDao=new RemarkDaoImpl();
    @Override
    public List<Remark> findAllRemark(Integer status_id) {
        List<Remark> remarks=remarkDao.findAllRemark(status_id);
        return remarks;
    }

    @Override
    public void addRemark(Remark remark) {
        remarkDao.addRemark(remark);
    }

    @Override
    public void addStatus_Remark(int status_id) {
        remarkDao.addStatus_Remark(status_id);
    }

    @Override
    public Remark findNewRemark(int status_id) {
        return remarkDao.findNewRemark(status_id);
    }


}
