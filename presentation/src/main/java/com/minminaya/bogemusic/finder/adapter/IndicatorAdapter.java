package com.minminaya.bogemusic.finder.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.minminaya.bogemusic.App;
import com.minminaya.bogemusic.C;
import com.minminaya.bogemusic.finder.activity.WebSongListActivity;
import com.shizhefei.view.indicator.IndicatorViewPager;


/** 轮播banner的adapter
 * Created by Niwa on 2017/5/26.
 */

public class IndicatorAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {

    private int[] images;
    private Context mContext;
    public IndicatorAdapter(int[] images, Context context) {
        this.images = images;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = new View(container.getContext());
        }
        return convertView;
    }

    @Override
    public View getViewForPage(final int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = new ImageView(App.getINSTANCE());
            convertView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        ImageView imageView = (ImageView) convertView;
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(images[position]);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** 排行榜代表的tpye值*/
                int tpye = 0;
                switch (position) {
                    case 0:
                        tpye = 2;
                        break;
                    case 1:
                        tpye = 1;
                        break;
                    case 2:
                        tpye = 21;
                        break;
                    case 3:
                        tpye = 25;
                        break;
                    case 4:
                        tpye = 8;
                        break;
                }
                Intent intent = new Intent(mContext, WebSongListActivity.class);

                Bundle bundle = new Bundle();
                bundle.putInt("tpye", tpye);
                bundle.putInt("source", C.GO_TO_TOP_SONG_LIST_FRAGMENT);
                intent.putExtras(bundle);
                mContext.startActivity(intent);

            }
        });
        return convertView;
    }
}
