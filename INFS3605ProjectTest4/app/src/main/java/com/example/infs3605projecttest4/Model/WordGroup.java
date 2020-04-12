package com.example.infs3605projecttest4.Model;

public class WordGroup {
    private Word word1;
    private Word word2;

    public WordGroup(Word word1, Word word2) {
        this.word1 = word1;
        this.word2 = word2;
    }

    public Word getWord1() {
        return word1;
    }

    public void setWord1(Word word1) {
        this.word1 = word1;
    }

    public Word getWord2() {
        return word2;
    }

    public void setWord2(Word word2) {
        this.word2 = word2;
    }


}
