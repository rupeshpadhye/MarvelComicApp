package com.marvel.android.app.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.marvel.android.app.MarvelComicsApp;
import com.marvel.android.app.R;
import com.marvel.android.app.model.entities.Comic;
import com.marvel.android.app.presenter.ComicsPresenter;
import com.marvel.android.app.ui.adapter.ComicsGridAdapter;
import com.marvel.android.app.util.AppConstants;
import com.marvel.android.app.view.ComicsGridView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RUPESH on 1/20/2017.
 */

public class ComicsGridFragment extends Fragment implements ComicsGridView {

    @Inject
    ComicsPresenter comicsPresenter;

    @BindView(R.id.grid)
    GridView mProductGridView;
    @BindView(R.id.gridRelativeLayout)
    RelativeLayout mRelativeLayout;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private static int mPosition = GridView.INVALID_POSITION;
    private ComicsGridAdapter mGridAdapter;
    private OnGridItemSelected mOnGridItemSelected;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MarvelComicsApp.getAppComponent(getActivity()).inject(this);
        setRetainInstance(true);
        initiateAdapter();
        comicsPresenter.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.grid_fragment, container, false);
        ButterKnife.bind(this, rootView);
        initializePresenter();
        intializeViewComponents();
        if (savedInstanceState != null &&
                savedInstanceState.containsKey(AppConstants.EXTRA_COMIC_LIST)) {
            List ComList = (List<Comic>) savedInstanceState.getSerializable(AppConstants.EXTRA_COMIC_LIST);
            mGridAdapter.setGridData(ComList);
            hideLoadingSpinner();
            showComics();
        } else {
            comicsPresenter.onCreateView();
        }
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnGridItemSelected) {
            mOnGridItemSelected = (OnGridItemSelected) context;
        } else {
            throw new ClassCastException("not instance of OnGridItemSelected");
        }
    }

    private void intializeViewComponents() {
        mProductGridView.setAdapter(mGridAdapter);
        mProductGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Comic comic = mGridAdapter.getItem(position);
                //mPosition = position;
                comicsPresenter.onProductSelected(comic);

            }
        });

        mProductGridView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                int lastInScreen = firstVisibleItem + visibleItemCount;
                if ((lastInScreen == totalItemCount)) {
                    comicsPresenter.onListEndReached();
                }
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                comicsPresenter.doPullToRefresh();
            }

        });
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

    private void initiateAdapter() {
        mGridAdapter = new ComicsGridAdapter(getActivity(), R.layout.grid_item, new ArrayList<>());
    }

    private void initializePresenter() {
        comicsPresenter.attachView(this);
    }


    @Override
    public void bindComics(List<Comic> comics) {
        mGridAdapter.setGridData(comics);
    }

    @Override
    public void showComics() {
        mProductGridView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideComics() {
        mProductGridView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoadingSpinner() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingSpinner() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showPullToRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hidePullToRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showNetworkError() {
        Snackbar.make(mRelativeLayout, getString(R.string.no_network), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showServerError() {
        Snackbar.make(mRelativeLayout, getString(R.string.something_wrong), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showComicDetail(Comic comic) {
        mOnGridItemSelected.OnItemClicked(comic);
    }


    @Override
    public void updateComicLimit(int limit) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(AppConstants.EXTRA_COMIC_LIST
                , (Serializable) comicsPresenter.getComicList());
        super.onSaveInstanceState(outState);
    }

    public interface OnGridItemSelected {
        void OnItemClicked(Comic productDetail);

    }
}