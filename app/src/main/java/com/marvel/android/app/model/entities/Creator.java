package com.marvel.android.app.model.entities;

import java.io.Serializable;
import java.util.List;

/**
 * Created by RUPESH on 1/19/2017.
 */

public class Creator implements Serializable {
    int available;
    int returned;
    int collectionURI;
    List<Summary> creatorSummaryList;

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    public int getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(int collectionURI) {
        this.collectionURI = collectionURI;
    }

    public List<Summary> getCreatorSummaryList() {
        return creatorSummaryList;
    }

    public void setCreatorSummaryList(List<Summary> creatorSummaryList) {
        this.creatorSummaryList = creatorSummaryList;
    }
}
