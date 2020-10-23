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
    long insertCollection(Collection collection);

    @Query("select * from Collection")
    LiveData<List<Collection>> loadAllCollection();

    @Query("select * from Collection where title like :title")
    List<Collection> searchCollection(String title);

    @Query("delete from Collection where recipeId = :recipeId")
    int deleteCollectionById(long recipeId);

}
