package pojo;

/**
 * 评论表
 */

public class Remark {
    private int id;
    private Status status;
    private User remarker;
    private String remark_content;
    private String remark_time;
    private int reply_count;

    public Remark() {
        super();
    }

    public int getReply_count() {
        return reply_count;
    }

    public void setReply_count(int reply_count) {
        this.reply_count = reply_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getRemarker() {
        return remarker;
    }

    public void setRemarker(User remarker) {
        this.remarker = remarker;
    }

    public String getRemark_content() {
        return remark_content;
    }

    public void setRemark_content(String remark_content) {
        this.remark_content = remark_content;
    }

    public String getRemark_time()
    {
       return remark_time;
    }

    public void setRemark_time(String remark_time) {
        this.remark_time = remark_time;
    }

    public Remark(int id, Status status, User remarker, String remark_content, String remark_time, int reply_count) {
        this.id = id;
        this.status = status;
        this.remarker = remarker;
        this.remark_content = remark_content;
        this.remark_time = remark_time;
        this.reply_count = reply_count;
    }
}
