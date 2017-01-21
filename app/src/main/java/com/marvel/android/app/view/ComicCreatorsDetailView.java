package com.marvel.android.app.view;

import com.marvel.android.app.model.entities.Creator;

import java.util.List;

/**
 * Created by RUPESH on 1/21/2017.
 */

public interface ComicCreatorsDetailView extends MvpView {
     void showLoadingSpinner();

    void showNoDataAvailable();

    void showCreatorList(List<Creator> creatorList);

    void hideLoadingSpinner();

    void showServerErrorMessage();

    void hideCreatorList();
}
