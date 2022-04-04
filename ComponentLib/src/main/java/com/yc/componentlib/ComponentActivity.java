package com.yc.componentlib;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Iterator;

public abstract class ComponentActivity extends AppCompatActivity implements IComponent{

    private final ComponentGroup mComponentGroup = new ComponentGroup();
    private boolean active;

    @Override
    public boolean addComponent(BaseComponent component) {
        if (!mComponentGroup.addComponent(component)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean removeComponent(BaseComponent component) {
        if (mComponentGroup.removeComponent(component)) {
            return true;
        }
        return false;
    }

    @Override
    public BaseComponent getComponent(Class<? extends BaseComponent> clazz) {
        return mComponentGroup.getComponent(clazz);
    }

    @Override
    public void removeAllComponent() {
        mComponentGroup.onCreate(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.component_layout);
        LinearLayout llComponent = findViewById(R.id.ll_component);
        addAllComponent();
        active = true;
        Iterator<BaseComponent> iterator = mComponentGroup.iterator();
        while (iterator.hasNext()){
            BaseComponent component = iterator.next();
            if (component!=null && component.getIView()!=null) {
                View view = component.getIView().getView();
                llComponent.addView(view);
            }
        }
        playLifecycle(ILifecycleListener.ON_CREATE, savedInstanceState);
    }

    protected abstract void addAllComponent();

    @Override
    protected void onStart() {
        super.onStart();
        active = true;
        playLifecycle(ILifecycleListener.ON_START, null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        active = true;
        playLifecycle(ILifecycleListener.ON_RESUME, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        active = true;
        playLifecycle(ILifecycleListener.ON_PAUSE, null);
    }

    @Override
    protected void onStop() {
        super.onStop();
        active = true;
        playLifecycle(ILifecycleListener.ON_STOP, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        active = false;
        playLifecycle(ILifecycleListener.ON_DESTROY, null);
    }

    /**
     * 分发Activity的生命周期
     *
     * @param status 生命周期阶段
     * @param bundle 附加数据
     */
    private void playLifecycle(String status, Bundle bundle) {
        Iterator<BaseComponent> iterator = mComponentGroup.iterator();
        while (iterator.hasNext()){
            BaseComponent component = iterator.next();
            if (component!=null && component.getPresenter()!=null){
                switch (status) {
                    case ILifecycleListener.ON_CREATE:
                        component.getPresenter().onCreate(bundle);
                        break;
                    case ILifecycleListener.ON_START:
                        component.getPresenter().onStart();
                        break;
                    case ILifecycleListener.ON_RESUME:
                        component.getPresenter().onResume();
                        break;
                    case ILifecycleListener.ON_PAUSE:
                        component.getPresenter().onPause();
                        break;
                    case ILifecycleListener.ON_STOP:
                        component.getPresenter().onStop();
                        break;
                    case ILifecycleListener.ON_DESTROY:
                        component.getPresenter().onDestroy();
                        break;
                }
            }
        }
    }

}
