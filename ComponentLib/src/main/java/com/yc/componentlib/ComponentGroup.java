package com.yc.componentlib;

import android.os.Bundle;

import androidx.annotation.NonNull;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class ComponentGroup implements Iterable<BaseComponent>, ILifecycleListener {

    private final List<BaseComponent> mComponents;
    private String status;

    public ComponentGroup() {
        mComponents = new LinkedList<>();
    }

    public final BaseComponent getComponent(Class<? extends BaseComponent> clazz) {
        for (BaseComponent c : mComponents) {
            if (c.getClass() == clazz) {
                return c;
            }
        }
        return null;
    }


    public final boolean addComponent(BaseComponent component) {
        //initial it here
        if (component == null) {
            return false;
        }
        component.init();
        mComponents.add(component);
        return true;
    }

    public boolean removeComponent(BaseComponent component) {
        if (component == null) {
            return false;
        }
        boolean removed = mComponents.remove(component);
        if (!removed) {
            return false;
        }
        //处理生命周期
        component.detachView();
        return true;
    }

    @NonNull
    @Override
    public Iterator<BaseComponent> iterator() {
        return Collections.unmodifiableCollection(mComponents).iterator();
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

