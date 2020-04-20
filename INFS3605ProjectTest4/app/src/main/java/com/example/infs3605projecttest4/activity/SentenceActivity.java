package com.example.infs3605projecttest4.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infs3605projecttest4.CustomToast;
import com.example.infs3605projecttest4.database.ImportantData;
import com.example.infs3605projecttest4.Model.Sentence;
import com.example.infs3605projecttest4.Model.TestType;
import com.example.infs3605projecttest4.Model.Word;
import com.example.infs3605projecttest4.R;
import com.example.infs3605projecttest4.SentenceAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class SentenceActivity extends AppCompatActivity {

    private TextView se_typename;
    private TextView se_wordname;
    private RecyclerView se_serv;
    private RecyclerView se_rv_selected;
    private Button se_submitbt;

    private int currIndex = 0;
    private TestType currType;
    private Sentence currSentence;

    public static SentenceAdapter seAd1;
    public static SentenceAdapter seAd2;
    private CustomToast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence);

        se_typename = findViewById(R.id.se_typename);
        se_wordname = findViewById(R.id.se_wordname);
        se_serv = findViewById(R.id.se_serv);
        se_rv_selected = findViewById(R.id.se_rv_selected);
        se_submitbt = findViewById(R.id.se_submitbt);

        // get currType
        currType = ImportantData.getCurrExeType();
        if (currType.getSentences().size() > 0) {
            setExe();

            // set the submitbt
            se_submitbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (SentenceAdapter.selectWordAy.toString().equals(currSentence.getWordsList().toString())) {
                        toastMessage("correct" + " 😊");
                        //Toast.makeText(SentenceActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                        if (currIndex < currType.getSentences().size() - 1) {
                            currIndex++;
                            setExe();
                        } else {
                            toastMessage("you have finished" + " 😃");
                            //Toast.makeText(SentenceActivity.this, "You have finished!", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        toastMessage("false" + " 🙁");
                        //Toast.makeText(SentenceActivity.this, "False :(", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(this, "There is not enough sentences to practice!", Toast.LENGTH_SHORT).show();
        }

    }

    private void setExe() {
        // get currSentence and currIndex=0
        currSentence = currType.getSentences().get(currIndex);

        // set the textview
        se_typename.setText(currType.getName());
        se_wordname.setText("Translate this sentence"+"\n \n    "+currSentence.getEnglish());

        // set the recyclerview
        se_serv.setLayoutManager(new GridLayoutManager(this,5));
        se_rv_selected.setLayoutManager(new GridLayoutManager(this,5));
        seAd1 = new SentenceAdapter("notSelected");
        seAd2 = new SentenceAdapter("Selected");
        SentenceAdapter.notSelectedWordAy = (ArrayList<Word>) currSentence.getWordsList().clone();
        Collections.shuffle(SentenceAdapter.notSelectedWordAy);
        SentenceAdapter.selectWordAy = new ArrayList<>();
        seAd1.setCurrActivity(this);
        seAd2.setCurrActivity(this);
        se_serv.setAdapter(seAd1);
        se_rv_selected.setAdapter(seAd2);


    }

    private void toastMessage(String content) {
        if (toast != null) {
            toast.hide();
        }
        toast = new CustomToast(this,
                (ViewGroup) this.findViewById(R.id.toast_custom_parent));
        toast.show(content, 500);
    }
}
