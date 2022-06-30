package pojo;

/**
 * 动态表
 */

public class Status {
    private int status_id;
    private User user;
    private String mood;
    private String description;
    private String create_time;
    private String title;
    private int remark; //评论数
    private int enjoy; //喜欢数
    private int star; //收藏数
    private Label label;


    public Status() {
        this.remark=0;
        this.enjoy=0;
        this.star=0;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRemark() {
        return remark;
    }

    public void setRemark(int remark) {
        this.remark = remark;
    }

    public int getEnjoy() {
        return enjoy;
    }

    public void setEnjoy(int enjoy) {
        this.enjoy = enjoy;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Status{" +
                "status_id=" + status_id +
                ", user=" + user +
                ", mood='" + mood + '\'' +
                ", description='" + description + '\'' +
                ", create_time=" + create_time +
                ", title='" + title + '\'' +
                ", remark=" + remark +
                ", enjoy=" + enjoy +
                ", star=" + star +
                ", label_id='" + label + '\'' +
                '}';
    }
}
