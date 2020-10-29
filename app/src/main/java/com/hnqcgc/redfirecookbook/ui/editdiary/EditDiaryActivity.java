package com.hnqcgc.redfirecookbook.ui.editdiary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.hnqcgc.redfirecookbook.R;
import com.hnqcgc.redfirecookbook.logic.model.KitchenDiary;

import java.util.Date;

public class EditDiaryActivity extends AppCompatActivity {

    private EditDiaryViewModel viewModel;

    private EditText titleEdit;

    private EditText contentEdit;

    private ImageView overImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_diary);
        viewModel = new ViewModelProvider(this).get(EditDiaryViewModel.class);
        initView();
        initDiary();
    }

    private void initDiary() {
        Intent intent = getIntent();
        String diary = intent.getStringExtra("diary");
        viewModel.kitchenDiary = new Gson().fromJson(diary, KitchenDiary.class);
        if (viewModel.kitchenDiary != null) {
            titleEdit.setText(viewModel.kitchenDiary.getTitle());
            contentEdit.setText(viewModel.kitchenDiary.getContent());
        }
    }

    private void initView() {
        Toolbar toolBar = findViewById(R.id.toolBar);
        overImg = findViewById(R.id.overImg);
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
        String titleStr = titleEdit.getText().toString();
        if (!"".equals(titleStr)||!"".equals(contentStr)) {
            if (viewModel.kitchenDiary == null) {
                viewModel.kitchenDiary = new KitchenDiary();
                editDiary(contentStr, titleStr);
                viewModel.insertKitchenDiary();
            }else {
                editDiary(contentStr, titleStr);
                viewModel.updateKitchenDiary();
            }
            hideInput();
        }else {
            Toast.makeText(this, "日记不可为空", Toast.LENGTH_SHORT).show();
        }
    }

    private void editDiary(String contentStr, String titleStr) {
        viewModel.kitchenDiary.setTitle(titleStr);
        viewModel.kitchenDiary.setContent(contentStr);
        viewModel.kitchenDiary.setLastWriteDate(new Date().getTime());
    }

    private void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View v = getWindow().peekDecorView();
        overImg.setVisibility(View.GONE);
        titleEdit.clearFocus();
        contentEdit.clearFocus();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    public static void startAddDiaryActivity(Context context) {
        Intent intent = new Intent(context, EditDiaryActivity.class);
        context.startActivity(intent);
    }

    public static void startAddDiaryActivity(Context context, KitchenDiary diary) {
        Intent intent = new Intent(context, EditDiaryActivity.class);
        intent.putExtra("diary", new Gson().toJson(diary));
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