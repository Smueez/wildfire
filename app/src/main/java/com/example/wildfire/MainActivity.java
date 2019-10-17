package com.example.wildfire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    int x = 0;
    String TAG = "firstpage";
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this,Login_activity.class);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        //open intent
        new CountDownTimer(2000,1000) {
            @Override
            public void onTick(long l) {
                Log.d(TAG, "onTick: "+x++);
            }

            @Override
            public void onFinish() {

                startActivity(intent);
            }
        };
    }
}
