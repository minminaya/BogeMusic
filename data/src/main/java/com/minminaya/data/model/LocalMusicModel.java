package com.minminaya.data.model;

/**
 * Created by Niwa on 2017/5/29.
 */

public class LocalMusicModel {


    private long songId;
    private String songTitle;
    private int songDuration;
    private String songPath;
//    private int songSize;

    public LocalMusicModel(long songId, String songTitle, int songDuration, String songPath) {
        this.songId = songId;
        this.songTitle = songTitle;
        this.songDuration = songDuration;
        this.songPath = songPath;
    }

    public long getSongId() {
        return songId;
    }

    public void setSongId(long songId) {
        this.songId = songId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {

        this.songTitle = songTitle;
    }

    public int getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(int songDuration) {
        this.songDuration = songDuration;
    }

    public String getSongPath() {
        return songPath;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }
}
