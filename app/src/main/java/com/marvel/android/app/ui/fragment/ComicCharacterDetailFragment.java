package com.marvel.android.app.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marvel.android.app.MarvelComicsApp;
import com.marvel.android.app.R;
import com.marvel.android.app.injector.components.DaggerComicDetailsComponent;
import com.marvel.android.app.injector.modules.ComicDetailsModule;
import com.marvel.android.app.injector.modules.ContextModule;
import com.marvel.android.app.model.entities.Character;
import com.marvel.android.app.model.entities.Comic;
import com.marvel.android.app.presenter.ComicsCharacterDetailsPresenter;
import com.marvel.android.app.ui.adapter.CharactersListAdapter;
import com.marvel.android.app.util.AppConstants;
import com.marvel.android.app.view.ComicCharacterDetailView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RUPESH on 1/20/2017.
 */

public class ComicCharacterDetailFragment extends Fragment implements ComicCharacterDetailView {

    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.message)
    TextView mMessage;

    @Inject
    ComicsCharacterDetailsPresenter mComicsDetailsPresenter;
    private  Comic mComic;

    private CharactersListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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
        Log.d("RUPESH","Comics detail id"+mComic);
        initDependancyInjection();
        initRecycleViewAdapter();
        initPresenter();
        return  rootView;
    }

    private void initPresenter() {
        mComicsDetailsPresenter.attachView(this);
        mComicsDetailsPresenter.setPresenter(mComic);
        mComicsDetailsPresenter.onCreateView();
    }

    private void initDependancyInjection() {
        MarvelComicsApp application = (MarvelComicsApp) getActivity().getApplication();
        DaggerComicDetailsComponent.builder().contextModule(new ContextModule(getContext()))
                .appComponent(application.getAppComponent(getActivity()))
                .comicDetailsModule(new ComicDetailsModule(mComic.getId()))
               // .comicDetailsModule(new ComicDetailsModule(10252))
                .build().inject(this);
    }

    private void initRecycleViewAdapter() {
        //mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager =   new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CharactersListAdapter(new ArrayList<Character>(),getContext());
        mRecyclerView.setAdapter(mAdapter);

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


    @Override
    public void showLoadingSpinner() {

    }

    @Override
    public void showNoDataAvailable() {
        mMessage.setVisibility(View.VISIBLE);
        mMessage.setText(getString(R.string.no_detail));
    }

    @Override
    public void showCharacterList(List<Character> creatorList) {
      mAdapter.setCreatorList(creatorList);
    }

    @Override
    public void hideLoadingSpinner() {

    }

    @Override
    public void showServerErrorMessage() {
        mMessage.setText(getString(R.string.something_wrong));
    }

    @Override
    public void hideCharacterList() {
        mRecyclerView.setVisibility(View.GONE);
    }
}
