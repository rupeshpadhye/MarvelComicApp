package com.marvel.android.app.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marvel.android.app.MarvelComicsApp;
import com.marvel.android.app.R;
import com.marvel.android.app.injector.components.DaggerComicDetailsComponent;
import com.marvel.android.app.injector.modules.ComicDetailsModule;
import com.marvel.android.app.injector.modules.ContextModule;
import com.marvel.android.app.model.entities.Comic;
import com.marvel.android.app.presenter.ComicsCharacterDetailsPresenter;
import com.marvel.android.app.util.AppConstants;
import com.marvel.android.app.view.ComicCharacterDetailView;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by RUPESH on 1/20/2017.
 */

public class ComicCharacterDetailFragment extends Fragment implements ComicCharacterDetailView {

    @Inject
    ComicsCharacterDetailsPresenter mComicsDetailsPresenter;
    private  Comic mComic;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.comic_characters, container, false);
        ButterKnife.bind(this, rootView);
        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(AppConstants.EXTRA_COMIC_DETAIL)) {
            mComic = (Comic) intent.getSerializableExtra(AppConstants.EXTRA_COMIC_DETAIL);
        } else if (getArguments() != null) {
            Bundle bundle = getArguments();
            mComic = (Comic) bundle.getSerializable(AppConstants.EXTRA_COMIC_DETAIL);
        }
        Log.d("RUPESH","Comics detail"+mComic);

        MarvelComicsApp application = (MarvelComicsApp) getActivity().getApplication();
        DaggerComicDetailsComponent.builder().contextModule(new ContextModule(getContext()))
                .appComponent(application.getAppComponent(getActivity()))
                .comicDetailsModule(new ComicDetailsModule(mComic.getId()))
                .build().inject(this);


        mComicsDetailsPresenter.onCreateView();
        return  rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mComicsDetailsPresenter.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        mComicsDetailsPresenter.onPause();

    }


}
