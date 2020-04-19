package com.example.infs3605projecttest4.database;

import android.content.Context;

import androidx.room.Room;

import com.example.infs3605projecttest4.Model.Alphabet;
import com.example.infs3605projecttest4.Model.Sentence;
import com.example.infs3605projecttest4.Model.TestType;
import com.example.infs3605projecttest4.Model.Word;
import com.example.infs3605projecttest4.Model.WordGroup;
import com.example.infs3605projecttest4.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private static Database db = null;
    private static ArrayList<Alphabet> alphabetArrayList = null;
    private static ArrayList<Word> wordsByCurrType = null;
    private static Map<String, TestType> typesMap = new HashMap<>();

    public static void startDatabase(final Context context) {
        Warehouse.db =Room.databaseBuilder(context,Database .class,"mydb")
                .build();
    }

    public static void insertAllDataIntoDatabase() {
        new Thread() {
            @Override
            public void run() {
                // insert the alphabets into database
                db.alphabetDao().insert(new Alphabet("A","a is always as in father\naa as in Kaat"
                        ,"father","tjak/kaat/maat"));
                db.alphabetDao().insert(new Alphabet("B/P","b and p are interchangeable"
                        ,"-","balyat/palyat"));

                // insert the words into database
                insertllWords();

                // insert the wordgroups into database
                db.wordGroupDao().insertWordGroup(new WordGroup(900,901,"family"));
                db.wordGroupDao().insertWordGroup(new WordGroup(902,903,"family"));
                db.wordGroupDao().insertWordGroup(new WordGroup(904,905,"family"));
            }
        }.start();
    }

    public static void setAllData() {
        new Thread() {
            @Override
            public void run() {
                // get alphabets form database
                alphabetArrayList = (ArrayList<Alphabet>) db.alphabetDao().getAlphabets();

                // set the types map
                typesMap.put("family",new TestType("family"));
                typesMap.put("adjective",new TestType("adjective"));
                typesMap.put("animal",new TestType("animal"));
                typesMap.put("body part",new TestType("body part"));
                typesMap.put("color",new TestType("color"));
                typesMap.put("conversation",new TestType("conversation"));
                typesMap.put("direction",new TestType("direction"));
                typesMap.put("emotion",new TestType("emotion"));
                typesMap.put("number",new TestType("number"));
                typesMap.put("object",new TestType("object"));
                typesMap.put("place",new TestType("place"));
                typesMap.put("verb",new TestType("verb"));
                typesMap.put("weather",new TestType("weather"));
                typesMap.put("other",new TestType("other"));
                // set the wordslist AND the wordgroupslist to each types
                for (TestType x : typesMap.values()) {
                    x.setWordList((ArrayList<Word>) db.wordDao().getWordsByType(x.getName()));
                    x.setWordGroupList((ArrayList<WordGroup>) db.wordGroupDao().getWordGroupsByType(x.getName()));
                    setWordsToWordGroups(x.getWordGroupList());
                }

                // set the sentences
                // 4 wordlists ---> sentences
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
                typesMap.get("family").getSentences().add(new Sentence(1, s1,"A B C"));
                typesMap.get("family").getSentences().add(new Sentence(2, s2,"C B A"));
                typesMap.get("family").getSentences().add(new Sentence(3, s3,"A C B"));
                typesMap.get("family").getSentences().add(new Sentence(4, s4,"B A C"));
            }
        }.start();
    }

    public static ArrayList<Word> getWordsByType(final String type) {
        new Thread() {
            @Override
            public void run() {
                wordsByCurrType = (ArrayList<Word>) db.wordDao().getWordsByType(type);
            }
        }.start();
        return wordsByCurrType;
    }

    public static Database getDb() {
        return db;
    }

    public static ArrayList<Alphabet> getAlphabetArrayList() {
        return alphabetArrayList;
    }

    public static ArrayList<Word> getWordsByCurrType() {
        return wordsByCurrType;
    }

    public static Map<String, TestType> getTypesMap() {
        return typesMap;
    }

    public static void insertllWords() {
        db.wordDao().insertWord(new Word(1,"Boy", R.drawable.boy,"Nop","family"));
        db.wordDao().insertWord(new Word(2,"Girl", R.drawable.girl,"Koort","family"));
        db.wordDao().insertWord(new Word(3,"Man", R.drawable.man,"Noongar","family"));
        db.wordDao().insertWord(new Word(4,"Woman", R.drawable.woman,"Yoka","family"));
        db.wordDao().insertWord(new Word(5,"Uncle", R.drawable.woman,"Conk","family"));
        db.wordDao().insertWord(new Word(900,"WA", R.drawable.woman,"WA","family"));
        db.wordDao().insertWord(new Word(901,"WB", R.drawable.woman,"WB","family"));
        db.wordDao().insertWord(new Word(902,"EA", R.drawable.woman,"EA","family"));
        db.wordDao().insertWord(new Word(903,"EB", R.drawable.woman,"EB","family"));
        db.wordDao().insertWord(new Word(904,"CA", R.drawable.woman,"CA","family"));
        db.wordDao().insertWord(new Word(905,"CB", R.drawable.woman,"CB","family"));
    }

    public static void setWordsToWordGroups(ArrayList<WordGroup> wordsToWordGroups) {
        for (WordGroup x : wordsToWordGroups) {
            x.setWord1(db.wordDao().getWordsById(x.getWord1_id()));
            x.setWord2(db.wordDao().getWordsById(x.getWord2_id()));
        }
    }

}
