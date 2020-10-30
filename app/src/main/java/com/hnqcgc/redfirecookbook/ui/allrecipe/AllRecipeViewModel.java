package com.hnqcgc.redfirecookbook.ui.allrecipe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.hnqcgc.redfirecookbook.logic.Repository;
import com.hnqcgc.redfirecookbook.logic.db.RedFireCookBookDB;
import com.hnqcgc.redfirecookbook.logic.model.recipe.Recipe;
import com.hnqcgc.redfirecookbook.logic.model.recipe.RecipeInfo;

import java.util.ArrayList;
import java.util.List;

public class AllRecipeViewModel extends ViewModel {

    public RefreshType REFRESH_TYPE;

    enum RefreshType{
        TOP_REFRESH,
        BOTTOM_REFRESH,
        SEARCH_RECIPE_INFO
    }

    private final MutableLiveData<Integer> queryPositionLiveData = new MutableLiveData<>();

    private final MutableLiveData<Object> searchAllRecipeIdLiveData = new MutableLiveData<>();

    private final MutableLiveData<String> titleLiveData = new MutableLiveData<>();

    public final LiveData<List<Long>> allRecipeIdLiveData = Transformations.switchMap(
            searchAllRecipeIdLiveData, input -> Repository.getInstance().loadAllCollectionRecipeId());

    public LiveData<Recipe> allRecipe = Transformations.switchMap(queryPositionLiveData,
            input -> Repository.getInstance().searchAllRecipe(input));

    public LiveData<List<RecipeInfo>> searchRecipeInfoResult = Transformations.switchMap(
            titleLiveData, input -> Repository.getInstance().searchRecipeInfo(input));

    public List<RecipeInfo> infoList = new ArrayList<>();

    public int length;

    public void searchAllRecipe() {
        if (REFRESH_TYPE != RefreshType.SEARCH_RECIPE_INFO) {
            queryPositionLiveData.setValue(length);
        }
    }

    public void searchAllRecipeId() {
        searchAllRecipeIdLiveData.setValue(searchAllRecipeIdLiveData.getValue());
    }

    public void searchRecipeInfo(String title) {
        titleLiveData.setValue(title);
    }

    public void saveRecipeCount(int count) {
        Repository.getInstance().saveRecipeCount(count);
    }

    public int getRecipeCount() {
        return Repository.getInstance().getRecipeCount();
    }

}
