package com.minminaya.data.model;

import java.io.Serializable;

/**
 * Created by Niwa on 2017/5/29.
 */

public class LocalMusicModel implements Serializable {

    private static final long serialVersionUID = 6134781028719601869L;

    private long songId;
    private String songTitle;
    private int songDuration;
    private String songPath;
    private String songArtist;

//    private int songSize;

    public LocalMusicModel(long songId, String songTitle, int songDuration, String songPath, String songArtist) {
        this.songId = songId;
        this.songTitle = songTitle;
        this.songDuration = songDuration;
        this.songPath = songPath;
        this.songArtist = songArtist;
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

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public void setSongPath(String songPath) {


        this.songPath = songPath;
    }
}
