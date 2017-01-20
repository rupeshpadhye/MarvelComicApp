package com.marvel.android.app.model.repository.rest;

import com.marvel.android.app.model.entities.Character;
import com.marvel.android.app.model.entities.Comic;
import com.marvel.android.app.model.entities.Creator;

import java.util.List;

import rx.Observable;

/**
 * Created by RUPESH on 1/19/2017.
 */

public interface RestDataSource {
     String PARAM_API_KEY   = "apikey";
     String PARAM_HASH      = "hash";
     String PARAM_TIMESTAMP = "ts";

    Observable<List<Comic>> getComics(int defaultComicsLimit, int mCurrentOffset);
    Observable<List<Character>> getComicCharacters(int comicId);
    Observable<List<Creator>> getComicCreators(int comicId);
}
