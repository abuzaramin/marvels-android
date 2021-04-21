package com.example.marvel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText eName ;
    EditText ePassword;
    Button eLogin;

    String userName = "";
    String userPassword = "";

    String credName = "admin";
    String credPassword = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.etPassword);
        eLogin = findViewById(R.id.btnLogin);

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = eName.getText().toString();
                userPassword = ePassword.getText().toString();
                if(userName.isEmpty() || userPassword.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Please enter name and password!", Toast.LENGTH_LONG).show();

                }
                else {

                    if (!validate(userName, userPassword)) {
                        Toast.makeText(LoginActivity.this, "Incorrect credentials, please try again!", Toast.LENGTH_LONG).show();
                    } else {

                        SharedPreferences.Editor editor = getSharedPreferences("MY_PREFS", MODE_PRIVATE).edit();
                        editor.putBoolean("isLogin", true);
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }

                }
            }
        });
    }


    private boolean validate(String userName, String userPassword)
    {
        if(userName.equals(credName) && userPassword.equals(credPassword))
        {
            return true;
        }

        return false;
    }
}