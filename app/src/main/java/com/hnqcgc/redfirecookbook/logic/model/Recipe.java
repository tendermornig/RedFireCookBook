package com.hnqcgc.redfirecookbook.logic.model;

import java.util.List;

public class Recipe {

    private int count;

    private String next;

    private String previous;

    private List<RecipeInfo> results;

    public Recipe(int count, String next, String previous, List<RecipeInfo> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<RecipeInfo> getResults() {
        return results;
    }

    public void setResults(List<RecipeInfo> results) {
        this.results = results;
    }

}
