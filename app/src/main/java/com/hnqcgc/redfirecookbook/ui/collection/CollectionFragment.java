package com.hnqcgc.redfirecookbook.ui.collection;

import android.os.Bundle;
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

public class CollectionFragment extends Fragment {

    private CollectionViewModel viewModel;

    private EditText searchRecipeEdit;

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
        searchRecipeEdit = view.findViewById(R.id.searchRecipeEdit);
        collectionRecycleView = view.findViewById(R.id.collectionRecycleView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (viewModel.collections.size() == 0)
            viewModel.loadAllCollection();

        setCollectionRecipeRecycleView();

        viewModel.collectionsLiveData.observe(getViewLifecycleOwner(), collections -> {
            viewModel.collections.addAll(collections);
            adapter.notifyDataSetChanged();
        });

    }

    private void setCollectionRecipeRecycleView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        collectionRecycleView.setLayoutManager(manager);
        adapter = new CollectionAdapter(getContext(), viewModel.collections);
        collectionRecycleView.setAdapter(adapter);
    }

}
