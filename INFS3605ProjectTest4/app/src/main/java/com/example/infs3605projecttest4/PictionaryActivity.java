package com.example.infs3605projecttest4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infs3605projecttest4.Model.ImportantData;
import com.example.infs3605projecttest4.Model.TestType;
import com.example.infs3605projecttest4.Model.Word;

import java.util.ArrayList;
import java.util.HashSet;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class PictionaryActivity extends AppCompatActivity {
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;
    RadioButton curr = null;
    Word currWord = null;
    int currIndex = 0;
    TestType currType = null;
    int correctPosition = -1;
    ArrayList<ImageView> selectImageList = new ArrayList<>();
    ArrayList<Word> randomIncoWords = new ArrayList<>();
    ImageView pc_image1;
    ImageView pc_image2;
    ImageView pc_image3;
    ImageView pc_image4;
    private CustomToast toast;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pictionary);

        currType = ImportantData.getCurrExeType();

        // set the title
        TextView testText = findViewById(R.id.testText);

        testText.setText(currType.getName());


        // set the radio button
        rb1 = findViewById(R.id.radioButton1);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);

        final ArrayList<RadioButton> rbList = new ArrayList<>();
        rbList.add(rb1);
        rbList.add(rb2);
        rbList.add(rb3);
        rbList.add(rb4);

        setRadioButton(rbList);

        // set the picture
        pc_image1 = findViewById(R.id.pc_image1);
        pc_image2 = findViewById(R.id.pc_image2);
        pc_image3 = findViewById(R.id.pc_image3);
        pc_image4 = findViewById(R.id.pc_image4);
        selectImageList.add(pc_image1);
        selectImageList.add(pc_image2);
        selectImageList.add(pc_image3);
        selectImageList.add(pc_image4);

        // set the word
        TextView pc_word = findViewById(R.id.pc_word);
        if (currType.getWordList().size() >= 4 ) {
            setWord();
            // check answer button
            Button pc_bt = findViewById(R.id.pc_bt);
            // create intent
            final Intent intent = new Intent(this,SelectActivity.class);

            pc_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // show correct or not
                    if (rbList.get(correctPosition).isChecked()) {
                        toastMessage("correct"+" 😊");
                        //Toast.makeText(PictionaryActivity.this, "correct!", Toast.LENGTH_SHORT).show();
                        // next question
                        currIndex++;
                        if (currIndex < currType.getWordList().size()) {
                            curr.setChecked(false);
                            curr = null;
                            setWord();
                        } else { // End of test
                            startActivity(intent);
                            toastMessage("you have finished"+" 😃");
                            //Toast.makeText(PictionaryActivity.this, "You have finished!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        toastMessage("false"+" 🙁");
                        // show correct answer
                       // Toast.makeText(PictionaryActivity.this, "false(:", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            pc_word.setText("Sorry, We don't have enough vocabulary on this category now");
        }

    }

    private void setImage(ImageView correct) {
        int i = 0;
        for (ImageView im : selectImageList) {
            if (im.equals(correct)) {
                correct.setImageResource(currWord.getImage());
            } else {
                im.setImageResource(randomIncoWords.get(i).getImage());
                i += 1;
            }
        }

    }

    public void setRadioButton(ArrayList<RadioButton> rbList) {
        for (final RadioButton rb : rbList) {
            rb.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (curr == null || !curr.equals(rb)) {
                        if (curr != null) {
                            curr.setChecked(false);
                        }
                        curr = rb;
                        curr.setChecked(true);
                    }
                }
            });
        }
    }

    public int getRandomIndex() {
        if (currType != null) {
            int ri = 0;
            while (true) {
                ri = (int) (Math.random() * currType.getWordList().size());
                if (ri != currIndex) {
                    break;
                }
            }
            return ri;
        }else {
            return -1;
        }
    }

    private void setWord() {
        // set the word
        TextView pc_word = findViewById(R.id.pc_word);
        currWord = currType.getWordList().get(currIndex);
        pc_word.setText(currWord.getLocal());

        // get random number of word in this dict, the correct is currIndex
        // 0-(size-1)
        int r2 = getRandomIndex();
        int r3 = getRandomIndex();
        while (true) {
            if (r3 == r2) {
                r3 = getRandomIndex();
            } else {
                break;
            }
        }
        int r4 = getRandomIndex();
        while (true) {
            if (r4 == r3 || r4 == r2) {
                r4 = getRandomIndex();
            } else {
                break;
            }
        }

        HashSet<Word> incoWords = new HashSet<>();
        incoWords.add(currType.getWordList().get(r2));
        incoWords.add(currType.getWordList().get(r3));
        incoWords.add(currType.getWordList().get(r4));

        randomIncoWords = new ArrayList<>(incoWords);

        // 0-3
        correctPosition = (int) (Math.random() * 4);

        switch (correctPosition) {
            case 0:
                setImage(pc_image1);
                break;
            case 1:
                setImage(pc_image2);
                break;
            case 2:
                setImage(pc_image3);
                break;
            case 3:
                setImage(pc_image4);
                break;
        }
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

