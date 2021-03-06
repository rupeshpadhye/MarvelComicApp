package com.marvel.android.app.model.business;

import android.util.Log;

import com.marvel.android.app.model.entities.Comic;
import com.marvel.android.app.model.repository.rest.RestDataSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Action1;

public class GetComicsUseCase extends UseCase<List<Comic>> {
    public final static int DEFAULT_COMICS_LIMIT = 10;
    private int mComicsLimit = DEFAULT_COMICS_LIMIT;
    private final RestDataSource mRepository;
    private int mCurrentOffset;
    private String titleStartsWith;
    private final Scheduler mUiThread;
    private final Scheduler mExecutorThread;

    @Inject public GetComicsUseCase(RestDataSource repository,
                                    @Named("ui_thread") Scheduler uiThread,
                                    @Named("executor_thread") Scheduler executorThread) {

        mRepository = repository;
        mUiThread = uiThread;
        mExecutorThread = executorThread;
    }

    @Override
    public Observable<List<Comic>> buildObservable() {
        Log.d("RUPESH","titleStartsWith"+titleStartsWith);
        return mRepository.getComics(DEFAULT_COMICS_LIMIT,mCurrentOffset,titleStartsWith)
            .observeOn(mUiThread)
            .subscribeOn(mExecutorThread)
            .doOnError(new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    mCurrentOffset -= mComicsLimit;
                }
            });
    }

    @Override
    public Observable<List<Comic>> execute() {
        increaseOffset();
        return super.execute();
    }

    public void increaseOffset() {
        mCurrentOffset += mComicsLimit;
    }

    public int getCurrentOffset() {
        return mCurrentOffset;
    }

    public void settitleStartsWith(String titleStartsWith) {
        this.titleStartsWith = titleStartsWith;
    }
}
