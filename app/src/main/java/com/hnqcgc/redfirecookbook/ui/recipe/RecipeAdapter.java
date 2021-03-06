package com.hnqcgc.redfirecookbook.ui.recipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.hnqcgc.redfirecookbook.R;
import com.hnqcgc.redfirecookbook.logic.model.recipedateils.Material;
import com.hnqcgc.redfirecookbook.logic.model.recipedateils.StepWork;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;

    private final List<String> infoList;

    private final List<Material> materials;

    private final List<StepWork> stepWorks;

    private static final int TITLE = 0;

    public static final int INFO = 1;

    private static final int MATERIAL = 2;

    private static final int STEP_WORK = 3;

    public RecipeAdapter(Context context, List<String> infoList, List<Material> materials, List<StepWork> stepWorks) {
        this.context = context;
        this.infoList = infoList;
        this.materials = materials;
        this.stepWorks = stepWorks;
    }

    static class TitleViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleText;

        public TitleViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = (TextView) itemView;
        }

    }

    static class InfoViewHolder extends RecyclerView.ViewHolder {

        private final TextView infoText;

        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);
            infoText = (TextView) itemView;
        }

    }

    static class MaterialViewHolder extends RecyclerView.ViewHolder {

        private final TextView foodName;

        private final TextView foodNumber;

        public MaterialViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodName);
            foodNumber = itemView.findViewById(R.id.foodNumber);
        }

    }

    static class StepWorkViewHolder extends RecyclerView.ViewHolder {

        private final ImageView stepImg;

        private final TextView stepContent;

        public StepWorkViewHolder(@NonNull View itemView) {
            super(itemView);
            stepImg = itemView.findViewById(R.id.stepImg);
            stepContent = itemView.findViewById(R.id.stepContent);
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder holder;
        switch (viewType) {
            case TITLE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.title, parent, false);
                holder = new TitleViewHolder(view);
                break;
            case INFO:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_info, parent, false);
                holder = new InfoViewHolder(view);
                break;
            case MATERIAL:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.material_item, parent, false);
                holder = new MaterialViewHolder(view);
                break;
            case STEP_WORK:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_work, parent,false);
                holder = new StepWorkViewHolder(view);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + viewType);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof StepWorkViewHolder) {
            setStepWork((StepWorkViewHolder) holder, position - (infoList.size() + materials.size() + 3));
        }else if (holder instanceof MaterialViewHolder) {
            setMaterial((MaterialViewHolder) holder, position - (infoList.size() + 2));
        }else if (holder instanceof InfoViewHolder) {
            setInfo((InfoViewHolder) holder, position -1);
        }else {
            setTitle((TitleViewHolder) holder, position);
        }
    }

    private void setStepWork(@NonNull StepWorkViewHolder holder, int position) {
        Glide.with(context)
                .load(stepWorks.get(position).getImg())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(60)))
                .into(holder.stepImg);
        holder.stepContent.setText(stepWorks.get(position).getStep());
    }

    private void setMaterial(@NonNull MaterialViewHolder holder, int position) {
        holder.foodName.setText
                (materials.get(position).getName());
        holder.foodNumber.setText
                (materials.get(position).getNum());
    }

    private void setInfo(@NonNull InfoViewHolder holder, int position) {
        holder.infoText.setText(infoList.get(position));
    }

    private void setTitle(@NonNull TitleViewHolder holder, int position) {
        int textId;
        if (position == 0){
            textId = R.string.recipe_info_text;
        }else if (position == infoList.size() +1) {
            textId = R.string.material_text;
        }else {
            textId = R.string.step_work_text;
        }
        holder.titleText.setText(textId);
    }

    @Override
    public int getItemCount() {
        return infoList.size() + materials.size() + stepWorks.size() + 3;
    }

    @Override
    public int getItemViewType(int position) {
        if (position > 0 && position < infoList.size() + 1){
            return INFO;
        }else if (position > infoList.size() +1 && position < (infoList.size() + materials.size() + 2)) {
            return MATERIAL;
        }else if (position > (infoList.size() + materials.size() + 2)){
            return STEP_WORK;
        }else {
            return TITLE;
        }
    }

}
