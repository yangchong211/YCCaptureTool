package com.ycbjie.ycreddotview.component;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.yc.componentlib.BaseComponent;
import com.yc.componentlib.BasePresenter;
import com.yc.componentlib.IBaseView;
import com.ycbjie.ycreddotview.R;

public class ImageComponent extends BaseComponent {

    public ImageComponent(Context context) {
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
                ImageView imageView = new ImageView(context);
                imageView.setBackgroundResource(R.drawable.ic_launcher_background);
                imageView.setScaleType(ImageView.ScaleType.CENTER);
                return imageView;
            }
        };
    }

}
