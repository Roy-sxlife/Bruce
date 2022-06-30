package pojo;

/**
 * 用户表
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String name;
    private String signature;
    private String birthday;
    private String constellation;
    private String bgimg;
    private String photo;
    private String sex;
    private int fan;
    private int follow;
    private int statu;
    private int starcount;


    public User(int id,String username, String password, String name, String signature, String birthday, String constellation, String bgimg, String photo, String sex, int fan, int follow, int statu, int starcount) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.signature = signature;
        this.birthday = birthday;
        this.constellation = constellation;
        this.bgimg = bgimg;
        this.photo = photo;
        this.sex = sex;
        this.fan = fan;
        this.follow = follow;
        this.statu = statu;
        this.starcount = starcount;
        this.id = id;

    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getBgimg() {
        return bgimg;
    }

    public void setBgimg(String bgimg) {
        this.bgimg = bgimg;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getFan() {
        return fan;
    }

    public void setFan(int fan) {
        this.fan = fan;
    }

    public int getFollow() {
        return follow;
    }

    public void setFollow(int follow) {
        this.follow = follow;
    }

    public int getStatu() {
        return statu;
    }

    public void setStatu(int statu) {
        this.statu = statu;
    }

    public int getStarcount() {
        return starcount;
    }

    public void setStarcount(int starcount) {
        this.starcount = starcount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", signature='" + signature + '\'' +
                ", birthday='" + birthday + '\'' +
                ", constellation='" + constellation + '\'' +
                ", bgimg='" + bgimg + '\'' +
                ", photo='" + photo + '\'' +
                ", sex='" + sex + '\'' +
                ", fan=" + fan +
                ", follow=" + follow +
                ", statu=" + statu +
                ", starcount=" + starcount +
                '}';
    }
}
