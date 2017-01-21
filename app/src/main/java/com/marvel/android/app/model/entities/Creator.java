package com.marvel.android.app.model.entities;

import java.io.Serializable;

/**
 * Created by RUPESH on 1/19/2017.
 */

public class Creator implements Serializable {
  private int id;
  private String fullName;
  private Thumbnail thumbnail;
  private  String role;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "Creator{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", thumbnail=" + thumbnail +
                '}';
    }
}
