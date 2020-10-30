package com.hnqcgc.redfirecookbook.logic.model.recipe;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = "recipeId", unique = true)})
public class RecipeInfo {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private long recipeId;

    private String title;

    private String message;

    private String gredient;

    private String cover;

    private String category;

    @Ignore
    private boolean isCollection = false;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
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

    public boolean isCollection() {
        return isCollection;
    }

    public void setCollection(boolean collection) {
        this.isCollection = collection;
    }

}
