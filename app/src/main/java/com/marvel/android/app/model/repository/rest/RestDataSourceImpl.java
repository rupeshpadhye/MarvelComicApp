package com.marvel.android.app.model.repository.rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.marvel.android.app.model.entities.Character;
import com.marvel.android.app.model.entities.Comic;
import com.marvel.android.app.model.entities.Creator;
import com.marvel.android.app.model.repository.rest.deserializer.MarvelResultDeserializer;
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
                        new MarvelResultDeserializer<Comic>())
                .registerTypeAdapter(new TypeToken<List<Character>>() {}.getType(),
                        new MarvelResultDeserializer<Character>())
                .registerTypeAdapter(new TypeToken<List<Creator>>() {}.getType(),
                        new MarvelResultDeserializer<Creator>())
                .create();

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
        return mMarvelApi.getComics(limit,offset);
    }

    @Override
    public Observable<List<Character>> getComicCharacters(int comicId) {
        return mMarvelApi.getComicCharacters(comicId);
    }

    @Override
    public Observable<List<Creator>> getComicCreators(int comicId) {
        return mMarvelApi.getComicCreators(comicId);
    }

}
