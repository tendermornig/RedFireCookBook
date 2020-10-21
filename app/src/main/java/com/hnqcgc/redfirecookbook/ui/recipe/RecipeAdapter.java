package com.hnqcgc.redfirecookbook.ui.recipe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hnqcgc.redfirecookbook.R;
import com.hnqcgc.redfirecookbook.logic.model.Material;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<String> infoList;

    private final List<Material> materials;

    private static final int TITLE = 0;

    public static final int INFO = 1;

    private static final int MATERIAL = 2;

    private static final int STEP_WORK = 3;

    public RecipeAdapter(List<String> infoList, List<Material> materials) {
        this.infoList = infoList;
        this.materials = materials;
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

        public StepWorkViewHolder(@NonNull View itemView) {
            super(itemView);
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
        if (holder instanceof TitleViewHolder) {
            TitleViewHolder viewHolder = (TitleViewHolder) holder;
            if (position == 0){
                viewHolder.titleText.setText(R.string.recipe_info_text);
            }else if (position == infoList.size() +1) {
                viewHolder.titleText.setText(R.string.material_text);
            }else {
                viewHolder.titleText.setText(R.string.step_work_text);
            }
        }else if (holder instanceof InfoViewHolder) {
            InfoViewHolder viewHolder = (InfoViewHolder) holder;
            viewHolder.infoText.setText(infoList.get(position - 1));
        }else if (holder instanceof MaterialViewHolder) {
            MaterialViewHolder viewHolder = (MaterialViewHolder) holder;
            int pos = position - (infoList.size() + 2);
            viewHolder.foodName.setText
                    (materials.get(pos).getName());
            viewHolder.foodNumber.setText
                    (materials.get(pos).getNum());
        }
    }

    @Override
    public int getItemCount() {
        return infoList.size() + materials.size() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position > 0 && position < infoList.size() + 1){
            return INFO;
        }else if (position > infoList.size() +1 && position < (infoList.size() + materials.size() + 2)) {
            return MATERIAL;
        }else {
            return TITLE;
        }
    }

}
