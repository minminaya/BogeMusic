package com.minminaya.bogemusic.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.minminaya.bogemusic.R;
import com.minminaya.bogemusic.finder.activity.FinderActivity;

public class MainActivity extends Activity {
    ImageView imageView;
    boolean isClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.iv);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClick = true;
                Intent intent = new Intent(MainActivity.this, FinderActivity.class);
                startActivity(intent);
                finish();
            }
        });
        if (!isClick) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(MainActivity.this, FinderActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).start();
        }
    }
}
