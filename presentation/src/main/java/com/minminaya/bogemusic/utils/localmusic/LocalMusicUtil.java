package com.minminaya.bogemusic.utils.localmusic;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
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
//            int songSizeColumn = cursor.getColumnIndex(MediaStore.Audio.Media.SIZE);
            int songDurationColumn = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
            int pathColumn = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            Log.e("查询信息", "" + totalMusic);
//            idBag = new long[totalMusic];
//            localMusicModels = new ArrayList<>(totalMusic);

            do {
                long thisId = cursor.getLong(idColumn);
                String thisTitle = cursor.getString(titleColumn);
//                int songSize = cursor.getInt(songSizeColumn);
                int songDuration = cursor.getInt(songDurationColumn);
                String songPath = cursor.getString(pathColumn);
                localMusicModels.add(new LocalMusicModel(thisId, thisTitle, songDuration, songPath));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return localMusicModels;
    }

    /**
     * API19以及以下发送广播刷新内容提供器
     * API19以上按文件刷新内容提供器
     */
    public static void refreshcontentResolverData() {

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            IntentFilter intentFilter = new IntentFilter(Intent.ACTION_MEDIA_SCANNER_STARTED);
            intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_STARTED);
            intentFilter.addDataScheme("file");
            App.getINSTANCE().sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" +
                    Environment.getExternalStorageDirectory())));
        } else {
            //保存歌曲绝对路径的数组，这个用于MediaScannerConnection.scanFile（）第二个参数
            String[] songTotalPath = null;
//        String[] mimeTypes = null;
            //现在假设要扫描sd卡下的opo目录，“/”这个斜杠别丢了，接下来用到的file相关方法啊啥的建议参考下File的类文档
            File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath());

            if (f.isDirectory()) {
                //测试f这个路径表示的文件是否是一个目录
                File[] files = f.listFiles();//返回一个抽象（绝对）路径名数组，这些路径名表示此抽象路径名表示的目录中的文件
                if (files != null) {
                    //初始化数组长度
                    songTotalPath = new String[files.length];
//                mimeTypes = new String[files.length];
                    for (int i = 0; i < songTotalPath.length; i++) {
                        //默认路径，这里初始化数组每一项，只是单纯的防止后面用第二种方式扫描文件带来的空指针异常，无实际意义
                        songTotalPath[i] = Environment.getExternalStorageDirectory().getAbsolutePath();
                        //只是单纯的防止后面用第二种方式扫描文件带来的空指针异常
//                    mimeTypes[i] = null;
                    }

                    for (int i = 0; i < files.length; i++) {
                        if (files[i].isFile()) {
                            //如果 扫到的是文件，那么把具体路径存到songTotalPath下
                            songTotalPath[i] = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + files[i].getName();
                            Log.e("file", files[i].getName());
                        }
                    }
                }
            }
            //这里就可以直接用了，第三个这里用文件的后缀名，为空
            MediaScannerConnection.scanFile(App.getINSTANCE(), songTotalPath, null, new MediaScannerConnection.OnScanCompletedListener() {
                @Override
                public void onScanCompleted(String path, Uri uri) {
                    //扫描完成时逻辑
                }
            });

            //---------------------->第二种方式<----------------------
//        MediaScanner mediaScanner = new MediaScanner(App.getINSTANCE());
//        String[] filePaths = songTotalPath;
//        //MimeType文件也可以这个获取，如果不知道媒体文件对应的的值
////        String[] mimeTypes = new String[]{MimeTypeMap.getSingleton().getMimeTypeFromExtension("mp3")};
//
////        String[] mimeTypes = new String[]{null};
//        mediaScanner.scanFiles(filePaths, mimeTypes);
        }
    }
}

