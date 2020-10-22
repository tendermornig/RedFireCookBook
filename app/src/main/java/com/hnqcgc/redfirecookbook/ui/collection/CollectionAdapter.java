package com.hnqcgc.redfirecookbook.ui.collection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hnqcgc.redfirecookbook.R;
import com.hnqcgc.redfirecookbook.logic.model.Collection;
import com.hnqcgc.redfirecookbook.ui.recipe.RecipeActivity;

import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {

    private final Context context;

    private final List<Collection> collections;

    public CollectionAdapter(Context context, List<Collection> collections) {
        this.context = context;
        this.collections = collections;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView collectionRecipeImage;

        private final TextView collectionRecipeName;

        private final TextView collectionRecipeCategory;

        private final TextView collectionRecipeMaterial;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            collectionRecipeImage = itemView.findViewById(R.id.collectionRecipeImage);
            collectionRecipeName = itemView.findViewById(R.id.collectionRecipeName);
            collectionRecipeCategory = itemView.findViewById(R.id.collectionRecipeCategory);
            collectionRecipeMaterial = itemView.findViewById(R.id.collectionRecipeMaterial);
        }
    }

    @NonNull
    @Override
    public CollectionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        int recipeId = collections.get(holder.getAdapterPosition()).getRecipeId();
        holder.itemView.setOnClickListener(v -> RecipeActivity.startRecipeActivity(
                context, recipeId));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionAdapter.ViewHolder holder, int position) {
        Collection collection = collections.get(holder.getAdapterPosition());
        Glide.with(context)
                .load(collection.getCover())
                .into(holder.collectionRecipeImage);
        holder.collectionRecipeName.setText(collection.getTitle());
        holder.collectionRecipeCategory.setText(collection.getCategory());
        holder.collectionRecipeMaterial.setText(collection.getGredient());
    }

    @Override
    public int getItemCount() {
        return collections.size();
    }

}
