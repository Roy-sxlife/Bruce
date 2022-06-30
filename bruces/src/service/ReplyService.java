package service;

import pojo.Reply;

import java.util.List;

public interface ReplyService {
    List<Reply> findTheReply(int id);

    void addnewreply(int parseInt, int parseInt1, int parseInt2, String reply_content);

    Reply findNewReply(int remark_id);

    int findReplyCount(int remark_id);

    void addRemark_reply(int remark_id);
}
