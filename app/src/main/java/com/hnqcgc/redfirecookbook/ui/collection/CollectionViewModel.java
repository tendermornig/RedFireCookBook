package com.hnqcgc.redfirecookbook.ui.collection;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.hnqcgc.redfirecookbook.logic.Repository;
import com.hnqcgc.redfirecookbook.logic.model.Collection;

import java.util.ArrayList;
import java.util.List;

public class CollectionViewModel extends ViewModel {

    private final MutableLiveData<Object> loadAllRecipeLiveData = new MutableLiveData<>();

    private final MutableLiveData<String> searchRecipeNameLiveData = new MutableLiveData<>();

    public List<Collection> collections = new ArrayList<>();

    public final LiveData<List<Collection>> allCollectionLiveData = Transformations.switchMap(loadAllRecipeLiveData,
            input -> Repository.getInstance().loadAllCollection());

    public final LiveData<List<Collection>> searchCollectionLiveData = Transformations.switchMap(searchRecipeNameLiveData,
            input -> Repository.getInstance().searchCollection(input));

    public void loadAllCollection() {
        loadAllRecipeLiveData.setValue(loadAllRecipeLiveData.getValue());
    }

    public void searchRecipeName(String name) {
        searchRecipeNameLiveData.setValue(name);
    }

    public void deleteCollectionById(long recipeId) {
        Repository.getInstance().deleteCollectionById(recipeId);
    }

}
