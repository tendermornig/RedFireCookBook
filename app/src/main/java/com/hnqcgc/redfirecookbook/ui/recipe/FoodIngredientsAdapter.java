package com.hnqcgc.redfirecookbook.ui.recipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hnqcgc.redfirecookbook.R;
import com.hnqcgc.redfirecookbook.logic.model.Material;

import java.util.List;

public class FoodIngredientsAdapter extends BaseAdapter {

    private Context context;

    private List<Material> materials;

    public FoodIngredientsAdapter(Context context, List<Material> materials) {
        this.context = context;
        this.materials = materials;
    }

    @Override
    public int getCount() {
        return materials.size();
    }

    @Override
    public Object getItem(int position) {
        return materials.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.food_ingredients_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.foodName.setText(materials.get(position).getName());
        holder.foodNumber.setText(materials.get(position).getNum());
        return convertView;
    }

    static class ViewHolder {
        TextView foodName;
        TextView foodNumber;

        public ViewHolder(View itemView) {
            foodName = itemView.findViewById(R.id.foodName);
            foodNumber = itemView.findViewById(R.id.foodNumber);;
        }

    }

}
