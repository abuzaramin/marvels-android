package com.example.marvel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        SharedPreferences prefs = getSharedPreferences("MY_PREFS", MODE_PRIVATE);
        Boolean isLogin = prefs.getBoolean("isLogin", false);

        if (isLogin) {
            Intent detailIntent = new Intent(StartActivity.this, com.example.marvel.MainActivity.class);
            startActivity(detailIntent);
        }
        else
        {
            Intent detailIntent = new Intent(StartActivity.this, com.example.marvel.LoginActivity.class);
            startActivity(detailIntent);
        }
    }
}