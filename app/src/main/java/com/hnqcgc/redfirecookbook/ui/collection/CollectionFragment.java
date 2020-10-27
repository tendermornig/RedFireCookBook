package com.hnqcgc.redfirecookbook.ui.collection;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hnqcgc.redfirecookbook.R;
import com.hnqcgc.redfirecookbook.logic.model.Collection;

import java.util.Collections;
import java.util.List;

public class CollectionFragment extends Fragment {

    private static final String TAG = "CollectionFragment";

    public CollectionViewModel viewModel;

    private RecyclerView collectionRecycleView;

    private CollectionAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(CollectionViewModel.class);
        View view = inflater.inflate(R.layout.fragment_collection, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        collectionRecycleView = view.findViewById(R.id.collectionRecycleView);
        EditText searchRecipeEdit = view.findViewById(R.id.searchRecipeEdit);

        searchRecipeEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name = s.toString();
                if (!name.isEmpty()) {
                    viewModel.searchRecipeName(name);
                }else {
                    viewModel.loadAllCollection();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        searchRecipeEdit.setOnFocusChangeListener((v, hasFocus) -> {
            EditText _v=(EditText)v;
            if (!hasFocus) {// 失去焦点
                _v.setHint(_v.getTag().toString());
            } else {
                String hint=_v.getHint().toString();
                _v.setTag(hint);
                _v.setHint("");
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (viewModel.collections.size() == 0)
            viewModel.loadAllCollection();

        setCollectionRecipeRecycleView();

        setCollectionViewModel();

    }

    private void setCollectionViewModel() {
        viewModel.allCollectionLiveData.observe(getViewLifecycleOwner(), this::dataChange);
        viewModel.searchCollectionLiveData.observe(getViewLifecycleOwner(), this::dataChange);
    }

    private void dataChange(List<Collection> collections) {
        Collections.reverse(collections);
        viewModel.collections.clear();
        viewModel.collections.addAll(collections);
        adapter.notifyDataSetChanged();
    }

    private void setCollectionRecipeRecycleView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        collectionRecycleView.setLayoutManager(manager);
        adapter = new CollectionAdapter(this, viewModel.collections);
        collectionRecycleView.setAdapter(adapter);
    }

}
