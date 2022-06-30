package pojo;

import java.sql.Timestamp;

public class Message {
    private int id;
    private int send_id;
    private int receive_id;
    private String message_content;
    private Timestamp send_time;

    public Message() {
        this.send_time=new Timestamp(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSend_id() {
        return send_id;
    }

    public void setSend_id(int send_id) {
        this.send_id = send_id;
    }

    public int getReceive_id() {
        return receive_id;
    }

    public void setReceive_id(int receive_id) {
        this.receive_id = receive_id;
    }

    public String getMessage_content() {
        return message_content;
    }

    public void setMessage_content(String message_content) {
        this.message_content = message_content;
    }

    public Timestamp getSend_time() {
        return send_time;
    }

    public void setSend_time(Timestamp send_time) {
        this.send_time = send_time;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", send_id=" + send_id +
                ", receive_id=" + receive_id +
                ", message_content='" + message_content + '\'' +
                ", send_time=" + send_time +
                '}';
    }
}
