package com.example.infs3605projecttest4.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.infs3605projecttest4.Model.Word;
import com.example.infs3605projecttest4.R;
import com.example.infs3605projecttest4.database.Warehouse;

public class WordDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_detail);

        TextView ser_english = findViewById(R.id.ser_english);
        TextView ser_noongar = findViewById(R.id.ser_noongar);
        TextView ser_type = findViewById(R.id.ser_type);
        ImageView ser_image = findViewById(R.id.ser_image);

        Word serWord = Warehouse.getSerWord();

        ser_english.setText(serWord.getEnglish());
        ser_noongar.setText(serWord.getLocal());
        ser_type.setText(serWord.getType());
        ser_image.setImageResource(serWord.getImage());
    }


}
