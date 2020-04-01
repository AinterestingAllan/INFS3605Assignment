package com.example.infs3605projecttest4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Welcom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);


        Button but = findViewById(R.id.wel_entryButton);
        ImageView im = findViewById(R.id.wel_welcomeImage);

        final Intent instant = new Intent(this, MainActivity.class);
        im.setImageResource(R.drawable.welcome);

      Timer timer = new Timer();
        TimerTask tast = new TimerTask() {
            @Override
            public void run() {
                startActivity(instant);
            }
        };
        timer.schedule(tast, 2000);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(instant);
            }
        });

    }
}
