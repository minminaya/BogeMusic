package com.minminaya.bogemusic.mine.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.minminaya.bogemusic.App;
import com.minminaya.bogemusic.R;
import com.minminaya.bogemusic.base.BaseFragment;
import com.minminaya.bogemusic.mine.adapter.ListFragmentItemAdapter;
import com.minminaya.bogemusic.mine.presenter.ListFragmentPresenter;
import com.minminaya.bogemusic.mvp.view.MvpView;
import com.minminaya.bogemusic.play.service.MediaService;
import com.minminaya.bogemusic.utils.localmusic.LocalMusicUtil;
import com.minminaya.data.model.LocalMusicModel;


import java.util.List;

import butterknife.Bind;

/**
 * 歌曲列表Fragment
 * Created by Niwa on 2017/5/28.
 */

public class ListFragment extends BaseFragment implements MvpView {


    @Bind(R.id.recycle_view)
    XRecyclerView recycleView;
    ListFragmentPresenter mListFragmentPresenter;
    ListFragmentItemAdapter listFragmentItemAdapter;

    private static ListFragment fragment;
    public static ListFragment newInstance() {
        if(fragment == null){
            fragment = new ListFragment();
        }else {
            return fragment;
        }
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void iniView(View view) {
        //刷新内容提供器数据库
        // TODO: 2017/6/14

//        LocalMusicUtil.refreshcontentResolverData();
        mListFragmentPresenter = new ListFragmentPresenter();
        mListFragmentPresenter.attachView(this);
        mListFragmentPresenter.getSongListAndSaveSongList();

        mListFragmentPresenter.initService();

        listFragmentItemAdapter = new ListFragmentItemAdapter();
        mListFragmentPresenter.setSongListToAdapter();
        recycleView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recycleView.setLayoutManager(new LinearLayoutManager(App.getINSTANCE()));
        recycleView.setAdapter(listFragmentItemAdapter);
        listFragmentItemAdapter.setmPresenter(mListFragmentPresenter);

        recycleView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        LocalMusicUtil.refreshcontentResolverData();
                    }
                }).start();
                recycleView.refreshComplete();
            }

            @Override
            public void onLoadMore() {

            }
        });
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
        // TODO: 2017/6/14 暂停播放还没关
//        mListFragmentPresenter.closeMediaAndStopService();
        mListFragmentPresenter.detachView(this);
        mListFragmentPresenter.disattachHandler();
    }

    @Override
    public void onFailed(Throwable e) {

    }

    public void setSongListToAdapter(List<LocalMusicModel> list) {
        listFragmentItemAdapter.setSongList(list);
    }

}
