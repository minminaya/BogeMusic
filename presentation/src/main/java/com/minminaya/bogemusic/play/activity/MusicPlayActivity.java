package com.minminaya.bogemusic.play.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.minminaya.bogemusic.mine.fragment.ListFragment;
import com.minminaya.bogemusic.mvp.view.MvpView;
import com.minminaya.bogemusic.play.fragment.AlbumFragment;
import com.minminaya.bogemusic.play.fragment.LrcFragment;
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
    /**按钮是否是play*/
    private boolean isBtnPlay = false;
    FragmentManager fragmentManager;
    /**当前是否第一次发送更新数据的广播*/
    boolean isFirstSendBroadCast = false;
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
                    sendBroadCastDataForLrcFragment(progress, true);
                }

                sendBroadCastDataForLrcFragment(progress, fromUser);

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

    /**
     * 给fragment发送俩个数据
     */
    private void sendBroadCastDataForLrcFragment(int progress, boolean fromUser) {
        Intent intent1 = new Intent(C.InstantForBroadcastReceiverForMusicPlaySeekBar.ACTION);
        intent1.putExtra(C.InstantForBroadcastReceiverForMusicPlaySeekBar.LRC_VIEW_KEY_1, C.InstantForBroadcastReceiverForMusicPlaySeekBar.LRC_VIEW_FLAG);
        //下面是数据
        intent1.putExtra(C.InstantForBroadcastReceiverForMusicPlaySeekBar.LRC_VIEW_PLAY_CURRENT_POSITION, progress);
        intent1.putExtra(C.InstantForBroadcastReceiverForMusicPlaySeekBar.LRC_VIEW_PLAY_ISFROMUSER, fromUser);
        App.getINSTANCE().sendBroadcast(intent1);


    }

    @Override
    public void initView(Bundle savedInstanceState) {
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.frameLayout, AlbumFragment.newInstance())
                .commit();
        musicPlayActivityPresenter = new MusicPlayActivityPresenter();
        musicPlayActivityPresenter.attachView(this);


        broadcastReceiverForMusicServiceData = new BroadcastReceiverForMusicServiceData();
        IntentFilter intentFilter = new IntentFilter(C.InstantForBroadcastReceiverForMusicServiceData.ACTION);
        registerReceiver(broadcastReceiverForMusicServiceData, intentFilter);
        Log.e("MusicPlayActivity", "广播注册成功");

        seekbar.setMax(10000);
        seekbar.setProgress(0);
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
                onBackPressed();
                break;
            case R.id.btn_song_title:
                break;
            case R.id.activity_actist_title:
                break;
            case R.id.btn_search:
                break;
            case R.id.frameLayout:
                fragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, LrcFragment.newInstance())
                        .commit();
//                switchContent(AlbumFragment.newInstance(), LrcFragment.newInstance());
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

                //动态切换按钮图案
                if(isBtnPlay){
                    Drawable btnPauseIcon = getResources().getDrawable(R.drawable.icon_play_pause);
                    btnPlayOrPause.setBackground(btnPauseIcon);
                    isBtnPlay = false;
                }else {
                    Drawable btnPlayIcon = getResources().getDrawable(R.drawable.icon_play_start);
                    btnPlayOrPause.setBackground(btnPlayIcon);
                    isBtnPlay = true;
                }
                AlbumFragment.newInstance().StartOrPauseAnim();
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
        private boolean isFirstRecevered = true;

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
                    if(isFirstRecevered){
                        //用于标记是否是第一次收到进度数据
                        isFirstRecevered = false;
                        isFirstSendBroadCast = true;
                    }
                    if(isFirstSendBroadCast){
                        Intent intent2 = new Intent(C.InstantForReceiver.ACTION);
                        intent2.putExtra(C.InstantForReceiver.MUSIC_PLAY_KEY, C.InstantForReceiver.UPDATA_SONG_DATA_FLAG);
                        sendBroadcast(intent2);
                        isFirstSendBroadCast = false;
                    }
                    tvCurrentBar.setText(time.format(o));

                    if (!isTouchSeekBar) {
                        //如果没有按下seekBar，那么seekbar进度条一直更新
                        seekbar.setProgress(o);
                    }
                    break;
            }
        }
    }

    private Fragment mContent;

    public void switchContent(Fragment from, Fragment to) {
        if (mContent != to) {
            mContent = to;
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                transaction.hide(from).add(R.id.frameLayout, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }

    /**
     * 转换fragment
     **/
    public void switchFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frameLayout, AlbumFragment.newInstance())
                .commit();
    }
}

