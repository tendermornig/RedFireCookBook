package com.hnqcgc.redfirecookbook.ui.weicome;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.hnqcgc.redfirecookbook.R;
import com.hnqcgc.redfirecookbook.ui.main.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    private static final String TAG = "WelcomeActivity";

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