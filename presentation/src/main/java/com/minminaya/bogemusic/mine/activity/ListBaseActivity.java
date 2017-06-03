package com.minminaya.bogemusic.mine.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;
import com.minminaya.bogemusic.App;
import com.minminaya.bogemusic.R;
import com.minminaya.bogemusic.base.BaseActivity;
import com.minminaya.bogemusic.mine.fragment.ListFragment;
import com.minminaya.bogemusic.mine.presenter.ListBaseActivityPresenter;
import com.minminaya.bogemusic.mvp.view.MvpView;
import com.minminaya.bogemusic.utils.localmusic.LocalMusicUtil;

import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Niwa on 2017/5/27.
 */

public class ListBaseActivity extends BaseActivity implements MvpView {
    @Bind(R.id.btn_setting)
    Button btnSetting;
    @Bind(R.id.btn_search)
    Button btnSearch;
    @Bind(R.id.title_bar)
    RelativeLayout titleBar;
    @Bind(R.id.content)
    FrameLayout content;
    @Bind(R.id.mini_album)
    ImageView miniAlbum;
    @Bind(R.id.mini_play)
    CircleImageView miniPlay;
//    @Bind(R.id.play_circle_progress)
//    DonutProgress playCircleProgress;
    @Bind(R.id.mini_favorite)
    ImageView miniFavorite;
    @Bind(R.id.song_name)
    TextView songName;
    @Bind(R.id.artist_name)
    TextView artistName;

    ListBaseActivityPresenter mListBaseActivityPresenter  = null;
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
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.content, ListFragment.newInstance())
                .commit();

        mListBaseActivityPresenter = new ListBaseActivityPresenter();
        mListBaseActivityPresenter.attachView(this);
}

    @Override
    public int getContentView() {
        return R.layout.layout_list_base_activity;
    }


    @OnClick({R.id.btn_setting, R.id.btn_search, R.id.mini_album, R.id.mini_favorite, R.id.song_name, R.id.artist_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_setting:
                break;
            case R.id.btn_search:
                break;
            case R.id.mini_album:
                break;
            case R.id.mini_favorite:
                break;
            case R.id.song_name:
                break;
            case R.id.artist_name:
                break;
        }
    }

    @Override
    public void onFailed(Throwable e) {

    }

}
