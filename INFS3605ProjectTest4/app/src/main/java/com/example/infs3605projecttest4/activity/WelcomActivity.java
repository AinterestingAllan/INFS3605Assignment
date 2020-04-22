package com.example.infs3605projecttest4.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.infs3605projecttest4.R;
import com.example.infs3605projecttest4.database.Warehouse;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomActivity extends AppCompatActivity {

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

        //create the database connection
        Warehouse.startDatabase(this);

        SharedPreferences sharedPreferences = getSharedPreferences("databaseCreate",MODE_PRIVATE);

        // get data from database
        Warehouse.setAllData(sharedPreferences);
    }


}
