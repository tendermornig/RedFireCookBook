package com.hnqcgc.redfirecookbook.logic.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetails {

    private int recipeId;

    @SerializedName("material")
    private List<Material> materials;

    private List<Info> info;

    @SerializedName("step_word")
    private List<StepWord> stepWords;

    private String title;

    private String img;

    public RecipeDetails() {
    }

    public RecipeDetails(int recipeId, List<Material> materials, List<Info> info, List<StepWord> stepWords, String title, String img) {
        this.recipeId = recipeId;
        this.materials = materials;
        this.info = info;
        this.stepWords = stepWords;
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

    public static class Info {

        private String flavour;

        private String method;

        private String time;

        private String degree;

        public Info() {
        }

        public Info(String flavour, String method, String time, String degree) {
            this.flavour = flavour;
            this.method = method;
            this.time = time;
            this.degree = degree;
        }

        public String getFlavour() {
            return "风味：" + flavour;
        }

        public void setFlavour(String flavour) {
            this.flavour = flavour;
        }

        public String getMethod() {
            return "做法：" + method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getTime() {
            return "制作时间" + time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getDegree() {
            return "制作温度：" + degree;
        }

        public void setDegree(String degree) {
            this.degree = degree;
        }

        public List<String> asList() {
            List<String> info = new ArrayList<>();
            if (!"".equals(flavour)) {
                info.add(getFlavour());
            }
            if (!"".equals(method)) {
                info.add(getMethod());
            }
            if (!"".equals(time)) {
                info.add(getTime());
            }
            if (!"".equals(degree)) {
                info.add(getDegree());
            }
            return info;
        }

    }

}
