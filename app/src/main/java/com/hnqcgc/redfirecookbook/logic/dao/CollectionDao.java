package com.hnqcgc.redfirecookbook.logic.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.hnqcgc.redfirecookbook.logic.model.collection.Collection;

import java.util.List;

@Dao
public interface CollectionDao {

    @Insert
    int insertCollection(Collection collection);

    @Query("select * from Collection")
    List<Collection> loadAllCollection();

    @Query("delete from Collection where id = :id")
    int deleteCollectionById(int id);

}
