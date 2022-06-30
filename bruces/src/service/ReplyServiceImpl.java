package service;

import dao.ReplyDao;
import dao.ReplyDaoImpl;
import pojo.Reply;

import java.util.List;

public class ReplyServiceImpl implements ReplyService {
    ReplyDao replyDao=new ReplyDaoImpl();
    @Override
    public List<Reply> findTheReply(int id) {
        return replyDao.findTheReply(id);
    }

    @Override
    public void addnewreply(int parseInt, int parseInt1, int parseInt2, String reply_content) {
        replyDao.addnewreply(parseInt,parseInt1,parseInt2,reply_content);
    }

    @Override
    public Reply findNewReply(int remark_id) {
        return replyDao.findNewReply(remark_id);
    }

    @Override
    public int findReplyCount(int remark_id) {
        return replyDao.findReplyCount(remark_id);
    }

    @Override
    public void addRemark_reply(int remark_id) {
        replyDao.addRemark_reply(remark_id);
    }
}
