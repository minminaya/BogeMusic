package com.minminaya.bogemusic.finder.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
import android.media.MediaPlayer;
import android.os.Binder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

/** 单独用于播放网络流的服务
 * Created by Niwa on 2017/6/27.
 */

public class WebMusicService extends Service {
    private static final String TAG = "MediaService";


    private MyBinder mBinder = new MyBinder();
    //标记当前歌曲的序号

    private int i = 0;
    //初始化MediaPlayer
    public MediaPlayer mMediaPlayer = new MediaPlayer();


    public WebMusicService() {
//        iniMediaPlayerInternet();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class MyBinder extends Binder {

        /**
         * 播放音乐
         */
        public void playMusic() {
            if (!mMediaPlayer.isPlaying()) {
                //如果还没开始播放，就开始
                mMediaPlayer.start();
            }
        }

        /**
         * 暂停播放
         */
        public void pauseMusic() {
            if (mMediaPlayer.isPlaying()) {
                //如果还没开始播放，就开始
                mMediaPlayer.pause();
            }
        }

        /**
         * reset
         */
        public void resetMusic() {
            if (!mMediaPlayer.isPlaying()) {
                //如果还没开始播放，就开始
                mMediaPlayer.reset();
            }
        }

        /**
         * 关闭播放器
         */
        public void closeMedia() {
            if (mMediaPlayer != null) {
                mMediaPlayer.stop();
                mMediaPlayer.release();
            }
        }

        /**
         * 下一首
         */
        public void nextMusic() {
            if (mMediaPlayer != null && i < 4 && i >= 0) {
                //切换歌曲reset()很重要很重要很重要，没有会报IllegalStateException
                mMediaPlayer.reset();
                //这里的if只要是为了不让歌曲的序号越界，因为只有4首歌
                if (i == 2) {

                } else {
                    i = i + 1;
                }
                playMusic();
            }

        }

        /**
         * 上一首
         */
        public void preciousMusic() {
            if (mMediaPlayer != null && i < 4 && i > 0) {
                mMediaPlayer.reset();
                if (i == 1) {

                } else {

                    i = i - 1;
                }
                playMusic();
            }
        }

        /**
         * 获取歌曲长度
         **/
        public int getProgress() {

            return mMediaPlayer.getDuration();
        }

        /**
         * 获取播放位置
         */
        public int getPlayPosition() {

            return mMediaPlayer.getCurrentPosition();
        }
        /**
         * 播放指定位置
         */
        public void seekToPositon(int msec) {
            mMediaPlayer.seekTo(msec);
        }
        public void loadWebData(String fileUrl){
            iniMediaPlayerInternet(fileUrl);
        }
    }

    private void iniMediaPlayerInternet(String fileUrl) {
        Log.e("musicmusicmusic11", "初始化");

//        String url = "http://yinyueshiting.baidu.com/data2/music/42783748/42783748.mp3?xcode=97ae4b3e5a50d5e0c9bf3d409e407d1c";
        String url = fileUrl;
        //设置资源为音频流
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mMediaPlayer.setDataSource(url);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

