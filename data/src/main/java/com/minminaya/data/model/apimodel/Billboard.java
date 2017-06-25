
package com.minminaya.data.model.apimodel;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Billboard {

    @SerializedName("billboard_no")
    private String mBillboardNo;
    @SerializedName("billboard_songnum")
    private String mBillboardSongnum;
    @SerializedName("billboard_type")
    private String mBillboardType;
    @SerializedName("comment")
    private String mComment;
    @SerializedName("havemore")
    private Long mHavemore;
    @SerializedName("name")
    private String mName;
    @SerializedName("pic_s192")
    private String mPicS192;
    @SerializedName("pic_s210")
    private String mPicS210;
    @SerializedName("pic_s260")
    private String mPicS260;
    @SerializedName("pic_s444")
    private String mPicS444;
    @SerializedName("pic_s640")
    private String mPicS640;
    @SerializedName("update_date")
    private String mUpdateDate;
    @SerializedName("web_url")
    private String mWebUrl;

    public String getBillboardNo() {
        return mBillboardNo;
    }

    public void setBillboardNo(String billboardNo) {
        mBillboardNo = billboardNo;
    }

    public String getBillboardSongnum() {
        return mBillboardSongnum;
    }

    public void setBillboardSongnum(String billboardSongnum) {
        mBillboardSongnum = billboardSongnum;
    }

    public String getBillboardType() {
        return mBillboardType;
    }

    public void setBillboardType(String billboardType) {
        mBillboardType = billboardType;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    public Long getHavemore() {
        return mHavemore;
    }

    public void setHavemore(Long havemore) {
        mHavemore = havemore;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPicS192() {
        return mPicS192;
    }

    public void setPicS192(String picS192) {
        mPicS192 = picS192;
    }

    public String getPicS210() {
        return mPicS210;
    }

    public void setPicS210(String picS210) {
        mPicS210 = picS210;
    }

    public String getPicS260() {
        return mPicS260;
    }

    public void setPicS260(String picS260) {
        mPicS260 = picS260;
    }

    public String getPicS444() {
        return mPicS444;
    }

    public void setPicS444(String picS444) {
        mPicS444 = picS444;
    }

    public String getPicS640() {
        return mPicS640;
    }

    public void setPicS640(String picS640) {
        mPicS640 = picS640;
    }

    public String getUpdateDate() {
        return mUpdateDate;
    }

    public void setUpdateDate(String updateDate) {
        mUpdateDate = updateDate;
    }

    public String getWebUrl() {
        return mWebUrl;
    }

    public void setWebUrl(String webUrl) {
        mWebUrl = webUrl;
    }

}
