package com.marvel.android.app.presenter;

import android.util.Log;

import com.marvel.android.app.model.business.GetComicCreatorsCase;
import com.marvel.android.app.model.entities.Comic;
import com.marvel.android.app.model.entities.Creator;
import com.marvel.android.app.model.entities.Summary;
import com.marvel.android.app.view.ComicCreatorsDetailView;
import com.marvel.android.app.view.MvpView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by RUPESH on 1/21/2017.
 */

public class ComicCreatorsDetailsPresenter implements Presenter {
    private ComicCreatorsDetailView mComicDetailView;
    private GetComicCreatorsCase mGetComicCreatorCase;
    private Comic mComic;
    private Subscription mComicsCretorSubscription;
    Map<String,String> mcreatorMap =new HashMap<>();
    @Inject
    ComicCreatorsDetailsPresenter(GetComicCreatorsCase getComicCharactersCase){
        this.mGetComicCreatorCase =getComicCharactersCase;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if(mComicsCretorSubscription !=null && mComicsCretorSubscription.isUnsubscribed()){
            mComicsCretorSubscription.unsubscribe();
        }
    }

    @Override
    public void onPause() {
        if(mComicsCretorSubscription !=null && mComicsCretorSubscription.isUnsubscribed()){
            mComicsCretorSubscription.unsubscribe();
        }
    }

    @Override
    public void attachView(MvpView v) {
        mComicDetailView=(ComicCreatorsDetailView) v;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onCreateView() {
        if(mComic.getCreators().getAvailable()>0){
            loadCreatorsFromComicSummary();
            fetchComicCreators();
        }
        else{
            mComicDetailView.showNoDataAvailable();
            mComicDetailView.hideCreatorList();
        }

    }

    private void loadCreatorsFromComicSummary() {
        List<Creator> mCreatorList=new ArrayList<>();
        List<Summary> creatorSummaryList=mComic.getCreators().getItems();
        for(Summary summary :creatorSummaryList){
              Creator creator=new Creator();
              creator.setFullName(summary.getName());
              creator.setRole(summary.getRole());
              mcreatorMap.put(summary.getName(),summary.getRole());
              mCreatorList.add(creator);
        }
        mComicDetailView.showCreatorList(mCreatorList);
    }

    private void fetchComicCreators() {
        Log.d("RUPESH","before fetchComicsDetailst");
        mComicsCretorSubscription = mGetComicCreatorCase
                .execute()
                .subscribe(this::onResult,this::onFailure);
    }

    private void onResult(List<Creator> creatorList){
        for(Creator creator:creatorList){
            if(mcreatorMap.containsKey(creator.getFullName())){
                creator.setRole(mcreatorMap.get(creator.getFullName()));
            }
        }
        Log.d("RUPESH","Creator list"+creatorList.toString());
        mComicDetailView.showCreatorList(creatorList);
        mComicDetailView.hideLoadingSpinner();
    }

    private void onFailure(Throwable throwable){
        mComicDetailView.hideLoadingSpinner();
        mComicDetailView.showServerErrorMessage();
    }

    public void setPresenter(Comic comic) {
        this.mComic=comic;
    }
}
