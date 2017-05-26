package com.minminaya.bogemusic.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.minminaya.bogemusic.utils.StatusBarUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**  Activity基类
 * Created by Niwa on 2017/5/26.
 */

public abstract class BaseActivity extends RxAppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
        StatusBarUtils.initStatusBar(this);
        initView(savedInstanceState);
        setListeners();
        bind();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unBind();
    }

    public abstract void unBind();

    /**
     * --次序3--
     * 绑定
     */
    public abstract void bind();

    /**
     * 设置监听器，在initView()之后
     * --次序2--
     */
    public abstract void setListeners();

    /**
     * --次序1--
     * 初始化view
     */
    public abstract void initView(Bundle savedInstanceState);

    /**
     * --次序0--
     * 返回layout的id
     */
    public abstract int getContentView();

}
