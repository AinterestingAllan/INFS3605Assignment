package com.example.infs3605projecttest4.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Alphabet {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String letter;
    private String rule;
    private String englishExample;
    private String noongarExample;

    public Alphabet(String letter, String rule, String englishExample, String noongarExample) {
        this.letter = letter;
        this.rule = rule;
        this.englishExample = englishExample;
        this.noongarExample = noongarExample;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getEnglishExample() {
        return englishExample;
    }

    public void setEnglishExample(String englishExample) {
        this.englishExample = englishExample;
    }

    public String getNoongarExample() {
        return noongarExample;
    }

    public void setNoongarExample(String noongarExample) {
        this.noongarExample = noongarExample;
    }


}
