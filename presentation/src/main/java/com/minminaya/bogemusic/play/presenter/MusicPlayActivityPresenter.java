package com.minminaya.bogemusic.play.presenter;

import android.content.Intent;

import com.minminaya.bogemusic.C;
import com.minminaya.bogemusic.mvp.presenter.base.BasePresenter;
import com.minminaya.bogemusic.play.activity.MusicPlayActivity;

/**
 * Created by Niwa on 2017/6/14.
 */

public class MusicPlayActivityPresenter extends BasePresenter<MusicPlayActivity> {

        public void sendBroadcast(int progress){
            Intent intent = new Intent(C.InstantForReceiver.ACTION);
            intent.putExtra(C.InstantForReceiver.MUSIC_PLAY_KEY, C.InstantForReceiver.UPDATA_SONG_POSITION_FlAG);
            intent.putExtra(C.InstantForReceiver.UPDATA_SONG_POSITION, progress);
            getMvpView().sendBroadcast(intent);
        }


}
