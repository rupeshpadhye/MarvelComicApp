package com.marvel.android.app.model.entities;

/**
 * Created by RUPESH on 1/19/2017.
 */

public class Character {
    private int id;
    private int imageResource;
    private String name;
    private String description;
    private Thumbnail thumbnail;
    private String resourceURI;
    private ComicsCollection comics;
    private ComicsCollection series;
    private ComicsCollection stories;
    private ComicsCollection events;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return (thumbnail != null) ? thumbnail.getImageUrl() : null;
    }

    public int getImageResource() {
        return imageResource;
    }

    public ComicsCollection getSeries() {
        return series;
    }

    public ComicsCollection getStories() {
        return stories;
    }

    public ComicsCollection getEvents() {
        return events;
    }

    public ComicsCollection getComics() {
        return comics;
    }
}
