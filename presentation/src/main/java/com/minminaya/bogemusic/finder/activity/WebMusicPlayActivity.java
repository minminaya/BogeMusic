package com.minminaya.bogemusic.finder.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.SeekBar;

import com.minminaya.bogemusic.C;
import com.minminaya.bogemusic.R;
import com.minminaya.bogemusic.base.BaseActivity;
import com.minminaya.bogemusic.finder.service.WebMusicService;

/**
 * Created by Niwa on 2017/6/27.
 */

public class WebMusicPlayActivity extends BaseActivity {

    private SeekBar mSeekBar;
    Intent MediaServiceIntent;


    private Handler mHandler = new Handler();
    private Bundle bundle;
    private String fileLink;

    private static final String TAG = "MainActivity";
    private WebMusicService.MyBinder mMyBinder;
    @Override
    public void unBind() {
        //我们的handler发送是定时1000s发送的，如果不关闭，MediaPlayer release掉了还在获取getCurrentPosition就会爆IllegalStateException错误
        mHandler.removeCallbacks(mRunnable);

        mMyBinder.closeMedia();
        unbindService(mServiceConnection);
    }

    @Override
    public void bind() {

    }

    @Override
    public void setListeners() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mSeekBar = (SeekBar) findViewById(R.id.seekbar);
        
        sendBroadcastForOtherService();
        bundle = getIntent().getExtras();
        fileLink = bundle.getString("fileLink");

        MediaServiceIntent = new Intent(this, WebMusicService.class);
        bindService(MediaServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
    }
    /**
     *  发送广播停止本地歌曲的播放
     *
     * */
    private void sendBroadcastForOtherService() {
        Intent intent = new Intent(C.InstantForReceiver.ACTION);
        intent.putExtra(C.InstantForReceiver.MUSIC_PLAY_KEY, C.InstantForReceiver.BUTTON_RESET);
        sendBroadcast(intent);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_web_music_play;
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMyBinder = (WebMusicService.MyBinder) service;
            mMyBinder.loadWebData(fileLink);
            mSeekBar.setMax(mMyBinder.getProgress());


            mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                int progress;
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    //这里很重要，如果不判断是否来自用户操作进度条，会不断执行下面语句块里面的逻辑，然后就会卡顿卡顿
                    if(fromUser){
                        this.progress = progress;
//                    mMediaService.mMediaPlayer.seekTo(seekBar.getProgress());
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    mMyBinder.seekToPositon(progress);
                }
            });
            mHandler.post(mRunnable);

            Log.d(TAG, "Service与Activity已连接");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    /**
     * 更新ui的runnable
     */
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mSeekBar.setProgress(mMyBinder.getPlayPosition());
            mHandler.postDelayed(mRunnable, 1000);
        }
    };

}
