package com.boss.tictactoewithfriends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button playWithFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.BLACK);
        playWithFriend=findViewById(R.id.playWithFriend);
        playWithFriend.setAlpha(0f);
//        playWithFriend.setVisibility(View.GONE);

       int shortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime);

        playWithFriend.animate()
                .alpha(1f)
                .setDuration(1500)
                .setListener(null);
        playWithFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),getCode.class));
            }
        });
    }
}