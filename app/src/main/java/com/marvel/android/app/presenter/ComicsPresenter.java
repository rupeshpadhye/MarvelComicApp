package com.marvel.android.app.presenter;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.marvel.android.app.model.business.GetComicsUseCase;
import com.marvel.android.app.model.entities.Comic;
import com.marvel.android.app.util.AppUtils;
import com.marvel.android.app.view.ComicsGridView;
import com.marvel.android.app.view.MvpView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by RUPESH on 1/20/2017.
 */

public class ComicsPresenter implements  Presenter {

    private  final GetComicsUseCase mGetComicsUseCase;
    private ComicsGridView mComicsListView;
    private Subscription mComicsSubscription;
    private List<Comic> mComicList =new ArrayList<>();
    private boolean isRequestInProcess;
    private final String TAG=ComicsPresenter.class.getName();

    @Inject
    ComicsPresenter(GetComicsUseCase getComicsUseCase){
        this.mGetComicsUseCase = getComicsUseCase;
    }
    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onPause() {
        if(mComicsSubscription !=null){
            mComicsSubscription.unsubscribe();
        }

    }

    @Override
    public void attachView(MvpView mvpView) {
        mComicsListView=(ComicsGridView)mvpView;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onCreateView() {
        if (isConnectedToNetwork()) {
            mComicsListView.hideComics();
            mComicsListView.showLoadingSpinner();
            fetchComics();
        }
        else {
        //    mComicsListView.showNetworkError();
            mComicsListView.hideLoadingSpinner();
            mComicsListView.hidePullToRefresh();
        }
    }


    private void fetchComics() {
        isRequestInProcess=true;
        mComicsSubscription = mGetComicsUseCase.execute()
                .subscribe(this::onComicsRetrieved, this::onComicsRetrievError);
    }

    private void onComicsRetrievError(Throwable error) {
        mComicsListView.hideLoadingSpinner();
        mComicsListView.hidePullToRefresh();
        mComicsListView.showServerError();
        Log.e(TAG,error.toString());
        isRequestInProcess=false;
    }

    private void onComicsRetrieved(List<Comic> newCharacters) {
        mComicsListView.showComics();
        mComicsListView.hideLoadingSpinner();
        mComicsListView.hidePullToRefresh();
        mComicList.addAll(newCharacters);
        mComicsListView.bindComics(mComicList);
        isRequestInProcess=false;

    }

    public void doPullToRefresh() {
        if (isConnectedToNetwork()) {
            mComicsListView.showPullToRefresh();
            fetchComics();
        } else {
            mComicsListView.showNetworkError();
            mComicsListView.hidePullToRefresh();
        }
    }

    public void onProductSelected(Comic comic) {
        mComicsListView.showComicDetail(comic);
    }

    private boolean isConnectedToNetwork(){
        Fragment fragment=(Fragment)mComicsListView;
        return  AppUtils.isNetworkConnected(fragment.getActivity());
    }


    public List<Comic> getComicList() {
        return mComicList;
    }

    public void onListEndReached() {
        if(!isRequestInProcess){
            fetchComics();
        }

    }
}
