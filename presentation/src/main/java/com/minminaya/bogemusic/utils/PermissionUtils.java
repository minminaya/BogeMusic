package com.minminaya.bogemusic.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Created by Niwa on 2017/5/29.
 */

public class PermissionUtils {
    /**
     * @param activity
     * @param failInfo 错误信息之后的
     */
    public static void checkPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, 1);
                Log.e("PermissionUtils", "权限申请成功");
            } else {
//            Log.e("PermissionUtils",failInfo);
            }
        }
    }

}
