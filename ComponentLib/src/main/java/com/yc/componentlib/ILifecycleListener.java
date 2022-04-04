package com.yc.componentlib;

import android.os.Bundle;

public interface ILifecycleListener {

    String ON_CREATE = "on_create";
    String ON_START = "on_start";
    String ON_RESUME = "on_resume";
    String ON_PAUSE = "on_pause";
    String ON_STOP = "on_stop";
    String ON_DESTROY = "on_destroy";

    void onCreate(Bundle bundle);

    void onStart();

    void onRestart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void onAttach();

    void onDetach();
}
