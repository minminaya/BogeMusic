package com.minminaya.bogemusic.finder.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.minminaya.bogemusic.App;
import com.minminaya.bogemusic.R;
import com.minminaya.bogemusic.base.BaseFragment;
import com.minminaya.bogemusic.finder.adapter.WebTopItemRecyclerViewAdapter;
import com.minminaya.data.api.ApiMethodString;
import com.minminaya.data.http.NetWork;
import com.minminaya.data.model.apimodel.MusicTopModel;
import com.minminaya.data.model.apimodel.SongList;

import java.util.List;

import butterknife.Bind;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Niwa on 2017/6/25.
 */

public class TopSongListFragment extends BaseFragment {

    //榜单标记
    private int tpye;

    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.text1)
    TextView tvTopTile;
    @Bind(R.id.tv_topInfo)
    TextView tvTopInfo;
    @Bind(R.id.recycle_view)
    RecyclerView recycleView;
    @Bind(R.id.relativeLayout)
    RelativeLayout relativeLayout;

    private WebTopItemRecyclerViewAdapter webTopItemRecyclerViewAdapter;


    private List<SongList> mSongList;


    public static TopSongListFragment newInstance(int mType) {
        Bundle args = new Bundle();
        args.putInt("tpye", mType);//传递到本fragment
        TopSongListFragment fragment = new TopSongListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void iniView(View view) {
        tpye =getArguments().getInt("tpye");//获取传过来的tpye值

        recycleView.setFocusable(false);
        recycleView.setLayoutManager(new LinearLayoutManager(App.getINSTANCE()));
        webTopItemRecyclerViewAdapter = new WebTopItemRecyclerViewAdapter();
    }

    @Override
    public void bind() {

    }

    @Override
    public void lazyLoad() {

    }

    @Override
    public void setListeners() {

        loadTopInfo(tpye);//加载数据
    }

    @Override
    public int setContView() {
        return R.layout.fragment_top_song_list;
    }

    @Override
    protected void unBind() {

    }

    /**
     * 获取指定排行榜信息
     *
     * @param topType 指定排行榜的tpye
     */
    public void loadTopInfo(int topType) {
        NetWork.getMusicApi()
                .loadMusicTopContentAndInfo(ApiMethodString.MUSIC_TOP_FLAG, topType, 21, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    Observer<MusicTopModel> observer = new Observer<MusicTopModel>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(MusicTopModel value) {
            mSongList = value.getSongList();
            webTopItemRecyclerViewAdapter.setmSongList(mSongList);

            //设置fragment的图片 文字 简介
            Glide.with(App.getINSTANCE()).load(value.getBillboard().getPicS192()).into(imageView);
            tvTopTile.setText(value.getBillboard().getName());
            tvTopInfo.setText(value.getBillboard().getComment());
            relativeLayout.setVisibility(View.VISIBLE);
            recycleView.setAdapter(webTopItemRecyclerViewAdapter);

        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onComplete() {

        }
    };

}
