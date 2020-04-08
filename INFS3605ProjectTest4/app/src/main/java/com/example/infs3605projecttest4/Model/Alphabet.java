package com.example.infs3605projecttest4.Model;

import java.util.ArrayList;

public class Alphabet {
    private static ArrayList<Alphabet> alphabets = new ArrayList<>();

    private int id;
    private String letter;
    private String rule;
    private String englishExample;
    private String noongarExample;

    public Alphabet(int id, String letter, String rule, String englishExample, String noongarExample) {
        this.id = id;
        this.letter = letter;
        this.rule = rule;
        this.englishExample = englishExample;
        this.noongarExample = noongarExample;
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

    public static ArrayList<Alphabet> getAlphabets() {
        return alphabets;
    }

    public static void setAlphabets(ArrayList<Alphabet> alphabets) {
        Alphabet.alphabets = alphabets;
    }

    static {
        alphabets.add(new Alphabet(1,"a","a is always as in father\naa as in Kaat"
                ,"father","tjak/kaat/maat"));
        alphabets.add(new Alphabet(2,"b/p","b and p are interchangeable"
                ,"-","balyat/palyat"));
    }
}
