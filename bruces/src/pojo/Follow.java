package pojo;

public class Follow {
    private int id;
    private int user_id;
    private int follow_id;
    private String follow_note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getFollow_id() {
        return follow_id;
    }

    public void setFollow_id(int follow_id) {
        this.follow_id = follow_id;
    }

    public String getFollow_note() {
        return follow_note;
    }

    public void setFollow_note(String follow_note) {
        this.follow_note = follow_note;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "id='" + id + '\'' +
                ", user_id=" + user_id +
                ", follow_id=" + follow_id +
                ", follow_note='" + follow_note + '\'' +
                '}';
    }
}
