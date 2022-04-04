package com.yc.componentlib;

import android.content.Context;

import androidx.annotation.NonNull;

/**
 * 新版首页的Component
 */
public abstract class BaseComponent<P extends BasePresenter<V>, V extends IBaseView> {

    Context mContext;
    P mPresenter;
    V mView;

    public BaseComponent(Context context) {
        mContext = context;
        init();
    }

    public void init() {
        mPresenter = genPresenter(mContext);
        mView = genView(mContext);
        if (mPresenter!=null){
            mPresenter.setBaseView(mView);
        }
    }

    public void detachView() {
        mView = null;
        mPresenter.onDetach();
    }

    @NonNull
    public abstract P genPresenter(Context context);

    @NonNull
    public abstract V genView(Context context);

    @NonNull
    public P getPresenter() {
        return mPresenter;
    }

    @NonNull
    public V getIView() {
        return mView;
    }

}
