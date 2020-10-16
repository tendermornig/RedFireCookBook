package com.hnqcgc.redfirecookbook.logic.network;



import com.hnqcgc.redfirecookbook.logic.model.Recipe;
import com.hnqcgc.redfirecookbook.logic.model.RecipeDetails;

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

    public Call<Recipe> searchAllRecipe(int limit, int offset) {
        return service.searchAllRecipe(limit, offset);
    }

    public Call<RecipeDetails> searchRecipe(long recipeId) {
        return service.searchRecipe(recipeId);
    }

}
