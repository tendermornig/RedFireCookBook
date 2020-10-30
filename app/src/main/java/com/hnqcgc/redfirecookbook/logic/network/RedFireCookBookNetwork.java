package com.hnqcgc.redfirecookbook.logic.network;



import com.hnqcgc.redfirecookbook.logic.model.recipe.Recipe;
import com.hnqcgc.redfirecookbook.logic.model.recipedateils.RecipeDetails;

import retrofit2.Call;

public class RedFireCookBookNetwork {

    private static RedFireCookBookNetwork redFireCookBookNetwork;

    private final RecipeService service = ServiceCreator.getInstance()
            .create(RecipeService.class);

    private RedFireCookBookNetwork() {}

    public static RedFireCookBookNetwork getInstance() {
        if (redFireCookBookNetwork == null) {
            redFireCookBookNetwork = new RedFireCookBookNetwork();
        }
        return redFireCookBookNetwork;
    }

    public Call<Recipe> searchAllRecipe(int offset) {
        return service.searchAllRecipe(10, offset);
    }

    public Call<Recipe> loadAllRecipe(int limit) {
        return service.searchAllRecipe(limit, 0);
    }

    public Call<RecipeDetails> searchRecipe(long recipeId) {
        return service.searchRecipe(recipeId);
    }

}
