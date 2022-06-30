package service;

import pojo.Remark;
import pojo.Status;
import pojo.User;

import java.util.List;

public interface RemarkService {
    List<Remark> findAllRemark(Integer status_id);


    void addRemark(Remark remark);

    void addStatus_Remark(int status_id);

    Remark findNewRemark(int status_id);
}
