package com.example.marvel.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.marvel.R;
import com.facebook.AccessToken;


public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        SharedPreferences prefs = getSharedPreferences("MY_PREFS", MODE_PRIVATE);
        Boolean isLogin = prefs.getBoolean("isLogin", false);


        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isFBLoggedIn = accessToken != null && !accessToken.isExpired();


        if (isLogin || isFBLoggedIn) {
            Intent detailIntent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(detailIntent);
        }
        else
        {
            Intent detailIntent = new Intent(StartActivity.this, LoginActivity.class);
            startActivity(detailIntent);
        }
    }
}