package com.hnqcgc.redfirecookbook.ui.allrecipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.hnqcgc.redfirecookbook.R;

public class AllRecipeFragment extends Fragment {

    private AllRecipeViewModel viewModel;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(AllRecipeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_all_recipe, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycleView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        AllRecipeAdapter adapter = new AllRecipeAdapter(getContext(), viewModel.infoList);
        recyclerView.setAdapter(adapter);
        viewModel.searchAllRecipe(viewModel.length);
        viewModel.allRecipe.observe(getViewLifecycleOwner(), recipe -> {
            if (recipe != null) {
                viewModel.infoList.addAll(0, recipe.getResults());
                adapter.notifyDataSetChanged();
            }else {
                Toast.makeText(getContext(), "数据未能获取", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
