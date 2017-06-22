package com.minminaya.bogemusic.mine.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import com.minminaya.bogemusic.C;
import com.minminaya.bogemusic.R;
import com.minminaya.bogemusic.base.BaseActivity;
import com.minminaya.bogemusic.mine.fragment.ListFragment;
import com.minminaya.bogemusic.mine.presenter.ListBaseActivityPresenter;
import com.minminaya.bogemusic.mvp.view.MvpView;
import com.minminaya.bogemusic.play.activity.MusicPlayActivity;
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
    private boolean isBtnPlay = false;
    ListBaseActivityPresenter mListBaseActivityPresenter = null;

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


    @OnClick({R.id.btn_setting, R.id.btn_search, R.id.mini_album, R.id.mini_favorite, R.id.song_name, R.id.artist_name, R.id.mini_play})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_setting:
                onBackPressed();
                break;
            case R.id.btn_search:
                break;
            case R.id.mini_album:
                Intent intent = new Intent(this, MusicPlayActivity.class);
                startActivity(intent);
                break;
            case R.id.mini_favorite:
                break;
            case R.id.song_name:
                break;
            case R.id.artist_name:
                break;
            case R.id.mini_play:
                //动态切换按钮图案
                if(isBtnPlay){
                    Drawable btnPauseIcon = getResources().getDrawable(R.drawable.icon_play_pause);
                    miniPlay.setBackground(btnPauseIcon);
                    isBtnPlay = false;
                }else {
                    Drawable btnPlayIcon = getResources().getDrawable(R.drawable.icon_play_start);
                    miniPlay.setBackground(btnPlayIcon);
                    isBtnPlay = true;
                }
                Intent intent2 = new Intent(C.InstantForReceiver.ACTION);
                intent2.putExtra(C.InstantForReceiver.MUSIC_PLAY_KEY, C.InstantForReceiver.PLAY_OR_PAUSE);
                sendBroadcast(intent2);
                break;
        }
    }

    @Override
    public void onFailed(Throwable e) {

    }

}
