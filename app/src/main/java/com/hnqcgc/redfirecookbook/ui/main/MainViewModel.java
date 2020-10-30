package com.hnqcgc.redfirecookbook.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.hnqcgc.redfirecookbook.logic.Repository;

import java.util.List;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<Object> searchLastIdLiveData = new MutableLiveData<>();

    public LiveData<List<Long>> lastIdLiveData = Transformations.switchMap(searchLastIdLiveData, input -> Repository.getInstance().searchLastRecipeInfoId());

    public void loadAllRecipeInfo() {
        Repository.getInstance().loadAllRecipeInfo();
    }

    public void searchLastId() {
        searchLastIdLiveData.setValue(searchLastIdLiveData.getValue());
    }

    public int getRecipeCount() {
        return Repository.getInstance().getRecipeCount();
    }

}
