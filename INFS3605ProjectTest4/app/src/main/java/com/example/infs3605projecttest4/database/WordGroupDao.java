package com.example.infs3605projecttest4.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.infs3605projecttest4.Model.WordGroup;

import java.util.List;

@Dao
public interface WordGroupDao {
    @Insert
    void insertWordGroup(WordGroup wordGroup);

    @Query("Select * From wordgroup")
    List<WordGroup> getWordGroups();

    @Query("Select * From wordgroup Where type = :type")
    List<WordGroup> getWordGroupsByType(String type);
}
