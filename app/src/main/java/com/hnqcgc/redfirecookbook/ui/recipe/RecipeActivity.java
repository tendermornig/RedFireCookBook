package com.hnqcgc.redfirecookbook.ui.recipe;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hnqcgc.redfirecookbook.R;
import com.hnqcgc.redfirecookbook.RedFireCookBookApplication;
import com.hnqcgc.redfirecookbook.logic.model.Collection;
import com.hnqcgc.redfirecookbook.logic.model.recipedateils.Material;
import com.hnqcgc.redfirecookbook.logic.model.recipedateils.StepWork;
import com.hnqcgc.redfirecookbook.util.LogUtil;

import java.util.Date;
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

    private RecyclerView recipeBody;

    private FloatingActionButton collectionFloatBtn;

    private IsCollection COLLECTION_TYPE;

    enum IsCollection {
        COLLECTION,
        NOT_COLLECTION
    }

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
                List<String> infoList = recipeDetails.getInfo().get(0).asList();
                List<Material> materials = recipeDetails.getMaterials();
                List<StepWork> stepWorks = recipeDetails.getStepWorks();
                RecipeAdapter adapter = new RecipeAdapter(this, infoList, materials, stepWorks);
                recipeBody.setAdapter(adapter);
                collectionFloatBtn.setOnClickListener(v -> {
                    if (COLLECTION_TYPE == IsCollection.NOT_COLLECTION) {
                        Collection collection = new Collection();
                        collection.setRecipeId(recipeDetails.getRecipeId());
                        collection.setTitle(recipeDetails.getTitle());
                        collection.setImg(recipeDetails.getImg());
                        collection.setMaterial(recipeDetails.toMaterialString());
                        collection.setCollectionTime(new Date().getTime());
                        viewModel.insertCollection(collection);
                        changeCollection(IsCollection.COLLECTION, R.drawable.collection);
                    }else {
                        viewModel.deleteCollectionById(recipeDetails.getRecipeId());
                        changeCollection(IsCollection.NOT_COLLECTION, R.drawable.not_collection);
                    }
                });
            }else {
                LogUtil.getInstance().d(TAG, "recipeDetails is null");
            }
        });
        viewModel.isCollectionLiveData.observe(this, longs -> {
            if (longs.size() > 0) {
                changeCollection(IsCollection.COLLECTION, R.drawable.collection);
            }else {
                changeCollection(IsCollection.NOT_COLLECTION, R.drawable.not_collection);
            }
        });
    }

    private void changeCollection(IsCollection type, int icon) {
        COLLECTION_TYPE = type;
        collectionFloatBtn.setImageResource(icon);
        collectionFloatBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#FF0000")));
    }

    private void getRecipeId() {
        long recipeId = getIntent().getLongExtra("recipeId", -1);
        if (recipeId != -1)
            viewModel.searchRecipe(recipeId);
        else {
            Toast.makeText(this, "数据获取错误", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void setThemeUI(ActionBar supportActionBar) {
        if (RedFireCookBookApplication.isDarkTheme(getResources())) {
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
        recipeBody = findViewById(R.id.recipeBody);
        collectionFloatBtn = findViewById(R.id.collectionFloatBtn);

        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowTitleEnabled(false);
            setThemeUI(supportActionBar);
        }

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recipeBody.setLayoutManager(manager);

    }

    public static void startRecipeActivity(Context context, long recipeId) {
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