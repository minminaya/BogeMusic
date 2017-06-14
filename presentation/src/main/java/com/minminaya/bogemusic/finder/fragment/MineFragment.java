package com.minminaya.bogemusic.finder.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.minminaya.bogemusic.App;
import com.minminaya.bogemusic.R;
import com.minminaya.bogemusic.base.BaseFragment;
import com.minminaya.bogemusic.mine.activity.ListBaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Niwa on 2017/5/26.
 */

public class MineFragment extends BaseFragment {


    @Bind(R.id.text_last_play)
    LinearLayout textLastPlay;
    @Bind(R.id.text_my_favorite)
    LinearLayout textMyFavorite;
    @Bind(R.id.text_local_music)
    LinearLayout textLocalMusic;

    public static MineFragment newInstance() {

        Bundle args = new Bundle();

        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void iniView(View view) {

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
        return R.layout.fragment_mine;
    }

    @Override
    protected void unBind() {

    }

    @OnClick({R.id.text_local_music, R.id.text_last_play, R.id.text_my_favorite})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_local_music:
                Intent i = new Intent(App.getINSTANCE(), ListBaseActivity.class);
                startActivity(i);
                break;
            case R.id.text_last_play:
                break;
            case R.id.text_my_favorite:
                break;
        }
    }



}
