package com.hnqcgc.redfirecookbook.ui.kitchendiary;

import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hnqcgc.redfirecookbook.R;
import com.hnqcgc.redfirecookbook.logic.model.KitchenDiary;
import com.hnqcgc.redfirecookbook.ui.editdiary.EditDiaryActivity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class KitchenDiaryFragment extends Fragment {

    private static final String TAG = "KitchenDiaryFragment";

    private KitchenDiaryViewModel viewModel;

    private RecyclerView diary;

    private KitchenDiaryAdapter adapter;

    private RelativeLayout noDataLayout;
    private FloatingActionButton addDiaryBtn;

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
        setKitchenDiaryRecycle();

        setViewModel();
    }

    private void setViewModel() {
        viewModel.allKitchenDiaryLiveData.observe(getViewLifecycleOwner(), this::diaryDataChanged);
        viewModel.searchResultLiveData.observe(getViewLifecycleOwner(), this::diaryDataChanged);
    }

    private void diaryDataChanged(List<KitchenDiary> kitchenDiaries) {
        viewModel.allKitchenDiary.clear();
        viewModel.allKitchenDiary.addAll(kitchenDiaries);
        if (viewModel.allKitchenDiary.size() == 0) {
            noDataLayout.setVisibility(View.VISIBLE);
            diary.setVisibility(View.GONE);
            addDiaryBtn.setVisibility(View.GONE);
        }else {
            noDataLayout.setVisibility(View.GONE);
            diary.setVisibility(View.VISIBLE);
            addDiaryBtn.setVisibility(View.VISIBLE);
            adapter.notifyDataSetChanged();
        }
    }

    private void setKitchenDiaryRecycle() {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL) {

            private Method markItemDecorInsetsDirty = null;

            private boolean reflectError = false;

            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                if (markItemDecorInsetsDirty == null && !reflectError) {
                    try {
                        markItemDecorInsetsDirty = RecyclerView.class.getDeclaredMethod("markItemDecorInsetsDirty");
                        markItemDecorInsetsDirty.setAccessible(true);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                        reflectError = true;
                    }
                }
                // 更新条件
                if (markItemDecorInsetsDirty != null && state.willRunSimpleAnimations()) {
                    // noinspection TryWithIdenticalCatches
                    try {
                        markItemDecorInsetsDirty.invoke(diary);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                super.onLayoutChildren(recycler, state);
            }

            @Override
            public void requestSimpleAnimationsInNextLayout() {
                super.requestSimpleAnimationsInNextLayout();
                if (markItemDecorInsetsDirty != null) {
                    // noinspection TryWithIdenticalCatches
                    try {
                        markItemDecorInsetsDirty.invoke(diary);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        diary.setLayoutManager(manager);
        adapter = new KitchenDiaryAdapter(viewModel.allKitchenDiary) {
            @Override
            void deleteDiary(long id) {
                viewModel.deleteKitchenDiaryById(id);
            }
        };
        diary.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
                if (params.getSpanIndex()%2 == 0) {
                    outRect.left = 30;
                }else {
                    outRect.left = 15;
                    outRect.right = 30;
                }
                outRect.top = 10;
                outRect.bottom = 10;
            }
        });
        diary.setAdapter(adapter);
    }

    private void initView(View view) {
        EditText searchDiaryEdit = view.findViewById(R.id.searchDiaryEdit);
        noDataLayout = view.findViewById(R.id.noDataLayout);
        diary = view.findViewById(R.id.diary);
        addDiaryBtn = view.findViewById(R.id.addDiaryBtn);
        addDiaryBtn.setOnClickListener(v -> EditDiaryActivity.startAddDiaryActivity(getContext()));

        searchDiaryEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchContent = s.toString();
                if (!searchContent.isEmpty()) {
                    viewModel.searchDiary(searchContent);
                }else {
                    viewModel.loadAllKitchenDiary();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
