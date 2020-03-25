package com.example.infs3605projecttest4.Model;

import com.example.infs3605projecttest4.R;

import java.util.ArrayList;

public class TestType {
    private String name;
    private ArrayList<Word> wordList = new ArrayList<>();

    public TestType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Word> getWordList() {
        return wordList;
    }

    public void setWordList(ArrayList<Word> wordList) {
        this.wordList = wordList;
    }

    private static ArrayList<TestType> typeList = new ArrayList<>();

    static {
        typeList.add(new TestType("Family"));
        typeList.add(new TestType("XXXX2"));
        typeList.add(new TestType("XXXX3"));
        typeList.add(new TestType("XXXX4"));
        typeList.get(0).wordList.add(new Word("Boy", R.drawable.boy,"Nop"));
        typeList.get(0).wordList.add(new Word("Girl", R.drawable.girl,"Koort"));
        typeList.get(0).wordList.add(new Word("Man", R.drawable.man,"Noongar"));
        typeList.get(0).wordList.add(new Word("Woman", R.drawable.woman,"Yoka"));
    }

    public static ArrayList<TestType> getTypeList() {
        return typeList;
    }
}
