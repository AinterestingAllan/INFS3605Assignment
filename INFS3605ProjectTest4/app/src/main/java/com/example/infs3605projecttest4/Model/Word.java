package com.example.infs3605projecttest4.Model;

import com.example.infs3605projecttest4.R;

import java.util.ArrayList;

public class Word {
    private String english;
    private int image;
    private String local;
    private String rule;
    private String noongarExample;
    private String englishExample;

    private static ArrayList<Word> allWordList = new ArrayList<>();

    public Word(String english, int image, String local) {
        this.english = english;
        this.image = image;
        this.local = local;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public static ArrayList<Word> getAllWordList() {
        return allWordList;
    }

    public static void setAllWordList(ArrayList<Word> allWordList) {
        Word.allWordList = allWordList;
    }

    static {
        allWordList.add(new Word("Boy", R.drawable.boy,"Nop"));
        allWordList.add(new Word("Boy", R.drawable.boy,"Nop"));
    }


}
