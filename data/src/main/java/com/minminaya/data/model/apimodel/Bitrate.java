
package com.minminaya.data.model.apimodel;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Bitrate {

    @SerializedName("file_bitrate")
    private Long mFileBitrate;
    @SerializedName("file_duration")
    private Long mFileDuration;
    @SerializedName("file_extension")
    private String mFileExtension;
    @SerializedName("file_link")
    private String mFileLink;
    @SerializedName("file_size")
    private Long mFileSize;
    @SerializedName("free")
    private Long mFree;
    @SerializedName("hash")
    private String mHash;
    @SerializedName("show_link")
    private String mShowLink;
    @SerializedName("song_file_id")
    private Long mSongFileId;

    public Long getFileBitrate() {
        return mFileBitrate;
    }

    public void setFileBitrate(Long fileBitrate) {
        mFileBitrate = fileBitrate;
    }

    public Long getFileDuration() {
        return mFileDuration;
    }

    public void setFileDuration(Long fileDuration) {
        mFileDuration = fileDuration;
    }

    public String getFileExtension() {
        return mFileExtension;
    }

    public void setFileExtension(String fileExtension) {
        mFileExtension = fileExtension;
    }

    public String getFileLink() {
        return mFileLink;
    }

    public void setFileLink(String fileLink) {
        mFileLink = fileLink;
    }

    public Long getFileSize() {
        return mFileSize;
    }

    public void setFileSize(Long fileSize) {
        mFileSize = fileSize;
    }

    public Long getFree() {
        return mFree;
    }

    public void setFree(Long free) {
        mFree = free;
    }

    public String getHash() {
        return mHash;
    }

    public void setHash(String hash) {
        mHash = hash;
    }

    public String getShowLink() {
        return mShowLink;
    }

    public void setShowLink(String showLink) {
        mShowLink = showLink;
    }

    public Long getSongFileId() {
        return mSongFileId;
    }

    public void setSongFileId(Long songFileId) {
        mSongFileId = songFileId;
    }

}
