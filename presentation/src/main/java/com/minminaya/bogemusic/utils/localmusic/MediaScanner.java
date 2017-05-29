package com.minminaya.bogemusic.utils.localmusic;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;

/**
 * Created by Niwa on 2017/5/29.
 */

public class MediaScanner implements MediaScannerConnection.MediaScannerConnectionClient {

    private MediaScannerConnection mediaScannerConnection = null;

    public MediaScanner(Context context) {
        mediaScannerConnection = new MediaScannerConnection(context, this);
    }

    private String[] filePaths;
    private String[] mimeTypes;

    public void scanFiles(String[] filePaths, String[] mimeTypes) {
        this.filePaths = filePaths;
        this.mimeTypes = mimeTypes;
        mediaScannerConnection.connect();
    }

    @Override
    public void onMediaScannerConnected() {
        for (int i = 0; i < filePaths.length; i++) {
            mediaScannerConnection.scanFile(filePaths[i], mimeTypes[i]);
        }
        filePaths = null;
        mimeTypes = null;

    }

    private int scanTimes = 0;

    @Override
    public void onScanCompleted(String path, Uri uri) {
//        scanTimes++;
//        if (scanTimes == filePaths.length) {
            mediaScannerConnection.disconnect();
//            scanTimes = 0;
//        }
    }
}
