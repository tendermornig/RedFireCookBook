package com.hnqcgc.redfirecookbook.logic;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.hnqcgc.redfirecookbook.RedFireCookBookApplication;
import com.hnqcgc.redfirecookbook.logic.dao.RecipeCountDao;
import com.hnqcgc.redfirecookbook.logic.db.RedFireCookBookDB;
import com.hnqcgc.redfirecookbook.logic.model.Collection;
import com.hnqcgc.redfirecookbook.logic.model.recipe.Recipe;
import com.hnqcgc.redfirecookbook.logic.model.recipedateils.RecipeDetails;
import com.hnqcgc.redfirecookbook.logic.network.RedFireCookBookNetwork;
import com.hnqcgc.redfirecookbook.util.LogUtil;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private static final String TAG = "Repository";
    
    private static Repository repository;
    
    private Repository() {}

    public static Repository getInstance() {
        if (repository == null) {
            repository = new Repository();
        }
        return repository;
    }

    public MutableLiveData<Recipe> searchAllRecipe(int offset) {
        MutableLiveData<Recipe> recipeLiveData = new MutableLiveData<>();
        Call<Recipe> call = RedFireCookBookNetwork.getInstance().searchAllRecipe(offset);
        call.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(@NonNull Call<Recipe> call, @NonNull Response<Recipe> response) {
                recipeLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Recipe> call, @NonNull Throwable t) {
                Toast.makeText(RedFireCookBookApplication.getContext(),
                        "数据访问失败：" + t.getMessage(), Toast.LENGTH_SHORT).show();
                LogUtil.getInstance().d(TAG, t.getMessage());
            }
        });
        return recipeLiveData;
    }

    public MutableLiveData<RecipeDetails> searchRecipe(int recipeId) {
        MutableLiveData<RecipeDetails> recipeDetailsLiveData = new MutableLiveData<>();
        Call<RecipeDetails> call = RedFireCookBookNetwork.getInstance().searchRecipe(recipeId);
        call.enqueue(new Callback<RecipeDetails>() {
            @Override
            public void onResponse(@NonNull Call<RecipeDetails> call, @NonNull Response<RecipeDetails> response) {
                recipeDetailsLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<RecipeDetails> call, @NonNull Throwable t) {
                Toast.makeText(RedFireCookBookApplication.getContext(),
                        "数据访问失败：" + t.getMessage(), Toast.LENGTH_SHORT).show();
                LogUtil.getInstance().d(TAG, t.getMessage());
            }
        });
        return recipeDetailsLiveData;
    }

    public void saveRecipeCount(int count) {
        RecipeCountDao.getInstance().saveRecipeCount(count);
    }

    public int getRecipeCount() {
        return RecipeCountDao.getInstance().getRecipeCount();
    }

    public int insertCollection(Collection collection) {
        return RedFireCookBookDB.getInstance().insertCollection(collection);
    }

    public List<Collection> loadAllCollection() {
        return RedFireCookBookDB.getInstance().loadAllCollection();
    }

    public int deleteCollectionById(int id) {
        return RedFireCookBookDB.getInstance().deleteCollectionById(id);
    }

}
