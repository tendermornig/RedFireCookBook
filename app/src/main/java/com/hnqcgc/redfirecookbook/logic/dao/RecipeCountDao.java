package com.hnqcgc.redfirecookbook.logic.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.hnqcgc.redfirecookbook.RedFireCookBookApplication;

public class RecipeCountDao {

    private static RecipeCountDao allRecipeDao;

    private RecipeCountDao() {}

    public static RecipeCountDao getInstance() {
        if (allRecipeDao == null) {
            allRecipeDao = new RecipeCountDao();
        }
        return allRecipeDao;
    }

    public void saveRecipeCount(int count) {
        SharedPreferences.Editor edit = sharedPreferences().edit();
        edit.putInt("count", count);
        edit.apply();
    }

    public int getRecipeCount() {
        return sharedPreferences().getInt("count", 36352);
    }

    private SharedPreferences sharedPreferences() {
        return RedFireCookBookApplication.getContext().getSharedPreferences("all_recipe", Context.MODE_PRIVATE);
    }
}
