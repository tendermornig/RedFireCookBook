package com.hnqcgc.redfirecookbook.logic.network;

import com.hnqcgc.redfirecookbook.logic.model.Recipe;
import com.hnqcgc.redfirecookbook.logic.model.RecipeDetails;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RecipeService {

    @GET("v1/recipe/")
    Call<Recipe> searchAllRecipe(@Query("limit") int limit, @Query("offset") int offset);

    @GET("v1/recipe/{recipeId}")
    Call<RecipeDetails> searchRecipe(@Path("recipeId") long recipeId);

}
