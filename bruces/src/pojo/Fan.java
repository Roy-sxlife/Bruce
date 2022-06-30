package pojo;

import java.sql.Timestamp;

/**
 * 粉丝表
 */
public class Fan {
    private int id;
    private int user_id;
    private int fan_id;
    private String fan_note;

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

    public int getFan_id() {
        return fan_id;
    }

    public void setFan_id(int fan_id) {
        this.fan_id = fan_id;
    }

    public String getFan_note() {
        return fan_note;
    }

    public void setFan_note(String fan_note) {
        this.fan_note = fan_note;
    }

    @Override
    public String toString() {
        return "Fan{" +
                "id='" + id + '\'' +
                ", user_id=" + user_id +
                ", fan_id=" + fan_id +
                ", fan_note='" + fan_note + '\'' +
                '}';
    }
}
