package com.marvel.android.app.model.entities;

import java.io.Serializable;
import java.util.List;

/**
 * Created by RUPESH on 1/19/2017.
 */

public class Comic implements Serializable {

    private String id;
    private String title;
    private String description;
    private Thumbnail thumbnail;
    private List<TextObject> textObjects;
    private List<ComicDate> dates;
    private int pageCount;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<TextObject> getTextObjects() {
        return textObjects;
    }

    public void setTextObjects(List<TextObject> textObjects) {
        this.textObjects = textObjects;
    }

    public List<ComicDate> getDates() {
        return dates;
    }

    public void setDates(List<ComicDate> dates) {
        this.dates = dates;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}

