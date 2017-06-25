package com.minminaya.bogemusic.finder.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.minminaya.bogemusic.App;
import com.minminaya.bogemusic.finder.activity.WebSongListActivity;
import com.minminaya.data.api.ApiMethodString;
import com.minminaya.data.http.NetWork;
import com.minminaya.data.model.apimodel.MusicTopModel;
import com.shizhefei.view.indicator.IndicatorViewPager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Niwa on 2017/5/26.
 */

public class IndicatorAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {

    private int[] images;

    public IndicatorAdapter(int[] images) {
        this.images = images;
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
                /** 排行榜代表的值*/
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
                Toast.makeText(App.getINSTANCE(), "当前view是：" + position + ",tpye:" + tpye, Toast.LENGTH_SHORT).show();
                loadTopInfo(tpye);//加载数据
                Intent intent = new Intent(App.getINSTANCE(), WebSongListActivity.class);
                App.getINSTANCE().startActivity(intent);
            }
        });
        return convertView;
    }
    /**
     *  获取指定排行榜信息
     *  @param topType 指定排行榜的tpye
     * */
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
            Log.e("IndicatorAdapter", "" + value.getBillboard().getComment());
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
