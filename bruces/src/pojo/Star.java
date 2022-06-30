package pojo;

import java.sql.Timestamp;

/**
 * 收藏表
 */
public class Star {
    private int id;
    private int status_id;
    private int user_id;
    private Timestamp star_time;

    public Star() {
        this.star_time=new Timestamp(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Timestamp getStar_time() {
        return star_time;
    }

    public void setStar_time(Timestamp star_time) {
        this.star_time = star_time;
    }

    @Override
    public String toString() {
        return "Star{" +
                "id='" + id + '\'' +
                ", status_id='" + status_id + '\'' +
                ", user_id=" + user_id +
                ", star_time=" + star_time +
                '}';
    }
}
