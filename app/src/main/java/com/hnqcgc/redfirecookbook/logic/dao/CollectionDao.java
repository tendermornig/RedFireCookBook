package com.hnqcgc.redfirecookbook.logic.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.hnqcgc.redfirecookbook.logic.model.Collection;

import java.util.List;

@Dao
public interface CollectionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCollection(Collection collection);

    @Query("select * from Collection ORDER BY collectionTime ASC")
    LiveData<List<Collection>> loadAllCollection();

    @Query("select recipeId from Collection")
    LiveData<List<Long>> loadAllCollectionRecipeId();

    @Query("select * from Collection where title like :title")
    LiveData<List<Collection>> searchCollection(String title);

    @Query("select recipeId from Collection where recipeId = :recipeId limit 1")
    LiveData<List<Long>> isCollection(long recipeId);

    @Query("delete from Collection where recipeId = :recipeId")
    void deleteCollectionById(long recipeId);

}
