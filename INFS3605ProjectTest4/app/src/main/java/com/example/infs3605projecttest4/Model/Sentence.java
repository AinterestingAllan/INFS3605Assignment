package com.example.infs3605projecttest4.Model;

import java.util.ArrayList;

public class Sentence {

    private int id;
    private ArrayList<Word> wordsList;
    private String english;


    public Sentence(int id, ArrayList<Word> wordsList, String english) {
        this.id = id;
        this.wordsList = wordsList;
        this.english = english;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Word> getWordsList() {
        return wordsList;
    }

    public void setWordsList(ArrayList<Word> wordsList) {
        this.wordsList = wordsList;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }
}
