package com.hnqcgc.redfirecookbook.ui.editdiary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.hnqcgc.redfirecookbook.R;

public class EditDiaryActivity extends AppCompatActivity {

    private EditText titleEdit;
    private EditText contentEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_diary);
        initView();
    }

    private void initView() {
        Toolbar toolBar = findViewById(R.id.toolBar);
        ImageView overImg = findViewById(R.id.overImg);
        titleEdit = findViewById(R.id.titleEdit);
        contentEdit = findViewById(R.id.contentEdit);
        setSupportActionBar(toolBar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        overImg.setOnClickListener(v -> {
           saveDiary();
        });
    }

    private void saveDiary() {
        String contentStr = contentEdit.getText().toString();
        if (!"".equals(contentStr)) {

        }else {
            Toast.makeText(this, "内容不可未空", Toast.LENGTH_SHORT).show();
        }
    }

    public static void startAddDiaryActivity(Context context) {
        Intent intent = new Intent(context, EditDiaryActivity.class);
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