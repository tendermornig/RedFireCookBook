package com.hnqcgc.redfirecookbook.logic.db;

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

    public int insertCollection(Collection collection) {
        return collectionDao.insertCollection(collection);
    }

    public List<Collection> loadAllCollection() {
        return collectionDao.loadAllCollection();
    }

    public int deleteCollectionById(int id) {
        return collectionDao.deleteCollectionById(id);
    }

}
