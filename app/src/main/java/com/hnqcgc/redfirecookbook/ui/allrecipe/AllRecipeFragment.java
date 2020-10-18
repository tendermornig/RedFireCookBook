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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.hnqcgc.redfirecookbook.R;

import java.util.Random;

public class AllRecipeFragment extends Fragment {

    private AllRecipeViewModel viewModel;

    private RecyclerView recyclerView;

    private SwipeRefreshLayout swipeRefresh;

    private AllRecipeAdapter adapter;

    private final int NO_POSITION = -1;

    private int REFRESH_TYPE;

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
        swipeRefresh = view.findViewById(R.id.swipeRefresh);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (viewModel.infoList.size() == 0)
            refreshRecipe();

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new AllRecipeAdapter(getContext(), viewModel.infoList);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isBottomViewVisible()) {
                    REFRESH_TYPE = 1;
                    viewModel.searchAllRecipe(viewModel.length += 10);
                }
            }
        });

        viewModel.allRecipe.observe(getViewLifecycleOwner(), recipe -> {
            if (recipe != null) {
                if (recipe.getCount() > viewModel.getRecipeCount())
                    viewModel.saveRecipeCount(recipe.getCount());
                if (REFRESH_TYPE == 0) {
                    viewModel.infoList.addAll(0, recipe.getResults());
                    swipeRefresh.setRefreshing(false);
                }else
                    viewModel.infoList.addAll(recipe.getResults());
                adapter.notifyDataSetChanged();
            }else {
                Toast.makeText(getContext(), "数据未能获取", Toast.LENGTH_SHORT).show();
            }
        });

        swipeRefresh.setColorSchemeResources(R.color.red_500);
        swipeRefresh.setOnRefreshListener(this::refreshRecipe);
    }

    private void refreshRecipe() {
        REFRESH_TYPE = 0;
        viewModel.length = new Random().nextInt(viewModel.getRecipeCount());
        viewModel.searchAllRecipe(viewModel.length);
        swipeRefresh.setRefreshing(true);
    }

    private int getLastVisibleItemPosition() {
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof StaggeredGridLayoutManager)
            return ((StaggeredGridLayoutManager) manager).findLastVisibleItemPositions(null)[0];
        return NO_POSITION;
    }

    private boolean isBottomViewVisible() {
        int lastVisibleItem = getLastVisibleItemPosition();
        return lastVisibleItem != NO_POSITION && lastVisibleItem == adapter.getItemCount() - 1;
    }

}
