package com.hnqcgc.redfirecookbook.logic.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hnqcgc.redfirecookbook.logic.model.collection.Collection;

@Database(version = 1, entities = {Collection.class})
public abstract class AppDatabase extends RoomDatabase {

    abstract CollectionDao collectionDao();

    private static AppDatabase instance = null;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "app_database")
                    .build();
        }
        return instance;
    }

}
