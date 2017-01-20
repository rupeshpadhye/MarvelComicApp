package com.marvel.android.app.model.entities;

import java.util.List;

/**
 * Created by RUPESH on 1/19/2017.
 */

public class Creator {
    int available;
    int returned;
    int collectionURI;
    List<CreatorSummary> creatorSummaryList;

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

    public List<CreatorSummary> getCreatorSummaryList() {
        return creatorSummaryList;
    }

    public void setCreatorSummaryList(List<CreatorSummary> creatorSummaryList) {
        this.creatorSummaryList = creatorSummaryList;
    }
}
