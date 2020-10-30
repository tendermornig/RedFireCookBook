package com.hnqcgc.redfirecookbook.ui.allrecipe;

import com.hnqcgc.redfirecookbook.ui.allrecipe.AllRecipeViewModel.RefreshType;

import android.graphics.Rect;
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
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.hnqcgc.redfirecookbook.R;
import com.hnqcgc.redfirecookbook.logic.model.recipe.RecipeInfo;
import com.hnqcgc.redfirecookbook.util.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AllRecipeFragment extends Fragment {

    private static final String TAG = "AllRecipeFragment";

    public AllRecipeViewModel viewModel;

    private RecyclerView allRecipeRecycleView;

    private SwipeRefreshLayout swipeRefresh;

    private AllRecipeAdapter adapter;

    @Override
    public void onResume() {
        super.onResume();
        if (viewModel != null)
            viewModel.searchAllRecipeId();
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
        EditText searchRecipeEdit = view.findViewById(R.id.searchRecipeEdit);
        allRecipeRecycleView = view.findViewById(R.id.allRecipeRecycleView);
        swipeRefresh = view.findViewById(R.id.swipeRefresh);

        searchRecipeEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name = s.toString();
                swipeRefresh.setRefreshing(true);
                if (!name.isEmpty()) {
                    swipeRefresh.setEnabled(false);
                    viewModel.REFRESH_TYPE = RefreshType.SEARCH_RECIPE_INFO;
                    viewModel.searchRecipeInfo(name);
                }else {
                    viewModel.REFRESH_TYPE = RefreshType.TOP_REFRESH;
                    viewModel.infoList.clear();
                    viewModel.searchAllRecipe();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        swipeRefresh.setColorSchemeResources(R.color.grey_800_alpha_100);
        swipeRefresh.setOnRefreshListener(this::refreshTopRecipe);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (viewModel.infoList.size() == 0){
            refreshTopRecipe();
            viewModel.searchAllRecipeId();
        }

        setRecipeRecycleView();

        initViewModel();

    }

    private void initViewModel() {
        viewModel.allRecipe.observe(getViewLifecycleOwner(), recipe -> {
            if (recipe != null) {
                if (recipe.getCount() > viewModel.getRecipeCount())
                    viewModel.saveRecipeCount(recipe.getCount());
                if (viewModel.REFRESH_TYPE == RefreshType.TOP_REFRESH) {
                    viewModel.infoList.addAll(0, recipe.getResults());
                }else
                    viewModel.infoList.addAll(recipe.getResults());
                adapter.notifyDataSetChanged();
            }else {
                LogUtil.getInstance().d(TAG, "recipe is null");
            }
            swipeRefresh.setRefreshing(false);
        });

        viewModel.searchRecipeInfoResult.observe(getViewLifecycleOwner(), recipeInfo -> {
            swipeRefresh.setRefreshing(false);
            viewModel.infoList.clear();
            viewModel.infoList.addAll(recipeInfo);
            adapter.notifyDataSetChanged();
        });

        viewModel.allRecipeIdLiveData.observe(getViewLifecycleOwner(), longs -> {
            int index;
            List<Long> recipeIds = asRecipeIdList(viewModel.infoList);
            for (long recipeId : longs) {
                index = recipeIds.indexOf(recipeId);
                if (index != -1) {
                    RecipeInfo info = viewModel.infoList.get(index);
                    info.setCollection(true);
                    viewModel.infoList.set(index, info);
                    adapter.notifyItemChanged(index, R.id.collectionImg);
                }
            }
        });
    }

    private void setRecipeRecycleView() {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL);
        allRecipeRecycleView.setLayoutManager(manager);
        adapter = new AllRecipeAdapter(this, viewModel.infoList);
        allRecipeRecycleView.setAdapter(adapter);
        allRecipeRecycleView.addItemDecoration(new RecyclerView.ItemDecoration() {
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
        allRecipeRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (viewModel.REFRESH_TYPE != RefreshType.SEARCH_RECIPE_INFO) {
                    swipeRefresh.setEnabled(!recyclerView.canScrollVertically(-1));
                    if (!recyclerView.canScrollVertically(1)) {
                        viewModel.REFRESH_TYPE = RefreshType.BOTTOM_REFRESH;
                        viewModel.length += 10;
                        viewModel.searchAllRecipe();
                    }
                }
            }
        });
    }

    private void refreshTopRecipe() {
        viewModel.REFRESH_TYPE = RefreshType.TOP_REFRESH;
        viewModel.length = new Random().nextInt(viewModel.getRecipeCount());
        viewModel.searchAllRecipe();
        swipeRefresh.setRefreshing(true);
    }

    private List<Long> asRecipeIdList(List<RecipeInfo> recipeInfoList) {
        List<Long> recipeIds = new ArrayList<>();
        for (RecipeInfo info : recipeInfoList) {
            recipeIds.add(info.getRecipeId());
        }
        return recipeIds;
    }

}
