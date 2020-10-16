package com.hnqcgc.redfirecookbook.logic;

import com.hnqcgc.redfirecookbook.logic.model.Recipe;
import com.hnqcgc.redfirecookbook.logic.model.RecipeDetails;
import com.hnqcgc.redfirecookbook.logic.network.RedFireCookBookNetwork;

import retrofit2.Call;
import retrofit2.Callback;

public class Repository {
    
    private static Repository repository;
    
    private Repository() {}

    public static Repository getInstance() {
        if (repository == null) {
            repository = new Repository();
        }
        return repository;
    }

    public void searchAllRecipe(int limit, int offset, Callback<Recipe> callback) {
        Call<Recipe> call = RedFireCookBookNetwork.getInstance().searchAllRecipe(limit, offset);
        call.enqueue(callback);
    }

    public void searchRecipe(long recipeId, Callback<RecipeDetails> callback) {
        Call<RecipeDetails> call = RedFireCookBookNetwork.getInstance().searchRecipe(recipeId);
        call.enqueue(callback);
    }

}
