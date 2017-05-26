package com.minminaya.bogemusic.finder.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minminaya.bogemusic.App;
import com.minminaya.bogemusic.R;
import com.minminaya.bogemusic.base.BaseFragment;
import com.minminaya.bogemusic.finder.adapter.IndicatorAdapter;
import com.shizhefei.view.indicator.BannerComponent;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.slidebar.DrawableBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Niwa on 2017/5/26.
 */

public class FinderFragment extends BaseFragment {


    @Bind(R.id.banner_indicator)
    FixedIndicatorView bannerIndicator;
    @Bind(R.id.banner_viewpager)
    ViewPager bannerViewpager;



    public static FinderFragment newInstance() {

        Bundle args = new Bundle();

        FinderFragment fragment = new FinderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void iniView(View view) {
        bannerIndicator.setScrollBar(new DrawableBar(App.getINSTANCE(), R.drawable.dot, ScrollBar.Gravity.CENTENT_BACKGROUND));
//        bannerIndicator.setScrollBar(new ColorBar(App.getINSTANCE(), Color.RED,0, ScrollBar.Gravity.CENTENT_BACKGROUND));
        bannerViewpager.setOffscreenPageLimit(5);
        BannerComponent bannerComponent = new BannerComponent(bannerIndicator, bannerViewpager, false);
        int[] images = getBannerImages();
        IndicatorAdapter indicatorAdapter = new IndicatorAdapter(images);
        bannerComponent.setAdapter(indicatorAdapter);
        bannerComponent.startAutoPlay();
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
        return R.layout.fragment_finder;
    }

    @Override
    protected void unBind() {

    }

    private int[] getBannerImages() {
        // TODO: 2017/5/26 这里获取banner条数据，通过网络
        int[] images = {R.drawable.bg_finder_scroll_view, R.drawable.bg_finder_scroll_view, R.drawable.bg_finder_scroll_view, R.drawable.bg_finder_scroll_view, R.drawable.bg_finder_scroll_view};
        return images;
    }

}
