package com.hnqcgc.redfirecookbook.ui.allrecipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.hnqcgc.redfirecookbook.R;

public class AllRecipeFragment extends Fragment {

    private AllRecipeViewModel allRecipeViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        allRecipeViewModel = new ViewModelProvider(this).get(AllRecipeViewModel.class);
        return inflater.inflate(R.layout.fragment_all_recipe, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
