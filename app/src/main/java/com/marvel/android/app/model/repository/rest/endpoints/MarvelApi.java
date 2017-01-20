package com.marvel.android.app.model.repository.rest.endpoints;

import com.marvel.android.app.model.entities.Character;
import com.marvel.android.app.model.entities.Comic;
import com.marvel.android.app.model.entities.Creator;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by RUPESH on 1/19/2017.
 */

public interface MarvelApi {
    @GET("/v1/public/comics")
    Observable<List<Comic>> getComics (@Query("limit") int limit,@Query("offset") int offset);

    @GET("/v1/public/comics/{comicId}/characters")
    Observable<List<Character>> getComicCharacters(@Path("comicId") int comicId);

    @GET("/v1/public/comics/{comicId}/creators")
    Observable<List<Creator>> getComicCreators(@Path("comicId") int comicId);

}
