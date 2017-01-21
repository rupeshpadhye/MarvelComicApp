package com.marvel.android.app.model.entities;

import java.io.Serializable;
import java.util.List;

/**
 * Created by RUPESH on 1/19/2017.
 */
public class ComicsCollection implements Serializable {
    private int available;
    private String collectionURI;
    private List<Summary> items;

    public void setAvailable(int available) {
        this.available = available;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }
    public int getAvailable() {
        return available;
    }
    public String getCollectionURI() {
        return collectionURI;
    }


    public List<Summary> getItems() {
        return items;
    }

    public void setItems(List<Summary> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ComicsCollection{" +
                "available=" + available +
                ", collectionURI='" + collectionURI + '\'' +
                ", items=" + items +
                '}';
    }
}
