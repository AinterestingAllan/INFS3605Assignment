package com.example.infs3605projecttest4.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.infs3605projecttest4.Model.Word;

import java.util.List;

@Dao
public interface WordDao {
    @Insert
    void insertWord(Word word);

    @Query("Select * From word")
    List<Word> getWords();

    @Query("Select * From word Where type = :type")
    List<Word> getWordsByType(String type);

    @Query("Select * From word Where id = :id")
    Word getWordsById(int id);

    @Query("Select * From word Where lower(english) = :english")
    Word getWordsByEnglish(String english);

    @Query("Select * From word Where lower(english) like :like")
    List<Word> getWordsByLike(String like);
}
