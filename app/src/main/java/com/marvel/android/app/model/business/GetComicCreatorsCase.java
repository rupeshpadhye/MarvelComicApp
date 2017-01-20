package com.marvel.android.app.model.business;

import com.marvel.android.app.model.entities.Creator;
import com.marvel.android.app.model.repository.rest.RestDataSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by RUPESH on 1/20/2017.
 */

public class GetComicCreatorsCase extends UseCase<List<Creator>> {

    private final RestDataSource mRepository;
    private final Scheduler mUiThread;
    private final Scheduler mExecutorThread;
    private int mComicId;
    @Inject
    public GetComicCreatorsCase(int comicId, RestDataSource repository,
                                  @Named("ui_thread") Scheduler uiThread,
                                  @Named("executor_thread") Scheduler executorThread) {

        mRepository = repository;
        mUiThread = uiThread;
        mExecutorThread = executorThread;
        mComicId=comicId;
    }

    @Override
    public Observable<List<Creator>> buildObservable() {
        return mRepository.getComicCreators(mComicId)
                .observeOn(mUiThread)
                .subscribeOn(mExecutorThread);
    }
}
