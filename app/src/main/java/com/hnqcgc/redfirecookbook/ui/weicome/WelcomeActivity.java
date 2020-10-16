package com.hnqcgc.redfirecookbook.ui.weicome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hnqcgc.redfirecookbook.MainActivity;
import com.hnqcgc.redfirecookbook.R;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                MainActivity.startMainActivity(WelcomeActivity.this);
                finish();
            }
        };
        timer.schedule(task, 1500);
    }
}