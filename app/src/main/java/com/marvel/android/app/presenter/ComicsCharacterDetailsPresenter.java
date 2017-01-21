package com.marvel.android.app.presenter;

import android.util.Log;

import com.marvel.android.app.model.business.GetComicCharactersCase;
import com.marvel.android.app.model.entities.Character;
import com.marvel.android.app.view.ComicCharacterDetailView;
import com.marvel.android.app.view.MvpView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by RUPESH on 1/20/2017.
 */

public class ComicsCharacterDetailsPresenter implements Presenter {

    private ComicCharacterDetailView mComicDetailView;
    private  GetComicCharactersCase mGetComicCharactersCase;
    //private int mComicId;
    private Subscription mComicsCharacterSubscription;
    @Inject
    ComicsCharacterDetailsPresenter(GetComicCharactersCase getComicCharactersCase){
          this.mGetComicCharactersCase=getComicCharactersCase;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if(mComicsCharacterSubscription!=null && mComicsCharacterSubscription.isUnsubscribed()){
            mComicsCharacterSubscription.unsubscribe();
        }
    }

    @Override
    public void onPause() {
        if(mComicsCharacterSubscription!=null && mComicsCharacterSubscription.isUnsubscribed()){
            mComicsCharacterSubscription.unsubscribe();
        }
    }

    @Override
    public void attachView(MvpView v) {
        mComicDetailView=(ComicCharacterDetailView)v;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onCreateView() {
          fetchComicsDetails();
    }

    private void fetchComicsDetails() {
        Log.d("RUPESH","before Characters");
        mComicsCharacterSubscription=mGetComicCharactersCase
                .execute()
                .subscribe(this::onResultComicCharacters,this::onFailureComicCharacters);
    }

    private void onResultComicCharacters(List<Character> characterList){
        Log.d("RUPESH","Character List"+characterList.toString());
    }

    private void onFailureComicCharacters(Throwable throwable){

    }
}
