package com.minminaya.bogemusic.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;

/**
 * Fragment基类
 * Created by Niwa on 2017/5/26.
 */

public abstract class BaseFragment extends RxFragment {
    //是否加载了rootView
    protected boolean mPrepared;
    //Fragment是否显示给了用户
    protected boolean isVisibleToUser;

    private BaseActivity baseActivity;

    public BaseActivity getBaseActivity() {
        return baseActivity;
    }


    /**
     * 当Fragment被添加到Activity时调用，只调用一次
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        baseActivity = (BaseActivity) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(setContView(), container, false);
        ButterKnife.bind(this, rootView);
        iniView(rootView);
        mPrepared = true;
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        tryLoad();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bind();
        tryLoad();
        setListeners();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPrepared = false;
        unBind();
    }

    /**
     * ---次序--0--
     */
    public abstract void iniView(View view);

    /**
     * ---次序--1--
     */
    public abstract void bind();

    /**
     * ---次序--2--
     */
    public void tryLoad() {
        if (isVisibleToUser && mPrepared) {
            lazyLoad();
        }
    }

    /**
     * 懒加载
     */
    public abstract void lazyLoad();

    /**
     * ---次序--3--
     */
    public abstract void setListeners();

    public abstract int setContView();

    protected abstract void unBind();
}
