package com.hnqcgc.redfirecookbook.ui.kitchendiary;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hnqcgc.redfirecookbook.R;
import com.hnqcgc.redfirecookbook.ui.editdiary.EditDiaryActivity;

public class KitchenDiaryFragment extends Fragment {

    private KitchenDiaryViewModel viewModel;

    private RecyclerView diary;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(KitchenDiaryViewModel.class);
        View view = inflater.inflate(R.layout.fragment_kitchen_diary, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (viewModel.allKitchenDiary.size() == 0)
            viewModel.loadAllKitchenDiary();
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL);
        diary.setLayoutManager(manager);
        KitchenDiaryAdapter adapter = new KitchenDiaryAdapter(viewModel.allKitchenDiary);
        diary.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                if (parent.getChildAdapterPosition(view)%2 == 0) {
                    outRect.left = 30;
                }else {
                    outRect.left = 20;
                    outRect.right = 30;
                }
                outRect.top = 20;
            }
        });
        diary.setAdapter(adapter);

        viewModel.allKitchenDiaryLiveData.observe(getViewLifecycleOwner(), kitchenDiaries -> {
            viewModel.allKitchenDiary.clear();
            viewModel.allKitchenDiary.addAll(kitchenDiaries);
            adapter.notifyDataSetChanged();
        });
    }

    private void initView(View view) {
        diary = view.findViewById(R.id.diary);

        FloatingActionButton addDiaryBtn = view.findViewById(R.id.addDiaryBtn);

        addDiaryBtn.setOnClickListener(v -> EditDiaryActivity.startAddDiaryActivity(getContext()));
    }


}
