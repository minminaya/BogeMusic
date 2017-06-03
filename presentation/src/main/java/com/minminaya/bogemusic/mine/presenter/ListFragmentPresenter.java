package com.minminaya.bogemusic.mine.presenter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Environment;
import android.os.IBinder;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

import com.minminaya.bogemusic.App;
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

    private List<LocalMusicModel> list;
    private MediaService.MyBinder myBinder;
    private Intent mediaServiceIntent;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MediaService.MyBinder) service;
            Log.d("onServiceConnected", "Service与Activity已连接");
            myBinder.setData(list);
            myBinder.playMusic();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    /**
     *  初始化Service
     *
     * */
    public void initService() {
        mediaServiceIntent = new Intent(App.getINSTANCE(), MediaService.class);
        App.getINSTANCE().bindService(mediaServiceIntent, mServiceConnection, Context.BIND_AUTO_CREATE);

    }

    /**
     * 获取内容提供器提供的歌曲和将歌曲对象写入内存卡文件中
     *
     *
     */
    public void getSongListAndSaveSongList() {
        list = LocalMusicUtil.iniMediaPlayerContentUri();
        //list对象作为文件写入存储卡中
        DataSetUtil.writeObject(list, Environment.getExternalStorageDirectory().getAbsolutePath(), "songList");
        for (int i = 0; i < list.size(); i++) {
            Log.e("音乐title", "音乐:" + i + ":" + list.get(i).getSongTitle() + "," + "id:" + list.get(i).getSongId() + "长度：" + list.get(i).getSongDuration() + "歌曲位置：" + list.get(i).getSongPath());
        }
        Log.e("music数据", "" + list.size());
    }

    /**
     *  关闭Media和停止播放服务
     *
     * */
    public void closeMediaAndStopService() {
        myBinder.closeMedia();
        App.getINSTANCE().unbindService(mServiceConnection);
        Toast.makeText(getMvpView().getContext(), "播放服务已停止", Toast.LENGTH_SHORT).show();
    }

}
