package com.example.infs3605projecttest4.Model;

import com.example.infs3605projecttest4.R;

import java.util.ArrayList;

public class TestType {
    private String name;
    private ArrayList<Word> wordList = new ArrayList<>();
    private ArrayList<Sentence> sentences = new ArrayList<>();
    private ArrayList<WordGroup> wordGroupList = new ArrayList<>();

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

//    static {
//        // types
//        typeList.add(new TestType("Family"));
//        typeList.add(new TestType("XXXX2"));
//        typeList.add(new TestType("XXXX3"));
//        typeList.add(new TestType("XXXX4"));
//
//        // type's wordlist
//        typeList.get(0).wordList.add(new Word("Boy", R.drawable.boy,"Nop"));
//        typeList.get(0).wordList.add(new Word("Girl", R.drawable.girl,"Koort"));
//        typeList.get(0).wordList.add(new Word("Man", R.drawable.man,"Noongar"));
//        typeList.get(0).wordList.add(new Word("Woman", R.drawable.woman,"Yoka"));
//
//
//
//    }

    public static ArrayList<TestType> getTypeList() {
        return typeList;
    }

    public ArrayList<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(ArrayList<Sentence> sentences) {
        this.sentences = sentences;
    }

    public ArrayList<WordGroup> getWordGroupList() {
        return wordGroupList;
    }

    public void setWordGroupList(ArrayList<WordGroup> wordGroupList) {
        this.wordGroupList = wordGroupList;
    }

    public static void setTypeList(ArrayList<TestType> typeList) {
        TestType.typeList = typeList;
    }


}
