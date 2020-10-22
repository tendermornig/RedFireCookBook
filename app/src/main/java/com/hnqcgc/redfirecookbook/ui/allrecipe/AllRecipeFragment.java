package com.hnqcgc.redfirecookbook.ui.allrecipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.hnqcgc.redfirecookbook.R;
import com.hnqcgc.redfirecookbook.util.LogUtil;

import java.util.Random;

public class AllRecipeFragment extends Fragment {

    private static final String TAG = "AllRecipeFragment";

    private AllRecipeViewModel viewModel;

    private RecyclerView allRecipeRecycleView;

    private SwipeRefreshLayout swipeRefresh;

    private AllRecipeAdapter adapter;

    public RefreshType REFRESH_TYPE;

    private final int NO_POSITION = -1;

    enum RefreshType{
        TOP_REFRESH,
        BOTTOM_REFRESH
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(AllRecipeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_all_recipe, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        allRecipeRecycleView = view.findViewById(R.id.allRecipeRecycleView);
        swipeRefresh = view.findViewById(R.id.swipeRefresh);

        swipeRefresh.setColorSchemeResources(R.color.grey_800_alpha_100);
        swipeRefresh.setOnRefreshListener(this::refreshRecipe);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (viewModel.infoList.size() == 0)
            refreshRecipe();

        setRecipeRecycleView();

        initViewModel();

    }

    private void initViewModel() {
        viewModel.allRecipe.observe(getViewLifecycleOwner(), recipe -> {
            if (recipe != null) {
                if (recipe.getCount() > viewModel.getRecipeCount())
                    viewModel.saveRecipeCount(recipe.getCount());
                if (REFRESH_TYPE == RefreshType.TOP_REFRESH) {
                    viewModel.infoList.addAll(0, recipe.getResults());
                    swipeRefresh.setRefreshing(false);
                }else
                    viewModel.infoList.addAll(recipe.getResults());
                adapter.notifyDataSetChanged();
            }else {
                LogUtil.getInstance().d(TAG, "recipe is null");
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    private void setRecipeRecycleView() {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL);
        allRecipeRecycleView.setLayoutManager(manager);
        adapter = new AllRecipeAdapter(getContext(), viewModel.infoList);
        allRecipeRecycleView.setAdapter(adapter);
        allRecipeRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isBottomViewVisible()) {
                    REFRESH_TYPE = RefreshType.BOTTOM_REFRESH;
                    viewModel.searchAllRecipe(viewModel.length += 10);
                }
            }
        });
    }

    private void refreshRecipe() {
        REFRESH_TYPE = RefreshType.TOP_REFRESH;
        viewModel.length = new Random().nextInt(viewModel.getRecipeCount());
        viewModel.searchAllRecipe(viewModel.length);
        swipeRefresh.setRefreshing(true);
    }

    private int getLastVisibleItemPosition() {
        RecyclerView.LayoutManager manager = allRecipeRecycleView.getLayoutManager();
        if (manager instanceof StaggeredGridLayoutManager)
            return ((StaggeredGridLayoutManager) manager).findLastVisibleItemPositions(null)[0];
        return NO_POSITION;
    }

    private boolean isBottomViewVisible() {
        int lastVisibleItem = getLastVisibleItemPosition();
        return lastVisibleItem != NO_POSITION && lastVisibleItem == adapter.getItemCount() - 1;
    }

}
