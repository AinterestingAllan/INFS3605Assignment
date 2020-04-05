package com.example.infs3605projecttest4.Model;

import com.example.infs3605projecttest4.R;

import java.util.ArrayList;

public class TestType {
    private String name;
    private ArrayList<Word> wordList = new ArrayList<>();
    private ArrayList<Sentence> sentences = new ArrayList<>();

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
        ArrayList<Word> s1 = new ArrayList<>();
        ArrayList<Word> s2 = new ArrayList<>();
        ArrayList<Word> s3 = new ArrayList<>();
        ArrayList<Word> s4 = new ArrayList<>();
        s1.add(new Word("Boy1", R.drawable.boy,"NopA"));
        s1.add(new Word("Boy1", R.drawable.boy,"NopB"));
        s1.add(new Word("Boy1", R.drawable.boy,"NopC"));
        s2.add(new Word("Boy2", R.drawable.boy,"NopC"));
        s2.add(new Word("Boy2", R.drawable.boy,"NopB"));
        s2.add(new Word("Boy2", R.drawable.boy,"NopA"));
        s3.add(new Word("Boy3", R.drawable.boy,"NopA"));
        s3.add(new Word("Boy3", R.drawable.boy,"NopC"));
        s3.add(new Word("Boy3", R.drawable.boy,"NopB"));
        s4.add(new Word("Boy4", R.drawable.boy,"NopB"));
        s4.add(new Word("Boy4", R.drawable.boy,"NopA"));
        s4.add(new Word("Boy4", R.drawable.boy,"NopC"));
        typeList.get(0).sentences.add(new Sentence(1, s1,"A B C"));
        typeList.get(0).sentences.add(new Sentence(1, s2,"C B A"));
        typeList.get(0).sentences.add(new Sentence(1, s3,"A C B"));
        typeList.get(0).sentences.add(new Sentence(1, s4,"B A C"));
    }

    public static ArrayList<TestType> getTypeList() {
        return typeList;
    }

    public ArrayList<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(ArrayList<Sentence> sentences) {
        this.sentences = sentences;
    }
}
