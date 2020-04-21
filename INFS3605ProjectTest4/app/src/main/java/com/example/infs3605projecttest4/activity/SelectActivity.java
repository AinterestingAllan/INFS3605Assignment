package com.example.infs3605projecttest4.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.infs3605projecttest4.R;

public class SelectActivity extends AppCompatActivity {

    Button pictionaryBt;
    Button textBt;
    Button sentenceBt;
    Button missingBt;
    Button pairsBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        pictionaryBt = findViewById(R.id.select_pctionary);
        textBt = findViewById(R.id.select_text);
        sentenceBt = findViewById(R.id.select_sentence);
        missingBt = findViewById(R.id.select_missing);
        pairsBt = findViewById(R.id.select_pairs);

        pictionaryBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goActivity(PictionaryActivity.class);
            }
        });

        textBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextActivity.TAG = "text";
                goActivity(TextActivity.class);
            }
        });

        sentenceBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goActivity(SentenceActivity.class);
            }
        });

        missingBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextActivity.TAG = "group";
                goActivity(TextActivity.class);
            }
        });

        pairsBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goActivity(PairsActivity.class);
            }
        });
    }

    private void goActivity(Class x) {
        Intent intent = new Intent(this,x);
        startActivity(intent);
    }
}
