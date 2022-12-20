package com.example.secondapiproject.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.secondapiproject.databinding.ActivitySplashActivityBinding;
import com.example.secondapiproject.shpreferances.AppSharedPrefController;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ActivitySplashActivityBinding.inflate(getLayoutInflater())
                .getRoot());
    }


    @Override
    protected void onStart() {
        super.onStart();
        handelSplashActivity();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private void handelSplashActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean isLoggedIn = AppSharedPrefController.getInstance().isLoggedIn();
                Intent intent;
                if (isLoggedIn) {
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                } else {
                    intent = new Intent(getApplicationContext(), LoginActivity.class);
                }
                startActivity(intent);
            }
        }, 3000);

    }

}