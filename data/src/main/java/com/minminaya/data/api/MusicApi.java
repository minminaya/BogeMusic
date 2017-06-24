package com.minminaya.data.api;

import com.minminaya.data.model.apimodel.MusicTopModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Niwa on 2017/6/23.
 */

public interface MusicApi {

    /**
     *  获取不同榜单中歌曲列表及榜单信息
     *
     *  @param type 不同榜单  1-新歌榜,2-热歌榜,11-摇滚榜,12-爵士,16-流行,21-欧美金曲榜,22-经典老歌榜,23-情歌对唱榜,24-影视金曲榜,25-网络歌曲榜
     *  @param size 返回榜单中歌曲条目数量
     *  @param offset 偏移量-----(第几页)
     *
     * */
    @GET("ting")
    Observable<MusicTopModel> loadMusicTopContentAndInfo(
            @Query("method") String method,
            @Query("type") Integer type,
            @Query("size") Integer size,
            @Query("offset") Integer offset);
}
