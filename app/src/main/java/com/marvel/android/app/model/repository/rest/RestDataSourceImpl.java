package com.marvel.android.app.model.repository.rest;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.marvel.android.app.model.entities.Comic;
import com.marvel.android.app.model.repository.rest.deserializer.MarvelComicDeserializer;
import com.marvel.android.app.model.repository.rest.endpoints.Endpoint;
import com.marvel.android.app.model.repository.rest.endpoints.MarvelApi;
import com.marvel.android.app.model.repository.rest.endpoints.MarvelAuthorizer;
import com.marvel.android.app.model.repository.rest.endpoints.interceptor.SignInInterceptor;

import java.util.List;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by RUPESH on 1/20/2017.
 */

public class RestDataSourceImpl implements RestDataSource {


    MarvelAuthorizer marvelAuthorizer;
    Endpoint endpoint;

    private final MarvelApi mMarvelApi;
    @Inject
    RestDataSourceImpl( MarvelAuthorizer marvelAuthorizer, Endpoint endpoint) {
        this.marvelAuthorizer=marvelAuthorizer;
        this.endpoint=endpoint;

        SignInInterceptor signInInterceptor =
                new SignInInterceptor(marvelAuthorizer.getApiClient(),
                                      marvelAuthorizer.getApiSecret());

        OkHttpClient client=new OkHttpClient.Builder().addInterceptor(signInInterceptor).build();

        Gson customGsonInstance = new GsonBuilder()
                .registerTypeAdapter(new TypeToken<List<Comic>>() {}.getType(),
                        new MarvelComicDeserializer())
                .create();

        Log.d("RUPESH",endpoint.getEndpoint());
        Retrofit marvelApiAdapter = new Retrofit.Builder()
                .baseUrl(endpoint.getEndpoint())
                .addConverterFactory(GsonConverterFactory.create(customGsonInstance))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

        mMarvelApi =  marvelApiAdapter.create(MarvelApi.class);

    }

    @Override
    public Observable<List<Comic>> getComics(int limit,int offset) {
        Log.d("RUPESH","Calling getComics"+offset);
        return mMarvelApi.getComics(limit,offset);
    }

}
