package com.marvel.android.app.model.business;

import android.util.Log;

import com.marvel.android.app.model.entities.Character;
import com.marvel.android.app.model.repository.rest.RestDataSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by RUPESH on 1/20/2017.
 */

public class GetComicCharactersCase extends UseCase<List<Character>> {

    private final RestDataSource mRepository;
    private final Scheduler mUiThread;
    private final Scheduler mExecutorThread;
    private int mComicId;
    @Inject
    public GetComicCharactersCase(int comicId, RestDataSource repository,
                                  @Named("ui_thread") Scheduler uiThread,
                                  @Named("executor_thread") Scheduler executorThread) {

        mRepository = repository;
        mUiThread = uiThread;
        mExecutorThread = executorThread;
        mComicId=comicId;
    }

    @Override
    public Observable<List<Character>> buildObservable() {
        Log.d("RUPESH","Comic id is"+mComicId);
        return mRepository.getComicCharacters(mComicId)
                .observeOn(mUiThread)
                .subscribeOn(mExecutorThread);
    }
}
