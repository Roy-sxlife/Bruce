package pojo;



/**
 * <p>
 * 歌曲表
 * </p>
 *
 * @author marlowe
 * @since 2021-05-30
 */

public class Song{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */

    private Integer id;

    /**
     * 歌曲id
     */
    private String songId;

    /**
     * 歌手id
     */
    private String singerId;

    /**
     * 照片
     */
    private String pic;

    /**
     * 歌词
     */
    private String lyric;

    /**
     * url
     */
    private String url;


    /**
     * 歌曲名
     */
    private String name;

    /**
     * 歌曲专辑名
     */
    private String albumName;

    /**
     * 歌曲时长
     */
    private String duration;

    /**
     * 歌手名
     */
    private String singerName;
    /**
     * 简介
     */
    private String introduction;

    /**
     * 是否下载
     */
    private int isDownload;

    /**
     * 播放次数
     */
    private int playCount;

    /**
     * 发行时间
     */

    private String createTime;

    /**
     * 更新时间
     */

    private String updateTime;

    public Song() {
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

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSingerId() {
        return singerId;
    }

    public void setSingerId(String singerId) {
        this.singerId = singerId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getIsDownload() {
        return isDownload;
    }

    public void setIsDownload(int isDownload) {
        this.isDownload = isDownload;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
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

    public Song(Integer id, String songId, String singerId, String pic, String lyric, String url, String name, String albumName, String duration, String singerName, String introduction, int isDownload, int playCount, String createTime, String updateTime) {
        this.id = id;
        this.songId = songId;
        this.singerId = singerId;
        this.pic = pic;
        this.lyric = lyric;
        this.url = url;
        this.name = name;
        this.albumName = albumName;
        this.duration = duration;
        this.singerName = singerName;
        this.introduction = introduction;
        this.isDownload = isDownload;
        this.playCount = playCount;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
