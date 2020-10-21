package com.hnqcgc.redfirecookbook.ui.recipe;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class FoodIngredientsListView extends ListView {

    public FoodIngredientsListView(Context context) {
        super(context);
    }

    public FoodIngredientsListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FoodIngredientsListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override//解决ListView在NestedScrollView中只显示一个子项的问题
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
