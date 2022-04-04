package com.yc.componentlib;

import android.content.Context;
import android.os.Bundle;


public abstract class BasePresenter<V extends IBaseView> implements ILifecycleListener {

    protected V mView;
    protected Context mContext;

    public BasePresenter(Context context) {
        mContext = context;
    }

    public void setBaseView(V view) {
        mView = view;
    }

    @Override
    public void onCreate(Bundle bundle) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {

    }
}
