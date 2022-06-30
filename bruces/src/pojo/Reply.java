package pojo;

/**
 * 回复表
 */
public class Reply {

    private int id;
    private Remark remark;
    private User from_user;
    private User to_user;
    private String reply_content;
    private String reply_time;
    private int reply_number;


    public Reply() {
    }

    public int getReply_number() {
        return reply_number;
    }

    public void setReply_number(int reply_number) {
        this.reply_number = reply_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Remark getRemark() {
        return remark;
    }

    public void setRemark(Remark remark) {
        this.remark = remark;
    }

    public User getFrom_user() {
        return from_user;
    }

    public void setFrom_user(User from_user) {
        this.from_user = from_user;
    }

    public User getTo_user() {
        return to_user;
    }

    public void setTo_user(User to_user) {
        this.to_user = to_user;
    }

    public String getReply_content() {
        return reply_content;
    }

    public void setReply_content(String reply_content) {
        this.reply_content = reply_content;
    }

    public String getReply_time() {
        return reply_time;
    }

    public void setReply_time(String reply_time) {
        this.reply_time = reply_time;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", remark=" + remark +
                ", from_user=" + from_user +
                ", to_user=" + to_user +
                ", reply_content='" + reply_content + '\'' +
                ", reply_time='" + reply_time + '\'' +
                ", reply_number=" + reply_number +
                '}';
    }
}
