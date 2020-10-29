package com.hnqcgc.redfirecookbook.logic.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.hnqcgc.redfirecookbook.logic.model.KitchenDiary;

import java.util.List;

@Dao
public interface KitchenDiaryDao {

    @Insert
    void insertKitchenDiary(KitchenDiary kitchenDiary);

    @Query("delete from KitchenDiary where id = :id")
    void deleteKitchenDiaryById(long id);

    @Update
    void updateKitchenDiary(KitchenDiary kitchenDiary);

    @Query("select * from KitchenDiary order by lastWriteDate desc")
    LiveData<List<KitchenDiary>> loadAllKitchenDiary();

    @Query("select * from KitchenDiary where title like :searchContent or content like :searchContent order by lastWriteDate desc")
    LiveData<List<KitchenDiary>> searchDiary(String searchContent);

}
