package com.minminaya.bogemusic.play.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minminaya.bogemusic.App;
import com.minminaya.bogemusic.C;
import com.minminaya.bogemusic.R;
import com.minminaya.bogemusic.base.BaseFragment;
import com.minminaya.bogemusic.play.activity.MusicPlayActivity;
import com.minminaya.bogemusic.play.view.LrcView;
import com.minminaya.data.model.LrcRow;
import com.minminaya.domain.lrcparser.DefaultLrcParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LrcFragment extends BaseFragment {


    @Bind(R.id.lrcview)
    LrcView lrcview;

    private BroadcastReceiverForMusicPlaySeekBar broadcastReceiverForMusicPlaySeekBar;

    public static LrcFragment newInstance() {

        Bundle args = new Bundle();

        LrcFragment fragment = new LrcFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public LrcFragment() {
        // Required empty public constructor
    }


    @Override
    public void iniView(View view) {


        broadcastReceiverForMusicPlaySeekBar = new BroadcastReceiverForMusicPlaySeekBar();
        IntentFilter intentFilter = new IntentFilter(C.InstantForBroadcastReceiverForMusicPlaySeekBar.ACTION);
        App.getINSTANCE().registerReceiver(broadcastReceiverForMusicPlaySeekBar, intentFilter);

        lrcview.setLrcScalingFactor(1.6f);
        lrcview.setLrcRows(getLrcRows());

    }

    @Override
    public void bind() {

    }

    @Override
    public void lazyLoad() {

    }

    @Override
    public void setListeners() {
        final MusicPlayActivity musicPlayActivity = (MusicPlayActivity) getActivity();
        lrcview.setOnLrcClickListener(new LrcView.OnLrcClickListener() {
            @Override
            public void onClick() {
                musicPlayActivity.switchFragment();
            }
        });
        lrcview.setOnSeekToListener(new LrcView.OnSeekToListener() {
            @Override
            public void onSeekTo(int progress) {
                sendBroadcastForUpdateSongPosition(progress);
            }
        });

    }


    @Override
    public int setContView() {
        return R.layout.fragment_lrc;
    }

    @Override
    protected void unBind() {
        App.getINSTANCE().unregisterReceiver(broadcastReceiverForMusicPlaySeekBar);
    }

    /**
     * 获取歌词List集合，歌词路径，只要设置在这里面
     *
     * @return
     */
    private List<LrcRow> getLrcRows() {
        List<LrcRow> rows = null;
        // TODO: 2017/6/21 动态设置歌词
        InputStream is = getResources().openRawResource(R.raw.hs);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuffer sb = new StringBuffer();
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            System.out.println(sb.toString());
            rows = DefaultLrcParser.getIstance().getLrcRows(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
    }

    private int mCurrentProgress;
    private boolean isFromUser;


    /**
     * 用于接受来自别的线程的数据的广播接收器
     */
    public class BroadcastReceiverForMusicPlaySeekBar extends BroadcastReceiver {
        public BroadcastReceiverForMusicPlaySeekBar() {

        }

        @Override
        public void onReceive(Context context, Intent intent) {
            int i2 = intent.getIntExtra(C.InstantForBroadcastReceiverForMusicPlaySeekBar.LRC_VIEW_KEY_1, 100);
            switch (i2) {
                //接受来自seekbar回调的progress与fromUser值
                case C.InstantForBroadcastReceiverForMusicPlaySeekBar.LRC_VIEW_FLAG:
                    mCurrentProgress = intent.getIntExtra(C.InstantForBroadcastReceiverForMusicPlaySeekBar.LRC_VIEW_PLAY_CURRENT_POSITION, 100);
                    isFromUser = intent.getBooleanExtra(C.InstantForBroadcastReceiverForMusicPlaySeekBar.LRC_VIEW_PLAY_ISFROMUSER, false);
                    Log.e("BroadcastReceiverFo", "" + mCurrentProgress + "，" + isFromUser);
                    lrcview.seekTo(mCurrentProgress, true, isFromUser);
                    break;
                case C.InstantForBroadcastReceiverForMusicPlaySeekBar.LRC_VIEW_FLAG_2:
                    boolean isPlayCompletion = intent.getBooleanExtra(C.InstantForBroadcastReceiverForMusicPlaySeekBar.LRC_VIEW_PLAY_IS_PLAY_COMPLETION, true);
                    if (isPlayCompletion) {
                        lrcview.reset();
                        // TODO: 2017/6/21 重新设置歌词
                        lrcview.setLrcRows(getLrcRows());
                        //收到播放完成的广播
                        Log.d("MusicPlaySeekBar", "收到播放完成的广播");
                    }
                    break;
            }
        }
    }

    /**
     * 发到musicService用于更新播放位置
     * @param progress 最新的播放位置
     */
    public void sendBroadcastForUpdateSongPosition(int progress) {
        Intent intent = new Intent(C.InstantForReceiver.ACTION);
        intent.putExtra(C.InstantForReceiver.MUSIC_PLAY_KEY, C.InstantForReceiver.UPDATA_SONG_POSITION_FlAG);
        intent.putExtra(C.InstantForReceiver.UPDATA_SONG_POSITION, progress);
        App.getINSTANCE().sendBroadcast(intent);
    }
}
