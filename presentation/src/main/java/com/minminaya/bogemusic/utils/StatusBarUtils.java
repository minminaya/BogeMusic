package com.minminaya.bogemusic.utils;

import android.os.Build;
import android.view.WindowManager;

import com.minminaya.bogemusic.base.BaseActivity;

/**
 * 沉浸式装状态栏工具类
 * Created by Niwa on 2017/5/10.
 */

public class StatusBarUtils {
    public static void initStatusBar(BaseActivity baseActivity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //如果当前API版本大于等于19，那么lp的flags设为WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS 或 lp.flags
            WindowManager.LayoutParams lp = baseActivity.getWindow().getAttributes();
            lp.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | lp.flags);
        }
    }
}