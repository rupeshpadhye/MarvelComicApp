package com.marvel.android.app.model.endpoints;

import com.marvel.android.app.model.entities.Comic;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by RUPESH on 1/19/2017.
 */

public interface MarvelApi {
    @GET("/v1/public/characters")
    Observable<List<Comic>> getComics (@Query("offset") int offset);

}
