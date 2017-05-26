package com.minminaya.bogemusic;

import android.app.Application;

/**
 * Created by Niwa on 2017/5/26.
 */

public class App extends Application {
    private static App INSTANCE ;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE  = this;
    }

    /**
     *  全局获取App context上下文
     *
     * @return 放回Application上下文
     * */
    public static App getINSTANCE() {
        return INSTANCE;
    }
}
