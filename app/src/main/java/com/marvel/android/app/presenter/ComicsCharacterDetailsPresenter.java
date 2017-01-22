package com.marvel.android.app.presenter;

import android.util.Log;

import com.marvel.android.app.model.business.GetComicCharactersCase;
import com.marvel.android.app.model.entities.Character;
import com.marvel.android.app.model.entities.Comic;
import com.marvel.android.app.model.entities.Summary;
import com.marvel.android.app.view.ComicCharacterDetailView;
import com.marvel.android.app.view.MvpView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by RUPESH on 1/20/2017.
 */

public class ComicsCharacterDetailsPresenter implements Presenter {

    private ComicCharacterDetailView mComicDetailView;
    private  GetComicCharactersCase mGetComicCharactersCase;
    private Subscription mComicsCharacterSubscription;
    private Comic mComic;
    Map<String,String> mCharacterMap =new HashMap<>();

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
       if(mComic.getCharacters().getAvailable()>0){
            loadCharactersFromComicSummary();
            fetchComicsCharactersDetails();
        }
        else{
            mComicDetailView.showNoDataAvailable();
            mComicDetailView.hideCharacterList();
        }

        
    }

    private void loadCharactersFromComicSummary() {
        List<Character> characterList=new ArrayList<>();
        List<Summary> creatorSummaryList=mComic.getCreators().getItems();
        for(Summary summary :creatorSummaryList){
            Character creator=new Character();
            creator.setName(summary.getName());
            creator.setRole(summary.getRole());
            mCharacterMap.put(summary.getName(),summary.getRole());
            characterList.add(creator);
        }
        mComicDetailView.showCharacterList(characterList);
    }


    private void fetchComicsCharactersDetails() {
        Log.d("RUPESH","before Characters");
        mComicsCharacterSubscription=mGetComicCharactersCase
                .execute()
                .subscribe(this::onResultComicCharacters,this::onFailureComicCharacters);
    }

    private void onResultComicCharacters(List<Character> characterList){
        for(Character character:characterList){
            if(mCharacterMap.containsKey(character.getName())){
                character.setRole(mCharacterMap.get(character.getName()));
            }
        }
        mComicDetailView.showCharacterList(characterList);
        Log.d("RUPESH","Character List"+characterList.toString());
    }

    private void onFailureComicCharacters(Throwable throwable){
        mComicDetailView.showServerErrorMessage();
    }

    public void setPresenter(Comic comic) {
        this.mComic=comic;
    }
}
