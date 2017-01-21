package com.marvel.android.app.model.entities;

import java.io.Serializable;
import java.util.List;

/**
 * Created by RUPESH on 1/19/2017.
 */

public class Comic implements Serializable {

    private int id;
    private String title;
    private String description;
    private String isbn;
    private Thumbnail thumbnail;
    private List<TextObject> textObjects;
    private List<ComicDate> dates;
    private ComicsCollection creators;
    private ComicsCollection characters;
    private int pageCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    public String getIsbn() {
        return isbn;
    }


    public ComicsCollection getCreators() {
        return creators;
    }

    public void setCreators(ComicsCollection creators) {
        this.creators = creators;
    }

    public ComicsCollection getCharacters() {
        return characters;
    }

    public void setCharacters(ComicsCollection characters) {
        this.characters = characters;
    }


    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Comic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isbn='" + isbn + '\'' +
                ", thumbnail=" + thumbnail +
                ", textObjects=" + textObjects +
                ", dates=" + dates +
                ", creators=" + creators +
                ", characters=" + characters +
                ", pageCount=" + pageCount +
                '}';
    }
}

