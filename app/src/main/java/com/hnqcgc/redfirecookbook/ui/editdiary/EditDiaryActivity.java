package com.hnqcgc.redfirecookbook.ui.editdiary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.hnqcgc.redfirecookbook.R;
import com.hnqcgc.redfirecookbook.logic.model.KitchenDiary;

import java.util.Date;

public class EditDiaryActivity extends AppCompatActivity {

    private EditDiaryViewModel viewModel;

    private EditText titleEdit;

    private EditText contentEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_diary);
        viewModel = new ViewModelProvider(this).get(EditDiaryViewModel.class);
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
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (titleEdit.getText().length() + contentEdit.getText().length() > 0) {
                    overImg.setVisibility(View.VISIBLE);
                }else {
                    overImg.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        titleEdit.addTextChangedListener(textWatcher);
        contentEdit.addTextChangedListener(textWatcher);
        overImg.setOnClickListener(v -> saveDiary());
    }

    private void saveDiary() {
        String contentStr = contentEdit.getText().toString();
        if (!"".equals(contentStr)) {
            if (viewModel.kitchenDiary == null) {
                viewModel.kitchenDiary = new KitchenDiary();
                editDiary(contentStr);
                viewModel.insertKitchenDiary();
            }else {
                editDiary(contentStr);
                viewModel.updateKitchenDiary();
            }
        }else {
            Toast.makeText(this, "内容不可未空", Toast.LENGTH_SHORT).show();
        }
    }

    private void editDiary(String contentStr) {
        String titleStr = titleEdit.getText().toString();
        viewModel.kitchenDiary.setTitle(titleStr);
        viewModel.kitchenDiary.setContent(contentStr);
        viewModel.kitchenDiary.setLastWriteDate(new Date().getTime());
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