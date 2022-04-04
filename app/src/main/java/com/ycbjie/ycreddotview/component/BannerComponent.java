package com.ycbjie.ycreddotview.component;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.yc.componentlib.BaseComponent;
import com.yc.componentlib.BasePresenter;
import com.yc.componentlib.IBaseView;
import com.ycbjie.ycreddotview.R;

public class BannerComponent extends BaseComponent {

    public BannerComponent(Context context) {
        super(context);
    }

    @Override
    public BasePresenter genPresenter(Context context) {
        return null;
    }

    @Override
    public IBaseView genView(final Context context) {
        return new IBaseView() {
            @Override
            public View getView() {
                View view = LayoutInflater.from(context).inflate(R.layout.component_banner, null);
                return view;
            }
        };
    }

}
