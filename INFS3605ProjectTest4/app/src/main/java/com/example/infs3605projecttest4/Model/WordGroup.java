package com.example.infs3605projecttest4.Model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class WordGroup {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ForeignKey(entity = Word.class, parentColumns = "id", childColumns = "word1_id")
    private int word1_id;
    @ForeignKey(entity = Word.class, parentColumns = "id", childColumns = "word2_id")
    private int word2_id;
    private String type;
    @Ignore
    private Word word1 = null;
    @Ignore
    private Word word2 = null;

    public WordGroup(int word1_id, int word2_id, String type) {
        this.word1_id = word1_id;
        this.word2_id = word2_id;
        this.type = type;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWord1_id() {
        return word1_id;
    }

    public void setWord1_id(int word1_id) {
        this.word1_id = word1_id;
    }

    public int getWord2_id() {
        return word2_id;
    }

    public void setWord2_id(int word2_id) {
        this.word2_id = word2_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
