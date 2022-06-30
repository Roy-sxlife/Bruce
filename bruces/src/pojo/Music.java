package pojo;

public class Music {
    private Integer id;
    private String song;//歌曲名称
    private String singer;//歌手
    private String lyric;//歌词
    private String album;//专辑
    private String songimg;//歌曲图片
    private String songlocation;//歌曲存放路径

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }


    public String getSongimg() {
        return songimg;
    }

    public void setSongimg(String songimg) {
        this.songimg = songimg;
    }

    public String getSonglocation() {
        return songlocation;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setSonglocation(String songlocation) {
        this.songlocation = songlocation;
    }
}
