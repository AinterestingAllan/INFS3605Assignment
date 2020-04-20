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
                insertAllWords();

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

    public static void insertAllWords() {
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
        db.wordDao().insertWord(new Word(75,"Wet", R.drawable.wet,"Kippilly","adjective"));
        db.wordDao().insertWord(new Word(78,"Hungry", R.drawable.hungry,"Kobble Weert","adjective"));
        db.wordDao().insertWord(new Word(95,"Beautiful", R.drawable.beautiful,"Kwobadak","adjective"));
        db.wordDao().insertWord(new Word(113,"Sweet", R.drawable.sweet,"Ngoonyung","adjective"));
        db.wordDao().insertWord(new Word(129,"Itchy", R.drawable.itchy,"Nyindee","Adjective"));
        db.wordDao().insertWord(new Word(140,"Weak", R.drawable.weak,"Weern","Adjective"));
        db.wordDao().insertWord(new Word(137,"Big", R.drawable.big,"Wappalyung","Adjective"));
        db.wordDao().insertWord(new Word(9,"Pig", R.drawable.pig,"Beark","Animal"));
        db.wordDao().insertWord(new Word(16,"Ant", R.drawable.ant,"Bidit","Animal"));
        db.wordDao().insertWord(new Word(17,"Gecko", R.drawable.gecko,"Bidjul","Animal"));
        db.wordDao().insertWord(new Word(18,"Red Kangaroo", R.drawable.red_kangaroo,"Bigurida","Animal"));
        db.wordDao().insertWord(new Word(27,"Lizard", R.drawable.lizard,"Bungarrah","Animal"));
        db.wordDao().insertWord(new Word(38,"Birds", R.drawable.bird,"Djert","Animal"));
        db.wordDao().insertWord(new Word(39,"Grasshoper", R.drawable.grasshopper,"Djidaarly","Animal"));
        db.wordDao().insertWord(new Word(40,"Fish", R.drawable.fish,"Djildjit","Animal"));
        db.wordDao().insertWord(new Word(47,"Snake", R.drawable.snake,"Dugatch","Animal"));
        db.wordDao().insertWord(new Word(49,"Parrot", R.drawable.parrot,"Dumarlark","Animal"));
        db.wordDao().insertWord(new Word(50,"Dog", R.drawable.dog,"Dwert","Animal"));
        db.wordDao().insertWord(new Word(54,"Duck", R.drawable.duck,"Gwinnen","Animal"));
        db.wordDao().insertWord(new Word(79,"Owl", R.drawable.owl,"Koobeeaju","Animal"));
        db.wordDao().insertWord(new Word(82,"Sheep", R.drawable.sheep,"Kookanjerrie","Animal"));
        db.wordDao().insertWord(new Word(84,"Magpie", R.drawable.magpie,"Koolbardie","Animal"));
        db.wordDao().insertWord(new Word(86,"Possum", R.drawable.possum,"Koomal","Animal"));
        db.wordDao().insertWord(new Word(116,"Horse", R.drawable.horse,"Ngoort","Animal"));
        db.wordDao().insertWord(new Word(127,"Flies", R.drawable.flies,"Noort","Animal"));
        db.wordDao().insertWord(new Word(130,"Echidna", R.drawable.echidna,"Nyingarn","Animal"));
        db.wordDao().insertWord(new Word(134,"Emu", R.drawable.emu,"Waitch/Waitj","Animal"));
        db.wordDao().insertWord(new Word(139,"Crow", R.drawable.crow,"Warrdong","Animal"));
        db.wordDao().insertWord(new Word(141,"Rabbit", R.drawable.rabbit,"Wilbra","Animal"));
        db.wordDao().insertWord(new Word(144,"Turtle", R.drawable.turtle,"Yarginy","Animal"));
        db.wordDao().insertWord(new Word(158,"Spiders", R.drawable.spiders,"Kar","Animal"));
        db.wordDao().insertWord(new Word(12,"Nails", R.drawable.nails,"Beerr","Body part"));
        db.wordDao().insertWord(new Word(30,"Ribs", R.drawable.ribs,"Coong","Body part"));
        db.wordDao().insertWord(new Word(31,"Heart", R.drawable.heart,"Coort","Body part"));
        db.wordDao().insertWord(new Word(33,"Mouth", R.drawable.mouth,"Dar","Body part"));
        db.wordDao().insertWord(new Word(48,"Tongue", R.drawable.tongue,"Dulong","Body part"));
        db.wordDao().insertWord(new Word(53,"Armpit", R.drawable.armpit,"Gnayl","Body part"));
        db.wordDao().insertWord(new Word(57,"Foot", R.drawable.foot,"Jen","Body part"));
        db.wordDao().insertWord(new Word(59,"Eyebrows", R.drawable.eyebrows,"Jennt","Body part"));
        db.wordDao().insertWord(new Word(60,"Hair", R.drawable.hair,"Joiny","Body part"));
        db.wordDao().insertWord(new Word(61,"Bone", R.drawable.bone,"Juerl","Body part"));
        db.wordDao().insertWord(new Word(67,"Head", R.drawable.head,"Kart","Body part"));
        db.wordDao().insertWord(new Word(77,"Stomach", R.drawable.stomach,"Kobal","Body part"));
        db.wordDao().insertWord(new Word(96,"Wrist", R.drawable.wrist,"Kwoliny","Body part"));
        db.wordDao().insertWord(new Word(99,"Hand", R.drawable.hand,"Marra","Body part"));
        db.wordDao().insertWord(new Word(103,"Nose", R.drawable.nose,"Mooly","Body part"));
        db.wordDao().insertWord(new Word(114,"Blood", R.drawable.blood,"Ngoopo","Body part"));
        db.wordDao().insertWord(new Word(117,"Teeth", R.drawable.teeth,"Ngorluk","Body part"));
        db.wordDao().insertWord(new Word(118,"The elbow", R.drawable.the_elbow,"Ngoyung","Body part"));
        db.wordDao().insertWord(new Word(119,"Beard", R.drawable.beard,"Ngunoar","Body part"));
        db.wordDao().insertWord(new Word(131,"Brain", R.drawable.brain,"Nyoondeeak","Body part"));
        db.wordDao().insertWord(new Word(143,"Skull", R.drawable.skull,"Wubbert","Body part"));
        db.wordDao().insertWord(new Word(145,"Bone", R.drawable.bone,"Yatch","Body part"));
        db.wordDao().insertWord(new Word(146,"Jaw", R.drawable.jaw,"Yet","Body part"));
        db.wordDao().insertWord(new Word(58,"Grey", R.drawable.grey,"Jendal","Color"));
        db.wordDao().insertWord(new Word(104,"Black", R.drawable.black,"Moonie","Color"));
        db.wordDao().insertWord(new Word(138,"Welcome", R.drawable.welcome,"Wanju","Conversation"));
        db.wordDao().insertWord(new Word(147,"No", R.drawable.no,"Yuwart","Conversation"));
        db.wordDao().insertWord(new Word(170,"Why", R.drawable.why,"Nadjil","Conversation"));
        db.wordDao().insertWord(new Word(70,"Hello/Thank you", R.drawable.thank_you,"Kaya","Conversation"));
        db.wordDao().insertWord(new Word(74,"Yes", R.drawable.yes,"Kia","Conversation"));
        db.wordDao().insertWord(new Word(88,"West", R.drawable.west,"Koony Uk","Direction"));
        db.wordDao().insertWord(new Word(151,"South", R.drawable.south,"Boongarl","Direction"));
        db.wordDao().insertWord(new Word(152,"Inside", R.drawable.inside,"Bwora","Direction"));
        db.wordDao().insertWord(new Word(68,"Anger/Angry", R.drawable.anger,"Karrung","Emotion"));
        db.wordDao().insertWord(new Word(69,"Mad", R.drawable.mad,"Kart Warrah","Emotion"));
        db.wordDao().insertWord(new Word(155,"Happy", R.drawable.happy,"Djerap-Djerap","Emotion"));
        db.wordDao().insertWord(new Word(167,"Jealou", R.drawable.jealous,"Mirn","Emotion"));
        db.wordDao().insertWord(new Word(173,"Cry", R.drawable.cry,"Ngay","Emotion"));
        db.wordDao().insertWord(new Word(1,"Boy", R.drawable.boy,"Nop","Family"));
        db.wordDao().insertWord(new Word(2,"Girl", R.drawable.girl,"Koort","Family"));
        db.wordDao().insertWord(new Word(3,"Man", R.drawable.man,"Noongar","Family"));
        db.wordDao().insertWord(new Word(4,"Woman", R.drawable.woman,"Yoka","Family"));
        db.wordDao().insertWord(new Word(29,"Uncle", R.drawable.uncle,"Conk","Family"));
        db.wordDao().insertWord(new Word(35,"Grandmother", R.drawable.grandmother,"Demma","Family"));
        db.wordDao().insertWord(new Word(83,"Kids", R.drawable.kids,"Koolangka","Family"));
        db.wordDao().insertWord(new Word(89,"Friends", R.drawable.friends,"Koorda","Family"));
        db.wordDao().insertWord(new Word(105,"Family", R.drawable.family,"Moort","Family"));
        db.wordDao().insertWord(new Word(112,"Brother", R.drawable.brother,"Ngoon","Family"));
        db.wordDao().insertWord(new Word(123,"Little child", R.drawable.little_child,"Noobaritch","Family"));
        db.wordDao().insertWord(new Word(124,"People", R.drawable.people,"Noongar","Family"));
        db.wordDao().insertWord(new Word(156,"Sister", R.drawable.sister,"Djook","Family"));
        db.wordDao().insertWord(new Word(163,"Father", R.drawable.father,"Maam","Family"));
        db.wordDao().insertWord(new Word(165,"Child/baby son", R.drawable.child,"Maawit","Family"));
        db.wordDao().insertWord(new Word(166,"Child(Second Eldest)", R.drawable.child_second_eldest,"Mardidjit","Family"));
        db.wordDao().insertWord(new Word(174,"Elder Brother", R.drawable.elder_brother,"Ngoont","Family"));
        db.wordDao().insertWord(new Word(80,"One", R.drawable.one,"Kaen(keny)","Number"));
        db.wordDao().insertWord(new Word(81,"Two", R.drawable.two,"Koojal","Number"));
        db.wordDao().insertWord(new Word(98,"Five", R.drawable.five,"Mar","Number"));
        db.wordDao().insertWord(new Word(102,"Three", R.drawable.three,"Mo","Number"));
        db.wordDao().insertWord(new Word(14,"Paper", R.drawable.paper,"Bibal","Object"));
        db.wordDao().insertWord(new Word(21,"Clothing", R.drawable.clothing,"Bok","Object"));
        db.wordDao().insertWord(new Word(23,"Rifle", R.drawable.rifle,"Boorlba","Object"));
        db.wordDao().insertWord(new Word(24,"Rock/stone", R.drawable.rock,"Boya","Object"));
        db.wordDao().insertWord(new Word(26,"Bushes", R.drawable.bushes,"Bujep","Object"));
        db.wordDao().insertWord(new Word(34,"Knife", R.drawable.knife,"Darp","Object"));
        db.wordDao().insertWord(new Word(42,"Star", R.drawable.star,"Djinang","Object"));
        db.wordDao().insertWord(new Word(43,"Dust", R.drawable.dust,"Dooka","Object"));
        db.wordDao().insertWord(new Word(51,"Meat", R.drawable.meat,"Dytch","Object"));
        db.wordDao().insertWord(new Word(56,"Grass", R.drawable.grass,"Jeerp","Object"));
        db.wordDao().insertWord(new Word(71,"Water", R.drawable.water,"Kayibort","Object"));
        db.wordDao().insertWord(new Word(76,"Spear", R.drawable.spear,"Kitj","Object"));
        db.wordDao().insertWord(new Word(100,"Food", R.drawable.food,"Marany","Object"));
        db.wordDao().insertWord(new Word(106,"The Sun", R.drawable.the_sun,"Ngank","Object"));
        db.wordDao().insertWord(new Word(126,"Egg", R.drawable.egg,"Noorak","Object"));
        db.wordDao().insertWord(new Word(148,"Money", R.drawable.money,"Boorndoong","Object"));
        db.wordDao().insertWord(new Word(154,"Shoe", R.drawable.shoe,"Djen Bwoka","Object"));
        db.wordDao().insertWord(new Word(157,"Car", R.drawable.car,"Kaditj-Kaditj","Object"));
        db.wordDao().insertWord(new Word(164,"Moon", R.drawable.moon,"Maant","Object"));
        db.wordDao().insertWord(new Word(15,"Swamp", R.drawable.swamp,"Bibool","Place"));
        db.wordDao().insertWord(new Word(22,"Gound", R.drawable.gound,"Boojarra","Place"));
        db.wordDao().insertWord(new Word(25,"Country", R.drawable.country,"Budjar","Place"));
        db.wordDao().insertWord(new Word(65,"Home", R.drawable.home,"Karlak/Karluk","Place"));
        db.wordDao().insertWord(new Word(153,"Forest", R.drawable.forest,"Djaril-mari","Place"));
        db.wordDao().insertWord(new Word(159,"Hill", R.drawable.hill,"Kard","Place"));
        db.wordDao().insertWord(new Word(168,"Sea", R.drawable.sea,"MoomBoyet","Place"));
        db.wordDao().insertWord(new Word(177,"Cave", R.drawable.cave,"Yorakal","Place"));
        db.wordDao().insertWord(new Word(6,"Jump/fly/step", R.drawable.jump,"Bardang","Verb"));
        db.wordDao().insertWord(new Word(7,"Biting", R.drawable.biting,"Barkanyin","Verb"));
        db.wordDao().insertWord(new Word(8,"Hopping", R.drawable.hopping,"Barlanginy","Verb"));
        db.wordDao().insertWord(new Word(10,"Strangle", R.drawable.strangle,"Bearn","Verb"));
        db.wordDao().insertWord(new Word(11,"Walk", R.drawable.walk,"Barn","Verb"));
        db.wordDao().insertWord(new Word(19,"Sleep", R.drawable.sleep,"Bijaarr","Verb"));
        db.wordDao().insertWord(new Word(20,"Sleeping", R.drawable.sleeping,"Bitgarra","Verb"));
        db.wordDao().insertWord(new Word(32,"Running", R.drawable.running,"Daarlnyininy","Verb"));
        db.wordDao().insertWord(new Word(37,"Swim", R.drawable.swim,"Djabaly","Verb"));
        db.wordDao().insertWord(new Word(41,"Look/see", R.drawable.look,"Djinang","Verb"));
        db.wordDao().insertWord(new Word(44,"Knocking", R.drawable.knocking,"Dorll Dorliny","Verb"));
        db.wordDao().insertWord(new Word(52,"Eat", R.drawable.eat,"Gnarn","Verb"));
        db.wordDao().insertWord(new Word(62,"Laughing", R.drawable.laughing,"Ka Ka Winning","Verb"));
        db.wordDao().insertWord(new Word(72,"Sing", R.drawable.sing,"Keape","Verb"));
        db.wordDao().insertWord(new Word(73,"Dance", R.drawable.dance,"keniny","Verb"));
        db.wordDao().insertWord(new Word(90,"Throw", R.drawable.throw_,"Koordidj","Verb"));
        db.wordDao().insertWord(new Word(91,"Go", R.drawable.go,"Koorl","Verb"));
        db.wordDao().insertWord(new Word(94,"Thinking", R.drawable.thinking,"Kuttajinoong","Verb"));
        db.wordDao().insertWord(new Word(111,"A Cry", R.drawable.a_cry,"Ngiy","Verb"));
        db.wordDao().insertWord(new Word(115,"Bleeding", R.drawable.bleeding,"Ngoonpulung","Verb"));
        db.wordDao().insertWord(new Word(120,"Listen", R.drawable.listen,"Ni","Verb"));
        db.wordDao().insertWord(new Word(132,"Talk", R.drawable.talk,"Waangkiny","Verb"));
        db.wordDao().insertWord(new Word(133,"Play", R.drawable.play,"Wabiny","Verb"));
        db.wordDao().insertWord(new Word(138,"Doing", 0,"Warrdint","Verb"));
        db.wordDao().insertWord(new Word(162,"Wait", R.drawable.wait,"Kwidi","Verb"));
        db.wordDao().insertWord(new Word(175,"Sit", R.drawable.sit,"Nyin","Verb"));
        db.wordDao().insertWord(new Word(13,"Summer", R.drawable.summer,"Beeruk","Weather"));
        db.wordDao().insertWord(new Word(28,"Rain", R.drawable.rain,"Burong/Djart","Weather"));
        db.wordDao().insertWord(new Word(36,"Spring", R.drawable.spring,"Dirdong","Weather"));
        db.wordDao().insertWord(new Word(45,"Mist/Fog", R.drawable.mist,"Dudja/Djindi","Weather"));
        db.wordDao().insertWord(new Word(55,"Night", R.drawable.night,"Jadulukmaradony","Weather"));
        db.wordDao().insertWord(new Word(66,"Hot/Hot Weather", R.drawable.hot_weather,"Karlawooliny/Karlawoorliny","Weather"));
        db.wordDao().insertWord(new Word(92,"Cloud", R.drawable.cloud,"Koornden","Weather"));
        db.wordDao().insertWord(new Word(93,"Thunder", R.drawable.thunder,"Koorndilla","Weather"));
        db.wordDao().insertWord(new Word(107,"Sunrise", R.drawable.sunrise,"Ngank Barlunginy","Weather"));
        db.wordDao().insertWord(new Word(108,"Sunset", R.drawable.sunset,"Ngank Weerdiny","Weather"));
        db.wordDao().insertWord(new Word(135,"Rainbow", R.drawable.rainbow,"Walken","Weather"));
        db.wordDao().insertWord(new Word(150,"Daylight", R.drawable.daylight,"Birayit","Weather"));
        db.wordDao().insertWord(new Word(160,"Day", R.drawable.day,"Kedalak","Weather"));
        db.wordDao().insertWord(new Word(161,"Night", R.drawable.night,"Kedalaka","Weather"));
        db.wordDao().insertWord(new Word(169,"Black Night", R.drawable.black_night,"Moonawooliny","Weather"));
        db.wordDao().insertWord(new Word(176,"Sky", R.drawable.sky,"Worl","Weather"));
        db.wordDao().insertWord(new Word(5,"Lightning", R.drawable.lightning,"Babanginy","Other"));
        db.wordDao().insertWord(new Word(46,"Song", R.drawable.song,"Dudjarak","Other"));
        db.wordDao().insertWord(new Word(63,"Smile", R.drawable.smile,"Kar","Other"));
        db.wordDao().insertWord(new Word(64,"Fire", R.drawable.fire,"Karl","Other"));
        db.wordDao().insertWord(new Word(85,"Liar", R.drawable.liar,"Koolyyumit","Other"));
        db.wordDao().insertWord(new Word(87,"Side", R.drawable.side,"Koonga","Other"));
        db.wordDao().insertWord(new Word(101,"Sick", R.drawable.sick,"Mindich","Other"));
        db.wordDao().insertWord(new Word(109,"I/Me", R.drawable.i,"Ngany","Other"));
        db.wordDao().insertWord(new Word(110,"Who", R.drawable.who,"Ngeean","Other"));
        db.wordDao().insertWord(new Word(122,"Here", R.drawable.here,"Nitcha","Other"));
        db.wordDao().insertWord(new Word(121,"This", 0,"Ngeean","Other"));
        db.wordDao().insertWord(new Word(125,"You", R.drawable.you,"Noonuk","Other"));
        db.wordDao().insertWord(new Word(128,"What", R.drawable.what,"Nygar","Other"));
        db.wordDao().insertWord(new Word(142,"Where", R.drawable.where,"Winjar","Other"));
        db.wordDao().insertWord(new Word(149,"Tomorrow", R.drawable.tomorrow,"Benang","Other"));
        db.wordDao().insertWord(new Word(171,"We", 0,"Ngalak","Other"));
        db.wordDao().insertWord(new Word(172,"Our", 0,"Ngalang","Other"));

    }

    public static void setWordsToWordGroups(ArrayList<WordGroup> wordsToWordGroups) {
        for (WordGroup x : wordsToWordGroups) {
            x.setWord1(db.wordDao().getWordsById(x.getWord1_id()));
            x.setWord2(db.wordDao().getWordsById(x.getWord2_id()));
        }
    }

}
