package com.minminaya.bogemusic.play.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.minminaya.bogemusic.App;
import com.minminaya.bogemusic.C;
import com.minminaya.data.model.LocalMusicModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niwa on 2017/5/31.
 */

public class MediaService extends Service {
    private final static String TAG = "MediaService";
    private List<LocalMusicModel> localMusicModels = new ArrayList<>();
    private MediaPlayer mMediaPlayer = new MediaPlayer();
    private MyBinder myBinder = new MyBinder();
    //标记当前歌曲的序号
    private int i = 0;

    private Handler mHandler = new Handler();

    public MediaService() {
//        iniMediaPlayerFile(0);
    }

    public List<LocalMusicModel> getLocalMusicModels() {
        return localMusicModels;
    }

    public void setLocalMusicModels(List<LocalMusicModel> localMusicModels) {
        this.localMusicModels = localMusicModels;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        return myBinder;
    }

    public class MyBinder extends Binder {
        /**
         * 播放音乐
         */
        public void playMusic() {
            if (!mMediaPlayer.isPlaying()) {
                //如果还没开始播放，就开始
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        // 发送广播给LrcFragment表示播放完了
                        nextMusic();//下一首
                        sendBroadCastDataForLrcFragment(true);
                    }
                });
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
         * 选取指定歌曲
         */
        public void seekSong(int position) {
            mMediaPlayer.reset();
            iniMediaPlayerFile(position);
            i = position;//存当前播放的歌曲
            playMusic();
        }

        /**
         * 下一首
         */
        public void nextMusic() {
            if (mMediaPlayer != null && i < localMusicModels.size() && i >= 0) {
                //切换歌曲reset()很重要很重要很重要，没有会报IllegalStateException
                mMediaPlayer.reset();
                iniMediaPlayerFile(i + 1);
                //这里的if只要是为了不让歌曲的序号越界，因为只有4首歌
                if (i == localMusicModels.size() - 2) {

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
            if (mMediaPlayer != null && i < localMusicModels.size() && i > 0) {
                mMediaPlayer.reset();
                iniMediaPlayerFile(i - 1);
                if (i == 1) {

                } else {

                    i = i - 1;
                }
                playMusic();
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
         * 数据传入Service中
         */
        public void setData(List<LocalMusicModel> list) {
            setLocalMusicModels(list);
//            iniMediaPlayerFile(0);
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
            if (!mMediaPlayer.isPlaying()) {

//                mMediaPlayer.start();//同时继续播放
            }
        }

        /**
         * 重新发送当前播放的数据给MusicPlayActivity
         */
        public void resendSongTotalBroadcast() {
            sendSongTotalBroadcast(i);
        }

    }

    /**
     * 添加file文件到MediaPlayer对象并且准备播放音频
     */
    private void iniMediaPlayerFile(int dex) {
        //获取文件路径
        try {
            //此处的两个方法需要捕获IO异常
            //设置音频文件到MediaPlayer对象中
            mMediaPlayer.setDataSource(localMusicModels.get(dex).getSongPath());
            //让MediaPlayer对象准备
            mMediaPlayer.prepare();

            sendSongTotalBroadcast(dex);


        } catch (IOException e) {
//            Log.d(TAG, "设置资源，准备阶段出错");
            e.printStackTrace();
        }
    }

    /**
     * 将歌曲长度发送给播放界面MusicPlayActivity
     */
    private void sendSongTotalBroadcast(final int dex) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(200

                    );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(C.InstantForBroadcastReceiverForMusicServiceData.ACTION);
                i.putExtra(C.InstantForBroadcastReceiverForMusicServiceData.MUSIC_PLAY_KEY_2, C.InstantForBroadcastReceiverForMusicServiceData.TOTAL_LONG_FLAG);
                //下面传的是值
                i.putExtra(C.InstantForBroadcastReceiverForMusicServiceData.TOTAL_SONG_POSITION, myBinder.getProgress());
                //当前歌曲歌手名字
                i.putExtra(C.InstantForBroadcastReceiverForMusicServiceData.CURRENT_SONG_TITLE, localMusicModels.get(dex).getSongTitle());
                //当前歌曲名字
                i.putExtra(C.InstantForBroadcastReceiverForMusicServiceData.CURRENT_SONG_ARTIST_TITLE, localMusicModels.get(dex).getSongArtist());
                App.getINSTANCE().sendBroadcast(i);

                mHandler.post(mRunnable);
            }
        }).start();
    }
//    int p = 0;
    /**
     *
     */
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            Intent i = new Intent(C.InstantForBroadcastReceiverForMusicServiceData.ACTION);
            i.putExtra(C.InstantForBroadcastReceiverForMusicServiceData.MUSIC_PLAY_KEY_2, C.InstantForBroadcastReceiverForMusicServiceData.CURRENT_SONG_POSITION_FLAG);

            i.putExtra(C.InstantForBroadcastReceiverForMusicServiceData.CURRENT_SONG_POSITION, myBinder.getPlayPosition());
            App.getINSTANCE().sendBroadcast(i);

//            Log.e("MusicPlayActivity", "发送次数：" + p++);
            mHandler.postDelayed(mRunnable, 1000);
        }
    };

    /**
     * 给LrcFragment播放完成通知
     */
    private void sendBroadCastDataForLrcFragment(boolean isPlayCompletion) {
        Intent intent1 = new Intent(C.InstantForBroadcastReceiverForMusicPlaySeekBar.ACTION);
        intent1.putExtra(C.InstantForBroadcastReceiverForMusicPlaySeekBar.LRC_VIEW_KEY_1, C.InstantForBroadcastReceiverForMusicPlaySeekBar.LRC_VIEW_FLAG_2);
        //下面是数据
        intent1.putExtra(C.InstantForBroadcastReceiverForMusicPlaySeekBar.LRC_VIEW_PLAY_IS_PLAY_COMPLETION, isPlayCompletion);
        App.getINSTANCE().sendBroadcast(intent1);


    }
}
