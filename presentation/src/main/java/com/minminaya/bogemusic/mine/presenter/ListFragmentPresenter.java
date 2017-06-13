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

public class ListFragmentPresenter extends BasePresenter<ListFragment>{

    public ListFragmentPresenter() {
    }
    MyBroadcastReceiver myBroadcastReceiver;

    private List<LocalMusicModel> list;
    private MediaService.MyBinder myBinder;

    private Intent mediaServiceIntent;
    private DonutProgress playCircleProgress;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MediaService.MyBinder) service;
            Log.d("onServiceConnected", "Service与Activity已连接");
            myBinder.setData(list);
            myBinder.playMusic();
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
        myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter("com.minminaya.MY_BROADCAST");
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
            Log.e("音乐title", "音乐:" + i + ":" + list.get(i).getSongTitle() + "," + "id:" + list.get(i).getSongId() + "长度：" + list.get(i).getSongDuration() + "歌曲位置：" + list.get(i).getSongPath());
        }
        Log.e("music数据", "" + list.size());
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
     *  移除runnable，
     * 我们的handler发送是定时1000s发送的，如果不关闭，MediaPlayer release掉了还在获取getCurrentPosition就会爆IllegalStateException错误
     * */
    public void disattachHandler(){
        mHandler.removeCallbacks(runnable);
    }
    /**
     *  选取指定歌曲
     *
     * */
    public void seekSong(int position){
        myBinder.seekSong(position);
    }
    public class MyBroadcastReceiver extends BroadcastReceiver {

        public MyBroadcastReceiver() {
        }


        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("MyBroadcastReceiver", "接受到了哦");
            Toast.makeText(App.getINSTANCE(), "接受到了哦", Toast.LENGTH_SHORT).show();
            int i = intent.getIntExtra("paly_or_pause",100);
            if(i == 0){
                myBinder.seekSong(0);
            }else if(i == 1){
                myBinder.seekSong(1);
            }
        }
    }

}
