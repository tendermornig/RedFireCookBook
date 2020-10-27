package com.hnqcgc.redfirecookbook.ui.kitchendiary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hnqcgc.redfirecookbook.R;

public class KitchenDiaryFragment extends Fragment {

    private KitchenDiaryViewModel viewModel;

    private FloatingActionButton addDiaryBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(KitchenDiaryViewModel.class);
        View view = inflater.inflate(R.layout.fragment_kitchen_diary, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        addDiaryBtn = view.findViewById(R.id.addDiaryBtn);

        addDiaryBtn.setOnClickListener(v -> {

        });
    }


}
