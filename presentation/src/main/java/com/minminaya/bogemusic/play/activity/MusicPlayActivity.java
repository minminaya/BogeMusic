package com.minminaya.bogemusic.play.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.minminaya.bogemusic.App;
import com.minminaya.bogemusic.C;
import com.minminaya.bogemusic.R;
import com.minminaya.bogemusic.base.BaseActivity;
import com.minminaya.bogemusic.mvp.view.MvpView;
import com.minminaya.bogemusic.play.presenter.MusicPlayActivityPresenter;

import java.text.SimpleDateFormat;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Niwa on 2017/6/9.
 */

public class MusicPlayActivity extends BaseActivity implements MvpView {


    @Bind(R.id.btn_exit)
    Button btnExit;
    @Bind(R.id.btn_song_title)
    TextView tvSongTitle;
    @Bind(R.id.activity_actist_title)
    TextView tvArtistTitle;
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

    MusicPlayActivityPresenter musicPlayActivityPresenter;
    private Handler mHandler = new Handler();
    //进度条下面的当前进度文字，将毫秒化为m:ss格式
    private SimpleDateFormat time = new SimpleDateFormat("m:ss");
    /**
     * 是否按下seekbar
     */
    private boolean isTouchSeekBar = false;
    /**
     * 歌曲总长
     */
    private int songTotalPosition;

    private BroadcastReceiverForMusicServiceData broadcastReceiverForMusicServiceData;

    @Override
    public void unBind() {
        musicPlayActivityPresenter.detachView(this);

        unregisterReceiver(broadcastReceiverForMusicServiceData);
    }

    @Override
    public void bind() {

    }

    @Override
    public void setListeners() {


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int caculatedBar;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    caculatedBar = progress;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isTouchSeekBar = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                musicPlayActivityPresenter.sendBroadcast(caculatedBar);
                isTouchSeekBar = false;
            }
        });
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        musicPlayActivityPresenter = new MusicPlayActivityPresenter();
        musicPlayActivityPresenter.attachView(this);


        broadcastReceiverForMusicServiceData = new BroadcastReceiverForMusicServiceData();
        IntentFilter intentFilter = new IntentFilter(C.InstantForBroadcastReceiverForMusicServiceData.ACTION);
        registerReceiver(broadcastReceiverForMusicServiceData, intentFilter);
        Log.e("MusicPlayActivity", "广播注册成功");
    }

    @Override
    public int getContentView() {
        return R.layout.activity_music_player;
    }


    @OnClick({R.id.btn_exit, R.id.btn_song_title, R.id.activity_actist_title, R.id.btn_search, R.id.frameLayout, R.id.tv_current_bar, R.id.tv_song_total_time, R.id.btn_paly_mode, R.id.btn_last, R.id.btn_play_or_pause, R.id.btn_next, R.id.btn_song_info})
    public void onViewClicked(View view) {
        Intent intent = new Intent(C.InstantForReceiver.ACTION);
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
                intent.putExtra(C.InstantForReceiver.MUSIC_PLAY_KEY, C.InstantForReceiver.BUTTON_LAST);
                sendBroadcast(intent);
                break;
            case R.id.btn_play_or_pause:
                intent.putExtra(C.InstantForReceiver.MUSIC_PLAY_KEY, C.InstantForReceiver.PLAY_OR_PAUSE);
                sendBroadcast(intent);
                break;
            case R.id.btn_next:
                intent.putExtra(C.InstantForReceiver.MUSIC_PLAY_KEY, C.InstantForReceiver.BUTTON_NEXT);
                sendBroadcast(intent);
                break;
            case R.id.btn_song_info:
                Intent i = new Intent(C.InstantForBroadcastReceiverForMusicServiceData.ACTION);
                App.getINSTANCE().sendBroadcast(i);
                break;
        }
    }

    @Override
    public void onFailed(Throwable e) {

    }

    public class BroadcastReceiverForMusicServiceData extends BroadcastReceiver {
        public BroadcastReceiverForMusicServiceData() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            //默认值100随便填的
            int i = intent.getIntExtra(C.InstantForBroadcastReceiverForMusicServiceData.MUSIC_PLAY_KEY_2, 100);

            switch (i) {
                case C.InstantForBroadcastReceiverForMusicServiceData.TOTAL_LONG_FLAG:
                    songTotalPosition = intent.getIntExtra(C.InstantForBroadcastReceiverForMusicServiceData.TOTAL_SONG_POSITION, 100);
                    String artist = intent.getStringExtra(C.InstantForBroadcastReceiverForMusicServiceData.CURRENT_SONG_TITLE);
                    String songTitle = intent.getStringExtra(C.InstantForBroadcastReceiverForMusicServiceData.CURRENT_SONG_ARTIST_TITLE);

                    seekbar.setMax(songTotalPosition);
                    tvSongTotalTime.setText(time.format(songTotalPosition));
                    tvSongTitle.setText(songTitle);
                    tvArtistTitle.setText(artist);
                    break;
                case C.InstantForBroadcastReceiverForMusicServiceData.CURRENT_SONG_POSITION_FLAG:
                    int o = intent.getIntExtra(C.InstantForBroadcastReceiverForMusicServiceData.CURRENT_SONG_POSITION, 100);
                    tvCurrentBar.setText(time.format(o));

                    if (!isTouchSeekBar) {
                        //如果没有按下seekBar，那么seekbar进度条一直更新
                        seekbar.setProgress(o);
                    }
                    break;
            }
        }
    }
}

