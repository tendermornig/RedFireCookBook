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

    private final MutableLiveData<Object> searchRecipeNameLiveData = new MutableLiveData<>();

    public List<Collection> collections = new ArrayList<>();

    public final LiveData<List<Collection>> collectionsLiveData = Transformations.switchMap(searchRecipeNameLiveData,
            input -> Repository.getInstance().loadAllCollection());

    public void loadAllCollection() {
        searchRecipeNameLiveData.setValue(searchRecipeNameLiveData.getValue());
    }

}
