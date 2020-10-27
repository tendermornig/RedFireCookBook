package com.hnqcgc.redfirecookbook.logic.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hnqcgc.redfirecookbook.RedFireCookBookApplication;
import com.hnqcgc.redfirecookbook.logic.dao.CollectionDao;
import com.hnqcgc.redfirecookbook.logic.model.Collection;
import com.hnqcgc.redfirecookbook.logic.model.KitchenDiary;

@Database(version = 1, entities = {Collection.class, KitchenDiary.class})
public abstract class AppDatabase extends RoomDatabase {

    abstract CollectionDao collectionDao();

    abstract KitchenDiary kitchenDiaryDao();

    private static AppDatabase instance = null;

    public static AppDatabase getInstance() {
        if (instance == null) {
            instance = Room.databaseBuilder(RedFireCookBookApplication.getContext().getApplicationContext(),
                    AppDatabase.class, "app_database")
                    .build();
        }
        return instance;
    }

}
