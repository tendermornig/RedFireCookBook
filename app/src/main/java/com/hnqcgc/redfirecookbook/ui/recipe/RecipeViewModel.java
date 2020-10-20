package com.hnqcgc.redfirecookbook.ui.recipe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.hnqcgc.redfirecookbook.logic.Repository;
import com.hnqcgc.redfirecookbook.logic.model.RecipeDetails;

public class RecipeViewModel extends ViewModel {

    private final MutableLiveData<Integer> queryRecipeIdLiveData = new MutableLiveData<>();

    public LiveData<RecipeDetails> recipeDetailsLiveData = Transformations.switchMap(queryRecipeIdLiveData,
            input -> Repository.getInstance().searchRecipe(input));

    public void searchRecipe(int recipeId) {
        queryRecipeIdLiveData.setValue(recipeId);
    }

}
