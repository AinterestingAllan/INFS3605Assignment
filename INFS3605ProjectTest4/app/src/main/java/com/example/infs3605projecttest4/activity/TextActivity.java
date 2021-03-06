package com.example.infs3605projecttest4.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infs3605projecttest4.CustomToast;
import com.example.infs3605projecttest4.database.ImportantData;
import com.example.infs3605projecttest4.Model.TestType;
import com.example.infs3605projecttest4.Model.Word;
import com.example.infs3605projecttest4.Model.WordGroup;
import com.example.infs3605projecttest4.R;

import java.util.ArrayList;
import java.util.Stack;

public class TextActivity extends AppCompatActivity {
    // select or missing
    public static String TAG;

    Button bt;
    RadioButton text1;
    RadioButton text2;
    RadioButton text3;
    RadioButton text4;
    TextView text_word;
    TextView text_status;
    int index=0;
    Word currWord;
    TestType currType = null;
    int correctPosition;
    ArrayList<Word> currList;
    ArrayList<WordGroup> currGroup;
    RadioButton curr = null;
    ArrayList<RadioButton> rbList;
    private CustomToast toast;
    private int currSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        bt = findViewById(R.id.text_confirm);
        text1 = findViewById(R.id.text_rb1);
        text2 = findViewById(R.id.text_rb2);
        text3 = findViewById(R.id.text_rb3);
        text4 = findViewById(R.id.text_rb4);
        text_word = findViewById(R.id.text_word);
        text_status = findViewById(R.id.text_status);

        currType = ImportantData.getCurrExeType();
        currList = currType.getWordList();

        currSize = 0;
        // if user use the missing word function
        if ("group".equals(TAG)) {
            currGroup = currType.getWordGroupList();
            currSize = currType.getWordGroupList().size();
        } else {
            currSize = currType.getWordList().size();
        }

        rbList = new ArrayList<>();
        rbList.add(text1);
        rbList.add(text2);
        rbList.add(text3);
        rbList.add(text4);

        setRadioButton(rbList);

        if (currSize<4) {
            Toast.makeText(this, "Sorry, We don't have enough vocabulary on this category now", Toast.LENGTH_SHORT).show();
        } else {
            setWord();
            final int finalCurrSize = currSize;
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (rbList.get(correctPosition).isChecked()) {
                        toastMessage("correct"+" 😊");
                        //Toast.makeText(TextActivity.this, "correct!", Toast.LENGTH_SHORT).show();
                        // next question
                        index++;
                        if (index < finalCurrSize) {
                            curr.setChecked(false);
                            curr = null;
                            setWord();
                        } else { // End of test
                            toastMessage("you have finished"+" 😃");
                            //Toast.makeText(TextActivity.this, "You have finished!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // show correct answer
                        toastMessage("false"+" 🙁");
                        //Toast.makeText(TextActivity.this, "false(:", Toast.LENGTH_SHORT).show();
                    }
                }

            });
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

    @SuppressLint("SetTextI18n")
    void setWord() {
        if ("text".equals(TAG)) {
            currWord = currList.get(index);
            text_word.setText("How do you say '"+currWord.getEnglish()+"'?");
        } else {
            currWord = currGroup.get(index).getWord2();
            text_word.setText("Fill the missing word \n \n     "+currGroup.get(index).getWord1().getLocal()+"  ______");
        }
        text_status.setText(index+"/"+currSize);
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
        Stack<String> stack = new Stack<>();
        stack.push(currList.get(r2).getLocal());
        stack.push(currList.get(r3).getLocal());
        stack.push(currList.get(r4).getLocal());

        correctPosition = (int) (Math.random() * 4);

        for(int i=0; i<4; i++) {
            if (i == correctPosition){
                rbList.get(i).setText(currWord.getLocal());
            } else {
                rbList.get(i).setText(stack.pop());
            }
        }


    }

    public int getRandomIndex() {
        int ri = 0;
        while (true) {
            ri = (int) (Math.random() * currType.getWordList().size());
            if (ri != index) {
                break;
            }
        }
        return ri;
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
