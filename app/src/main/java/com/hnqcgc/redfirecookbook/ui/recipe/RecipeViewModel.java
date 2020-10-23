package com.hnqcgc.redfirecookbook.ui.recipe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.hnqcgc.redfirecookbook.logic.Repository;
import com.hnqcgc.redfirecookbook.logic.model.Collection;
import com.hnqcgc.redfirecookbook.logic.model.recipedateils.RecipeDetails;

public class RecipeViewModel extends ViewModel {

    private final MutableLiveData<Long> queryRecipeIdLiveData = new MutableLiveData<>();

    private final MutableLiveData<Collection> collectionMutableLiveData = new MutableLiveData<>();

    public LiveData<RecipeDetails> recipeDetailsLiveData = Transformations.switchMap(queryRecipeIdLiveData,
            input -> Repository.getInstance().searchRecipe(input));

    public LiveData<Long> insertReturnLiveData = Transformations.switchMap(
            collectionMutableLiveData, input -> Repository.getInstance().insertCollection(input));

    public void searchRecipe(long recipeId) {
        queryRecipeIdLiveData.setValue(recipeId);
    }

    public void insertCollection(Collection collection) {
        collectionMutableLiveData.setValue(collection);
    }

}
