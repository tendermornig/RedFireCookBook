package com.hnqcgc.redfirecookbook.logic.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.hnqcgc.redfirecookbook.logic.model.recipe.RecipeInfo;

import java.util.List;

@Dao
public interface RecipeInfoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAllRecipeInfo(List<RecipeInfo> recipeInfoList);

    @Query("select id from RecipeInfo order by id desc limit 1")
    LiveData<List<Long>> searchLastRecipeInfoId();

    @Query("select * from RecipeInfo where title like :title")
    LiveData<List<RecipeInfo>> searchRecipeInfo(String title);

}
