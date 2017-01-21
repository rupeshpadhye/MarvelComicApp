package com.marvel.android.app.model.entities;

import java.io.Serializable;

/**
 * Created by RUPESH on 1/19/2017.
 */

public class Character implements Serializable {
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
    private String role;


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

    public void setId(int id) {
        this.id = id;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public void setComics(ComicsCollection comics) {
        this.comics = comics;
    }

    public void setSeries(ComicsCollection series) {
        this.series = series;
    }

    public void setStories(ComicsCollection stories) {
        this.stories = stories;
    }

    public void setEvents(ComicsCollection events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", imageResource=" + imageResource +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", thumbnail=" + thumbnail +
                ", resourceURI='" + resourceURI + '\'' +
                ", comics=" + comics +
                ", series=" + series +
                ", stories=" + stories +
                ", events=" + events +
                '}';
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
