package com.minminaya.bogemusic.mine.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.minminaya.bogemusic.App;
import com.minminaya.bogemusic.R;
import com.minminaya.bogemusic.base.BaseFragment;
import com.minminaya.bogemusic.mine.adapter.ListFragmentItemAdapter;
import com.minminaya.bogemusic.mvp.view.MvpView;
import com.minminaya.bogemusic.play.service.MediaService;
import com.minminaya.bogemusic.utils.DataSetUtil;
import com.minminaya.bogemusic.utils.localmusic.LocalMusicUtil;
import com.minminaya.data.model.LocalMusicModel;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Niwa on 2017/5/28.
 */

public class ListFragment extends BaseFragment implements MvpView {

    @Bind(R.id.recycle_view)
    XRecyclerView recycleView;
    List<LocalMusicModel> list;

    public static ListFragment newInstance() {

        Bundle args = new Bundle();

        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void iniView(View view) {
        LocalMusicUtil.refreshcontentResolverData();
        list = LocalMusicUtil.iniMediaPlayerContentUri();
        //// TODO: 2017/6/2 测试写对象类 
        DataSetUtil.writeObject(list, Environment.getExternalStorageDirectory().getAbsolutePath(), "songList");
        for (int i = 0; i < list.size(); i++) {
            Log.e("音乐title", "音乐:" + i + ":" + list.get(i).getSongTitle() + "," + "id:" + list.get(i).getSongId() + "长度：" + list.get(i).getSongDuration() + "歌曲位置：" + list.get(i).getSongPath());
        }
        Log.e("music数据", "" + list.size());



        mediaServiceIntent = new Intent(App.getINSTANCE(), MediaService.class);

        App.getINSTANCE().bindService(mediaServiceIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
//        myBinder.setData(list);
//        myBinder.playMusic();



        ListFragmentItemAdapter listFragmentItemAdapter = new ListFragmentItemAdapter();
        recycleView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recycleView.setLayoutManager(new LinearLayoutManager(App.getINSTANCE()));
        recycleView.setAdapter(listFragmentItemAdapter);
    }

    @Override
    public void bind() {

    }

    @Override
    public void lazyLoad() {

    }

    @Override
    public void setListeners() {

    }

    @Override
    public int setContView() {
        return R.layout.fragment_list;
    }

    @Override
    protected void unBind() {
        myBinder.closeMedia();
        App.getINSTANCE().unbindService(mServiceConnection);
    }

    @Override
    public void onFailed(Throwable e) {

    }


    private MediaService.MyBinder myBinder;
    Intent mediaServiceIntent;
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


}
