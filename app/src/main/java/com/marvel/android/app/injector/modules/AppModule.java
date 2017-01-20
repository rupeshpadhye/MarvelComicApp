package com.marvel.android.app.injector.modules;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.marvel.android.app.BuildConfig;
import com.marvel.android.app.MarvelComicsApp;
import com.marvel.android.app.model.repository.rest.RestDataSource;
import com.marvel.android.app.model.repository.rest.RestDataSourceImpl;
import com.marvel.android.app.model.repository.rest.endpoints.Endpoint;
import com.marvel.android.app.model.repository.rest.endpoints.MarvelAuthorizer;
import com.squareup.picasso.Picasso;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by RUPESH on 1/18/2017.
 */
@Module
public class AppModule {
    private final MarvelComicsApp mApp;

    public AppModule(MarvelComicsApp marvelComicsApp) {
        this.mApp = marvelComicsApp;
    }
    @Provides @Singleton
    MarvelComicsApp provideMarvelComicsApp() {
        return mApp; }

    @Provides @Singleton public SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(mApp);
    }

    @Provides @Singleton
    MarvelAuthorizer provideMarvelAuthorizer() {
        return new MarvelAuthorizer(BuildConfig.MARVEL_PUBLIC_KEY, BuildConfig.MARVEL_PRIVATE_KEY);
    }

    @Provides@Singleton
    Endpoint provideEndpoint() {
        return new Endpoint("http://gateway.marvel.com/");
    }

    @Provides @Singleton public OkHttpClient provideOkHttpClient(){
        return  new OkHttpClient();
    }

    @Provides @Singleton
     RestDataSource provideRestDataSource(RestDataSourceImpl restDataSource) {
        return restDataSource; }

    @Provides @Singleton public Picasso providePicasso(){
       return new Picasso.Builder(mApp)
               .downloader(new OkHttp3Downloader(provideOkHttpClient()))
               .build();
    }

    @Provides @Named("executor_thread")
    Scheduler provideExecutorThread() {
        return Schedulers.newThread();
    }

    @Provides @Named("ui_thread")
    Scheduler provideUiThread() {
        return AndroidSchedulers.mainThread();
    }

}
