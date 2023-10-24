package com.boss.tictactoewithfriends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class getCode extends AppCompatActivity {
    Button playWithBot , playWithPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_code);
        getSupportActionBar().hide();
        getWindow().setStatusBarColor(Color.BLACK);
        playWithBot=(Button)findViewById(R.id.playNow);
        playWithPlayer = (Button) findViewById(R.id.playNow2);
        playWithBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),playActivity.class);
                i.putExtra("isBot",true);
                startActivity(i);
                Log.e("clicked","play clicked");
            }
        });
        playWithPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),playActivity.class);
                intent.putExtra("isBot" , false);
                startActivity(intent);
            }
        });
    }
}