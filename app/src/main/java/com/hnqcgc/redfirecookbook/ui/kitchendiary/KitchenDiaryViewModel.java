package com.hnqcgc.redfirecookbook.ui.kitchendiary;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.hnqcgc.redfirecookbook.logic.Repository;
import com.hnqcgc.redfirecookbook.logic.model.KitchenDiary;

import java.util.ArrayList;
import java.util.List;

public class KitchenDiaryViewModel extends ViewModel {

    private final MutableLiveData<Object> loadAllKitchenDiaryLiveData = new MutableLiveData<>();

    private final MutableLiveData<String> searchDiaryLiveData = new MutableLiveData<>();

    public List<KitchenDiary> allKitchenDiary = new ArrayList<>();

    public LiveData<List<KitchenDiary>> allKitchenDiaryLiveData = Transformations.switchMap(
            loadAllKitchenDiaryLiveData, input -> Repository.getInstance().loadAllKitchenDiary());

    public LiveData<List<KitchenDiary>> searchResultLiveData = Transformations.switchMap(
            searchDiaryLiveData, input -> Repository.getInstance().searchDiary(input));

    public void loadAllKitchenDiary() {
        loadAllKitchenDiaryLiveData.setValue(loadAllKitchenDiaryLiveData.getValue());
    }

    public void deleteKitchenDiaryById(long id) {
        Repository.getInstance().deleteKitchenDiaryById(id);
    }

    public void searchDiary(String searchContent) {
        searchDiaryLiveData.setValue(searchContent);
    }

}
