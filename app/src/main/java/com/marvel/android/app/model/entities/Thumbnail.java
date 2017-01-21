package com.marvel.android.app.model.entities;

import java.io.Serializable;

/**
 * Created by RUPESH on 1/19/2017.
 */
public class Thumbnail implements Serializable {
    private String path;
    private String extension;

    public String getImageUrl () {
        return String.format("%s.%s", path, extension);
    }

    @Override
    public String toString() {
        return "Thumbnail{" +
                "path='" + path + '\'' +
                ", extension='" + extension + '\'' +
                '}';
    }
}
