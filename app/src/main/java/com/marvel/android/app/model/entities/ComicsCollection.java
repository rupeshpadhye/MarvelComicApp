package com.marvel.android.app.model.entities;

import java.io.Serializable;

/**
 * Created by RUPESH on 1/19/2017.
 */
public class ComicsCollection implements Serializable {
    private int available;
    private String collectionURI;
    public int getAvailable() {
        return available;
    }
    public String getCollectionURI() {
        return collectionURI;
    }
}
