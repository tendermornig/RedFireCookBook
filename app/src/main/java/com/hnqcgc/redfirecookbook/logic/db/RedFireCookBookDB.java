package com.hnqcgc.redfirecookbook.logic.db;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;

import com.hnqcgc.redfirecookbook.logic.dao.CollectionDao;
import com.hnqcgc.redfirecookbook.logic.dao.KitchenDiaryDao;
import com.hnqcgc.redfirecookbook.logic.dao.RecipeInfoDao;
import com.hnqcgc.redfirecookbook.logic.model.Collection;
import com.hnqcgc.redfirecookbook.logic.model.KitchenDiary;
import com.hnqcgc.redfirecookbook.logic.model.recipe.RecipeInfo;

import java.util.List;

public class RedFireCookBookDB {

    private static RedFireCookBookDB redFireCookBookDB;

    private RedFireCookBookDB(){}

    private static final CollectionDao collectionDao = AppDatabase.getInstance().collectionDao();

    private static final KitchenDiaryDao kitchenDiaryDao = AppDatabase.getInstance().kitchenDiaryDao();

    private static final RecipeInfoDao recipeInfoDao = AppDatabase.getInstance().recipeInfoDao();

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

    public void insertKitchenDiary(KitchenDiary kitchenDiary) {
        kitchenDiaryDao.insertKitchenDiary(kitchenDiary);
    }

    public void deleteKitchenDiaryById(long id) {
        kitchenDiaryDao.deleteKitchenDiaryById(id);
    }

    public void updateKitchenDiary(KitchenDiary kitchenDiary) {
        kitchenDiaryDao.updateKitchenDiary(kitchenDiary);
    }

    public LiveData<List<KitchenDiary>> loadAllKitchenDiary() {
        return kitchenDiaryDao.loadAllKitchenDiary();
    }

    public LiveData<List<KitchenDiary>> searchDiary(String searchContent) {
        return kitchenDiaryDao.searchDiary("%" + searchContent + "%");
    }

    public void insertAllRecipeInfo(List<RecipeInfo> recipeInfoList) {
        recipeInfoDao.insertAllRecipeInfo(recipeInfoList);
    }

    public LiveData<List<Long>> searchLastRecipeInfoId() {
        return recipeInfoDao.searchLastRecipeInfoId();
    }

    public LiveData<List<RecipeInfo>> searchRecipeInfo(String title) {
        return recipeInfoDao.searchRecipeInfo("%" + title + "%");
    }

}
