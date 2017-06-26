package com.minminaya.bogemusic.finder.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.minminaya.bogemusic.C;
import com.minminaya.bogemusic.R;
import com.minminaya.bogemusic.base.BaseActivity;
import com.minminaya.bogemusic.finder.fragment.TopSongListFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Niwa on 2017/6/25.
 */

public class WebSongListActivity extends BaseActivity {


    FragmentManager fragmentManager;

    @Bind(R.id.btn_setting)
    Button btnSetting;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.btn_search)
    Button btnSearch;

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
        Bundle bundle = this.getIntent().getExtras();
        int tpye = bundle.getInt("tpye");
        int sources = bundle.getInt("source");
        if(sources == C.GO_TO_TOP_SONG_LIST_FRAGMENT){
            tvTitle.setText("排行榜");
            loadFragment(TopSongListFragment.newInstance(tpye));
        }
        Log.e("WebSongListActivity", "" + tpye);


    }
    /**
     *
     *  加载指定的fragment
     *  @param fragment 指定fragment
     * */
    private void loadFragment(Fragment fragment) {
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.content, fragment)
                .commit();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_websonglist;
    }


    @OnClick({R.id.btn_setting, R.id.btn_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_setting:
                break;
            case R.id.btn_search:
                break;
        }
    }
}
