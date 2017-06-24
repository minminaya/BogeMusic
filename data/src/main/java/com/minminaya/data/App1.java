package com.minminaya.data;

import android.app.Application;

/**
 * Created by Niwa on 2017/6/24.
 */

public class App1 extends Application {

    private static App1 INSTANCE;

    public static App1 getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}
