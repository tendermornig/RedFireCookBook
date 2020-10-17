package com.hnqcgc.redfirecookbook.ui.allrecipe;

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
import com.hnqcgc.redfirecookbook.logic.model.RecipeInfo;

import java.util.List;

public class AllRecipeAdapter extends RecyclerView.Adapter<AllRecipeAdapter.ViewHolder> {

    private final List<RecipeInfo> infoList;
    private final Context context;

    public AllRecipeAdapter(Context context, List<RecipeInfo> infoList) {
        this.context = context;
        this.infoList = infoList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView recipeImage;
        private final TextView titleText;
        private final TextView messageText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeImage = itemView.findViewById(R.id.recipeImage);
            titleText = itemView.findViewById(R.id.titleText);
            messageText = itemView.findViewById(R.id.messageText);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecipeInfo recipeInfo = infoList.get(holder.getAdapterPosition());
        Glide.with(context)
                .load(recipeInfo.getCover())
                .into(holder.recipeImage);
        holder.titleText.setText(recipeInfo.getTitle());
        if (!"".equals(recipeInfo.getMessage())) {
            holder.messageText.setText(recipeInfo.getMessage());
        }else {
            holder.messageText.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }

}
