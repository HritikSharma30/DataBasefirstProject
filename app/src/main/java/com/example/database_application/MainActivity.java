package com.example.database_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //title
        getSupportActionBar().setTitle("Firebase App");

        Button buttonLogin = findViewById(R.id.login_button);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentv1 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intentv1);
            }
        });

        Button buttonRegister = findViewById(R.id.Register_button);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentv2 = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intentv2);
            }
        });

        buttonRegister.setPaintFlags(buttonRegister.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
}