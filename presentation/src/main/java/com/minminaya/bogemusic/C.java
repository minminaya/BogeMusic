package com.minminaya.bogemusic;

/** 静态常量类
 * Created by Niwa on 2017/5/26.
 */

public class C {


    public static final class InstantForReceiver{
        public static final String ACTION = "com.minminaya.MY_BROADCAST";
        public static final String MUSIC_PLAY_KEY = "music_play_key";
        public static final String UPDATA_SONG_POSITION = "update_song_position";

        public static final int PLAY_OR_PAUSE = 0;
        public static final int BUTTON_NEXT = 1;
        public static final int BUTTON_LAST = 2;
        public static final int UPDATA_SONG_POSITION_FlAG = 110001;

    }

    public static final class InstantForBroadcastReceiverForMusicServiceData{
        public static final String ACTION = "com.minminaya.MY_BROADCAST_B";
        public static final String MUSIC_PLAY_KEY_2 = "music_play_key_2";

        public static final String CURRENT_SONG_TITLE = "current_song_title";
        public static final String CURRENT_SONG_ARTIST_TITLE = "current_song_artist_title";

        public static final String TOTAL_SONG_POSITION = "total_song_position";
        public static final int TOTAL_LONG_FLAG = 0;


        public static final String CURRENT_SONG_POSITION = "current_song_position";
        public static final int CURRENT_SONG_POSITION_FLAG = 3;
    }

}
