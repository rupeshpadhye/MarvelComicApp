package com.marvel.android.app.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marvel.android.app.MarvelComicsApp;
import com.marvel.android.app.R;
import com.marvel.android.app.model.entities.Comic;
import com.marvel.android.app.presenter.ComicsPresenter;
import com.marvel.android.app.view.ComicsGridView;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by RUPESH on 1/20/2017.
 */

public class ComicsGridFragment extends Fragment implements ComicsGridView {

    @Inject
    ComicsPresenter comicsPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MarvelComicsApp.getAppComponent(getActivity()).inject(this);
       // initiateAdapter();
        comicsPresenter.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.grid_fragment, container, false);
        ButterKnife.bind(this, rootView);
        initializePresenter();
        comicsPresenter.onCreateView();
        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();
        comicsPresenter.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        comicsPresenter.onPause();
    }

    private void initializePresenter() {
        comicsPresenter.attachView(this);
    }


    @Override
    public void bindComics(List<Comic> comics) {
        Log.d("RUPESH",comics.toString());
    }

    @Override
    public void showComics() {

    }

    @Override
    public void hideComics() {

    }

    @Override
    public void updateComicLimit(int limit) {

    }
}