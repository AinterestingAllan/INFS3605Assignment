package com.example.infs3605projecttest4.Model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.infs3605projecttest4.R;

import java.util.ArrayList;

@Entity
public class Word {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String english;
    private int image;
    private String local;
    private String type;

    private static ArrayList<Word> allWordList = new ArrayList<>();

    public Word(int id, String english, int image, String local, String type) {
        this.id = id;
        this.english = english;
        this.image = image;
        this.local = local;
        this.type = type;
    }

    @Ignore
    public Word(String english, int image, String local) {
        this.english = english;
        this.image = image;
        this.local = local;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
