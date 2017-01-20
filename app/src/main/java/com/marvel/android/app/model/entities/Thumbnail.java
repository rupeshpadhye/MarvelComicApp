package com.marvel.android.app.model.entities;

/**
 * Created by RUPESH on 1/19/2017.
 */
public class Thumbnail {
    private String path;
    private String extension;

    public String getImageUrl () {
        return String.format("%s.%s", path, extension);
    }
}
