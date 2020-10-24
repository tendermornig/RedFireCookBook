package com.hnqcgc.redfirecookbook.ui.recipe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.hnqcgc.redfirecookbook.logic.Repository;
import com.hnqcgc.redfirecookbook.logic.model.Collection;
import com.hnqcgc.redfirecookbook.logic.model.recipedateils.RecipeDetails;

import java.util.List;

public class RecipeViewModel extends ViewModel {

    private final MutableLiveData<Long> queryRecipeIdLiveData = new MutableLiveData<>();

    private final MutableLiveData<Long> searchIsCollectionLiveData = new MutableLiveData<>();

    public LiveData<RecipeDetails> recipeDetailsLiveData = Transformations.switchMap(queryRecipeIdLiveData,
            input -> Repository.getInstance().searchRecipe(input));


    public LiveData<List<Long>> isCollectionLiveData = Transformations.switchMap(
            searchIsCollectionLiveData, input -> Repository.getInstance().isCollection(input));

    public void searchRecipe(long recipeId) {
        queryRecipeIdLiveData.setValue(recipeId);
        searchIsCollectionLiveData.setValue(recipeId);
    }

    public void insertCollection(Collection collection) {
        Repository.getInstance().insertCollection(collection);
    }

    public void deleteCollectionById(long recipeId) {
        Repository.getInstance().deleteCollectionById(recipeId);
    }

}
