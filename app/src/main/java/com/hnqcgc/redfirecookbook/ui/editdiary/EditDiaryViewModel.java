package com.hnqcgc.redfirecookbook.ui.editdiary;

import androidx.lifecycle.ViewModel;

import com.hnqcgc.redfirecookbook.logic.Repository;
import com.hnqcgc.redfirecookbook.logic.model.KitchenDiary;

public class EditDiaryViewModel extends ViewModel {

    public KitchenDiary kitchenDiary;

    public void insertKitchenDiary() {
        Repository.getInstance().insertKitchenDiary(kitchenDiary);
    }

    public void updateKitchenDiary() {
        Repository.getInstance().updateKitchenDiary(kitchenDiary);
    }

}
