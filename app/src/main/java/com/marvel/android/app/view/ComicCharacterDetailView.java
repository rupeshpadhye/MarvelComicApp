package com.marvel.android.app.view;

import com.marvel.android.app.model.entities.Character;

import java.util.List;

/**
 * Created by RUPESH on 1/20/2017.
 */

public interface ComicCharacterDetailView extends MvpView {
    void showLoadingSpinner();

    void showNoDataAvailable();

    void showCharacterList(List<Character> creatorList);

    void hideLoadingSpinner();

    void showServerErrorMessage();

    void hideCharacterList();
}
