package com.hnqcgc.redfirecookbook.ui.allrecipe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.hnqcgc.redfirecookbook.logic.Repository;
import com.hnqcgc.redfirecookbook.logic.model.recipe.Recipe;
import com.hnqcgc.redfirecookbook.logic.model.recipe.RecipeInfo;

import java.util.ArrayList;
import java.util.List;

public class AllRecipeViewModel extends ViewModel {

    private final MutableLiveData<Integer> queryPositionLiveData = new MutableLiveData<>();

    public List<RecipeInfo> infoList = new ArrayList<>();

    public int length;

    public AllRecipeFragment.RefreshType REFRESH_TYPE;

    public LiveData<Recipe> allRecipe = Transformations.switchMap(queryPositionLiveData,
            input -> Repository.getInstance().searchAllRecipe(input));

    public void searchAllRecipe(int offset) {
        queryPositionLiveData.setValue(offset);
    }

    public void saveRecipeCount(int count) {
        Repository.getInstance().saveRecipeCount(count);
    }

    public int getRecipeCount() {
        return Repository.getInstance().getRecipeCount();
    }

}
