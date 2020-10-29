package com.hnqcgc.redfirecookbook.ui.kitchendiary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hnqcgc.redfirecookbook.R;
import com.hnqcgc.redfirecookbook.logic.model.KitchenDiary;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class KitchenDiaryAdapter extends RecyclerView.Adapter<KitchenDiaryAdapter.ViewHolder> {

    private final List<KitchenDiary> allKitchenDiary;

    private final SimpleDateFormat format = new SimpleDateFormat("MM月dd日", Locale.getDefault());

    public KitchenDiaryAdapter(List<KitchenDiary> allKitchenDiary) {
        this.allKitchenDiary = allKitchenDiary;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;

        private final TextView content;

        private final TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            date = itemView.findViewById(R.id.date);
        }

    }

    @NonNull
    @Override
    public KitchenDiaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diary_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KitchenDiaryAdapter.ViewHolder holder, int position) {
        KitchenDiary diary = allKitchenDiary.get(holder.getAdapterPosition());
        if ("".equals(diary.getTitle())) {
            holder.title.setVisibility(View.GONE);
        }else {
            holder.title.setVisibility(View.VISIBLE);
            holder.title.setText(diary.getTitle());
        }
        if ("".equals(diary.getContent())) {
            holder.content.setVisibility(View.GONE);
        }else {
            holder.content.setVisibility(View.VISIBLE);
            holder.content.setText(diary.getContent());
        }
        holder.date.setText(format.format(diary.getLastWriteDate()));
    }

    @Override
    public int getItemCount() {
        return allKitchenDiary.size();
    }

}
