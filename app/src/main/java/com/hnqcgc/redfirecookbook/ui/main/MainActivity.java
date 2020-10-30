package com.hnqcgc.redfirecookbook.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hnqcgc.redfirecookbook.R;
import com.hnqcgc.redfirecookbook.logic.Repository;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private boolean isBack = false;

    private final Handler handler = new Handler();

    private final Runnable runnable = () -> isBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.searchLastId();
        viewModel.lastIdLiveData.observe(this, longs -> {
            if (longs.size() == 0 || viewModel.getRecipeCount() > longs.get(0)) {
                viewModel.loadAllRecipeInfo();
            }
        });
        BottomNavigationView navigationView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public static void startMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (isBack) {
            super.onBackPressed();
            return;
        }
        Toast.makeText(this, "再次返回退出应用", Toast.LENGTH_SHORT).show();
        isBack = true;
        handler.postDelayed(runnable, 1500);
    }

}