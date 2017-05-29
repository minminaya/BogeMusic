package com.minminaya.bogemusic.mine.receciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Niwa on 2017/5/29.
 */
public class ScanSdFilesReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Intent.ACTION_MEDIA_SCANNER_STARTED.equals(action)) {

        }
    }

    private Handler scanHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
//                case :
//                break;
            }
        }
    };
}