package com.marvel.android.app.injector.modules;

import com.marvel.android.app.injector.App;
import com.marvel.android.app.model.business.GetComicCharactersCase;
import com.marvel.android.app.model.business.GetComicCreatorsCase;
import com.marvel.android.app.model.repository.rest.RestDataSource;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

/**
 * Created by RUPESH on 1/21/2017.
 */

@Module
public class ComicDetailsModule {
    private int mComicId;
    public ComicDetailsModule(int comicId){
        this.mComicId =comicId;
    }

    @Provides @App
    public GetComicCharactersCase provideComicCharactersCase(
            RestDataSource repository,
            @Named("ui_thread") Scheduler uiThread,
            @Named("executor_thread") Scheduler executorThread){
        return  new GetComicCharactersCase(mComicId,repository,uiThread,executorThread);
    }


    @Provides @App
    public GetComicCreatorsCase provideGetComicCreatorsCase(
            RestDataSource repository,
            @Named("ui_thread") Scheduler uiThread,
            @Named("executor_thread") Scheduler executorThread){
        return  new GetComicCreatorsCase(mComicId,repository,uiThread,executorThread);
    }

}
