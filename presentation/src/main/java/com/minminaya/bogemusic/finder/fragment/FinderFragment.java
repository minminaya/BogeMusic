package com.minminaya.bogemusic.finder.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.minminaya.bogemusic.App;
import com.minminaya.bogemusic.R;
import com.minminaya.bogemusic.base.BaseFragment;
import com.minminaya.bogemusic.finder.adapter.FinderRecycleViewAdapter;
import com.minminaya.bogemusic.finder.adapter.IndicatorAdapter;
import com.minminaya.data.api.ApiMethodString;
import com.minminaya.data.http.NetWork;
import com.minminaya.data.model.apimodel.MusicTopModel;
import com.minminaya.data.model.apimodel.SongList;
import com.shizhefei.view.indicator.BannerComponent;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.slidebar.LayoutBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Niwa on 2017/5/26.
 */

public class FinderFragment extends BaseFragment {


    @Bind(R.id.banner_indicator)
    FixedIndicatorView bannerIndicator;
    @Bind(R.id.banner_viewpager)
    ViewPager bannerViewpager;
    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.top)
    ImageView top;
    @Bind(R.id.song_list)
    ImageView songListIV;
    @Bind(R.id.recommend)
    ImageView recommend;
    @Bind(R.id.finder_recycle_view)
    RecyclerView finderRecycleView;

    private String newMusicTopPicAddress;
    private String hotMusicTopPicAddress;
    private String webMusicTopPicAddress;
    private String popularMusicTopPicAddress;
    private String westMusicTopPicAddress;


    private List<SongList> songList = new ArrayList<>();

    FinderRecycleViewAdapter mFinderRecycleViewAdapter;

    public static FinderFragment newInstance() {

        Bundle args = new Bundle();

        FinderFragment fragment = new FinderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    Observer<MusicTopModel> observer = new Observer<MusicTopModel>() {
        @Override
        public void onSubscribe(Disposable d) {
            Log.e("observer", "onSubscribe");

        }

        @Override
        public void onNext(MusicTopModel value) {
            songList = value.getSongList();
            Log.e("observer", "onNext" + value.getSongList().get(0).getTitle());
            Log.e("observer", "onNext2" + songList.get(0).getTitle());
            mFinderRecycleViewAdapter.setSongList(songList);
            mFinderRecycleViewAdapter.notifyDataSetChanged();
            finderRecycleView.setVisibility(View.VISIBLE);
        }

        @Override
        public void onError(Throwable e) {
            Log.e("observer", "onError");
            e.printStackTrace();

        }

        @Override
        public void onComplete() {

        }
    };


    @Override
    public void iniView(View view) {
        bannerIndicator.setScrollBar(new LayoutBar(App.getINSTANCE(), R.layout.banner_bar));
        bannerViewpager.setOffscreenPageLimit(5);
        BannerComponent bannerComponent = new BannerComponent(bannerIndicator, bannerViewpager, false);
        int[] images = getBannerImages();
        IndicatorAdapter indicatorAdapter = new IndicatorAdapter(images);
        bannerComponent.setAdapter(indicatorAdapter);
        bannerComponent.setAutoPlayTime(4000);
        bannerComponent.startAutoPlay();

        finderRecycleView.setLayoutManager(new GridLayoutManager(App.getINSTANCE(), 3));

        //请求数据
        refreshBannerData();
        mFinderRecycleViewAdapter = new FinderRecycleViewAdapter();
        finderRecycleView.setFocusable(false);//将显示焦点取消
        finderRecycleView.setAdapter(mFinderRecycleViewAdapter);
    }

    @Override
    public void bind() {
//        refreshBannerData();
    }

    @Override
    public void lazyLoad() {

    }

    @Override
    public void setListeners() {

    }

    @Override
    public int setContView() {
        return R.layout.fragment_finder;
    }

    @Override
    protected void unBind() {

    }

    private int[] getBannerImages() {
        int[] images = {R.drawable.img_hot_top, R.drawable.img_new_top, R.drawable.img_west_top, R.drawable.img_web_top, R.drawable.img_billboard};
        return images;
    }


    public void refreshBannerData() {
        NetWork.getMusicApi()
                .loadMusicTopContentAndInfo(ApiMethodString.MUSIC_TOP_FLAG, 1, 21, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
