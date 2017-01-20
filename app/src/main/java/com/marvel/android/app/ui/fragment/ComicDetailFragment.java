package com.marvel.android.app.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marvel.android.app.MarvelComicsApp;
import com.marvel.android.app.R;
import com.marvel.android.app.model.entities.Comic;
import com.marvel.android.app.presenter.ComicsDetailsPresenter;
import com.marvel.android.app.util.AppConstants;
import com.marvel.android.app.view.ComicDetailView;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by RUPESH on 1/20/2017.
 */

public class ComicDetailFragment extends Fragment implements ComicDetailView {

    @Inject
    ComicsDetailsPresenter mComicsDetailsPresenter;
    private  Comic mComic;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MarvelComicsApp.getAppComponent(getActivity()).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.comic_detail, container, false);
        ButterKnife.bind(this, rootView);
        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(AppConstants.EXTRA_COMIC_DETAIL)) {
            mComic = (Comic) intent.getSerializableExtra(AppConstants.EXTRA_COMIC_DETAIL);
        } else if (getArguments() != null) {
            Bundle bundle = getArguments();
            mComic = (Comic) bundle.getSerializable(AppConstants.EXTRA_COMIC_DETAIL);
        }
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
