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
        isRequestInProcess=false;

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
        }
    }


    private void fetchComics() {
        isRequestInProcess=true;
        mComicsSubscription = mGetComicsUseCase.execute()
                .subscribe(this::onComicsRetrieved, this::onComicsRetrievError);
    }

    private void onComicsRetrievError(Throwable error) {
        mComicsListView.hideLoadingSpinner();
        mComicsListView.showServerError();
        Log.e(TAG,error.toString());
        isRequestInProcess=false;
    }

    private void onComicsRetrieved(List<Comic> newCharacters) {
        mComicsListView.showComics();
        mComicsListView.hideLoadingSpinner();
        mComicList.addAll(newCharacters);
        mComicsListView.bindComics(mComicList);
        isRequestInProcess=false;
    }

    public void findComicsStartingByTitle(String titleStartsWith){
        mComicsListView.showLoadingSpinner();
        mComicsListView.hideComics();
        mGetComicsUseCase.settitleStartsWith(titleStartsWith);
        searchComics();
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
        Log.d("RUPESH","is Scroll in process"+isRequestInProcess);
        if(!isRequestInProcess){
            fetchComics();
        }
    }
    private void searchComics() {
        mComicsSubscription = mGetComicsUseCase.execute()
                .subscribe(this::onComicSearchResult, this::onComicsRetrievError);
    }

    private void onComicSearchResult(List<Comic> comics) {
        mComicsListView.hideLoadingSpinner();
        if(comics.size()>0){
            mComicsListView.bindComics(comics);
            mComicsListView.showComics();
        }
        else{
            mComicsListView.showNoComicsAvailable();
        }
    }
}
