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

    static {
        // types
        typeList.add(new TestType("Family"));
        typeList.add(new TestType("XXXX2"));
        typeList.add(new TestType("XXXX3"));
        typeList.add(new TestType("XXXX4"));

        // type's wordlist
        typeList.get(0).wordList.add(new Word("Boy", R.drawable.boy,"Nop"));
        typeList.get(0).wordList.add(new Word("Girl", R.drawable.girl,"Koort"));
        typeList.get(0).wordList.add(new Word("Man", R.drawable.man,"Noongar"));
        typeList.get(0).wordList.add(new Word("Woman", R.drawable.woman,"Yoka"));

        // type's sentencelist
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


        // type's wordgrouplist
        WordGroup wordGroup0 = new WordGroup(new Word("xxxx", R.drawable.boy,"WordA")
                ,new Word("xxxx", R.drawable.boy,"WordB"));
        WordGroup wordGroup1 = new WordGroup(new Word("xxxx", R.drawable.boy,"HahaA")
                ,new Word("xxxx", R.drawable.boy,"HahaB"));
        WordGroup wordGroup2 = new WordGroup(new Word("xxxx", R.drawable.boy,"ExampleA")
                ,new Word("xxxx", R.drawable.boy,"ExampleB"));
        typeList.get(0).wordGroupList.add(wordGroup0);
        typeList.get(0).wordGroupList.add(wordGroup1);
        typeList.get(0).wordGroupList.add(wordGroup2);


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
