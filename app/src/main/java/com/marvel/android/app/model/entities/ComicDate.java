package com.marvel.android.app.model.entities;

import java.io.Serializable;

/**
 * Created by RUPESH on 1/19/2017.
 */
public class ComicDate implements Serializable {
    private String type;
    private String date;

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }
}
