package com.hnqcgc.redfirecookbook.logic;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.hnqcgc.redfirecookbook.logic.model.Recipe;
import com.hnqcgc.redfirecookbook.logic.model.RecipeDetails;
import com.hnqcgc.redfirecookbook.logic.network.RedFireCookBookNetwork;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    
    private static Repository repository;
    
    private Repository() {}

    public static Repository getInstance() {
        if (repository == null) {
            repository = new Repository();
        }
        return repository;
    }

    public MutableLiveData<Recipe> searchAllRecipe(int offset) {
        MutableLiveData<Recipe> recipeLiveData = new MutableLiveData<>();
        Call<Recipe> call = RedFireCookBookNetwork.getInstance().searchAllRecipe(offset);
        call.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(@NonNull Call<Recipe> call, @NonNull Response<Recipe> response) {
                if (response.body() != null) {
                    recipeLiveData.postValue(response.body());
                }else {
                    throw new RuntimeException("response body is null");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Recipe> call, @NonNull Throwable t) {
                throw new RuntimeException(t.getMessage());
            }
        });
        return recipeLiveData;
    }

    public MutableLiveData<RecipeDetails> searchRecipe(long recipeId) {
        MutableLiveData<RecipeDetails> recipeDetailsLiveData = new MutableLiveData<>();
        Call<RecipeDetails> call = RedFireCookBookNetwork.getInstance().searchRecipe(recipeId);
        call.enqueue(new Callback<RecipeDetails>() {
            @Override
            public void onResponse(@NonNull Call<RecipeDetails> call, @NonNull Response<RecipeDetails> response) {
                if (response.body() != null) {
                    recipeDetailsLiveData.postValue(response.body());
                }else {
                    throw new RuntimeException("response body is null");
                }
            }

            @Override
            public void onFailure(@NonNull Call<RecipeDetails> call, @NonNull Throwable t) {
                throw new RuntimeException(t.getMessage());
            }
        });
        return recipeDetailsLiveData;
    }

}
