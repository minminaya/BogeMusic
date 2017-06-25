package com.minminaya.bogemusic.finder.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.minminaya.bogemusic.R;
import com.minminaya.bogemusic.base.BaseActivity;
import com.minminaya.bogemusic.finder.fragment.TopSongListFragment;

/**
 * Created by Niwa on 2017/6/25.
 */

public class WebSongListActivity extends BaseActivity {


    FragmentManager fragmentManager;
    @Override
    public void unBind() {

    }

    @Override
    public void bind() {

    }

    @Override
    public void setListeners() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.content, TopSongListFragment.newInstance())
                .commit();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_websonglist;
    }
}
