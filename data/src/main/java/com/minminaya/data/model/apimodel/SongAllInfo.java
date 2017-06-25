
package com.minminaya.data.model.apimodel;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class SongAllInfo {

    @SerializedName("bitrate")
    private Bitrate mBitrate;
    @SerializedName("error_code")
    private Long mErrorCode;
    @SerializedName("songinfo")
    private Songinfo mSonginfo;

    public Bitrate getBitrate() {
        return mBitrate;
    }

    public void setBitrate(Bitrate bitrate) {
        mBitrate = bitrate;
    }

    public Long getErrorCode() {
        return mErrorCode;
    }

    public void setErrorCode(Long errorCode) {
        mErrorCode = errorCode;
    }

    public Songinfo getSonginfo() {
        return mSonginfo;
    }

    public void setSonginfo(Songinfo songinfo) {
        mSonginfo = songinfo;
    }

}
