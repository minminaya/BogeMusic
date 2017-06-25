
package com.minminaya.data.model.apimodel;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class SongList {
    public SongList(String mTitle) {
        this.mTitle = mTitle;
    }

    @SerializedName("album_id")
    private String mAlbumId;
    @SerializedName("album_no")
    private String mAlbumNo;
    @SerializedName("album_title")
    private String mAlbumTitle;
    @SerializedName("all_artist_id")
    private String mAllArtistId;
    @SerializedName("all_artist_ting_uid")
    private String mAllArtistTingUid;
    @SerializedName("all_rate")
    private String mAllRate;
    @SerializedName("area")
    private String mArea;
    @SerializedName("artist_id")
    private String mArtistId;
    @SerializedName("artist_name")
    private String mArtistName;
    @SerializedName("author")
    private String mAuthor;
    @SerializedName("biaoshi")
    private String mBiaoshi;
    @SerializedName("bitrate_fee")
    private String mBitrateFee;
    @SerializedName("charge")
    private Long mCharge;
    @SerializedName("copy_type")
    private String mCopyType;
    @SerializedName("country")
    private String mCountry;
    @SerializedName("del_status")
    private String mDelStatus;
    @SerializedName("file_duration")
    private Long mFileDuration;
    @SerializedName("has_filmtv")
    private String mHasFilmtv;
    @SerializedName("has_mv")
    private Long mHasMv;
    @SerializedName("has_mv_mobile")
    private Long mHasMvMobile;
    @SerializedName("havehigh")
    private Long mHavehigh;
    @SerializedName("hot")
    private String mHot;
    @SerializedName("info")
    private String mInfo;
    @SerializedName("is_first_publish")
    private Long mIsFirstPublish;
    @SerializedName("is_new")
    private String mIsNew;
    @SerializedName("korean_bb_song")
    private String mKoreanBbSong;
    @SerializedName("language")
    private String mLanguage;
    @SerializedName("learn")
    private Long mLearn;
    @SerializedName("lrclink")
    private String mLrclink;
    @SerializedName("mv_provider")
    private String mMvProvider;
    @SerializedName("piao_id")
    private String mPiaoId;
    @SerializedName("pic_big")
    private String mPicBig;
    @SerializedName("pic_small")
    private String mPicSmall;
    @SerializedName("publishtime")
    private String mPublishtime;
    @SerializedName("rank")
    private String mRank;
    @SerializedName("rank_change")
    private String mRankChange;
    @SerializedName("relate_status")
    private String mRelateStatus;
    @SerializedName("resource_type")
    private String mResourceType;
    @SerializedName("resource_type_ext")
    private String mResourceTypeExt;
    @SerializedName("song_id")
    private String mSongId;
    @SerializedName("song_source")
    private String mSongSource;
    @SerializedName("style")
    private String mStyle;
    @SerializedName("ting_uid")
    private String mTingUid;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("toneid")
    private String mToneid;
    @SerializedName("versions")
    private String mVersions;

    public String getAlbumId() {
        return mAlbumId;
    }

    public void setAlbumId(String albumId) {
        mAlbumId = albumId;
    }

    public String getAlbumNo() {
        return mAlbumNo;
    }

    public void setAlbumNo(String albumNo) {
        mAlbumNo = albumNo;
    }

    public String getAlbumTitle() {
        return mAlbumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        mAlbumTitle = albumTitle;
    }

    public String getAllArtistId() {
        return mAllArtistId;
    }

    public void setAllArtistId(String allArtistId) {
        mAllArtistId = allArtistId;
    }

    public String getAllArtistTingUid() {
        return mAllArtistTingUid;
    }

    public void setAllArtistTingUid(String allArtistTingUid) {
        mAllArtistTingUid = allArtistTingUid;
    }

    public String getAllRate() {
        return mAllRate;
    }

    public void setAllRate(String allRate) {
        mAllRate = allRate;
    }

    public String getArea() {
        return mArea;
    }

    public void setArea(String area) {
        mArea = area;
    }

    public String getArtistId() {
        return mArtistId;
    }

    public void setArtistId(String artistId) {
        mArtistId = artistId;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public void setArtistName(String artistName) {
        mArtistName = artistName;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public String getBiaoshi() {
        return mBiaoshi;
    }

    public void setBiaoshi(String biaoshi) {
        mBiaoshi = biaoshi;
    }

    public String getBitrateFee() {
        return mBitrateFee;
    }

    public void setBitrateFee(String bitrateFee) {
        mBitrateFee = bitrateFee;
    }

    public Long getCharge() {
        return mCharge;
    }

    public void setCharge(Long charge) {
        mCharge = charge;
    }

    public String getCopyType() {
        return mCopyType;
    }

    public void setCopyType(String copyType) {
        mCopyType = copyType;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getDelStatus() {
        return mDelStatus;
    }

    public void setDelStatus(String delStatus) {
        mDelStatus = delStatus;
    }

    public Long getFileDuration() {
        return mFileDuration;
    }

    public void setFileDuration(Long fileDuration) {
        mFileDuration = fileDuration;
    }

    public String getHasFilmtv() {
        return mHasFilmtv;
    }

    public void setHasFilmtv(String hasFilmtv) {
        mHasFilmtv = hasFilmtv;
    }

    public Long getHasMv() {
        return mHasMv;
    }

    public void setHasMv(Long hasMv) {
        mHasMv = hasMv;
    }

    public Long getHasMvMobile() {
        return mHasMvMobile;
    }

    public void setHasMvMobile(Long hasMvMobile) {
        mHasMvMobile = hasMvMobile;
    }

    public Long getHavehigh() {
        return mHavehigh;
    }

    public void setHavehigh(Long havehigh) {
        mHavehigh = havehigh;
    }

    public String getHot() {
        return mHot;
    }

    public void setHot(String hot) {
        mHot = hot;
    }

    public String getInfo() {
        return mInfo;
    }

    public void setInfo(String info) {
        mInfo = info;
    }

    public Long getIsFirstPublish() {
        return mIsFirstPublish;
    }

    public void setIsFirstPublish(Long isFirstPublish) {
        mIsFirstPublish = isFirstPublish;
    }

    public String getIsNew() {
        return mIsNew;
    }

    public void setIsNew(String isNew) {
        mIsNew = isNew;
    }

    public String getKoreanBbSong() {
        return mKoreanBbSong;
    }

    public void setKoreanBbSong(String koreanBbSong) {
        mKoreanBbSong = koreanBbSong;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String language) {
        mLanguage = language;
    }

    public Long getLearn() {
        return mLearn;
    }

    public void setLearn(Long learn) {
        mLearn = learn;
    }

    public String getLrclink() {
        return mLrclink;
    }

    public void setLrclink(String lrclink) {
        mLrclink = lrclink;
    }

    public String getMvProvider() {
        return mMvProvider;
    }

    public void setMvProvider(String mvProvider) {
        mMvProvider = mvProvider;
    }

    public String getPiaoId() {
        return mPiaoId;
    }

    public void setPiaoId(String piaoId) {
        mPiaoId = piaoId;
    }

    public String getPicBig() {
        return mPicBig;
    }

    public void setPicBig(String picBig) {
        mPicBig = picBig;
    }

    public String getPicSmall() {
        return mPicSmall;
    }

    public void setPicSmall(String picSmall) {
        mPicSmall = picSmall;
    }

    public String getPublishtime() {
        return mPublishtime;
    }

    public void setPublishtime(String publishtime) {
        mPublishtime = publishtime;
    }

    public String getRank() {
        return mRank;
    }

    public void setRank(String rank) {
        mRank = rank;
    }

    public String getRankChange() {
        return mRankChange;
    }

    public void setRankChange(String rankChange) {
        mRankChange = rankChange;
    }

    public String getRelateStatus() {
        return mRelateStatus;
    }

    public void setRelateStatus(String relateStatus) {
        mRelateStatus = relateStatus;
    }

    public String getResourceType() {
        return mResourceType;
    }

    public void setResourceType(String resourceType) {
        mResourceType = resourceType;
    }

    public String getResourceTypeExt() {
        return mResourceTypeExt;
    }

    public void setResourceTypeExt(String resourceTypeExt) {
        mResourceTypeExt = resourceTypeExt;
    }

    public String getSongId() {
        return mSongId;
    }

    public void setSongId(String songId) {
        mSongId = songId;
    }

    public String getSongSource() {
        return mSongSource;
    }

    public void setSongSource(String songSource) {
        mSongSource = songSource;
    }

    public String getStyle() {
        return mStyle;
    }

    public void setStyle(String style) {
        mStyle = style;
    }

    public String getTingUid() {
        return mTingUid;
    }

    public void setTingUid(String tingUid) {
        mTingUid = tingUid;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getToneid() {
        return mToneid;
    }

    public void setToneid(String toneid) {
        mToneid = toneid;
    }

    public String getVersions() {
        return mVersions;
    }

    public void setVersions(String versions) {
        mVersions = versions;
    }

}
