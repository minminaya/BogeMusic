package com.minminaya.data.model;

/**
 * Created by Niwa on 2017/5/29.
 */

public class LocalMusicModel {



    private long songId;
    private String songTitle;

    public LocalMusicModel(long songId, String songTitle) {
        this.songId = songId;
        this.songTitle = songTitle;
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
}
