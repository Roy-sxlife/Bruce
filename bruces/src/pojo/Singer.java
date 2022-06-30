package pojo;



/**
 * <p>
 * 歌手表
 * </p>
 *
 * @author marlowe
 * @since 2021-05-30
 */
public class Singer {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 歌手id
     */
    private String singerId;


    /**
     * 姓名
     */
    private String name;




    /**
     * 性别
     */
    private Integer sex;

    /**
     * 照片
     */
    private String pic;

    /**
     * 生日
     */
    private String birth;

    /**
     * 地区
     */
    private String location;

    /**
     * 发行时间
     */

    private String createTime;

    /**
     * 更新时间
     */

    private String updateTime;

    /**
     * 简介
     */
    private String introduction;

    public Singer(String singerId, String name) {
        this.singerId = singerId;
        this.name = name;
    }

    public Singer(String singerId, String name, int sex, String pic, String location, String introduction) {
        this.singerId = singerId;
        this.name = name;
        this.sex = sex;
        this.pic = pic;
        this.location = location;
        this.introduction = introduction;
    }

    public Singer() {

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSingerId() {
        return singerId;
    }

    public void setSingerId(String singerId) {
        this.singerId = singerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Singer(Integer id, String singerId, String name, Integer sex, String pic, String birth, String location, String createTime, String updateTime, String introduction) {
        this.id = id;
        this.singerId = singerId;
        this.name = name;
        this.sex = sex;
        this.pic = pic;
        this.birth = birth;
        this.location = location;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.introduction = introduction;
    }
}
