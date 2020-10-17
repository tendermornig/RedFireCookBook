package com.hnqcgc.redfirecookbook.ui.allrecipe;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.hnqcgc.redfirecookbook.logic.Repository;
import com.hnqcgc.redfirecookbook.logic.model.QueryNumber;
import com.hnqcgc.redfirecookbook.logic.model.Recipe;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllRecipeViewModel extends ViewModel {

    private final MutableLiveData<QueryNumber> queryNumberLiveData = new MutableLiveData<>();

    public LiveData<Recipe> allRecipe = Transformations.switchMap(queryNumberLiveData,
            input -> Repository.getInstance().searchAllRecipe(input.getLimit(), input.getOffset()));

    public void searchAllRecipe(int limit, int offset) {
        queryNumberLiveData.setValue(new QueryNumber(limit, offset));
    }

}
