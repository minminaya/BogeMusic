package com.minminaya.bogemusic.mine.presenter;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.DonutProgress;
import com.minminaya.bogemusic.App;
import com.minminaya.bogemusic.C;
import com.minminaya.bogemusic.R;
import com.minminaya.bogemusic.mine.fragment.ListFragment;
import com.minminaya.bogemusic.mvp.presenter.base.BasePresenter;
import com.minminaya.bogemusic.play.service.MediaService;
import com.minminaya.bogemusic.utils.DataSetUtil;
import com.minminaya.bogemusic.utils.localmusic.LocalMusicUtil;
import com.minminaya.data.model.LocalMusicModel;

import java.util.List;

/**
 * Created by Niwa on 2017/5/31.
 */

public class ListFragmentPresenter extends BasePresenter<ListFragment> {

    public ListFragmentPresenter() {
    }

    private MyBroadcastReceiver myBroadcastReceiver;

    private List<LocalMusicModel> list;
    private MediaService.MyBinder myBinder;

    private Intent mediaServiceIntent;
    private DonutProgress playCircleProgress;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MediaService.MyBinder) service;
//            Log.d("onServiceConnected", "Service与Activity已连接");
            myBinder.setData(list);
//            myBinder.playMusic();
            mHandler.post(runnable);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    /**
     * 初始化Service
     */
    public void initService() {
        mediaServiceIntent = new Intent(App.getINSTANCE(), MediaService.class);
        App.getINSTANCE().bindService(mediaServiceIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
        playCircleProgress = (DonutProgress) getMvpView().getActivity().findViewById(R.id.play_circle_progress);

        //初始化并动态注册广播接收器
        myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(C.InstantForReceiver.ACTION);
        App.getINSTANCE().registerReceiver(myBroadcastReceiver, intentFilter);
    }

    /**
     * 获取内容提供器提供的歌曲和将歌曲对象写入内存卡文件中
     */
    public void getSongListAndSaveSongList() {
        list = LocalMusicUtil.iniMediaPlayerContentUri();
        //list对象作为文件写入存储卡中

        DataSetUtil.writeObject(list, Environment.getExternalStorageDirectory().getAbsolutePath() + "/BogeMusic/songList", "songList");
        for (int i = 0; i < list.size(); i++) {
//            Log.e("音乐title", "音乐:" + i + ":" + list.get(i).getSongTitle() + "," + "id:" + list.get(i).getSongId() + "长度：" + list.get(i).getSongDuration() + "歌曲位置：" + list.get(i).getSongPath());
        }
//        Log.e("music数据", "" + list.size());
    }

    /**
     * 关闭Media和停止播放服务
     */
    public void closeMediaAndStopService() {
        myBinder.closeMedia();
        App.getINSTANCE().unbindService(mServiceConnection);
        Toast.makeText(getMvpView().getContext(), "播放服务已停止", Toast.LENGTH_SHORT).show();
        App.getINSTANCE().unregisterReceiver(myBroadcastReceiver);
    }


    public void setSongListToAdapter() {
        getMvpView().setSongListToAdapter(list);
    }

    //用于mini圆圈进度条的刷新
    private Handler mHandler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            float currentLong = myBinder.getPlayPosition();
            float size = myBinder.getProgress();
            playCircleProgress.setProgress(100 * (currentLong / size));
            Log.d("songProgress", "" + 100 * (currentLong / size));
            mHandler.postDelayed(runnable, 1000);
        }
    };

    /**
     * 移除runnable，
     * 我们的handler发送是定时1000s发送的，如果不关闭，MediaPlayer release掉了还在获取getCurrentPosition就会爆IllegalStateException错误
     */
    public void disattachHandler() {
        mHandler.removeCallbacks(runnable);
    }

    /**
     * 选取指定歌曲
     */
    public void seekSong(int position) {
        myBinder.seekSong(position);
    }

    /**
     * 获取歌曲长度
     */
    public float getTotalPosition() {
        return myBinder.getProgress();
    }


    /**
     * 用于接受广播从而控制播放服务的类
     */
    public class MyBroadcastReceiver extends BroadcastReceiver {

        public MyBroadcastReceiver() {
        }

        //当前播放状态
        private boolean currentPlayState = true;

        @Override
        public void onReceive(Context context, Intent intent) {
//            Toast.makeText(App.getINSTANCE(), "接受到了哦", Toast.LENGTH_SHORT).show();
            //默认值100随便填的
            int i = intent.getIntExtra(C.InstantForReceiver.MUSIC_PLAY_KEY, 100);
            switch (i) {
                case C.InstantForReceiver.BUTTON_LAST:
                    myBinder.preciousMusic();
                    break;
                case C.InstantForReceiver.BUTTON_RESET:
                    myBinder.closeMedia();
                    break;
                case C.InstantForReceiver.BUTTON_NEXT:
                    myBinder.nextMusic();
                    break;
                case C.InstantForReceiver.PLAY_OR_PAUSE:
                    if (currentPlayState) {
                        myBinder.pauseMusic();
                        currentPlayState = false;
                    } else {
                        myBinder.playMusic();
                        currentPlayState = true;
                    }
                    break;
                case C.InstantForReceiver.UPDATA_SONG_POSITION_FlAG:
                    int position = intent.getIntExtra(C.InstantForReceiver.UPDATA_SONG_POSITION, 100);
                    myBinder.seekToPositon(position);
                    break;
                case C.InstantForReceiver.UPDATA_SONG_DATA_FLAG:
                    //发动更新数据的广播给MusicPlayActivity
                    myBinder.resendSongTotalBroadcast();
                    break;
            }
        }
    }

}
