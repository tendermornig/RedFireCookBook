package com.hnqcgc.redfirecookbook.ui.allrecipe;

import com.hnqcgc.redfirecookbook.ui.allrecipe.AllRecipeViewModel.RefreshType;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.hnqcgc.redfirecookbook.R;
import com.hnqcgc.redfirecookbook.logic.model.recipe.RecipeInfo;
import com.hnqcgc.redfirecookbook.ui.recipe.RecipeActivity;

import java.util.List;

public class AllRecipeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<RecipeInfo> infoList;

    private final AllRecipeFragment fragment;

    private static final int TYPE_NORMAL_ITEM = 0;

    private static final int TYPE_BOTTOM_REFRESH_ITEM = 1;

    public AllRecipeAdapter(AllRecipeFragment fragment, List<RecipeInfo> infoList) {
        this.fragment = fragment;
        this.infoList = infoList;
    }

    static class NormalViewHolder extends RecyclerView.ViewHolder {

        private final ImageView recipeImage;
        private final TextView titleText;
        private final TextView messageText;
        private final ImageView collectionImg;

        public NormalViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeImage = itemView.findViewById(R.id.recipeImage);
            titleText = itemView.findViewById(R.id.titleText);
            messageText = itemView.findViewById(R.id.messageText);
            collectionImg = itemView.findViewById(R.id.collectionImg);
        }

    }

    static class BottomRefreshViewHolder extends RecyclerView.ViewHolder {

        public BottomRefreshViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORMAL_ITEM) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recipe_item, parent, false);
            NormalViewHolder holder = new NormalViewHolder(view);
            holder.itemView.setOnClickListener(v -> {
                int position = holder.getAdapterPosition();
                long recipeId = infoList.get(position).getRecipeId();
                RecipeActivity.startRecipeActivity(parent.getContext(), recipeId);
            });
            return holder;
        }else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.bottom_refresh_layout, parent, false);
            return new BottomRefreshViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalViewHolder) {
            NormalViewHolder viewHolder = (NormalViewHolder) holder;
            RecipeInfo recipeInfo = infoList.get(holder.getAdapterPosition());
            Glide.with(fragment)
                    .load(recipeInfo.getCover())
                    .error(R.drawable.img_load_fail)
                    .into(viewHolder.recipeImage);
            viewHolder.titleText.setText(recipeInfo.getTitle());
            if (recipeInfo.isCollection()) {
                viewHolder.collectionImg.setImageResource(R.drawable.collection);
            }else {
                viewHolder.collectionImg.setImageResource(R.drawable.not_collection);
            }
            if (!"".equals(recipeInfo.getMessage())) {
                viewHolder.messageText.setVisibility(View.VISIBLE);
                viewHolder.messageText.setText(recipeInfo.getMessage());
            }else {
                viewHolder.messageText.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (holder instanceof BottomRefreshViewHolder) {
            //让底部刷新布局支持瀑布流布局
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                ((StaggeredGridLayoutManager.LayoutParams) lp).setFullSpan(true);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position < infoList.size()) {
            return TYPE_NORMAL_ITEM;
        }else {
            return TYPE_BOTTOM_REFRESH_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return infoList.size() == 0 || fragment.viewModel.REFRESH_TYPE == RefreshType.SEARCH_RECIPE_INFO ? infoList.size():infoList.size() + 1;
    }

}
