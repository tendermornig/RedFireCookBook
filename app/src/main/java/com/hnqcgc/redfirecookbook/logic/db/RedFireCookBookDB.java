package com.hnqcgc.redfirecookbook.logic.db;

import androidx.lifecycle.LiveData;

import com.hnqcgc.redfirecookbook.logic.dao.CollectionDao;
import com.hnqcgc.redfirecookbook.logic.model.Collection;

import java.util.List;

public class RedFireCookBookDB {

    private static RedFireCookBookDB redFireCookBookDB;

    private RedFireCookBookDB(){}

    public static CollectionDao collectionDao = AppDatabase.getInstance().collectionDao();

    public static RedFireCookBookDB getInstance() {
        if (redFireCookBookDB == null) {
            redFireCookBookDB = new RedFireCookBookDB();
        }
        return redFireCookBookDB;
    }

    public void insertCollection(Collection collection) {
        collectionDao.insertCollection(collection);
    }

    public LiveData<List<Collection>> loadAllCollection() {
        return collectionDao.loadAllCollection();
    }

    public LiveData<List<Long>> loadAllCollectionRecipeId() {
        return collectionDao.loadAllCollectionRecipeId();
    }

    public LiveData<List<Collection>> searchCollection(String title){
        return collectionDao.searchCollection("%" + title + "%");
    }

    public LiveData<List<Long>> isCollection(long recipeId) {
        return collectionDao.isCollection(recipeId);
    }

    public void deleteCollectionById(long recipeId) {
        collectionDao.deleteCollectionById(recipeId);
    }

}
