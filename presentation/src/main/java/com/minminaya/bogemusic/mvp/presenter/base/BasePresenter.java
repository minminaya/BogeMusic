package com.minminaya.bogemusic.mvp.presenter.base;

import com.minminaya.bogemusic.mvp.view.MvpView;

/**
 * Presenter基础类
 * Created by Niwa on 2017/5/26.
 */

public class BasePresenter<T extends MvpView> implements IPresenter<T> {
    private T mvpView;
//    public CompositeDisposable compositeDisposable;//用于绑定或取消绑定Rxjava的

    /**
     * @param view 子view，比如，子presenter是控制当前的Activity的presenter,那么子view就是该Activity
     *             绑定view
     */
    @Override
    public void attachView(T view) {
        this.mvpView = view;
//        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onDestroyed() {

    }

    /**
     * 取消view绑定
     */
    @Override
    public void detachView(T view) {
        mvpView = null;
//        compositeDisposable.isDisposed();
//        compositeDisposable = null;
    }

    /**
     * view是否连接
     */
    @Override
    public boolean isAttachView(T view) {
        return mvpView != null;
    }

    /**
     * 得到当前的绑定的view对象
     */
    @Override
    public T getMvpView() {
        return mvpView;
    }
}
