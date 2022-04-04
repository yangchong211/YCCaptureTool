package com.ycbjie.ycreddotview;

import com.yc.componentlib.BaseComponent;
import com.yc.componentlib.ComponentActivity;
import com.ycbjie.ycreddotview.component.BannerComponent;
import com.ycbjie.ycreddotview.component.ImageComponent;

public class HomeActivity extends ComponentActivity {

    @Override
    protected void addAllComponent() {
        addComponent(new BannerComponent(this));
        addComponent(new ImageComponent(this));
    }


}
