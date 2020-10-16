package com.hnqcgc.redfirecookbook.logic.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipeDetails {

    private long recipeId;

    @SerializedName("material")
    private List<Material> materials;

    @SerializedName("flavour")
    private List<Flavour> flavours;

    @SerializedName("step_word")
    private List<StepWord> stepWords;

    private String title;

    private String img;

    public RecipeDetails(long recipeId, List<Material> materials, List<Flavour> flavours, List<StepWord> stepWords, String title, String img) {
        this.recipeId = recipeId;
        this.materials = materials;
        this.flavours = flavours;
        this.stepWords = stepWords;
        this.title = title;
        this.img = img;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public List<Flavour> getFlavours() {
        return flavours;
    }

    public void setFlavours(List<Flavour> flavours) {
        this.flavours = flavours;
    }

    public List<StepWord> getStepWords() {
        return stepWords;
    }

    public void setStepWords(List<StepWord> stepWords) {
        this.stepWords = stepWords;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
