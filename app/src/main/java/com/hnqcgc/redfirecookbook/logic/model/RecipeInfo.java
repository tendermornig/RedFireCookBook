package com.hnqcgc.redfirecookbook.logic.model;

public class RecipeInfo {

    private int recipeId;

    private String title;

    private String message;

    private String gredient;

    private String cover;

    private String category;

    public RecipeInfo(int recipeId, String title, String message, String gredient, String cover, String category) {
        this.recipeId = recipeId;
        this.title = title;
        this.message = message;
        this.gredient = gredient;
        this.cover = cover;
        this.category = category;
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
