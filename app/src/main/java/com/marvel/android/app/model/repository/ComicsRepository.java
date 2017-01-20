package com.marvel.android.app.model.repository;

import com.marvel.android.app.model.entities.Comic;

import java.util.List;

import rx.Observable;

/**
 * Created by RUPESH on 1/19/2017.
 */

public interface ComicsRepository {
    Observable<List<Comic>> getComics (int offset);
}
