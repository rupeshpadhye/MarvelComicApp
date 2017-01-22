package com.marvel.android.app.view;

import com.marvel.android.app.model.entities.Comic;

import java.util.List;

/**
 * Created by RUPESH on 1/20/2017.
 */

public interface ComicsGridView extends MvpView {
    void bindComics(List<Comic> comics);
    void showComics();
    void hideComics();
    void updateComicLimit(int limit);
    void showLoadingSpinner();
    void hideLoadingSpinner();
    void showPullToRefresh();
    void hidePullToRefresh();
    void showNetworkError();
    void showServerError();
    void showComicDetail(Comic comic);
    void showNoComicsAvailable();
}
