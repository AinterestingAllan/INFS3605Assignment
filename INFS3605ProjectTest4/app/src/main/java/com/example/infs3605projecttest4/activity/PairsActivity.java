package com.example.infs3605projecttest4.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.infs3605projecttest4.R;

import java.util.ArrayList;

public class PairsActivity extends AppCompatActivity {

    RecyclerView pairs_rv;
    public TextView english;
    public TextView noongar;
    int currIndex = 0;
    ArrayList<String> currdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pairs);

//        pairs_rv = findViewById(R.id.pairs_rv);
//        pairs_rv.setLayoutManager(new GridLayoutManager(this,3));
//        PairsAdapter ad1 = new PairsAdapter();
//        ad1.setCurrActivity(this);
//
//        // set the data
//        currdata = new ArrayList<>();
//        ArrayList<Word> wordList = ImportantData.getCurrExeType().getWordList();
//
//        for (int i = 0; i<wordList.size() && i<2; i++) {
//            Word word = wordList.get(currIndex);
//            currdata.add(word.getEnglish());
//            currdata.add(word.getLocal());
//            currIndex++;
//        }
//
//        Collections.shuffle(currdata);
//        ad1.setData(currdata);
//
//        pairs_rv.setAdapter(ad1);

    }


}
