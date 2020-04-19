package com.example.infs3605projecttest4.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.infs3605projecttest4.Model.Alphabet;

import java.util.List;

@Dao
public interface AlphabetDao {
    @Insert
    void insert(Alphabet alphabet);

    @Query("Select * From alphabet")
    List<Alphabet> getAlphabets();
}
