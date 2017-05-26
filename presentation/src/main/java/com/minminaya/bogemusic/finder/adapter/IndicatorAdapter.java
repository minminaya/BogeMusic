package com.minminaya.bogemusic.finder.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.minminaya.bogemusic.App;
import com.shizhefei.view.indicator.IndicatorViewPager;

/**
 * Created by Niwa on 2017/5/26.
 */

public class IndicatorAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter{

    private int[] images ;

    public IndicatorAdapter(int[] images) {
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        if(convertView == null){
            convertView = new View(container.getContext());
        }
        return convertView;
    }

    @Override
    public View getViewForPage(int position, View convertView, ViewGroup container) {
        if(convertView == null){
            convertView = new ImageView(App.getINSTANCE());
            convertView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        ImageView imageView = (ImageView) convertView;
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(images[position]);
        return convertView;
    }
}
