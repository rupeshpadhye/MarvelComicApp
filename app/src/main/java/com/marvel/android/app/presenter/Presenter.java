package com.marvel.android.app.presenter;

import android.view.View;

/**
 * Created by RUPESH on 1/19/2017.
 */

public interface Presenter {
    void onStart();

    void onStop();

    void onPause();

    void attachView (View v);

    void onCreate();
}
