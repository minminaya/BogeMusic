package com.minminaya.bogemusic.finder.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.minminaya.bogemusic.R;
import com.minminaya.bogemusic.base.BaseActivity;

public class ScrollingActivity extends BaseActivity {


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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_scrolling;
    }
}
