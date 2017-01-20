package com.marvel.android.app.presenter;

import com.marvel.android.app.view.MvpView;

/**
 * Created by RUPESH on 1/19/2017.
 */

public interface Presenter {
    void onStart();

    void onStop();

    void onPause();

    void attachView (MvpView v);

    void onCreate();
    void onCreateView();
}
