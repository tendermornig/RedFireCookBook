package com.hnqcgc.redfirecookbook.ui.recipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.hnqcgc.redfirecookbook.R;
import com.hnqcgc.redfirecookbook.util.LogUtil;

import java.util.List;

public class RecipeActivity extends AppCompatActivity {

    private static final String TAG = "RecipeActivity";

    enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    private State mCurrentState = State.IDLE;

    private RecipeViewModel viewModel;

    private AppBarLayout appBar;

    private ImageView recipeImg;

    private TextView recipeTitle;

    private LinearLayout infoLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        viewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        getRecipeId();
        initView();
        initViewModel();
    }

    private void initViewModel() {
        viewModel.recipeDetailsLiveData.observe(this, recipeDetails -> {
            if (recipeDetails != null) {
                recipeTitle.setText(recipeDetails.getTitle());
                Glide.with(this)
                        .load(recipeDetails.getImg())
                        .into(recipeImg);
                List<String> dataList = recipeDetails.getInfo().get(0).asList();
                infoLayout.removeAllViews();
                for (String str:
                     dataList) {
                    View view = LayoutInflater.from(this).inflate(android.R.layout.test_list_item,
                            infoLayout, false);
                    TextView infoText = view.findViewById(android.R.id.text1);
                    infoText.setText(str);
                    infoLayout.addView(view);
                }
            }else {
                Toast.makeText(this, "数据获取失败", Toast.LENGTH_SHORT).show();
                LogUtil.getInstance().d(TAG, "recipeDetails is null");
            }
        });
    }

    private void getRecipeId() {
        int recipeId = getIntent().getIntExtra("recipeId", -1);
        if (recipeId != -1)
            viewModel.searchRecipe(recipeId);
        else {
            Toast.makeText(this, "数据获取错误", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void isDarkTheme(ActionBar supportActionBar) {
        int flag = getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
        if (flag == Configuration.UI_MODE_NIGHT_YES) {
            recipeTitle.setTextColor(Color.WHITE);
        }else {
            recipeTitle.setTextColor(Color.BLACK);
            appBar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
                if (verticalOffset == 0) {
                    if (mCurrentState != State.EXPANDED) {
                        supportActionBar.setHomeAsUpIndicator(R.drawable.white_back);
                    }
                    mCurrentState = State.EXPANDED;
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (mCurrentState != State.COLLAPSED) {
                        supportActionBar.setHomeAsUpIndicator(R.drawable.black_back);
                    }
                    mCurrentState = State.COLLAPSED;
                } else {
                    if (mCurrentState != State.IDLE) {
                        supportActionBar.setHomeAsUpIndicator(R.drawable.white_back);
                    }
                    mCurrentState = State.IDLE;
                }
            });
        }
    }

    private void initView() {
        appBar = findViewById(R.id.appBar);
        recipeTitle = findViewById(R.id.recipeTitle);
        Toolbar toolbar = findViewById(R.id.toolBar);
        recipeImg = findViewById(R.id.recipeImg);
        infoLayout = findViewById(R.id.infoLayout);

        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowTitleEnabled(false);
            isDarkTheme(supportActionBar);
        }
    }

    public static void startRecipeActivity(Context context, int recipeId) {
        Intent intent = new Intent(context, RecipeActivity.class);
        intent.putExtra("recipeId", recipeId);
        context.startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}