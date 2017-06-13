package com.minminaya.bogemusic.play.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.minminaya.bogemusic.R;
import com.minminaya.bogemusic.base.BaseActivity;
import com.minminaya.bogemusic.mine.presenter.ListFragmentPresenter;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Niwa on 2017/6/9.
 */

public class MusicPlayActivity extends BaseActivity {


    @Bind(R.id.btn_exit)
    Button btnExit;
    @Bind(R.id.btn_song_title)
    TextView btnSongTitle;
    @Bind(R.id.activity_actist_title)
    TextView activityActistTitle;
    @Bind(R.id.btn_search)
    Button btnSearch;
    @Bind(R.id.title_bar)
    RelativeLayout titleBar;
    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.tv_current_bar)
    TextView tvCurrentBar;
    @Bind(R.id.seekbar)
    SeekBar seekbar;
    @Bind(R.id.tv_song_total_time)
    TextView tvSongTotalTime;
    @Bind(R.id.layout_relative)
    RelativeLayout layoutRelative;
    @Bind(R.id.btn_paly_mode)
    Button btnPalyMode;
    @Bind(R.id.btn_last)
    Button btnLast;
    @Bind(R.id.btn_play_or_pause)
    Button btnPlayOrPause;
    @Bind(R.id.btn_next)
    Button btnNext;
    @Bind(R.id.btn_song_info)
    Button btnSongInfo;

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

    }

    @Override
    public int getContentView() {
        return R.layout.activity_music_player;
    }


    @OnClick({R.id.btn_exit, R.id.btn_song_title, R.id.activity_actist_title, R.id.btn_search, R.id.frameLayout, R.id.tv_current_bar, R.id.tv_song_total_time, R.id.btn_paly_mode, R.id.btn_last, R.id.btn_play_or_pause, R.id.btn_next, R.id.btn_song_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_exit:
                break;
            case R.id.btn_song_title:
                break;
            case R.id.activity_actist_title:
                break;
            case R.id.btn_search:
                break;
            case R.id.frameLayout:
                break;
            case R.id.tv_current_bar:
                break;
            case R.id.tv_song_total_time:
                break;
            case R.id.btn_paly_mode:
                break;
            case R.id.btn_last:
                break;
            case R.id.btn_play_or_pause:
                Log.e("MusicPlayActivity","btn_play_or_pause");
                Intent intent = new Intent("com.minminaya.MY_BROADCAST");
                intent.putExtra("paly_or_pause",0);
                sendBroadcast(intent);
                break;
            case R.id.btn_next:
                Intent intent1 = new Intent("com.minminaya.MY_BROADCAST");
                intent1.putExtra("paly_or_pause",1);
                sendBroadcast(intent1);
                break;
            case R.id.btn_song_info:
                break;
        }
    }
}
