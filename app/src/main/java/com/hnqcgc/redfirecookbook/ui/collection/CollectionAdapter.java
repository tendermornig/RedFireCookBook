package com.hnqcgc.redfirecookbook.ui.collection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.hnqcgc.redfirecookbook.R;
import com.hnqcgc.redfirecookbook.logic.model.Collection;
import com.hnqcgc.redfirecookbook.ui.recipe.RecipeActivity;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {

    private final CollectionFragment fragment;

    private final List<Collection> collections;

    private final SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日收藏", Locale.getDefault());


    public CollectionAdapter(CollectionFragment fragment, List<Collection> collections) {
        this.fragment = fragment;
        this.collections = collections;
        format.setTimeZone(TimeZone.getTimeZone("Etc/GMT-8"));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView collectionRecipeImage;

        private final TextView collectionRecipeName;

        private final TextView collectionTime;

        private final TextView collectionRecipeMaterial;

        private final Button deleteCollectionBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            collectionRecipeImage = itemView.findViewById(R.id.collectionRecipeImage);
            collectionRecipeName = itemView.findViewById(R.id.collectionRecipeName);
            collectionTime = itemView.findViewById(R.id.collectionTime);
            collectionRecipeMaterial = itemView.findViewById(R.id.collectionRecipeMaterial);
            deleteCollectionBtn = itemView.findViewById(R.id.deleteCollectionBtn);
        }
    }

    @NonNull
    @Override
    public CollectionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(v -> {
            long recipeId = collections.get(holder.getAdapterPosition()).getRecipeId();
            RecipeActivity.startRecipeActivity(fragment.getContext(), recipeId);
        });
        holder.deleteCollectionBtn.setOnClickListener(
                v -> Snackbar.make(
                        v, "确定要删除吗！", Snackbar.LENGTH_SHORT)
                .setAction("确定", v1 -> {
                    long recipeId = collections.get(holder.getAdapterPosition()).getRecipeId();
                    fragment.viewModel.deleteCollectionById(recipeId);
                    fragment.viewModel.loadAllCollection();
                })
                .show());
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionAdapter.ViewHolder holder, int position) {
        Collection collection = collections.get(holder.getAdapterPosition());
        Glide.with(fragment)
                .load(collection.getImg())
                .into(holder.collectionRecipeImage);
        holder.collectionRecipeName.setText(collection.getTitle());
        holder.collectionTime.setText(format.format(collection.getCollectionTime()));
        holder.collectionRecipeMaterial.setText(collection.getMaterial());
    }

    @Override
    public int getItemCount() {
        return collections.size();
    }

}
