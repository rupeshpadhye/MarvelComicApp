package com.marvel.android.app.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.marvel.android.app.MarvelComicsApp;
import com.marvel.android.app.R;
import com.marvel.android.app.injector.components.DaggerComicDetailsComponent;
import com.marvel.android.app.injector.modules.ComicDetailsModule;
import com.marvel.android.app.injector.modules.ContextModule;
import com.marvel.android.app.model.entities.Comic;
import com.marvel.android.app.model.entities.Creator;
import com.marvel.android.app.presenter.ComicCreatorsDetailsPresenter;
import com.marvel.android.app.ui.adapter.CreatorListAdapter;
import com.marvel.android.app.util.AppConstants;
import com.marvel.android.app.view.ComicCreatorsDetailView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RUPESH on 1/21/2017.
 */

public class ComicCreatorDetailFragment extends Fragment implements ComicCreatorsDetailView {

    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.message)
    TextView mMessage;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private CreatorListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Inject
    ComicCreatorsDetailsPresenter mComicsDetailsPresenter;
    private Comic mComic;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.comic_creators, container, false);
        ButterKnife.bind(this, rootView);
        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(AppConstants.EXTRA_COMIC_DETAIL)) {
            mComic = (Comic) intent.getSerializableExtra(AppConstants.EXTRA_COMIC_DETAIL);
        } else if (getArguments() != null) {
            Bundle bundle = getArguments();
            mComic = (Comic) bundle.getSerializable(AppConstants.EXTRA_COMIC_DETAIL);
        }

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
                .build().inject(this);
     }

    private void initRecycleViewAdapter() {
       // mLayoutManager = new LinearLayoutManager();
        mLayoutManager =   new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRecyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLayoutManager.getLayoutDirection() );
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mAdapter = new CreatorListAdapter(new ArrayList<Creator>(),getContext());
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
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoDataAvailable() {
        mMessage.setVisibility(View.VISIBLE);
        mMessage.setText(getString(R.string.no_detail));
    }

    @Override
    public void showCreatorList(List<Creator> creatorList) {
        mAdapter.setCreatorList(creatorList);
    }

    @Override
    public void hideLoadingSpinner() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showServerErrorMessage() {
        mMessage.setText(getString(R.string.something_wrong));
    }

    @Override
    public void hideCreatorList() {
        mRecyclerView.setVisibility(View.GONE);
    }
}
