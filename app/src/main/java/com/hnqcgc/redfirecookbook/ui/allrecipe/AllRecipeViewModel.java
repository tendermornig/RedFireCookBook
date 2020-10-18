package com.hnqcgc.redfirecookbook.ui.allrecipe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.hnqcgc.redfirecookbook.logic.Repository;
import com.hnqcgc.redfirecookbook.logic.model.Recipe;
import com.hnqcgc.redfirecookbook.logic.model.RecipeInfo;

import java.util.ArrayList;
import java.util.List;

public class AllRecipeViewModel extends ViewModel {

    private final MutableLiveData<Integer> queryNumberLiveData = new MutableLiveData<>();

    public List<RecipeInfo> infoList = new ArrayList<>();

    public int length;

    public LiveData<Recipe> allRecipe = Transformations.switchMap(queryNumberLiveData,
            input -> Repository.getInstance().searchAllRecipe(input));

    public void searchAllRecipe(int offset) {
        queryNumberLiveData.setValue(offset);
    }

    public void saveRecipeCount(int count) {
        Repository.getInstance().saveRecipeCount(count);
    }

    public int getRecipeCount() {
        return Repository.getInstance().getRecipeCount();
    }

}
