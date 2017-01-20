package com.marvel.android.app.presenter;

import android.util.Log;

import com.marvel.android.app.model.business.GetComicsUseCase;
import com.marvel.android.app.model.entities.Comic;
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
        mComicsSubscription.unsubscribe();
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
            fetchComics();
    }

    private void fetchComics() {
        mComicsSubscription = mGetComicsUseCase.execute()
                .subscribe(this::onComicsRetrieved, this::onComicsRetrievError);
    }

    private void onComicsRetrievError(Throwable error) {
        Log.d("RUPESH",error.toString());
    }

    private void onComicsRetrieved(List<Comic> newCharacters) {
        Log.d("RUPESH",newCharacters.toString());
        mComicList.addAll(newCharacters);
        mComicsListView.bindComics(mComicList);

    }
}
