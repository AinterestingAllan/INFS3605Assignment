package com.example.infs3605projecttest4.database;

import androidx.room.RoomDatabase;

import com.example.infs3605projecttest4.Model.Alphabet;
import com.example.infs3605projecttest4.Model.Word;
import com.example.infs3605projecttest4.Model.WordGroup;

@androidx.room.Database(entities={Alphabet.class, Word.class, WordGroup.class}
, version=1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract AlphabetDao alphabetDao();
    public abstract WordDao wordDao();
    public abstract WordGroupDao wordGroupDao();
}
