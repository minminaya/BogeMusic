package com.minminaya.bogemusic.utils.localmusic;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.minminaya.bogemusic.App;
import com.minminaya.data.model.LocalMusicModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LocalMusicUtil {


    /**
     * 通过内容提供器查询本地歌曲
     *
     * @return localMusicModels 包含查询结果的list集合
     */
    public static List<LocalMusicModel> iniMediaPlayerContentUri() {

        List<LocalMusicModel> localMusicModels = new ArrayList<>();
        ContentResolver contentResolver = App.getINSTANCE().getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
//        long[] idBag;
        if (cursor == null) {
            Toast.makeText(App.getINSTANCE(), "cursor查询错误", Toast.LENGTH_SHORT).show();
        } else if (!cursor.moveToFirst()) {
            Toast.makeText(App.getINSTANCE(), "没有查询到歌曲", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(App.getINSTANCE(), "有歌在设备上", Toast.LENGTH_SHORT).show();
            int titleColumn = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int idColumn = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int totalMusic = cursor.getCount();

            Log.e("查询信息", "" + totalMusic);
//            idBag = new long[totalMusic];
//            localMusicModels = new ArrayList<>(totalMusic);

            do {
                long thisId = cursor.getLong(idColumn);
                String thisTitle = cursor.getString(titleColumn);
                localMusicModels.add(new LocalMusicModel(thisId, thisTitle));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return localMusicModels;
    }

    /**
     *
     * */
    public static void refreshcontentResolverData() {
//        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_MEDIA_SCANNER_STARTED);
//        intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_STARTED);
//        intentFilter.addDataScheme("file");
//        App.getINSTANCE().sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" +
//                Environment.getExternalStorageDirectory())));

        String[] songTotal = null;
//        String[] mimeTypes = null;
        File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/opo");
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            if (files != null) {
                songTotal = new String[files.length];
//                mimeTypes = new String[files.length];
                for (int i = 0; i < songTotal.length; i++) {
                    //默认路径
                    songTotal[i] = Environment.getExternalStorageDirectory().getAbsolutePath();
//                    mimeTypes[i] = null;
                }
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isFile()) {
                        songTotal[i] = Environment.getExternalStorageDirectory().getAbsolutePath() + "/opo/" + files[i].getName();
                        Log.e("file", files[i].getName());
                    }
                }
            }
        }

        MediaScannerConnection.scanFile(App.getINSTANCE(), songTotal, null, null);


//        MediaScanner mediaScanner = new MediaScanner(App.getINSTANCE());
//        String[] filePaths = songTotal;
////        String[] mimeTypes = new String[]{MimeTypeMap.getSingleton().getMimeTypeFromExtension("mp3")};
//
////        String[] mimeTypes = new String[]{null};
//        mediaScanner.scanFiles(filePaths, mimeTypes);


    }
}

