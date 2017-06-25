
package com.minminaya.data.model.apimodel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class MusicTopModel {

    @SerializedName("billboard")
    private Billboard mBillboard;
    @SerializedName("error_code")
    private Long mErrorCode;
    @SerializedName("song_list")
    private List<SongList> mSongList;

    public Billboard getBillboard() {
        return mBillboard;
    }

    public void setBillboard(Billboard billboard) {
        mBillboard = billboard;
    }

    public Long getErrorCode() {
        return mErrorCode;
    }

    public void setErrorCode(Long errorCode) {
        mErrorCode = errorCode;
    }

    public List<SongList> getSongList() {
        return mSongList;
    }

    public void setSongList(List<SongList> songList) {
        mSongList = songList;
    }

}
