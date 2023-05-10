package com.example.database_application;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class LoginActivity extends AppCompatActivity {

    ImageView dice_img;
    Random ramdon = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        dice_img = findViewById(R.id.dice_img);

        dice_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotateDice();
            }
        });

    }

    private void rotateDice() {
        int i = ramdon.nextInt(5)+1;
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.rotate);
        dice_img.startAnimation(anim);
        switch (i) {
            case 1:
                dice_img.setImageResource(R.drawable.dice1num);
                break;
            case 2:
                dice_img.setImageResource(R.drawable.dice2num);
                break;
            case 3:
                dice_img.setImageResource(R.drawable.dice3num);
                break;
            case 4:
                dice_img.setImageResource(R.drawable.dice4num);
                break;
            case 5:
                dice_img.setImageResource(R.drawable.dice5num);
                break;
            case 6:
                dice_img.setImageResource(R.drawable.dice6num);
                break;
        }
    }
}