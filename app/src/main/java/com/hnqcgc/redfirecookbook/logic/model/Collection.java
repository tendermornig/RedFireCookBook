package com.hnqcgc.redfirecookbook.logic.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Collection {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private int recipeId;

    private String title;

    private String message;

    private String gredient;

    private String cover;

    private String category;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getGredient() {
        return gredient;
    }

    public void setGredient(String gredient) {
        this.gredient = gredient;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
