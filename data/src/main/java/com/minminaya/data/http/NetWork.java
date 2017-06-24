package com.minminaya.data.http;

import android.webkit.WebSettings;

import com.minminaya.data.App1;
import com.minminaya.data.api.MusicApi;
import com.minminaya.data.api.Urls;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/** 初始化网络Retrofit等服务
 * Created by Niwa on 2017/6/24.
 */

public class NetWork {

    private static MusicApi musicApi;
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();


    public static MusicApi getMusicApi(){
        if(musicApi == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Urls.BASE_URL)
                    .client(getOkHttpClient())
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            musicApi = retrofit.create(MusicApi.class);
        }
        return musicApi;
    }

    /**
     *  构造okhttp头部
     *
     * */
    private static OkHttpClient getOkHttpClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .removeHeader("User-Agent")//移除旧的
                                .addHeader("User-Agent", WebSettings.getDefaultUserAgent(App1.getINSTANCE()))//添加真正的头部
                                .build();

                        return chain.proceed(request);
                    }
                }).build();
        return httpClient;
    }

}
