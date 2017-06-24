package com.minminaya.bogemusic.finder.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.minminaya.bogemusic.App;
import com.minminaya.bogemusic.R;
import com.minminaya.bogemusic.base.BaseFragment;
import com.minminaya.bogemusic.finder.adapter.IndicatorAdapter;
import com.minminaya.data.http.NetWork;
import com.minminaya.data.model.apimodel.MusicTopModel;
import com.shizhefei.view.indicator.BannerComponent;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.slidebar.DrawableBar;
import com.shizhefei.view.indicator.slidebar.LayoutBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;

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

    private String newMusicTopPicAddress;
    private String hotMusicTopPicAddress;
    private String webMusicTopPicAddress;
    private String popularMusicTopPicAddress;
    private String westMusicTopPicAddress;


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
           newMusicTopPicAddress = value.getBillboard().getPicS640();
            Log.e("observer", "onNext" + value.getBillboard().getComment());
        }

        @Override
        public void onError(Throwable e) {
            Log.e("observer", "onError");

        }

        @Override
        public void onComplete() {

        }
    };



    @Override
    public void iniView(View view) {
        bannerIndicator.setScrollBar(new LayoutBar(App.getINSTANCE(), R.layout.banner_bar));
//        bannerIndicator.setScrollBar(new ColorBar(App.getINSTANCE(), Color.RED,0, ScrollBar.Gravity.CENTENT_BACKGROUND));
        bannerViewpager.setOffscreenPageLimit(5);
        BannerComponent bannerComponent = new BannerComponent(bannerIndicator, bannerViewpager, false);
        int[] images = getBannerImages();
        IndicatorAdapter indicatorAdapter = new IndicatorAdapter(images);
        bannerComponent.setAdapter(indicatorAdapter);
        bannerComponent.setAutoPlayTime(4000);
        bannerComponent.startAutoPlay();
    }

    @Override
    public void bind() {
        refreshBannerData();
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
        // TODO: 2017/5/26 这里获取banner条数据，通过网络
        int[] images = {R.drawable.img_hot_top, R.drawable.img_new_top, R.drawable.img_west_top, R.drawable.img_web_top, R.drawable.img_billboard};
        return images;
    }


    public void refreshBannerData() {
        NetWork.getMusicApi()
                .loadMusicTopContentAndInfo("baidu.ting.billboard.billList", 1, 10, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
