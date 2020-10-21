package com.hnqcgc.redfirecookbook.logic.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipeDetails {

    private int recipeId;

    @SerializedName("material")
    private List<Material> materials;

    private List<Info> info;

    @SerializedName("step_word")
    private List<StepWork> stepWorks;

    private String title;

    private String img;

    public RecipeDetails() {
    }

    public RecipeDetails(int recipeId, List<Material> materials, List<Info> info, List<StepWork> stepWorks, String title, String img) {
        this.recipeId = recipeId;
        this.materials = materials;
        this.info = info;
        this.stepWorks = stepWorks;
        this.title = title;
        this.img = img;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public List<Info> getInfo() {
        return info;
    }

    public void setInfo(List<Info> info) {
        this.info = info;
    }

    public List<StepWork> getStepWorks() {
        return stepWorks;
    }

    public void setStepWorks(List<StepWork> stepWorks) {
        this.stepWorks = stepWorks;
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
