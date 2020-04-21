package com.example.infs3605projecttest4.database;

import android.content.Context;
import android.content.SharedPreferences;

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
    private static Word serWord = null;
    private static Map<String, TestType> typesMap = new HashMap<>();

    public static void startDatabase(final Context context) {
        Warehouse.db =Room.databaseBuilder(context,Database .class,"mydb")
                .build();
    }


    public static void setAllData(final SharedPreferences sharedPreferences) {
        new Thread() {
            @Override
            public void run() {
                if (sharedPreferences.getInt("dbifcreated",0)==0) {
                    // insert the alphabets into database
                    insertAllAlphabets();

                    // insert all words to database
                    insertAllWords();

                    // insert the wordgroups into database
                    db.wordGroupDao().insertWordGroup(new WordGroup(900,901,"family"));
                    db.wordGroupDao().insertWordGroup(new WordGroup(902,903,"family"));
                    db.wordGroupDao().insertWordGroup(new WordGroup(904,905,"family"));
                    sharedPreferences.edit().putInt("dbifcreated",1).apply();
                }

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
                    String type = x.getName().toLowerCase();
                    x.setWordGroupList((ArrayList<WordGroup>) db.wordGroupDao().getWordGroupsByType(type));
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

    public static Word searchWordByEnglish(final String english) {
        new Thread() {
            public void run() {
                serWord = db.wordDao().getWordsByEnglish(english);
            }
        }.start();
        return serWord;
    }

    public static void insertAllWords() {
        db.wordDao().insertWord(new Word(1,"Boy", R.drawable.boy,"Nop","family"));
        db.wordDao().insertWord(new Word(2,"Girl", R.drawable.girl,"Koort","family"));
        db.wordDao().insertWord(new Word(3,"Man", R.drawable.man,"Noongar","family"));
        db.wordDao().insertWord(new Word(4,"Woman", R.drawable.woman,"Yoka","family"));
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
        db.wordDao().insertWord(new Word(129,"Itchy", R.drawable.itchy,"Nyindee","adjective"));
        db.wordDao().insertWord(new Word(140,"Weak", R.drawable.weak,"Weern","adjective"));
        db.wordDao().insertWord(new Word(137,"Big", R.drawable.big,"Wappalyung","adjective"));
        db.wordDao().insertWord(new Word(9,"Pig", R.drawable.pig,"Beark","animal"));
        db.wordDao().insertWord(new Word(16,"Ant", R.drawable.ant,"Bidit","animal"));
        db.wordDao().insertWord(new Word(17,"Gecko", R.drawable.gecko,"Bidjul","animal"));
        db.wordDao().insertWord(new Word(18,"Red Kangaroo", R.drawable.red_kangaroo,"Bigurida","animal"));
        db.wordDao().insertWord(new Word(27,"Lizard", R.drawable.lizard,"Bungarrah","animal"));
        db.wordDao().insertWord(new Word(38,"Birds", R.drawable.bird,"Djert","animal"));
        db.wordDao().insertWord(new Word(39,"Grasshoper", R.drawable.grasshopper,"Djidaarly","animal"));
        db.wordDao().insertWord(new Word(40,"Fish", R.drawable.fish,"Djildjit","animal"));
        db.wordDao().insertWord(new Word(47,"Snake", R.drawable.snake,"Dugatch","animal"));
        db.wordDao().insertWord(new Word(49,"Parrot", R.drawable.parrot,"Dumarlark","animal"));
        db.wordDao().insertWord(new Word(50,"Dog", R.drawable.dog,"Dwert","animal"));
        db.wordDao().insertWord(new Word(54,"Duck", R.drawable.duck,"Gwinnen","animal"));
        db.wordDao().insertWord(new Word(79,"Owl", R.drawable.owl,"Koobeeaju","animal"));
        db.wordDao().insertWord(new Word(82,"Sheep", R.drawable.sheep,"Kookanjerrie","animal"));
        db.wordDao().insertWord(new Word(84,"Magpie", R.drawable.magpie,"Koolbardie","animal"));
        db.wordDao().insertWord(new Word(86,"Possum", R.drawable.possum,"Koomal","animal"));
        db.wordDao().insertWord(new Word(116,"Horse", R.drawable.horse,"Ngoort","animal"));
        db.wordDao().insertWord(new Word(127,"Flies", R.drawable.flies,"Noort","animal"));
        db.wordDao().insertWord(new Word(130,"Echidna", R.drawable.echidna,"Nyingarn","animal"));
        db.wordDao().insertWord(new Word(134,"Emu", R.drawable.emu,"Waitch/Waitj","animal"));
        db.wordDao().insertWord(new Word(139,"Crow", R.drawable.crow,"Warrdong","animal"));
        db.wordDao().insertWord(new Word(141,"Rabbit", R.drawable.rabbit,"Wilbra","animal"));
        db.wordDao().insertWord(new Word(144,"Turtle", R.drawable.turtle,"Yarginy","animal"));
        db.wordDao().insertWord(new Word(158,"Spiders", R.drawable.spiders,"Kar","animal"));
        db.wordDao().insertWord(new Word(12,"Nails", R.drawable.nails,"Beerr","body part"));
        db.wordDao().insertWord(new Word(30,"Ribs", R.drawable.ribs,"Coong","body part"));
        db.wordDao().insertWord(new Word(31,"Heart", R.drawable.heart,"Coort","body part"));
        db.wordDao().insertWord(new Word(33,"Mouth", R.drawable.mouth,"Dar","body part"));
        db.wordDao().insertWord(new Word(48,"Tongue", R.drawable.tongue,"Dulong","body part"));
        db.wordDao().insertWord(new Word(53,"Armpit", R.drawable.armpit,"Gnayl","body part"));
        db.wordDao().insertWord(new Word(57,"Foot", R.drawable.foot,"Jen","body part"));
        db.wordDao().insertWord(new Word(59,"Eyebrows", R.drawable.eyebrows,"Jennt","body part"));
        db.wordDao().insertWord(new Word(60,"Hair", R.drawable.hair,"Joiny","body part"));
        db.wordDao().insertWord(new Word(61,"Bone", R.drawable.bone,"Juerl","body part"));
        db.wordDao().insertWord(new Word(67,"Head", R.drawable.head,"Kart","body part"));
        db.wordDao().insertWord(new Word(77,"Stomach", R.drawable.stomach,"Kobal","body part"));
        db.wordDao().insertWord(new Word(96,"Wrist", R.drawable.wrist,"Kwoliny","body part"));
        db.wordDao().insertWord(new Word(99,"Hand", R.drawable.hand,"Marra","body part"));
        db.wordDao().insertWord(new Word(103,"Nose", R.drawable.nose,"Mooly","body part"));
        db.wordDao().insertWord(new Word(114,"Blood", R.drawable.blood,"Ngoopo","body part"));
        db.wordDao().insertWord(new Word(117,"Teeth", R.drawable.teeth,"Ngorluk","body part"));
        db.wordDao().insertWord(new Word(118,"The elbow", R.drawable.the_elbow,"Ngoyung","body part"));
        db.wordDao().insertWord(new Word(119,"Beard", R.drawable.beard,"Ngunoar","body part"));
        db.wordDao().insertWord(new Word(131,"Brain", R.drawable.brain,"Nyoondeeak","body part"));
        db.wordDao().insertWord(new Word(143,"Skull", R.drawable.skull,"Wubbert","body part"));
        db.wordDao().insertWord(new Word(145,"Bone", R.drawable.bone,"Yatch","body part"));
        db.wordDao().insertWord(new Word(146,"Jaw", R.drawable.jaw,"Yet","body part"));
        db.wordDao().insertWord(new Word(58,"Grey", R.drawable.grey,"Jendal","color"));
        db.wordDao().insertWord(new Word(104,"Black", R.drawable.black,"Moonie","color"));
        db.wordDao().insertWord(new Word(138,"Welcome", R.drawable.welcome,"Wanju","conversation"));
        db.wordDao().insertWord(new Word(147,"No", R.drawable.no,"Yuwart","conversation"));
        db.wordDao().insertWord(new Word(170,"Why", R.drawable.why,"Nadjil","conversation"));
        db.wordDao().insertWord(new Word(70,"Hello/Thank you", R.drawable.thank_you,"Kaya","conversation"));
        db.wordDao().insertWord(new Word(74,"Yes", R.drawable.yes,"Kia","conversation"));
        db.wordDao().insertWord(new Word(88,"West", R.drawable.west,"Koony Uk","direction"));
        db.wordDao().insertWord(new Word(151,"South", R.drawable.south,"Boongarl","direction"));
        db.wordDao().insertWord(new Word(152,"Inside", R.drawable.inside,"Bwora","direction"));
        db.wordDao().insertWord(new Word(68,"Anger/Angry", R.drawable.anger,"Karrung","emotion"));
        db.wordDao().insertWord(new Word(69,"Mad", R.drawable.mad,"Kart Warrah","emotion"));
        db.wordDao().insertWord(new Word(155,"Happy", R.drawable.happy,"Djerap-Djerap","emotion"));
        db.wordDao().insertWord(new Word(167,"Jealou", R.drawable.jealous,"Mirn","emotion"));
        db.wordDao().insertWord(new Word(173,"Cry", R.drawable.cry,"Ngay","emotion"));
        db.wordDao().insertWord(new Word(29,"Uncle", R.drawable.uncle,"Conk","family"));
        db.wordDao().insertWord(new Word(35,"Grandmother", R.drawable.grandmother,"Demma","family"));
        db.wordDao().insertWord(new Word(83,"Kids", R.drawable.kids,"Koolangka","family"));
        db.wordDao().insertWord(new Word(89,"Friends", R.drawable.friends,"Koorda","family"));
        db.wordDao().insertWord(new Word(105,"Family", R.drawable.family,"Moort","family"));
        db.wordDao().insertWord(new Word(112,"Brother", R.drawable.brother,"Ngoon","family"));
        db.wordDao().insertWord(new Word(123,"Little child", R.drawable.little_child,"Noobaritch","family"));
        db.wordDao().insertWord(new Word(124,"People", R.drawable.people,"Noongar","family"));
        db.wordDao().insertWord(new Word(156,"Sister", R.drawable.sister,"Djook","family"));
        db.wordDao().insertWord(new Word(163,"Father", R.drawable.father,"Maam","family"));
        db.wordDao().insertWord(new Word(165,"Child/baby son", R.drawable.child,"Maawit","family"));
        db.wordDao().insertWord(new Word(166,"Child(Second Eldest)", R.drawable.child_second_eldest,"Mardidjit","family"));
        db.wordDao().insertWord(new Word(174,"Elder Brother", R.drawable.elder_brother,"Ngoont","family"));
        db.wordDao().insertWord(new Word(80,"One", R.drawable.one,"Kaen(keny)","number"));
        db.wordDao().insertWord(new Word(81,"Two", R.drawable.two,"Koojal","number"));
        db.wordDao().insertWord(new Word(98,"Five", R.drawable.five,"Mar","number"));
        db.wordDao().insertWord(new Word(102,"Three", R.drawable.three,"Mo","number"));
        db.wordDao().insertWord(new Word(14,"Paper", R.drawable.paper,"Bibal","object"));
        db.wordDao().insertWord(new Word(21,"Clothing", R.drawable.clothing,"Bok","object"));
        db.wordDao().insertWord(new Word(23,"Rifle", R.drawable.rifle,"Boorlba","object"));
        db.wordDao().insertWord(new Word(24,"Rock/stone", R.drawable.rock,"Boya","object"));
        db.wordDao().insertWord(new Word(26,"Bushes", R.drawable.bushes,"Bujep","object"));
        db.wordDao().insertWord(new Word(34,"Knife", R.drawable.knife,"Darp","object"));
        db.wordDao().insertWord(new Word(42,"Star", R.drawable.star,"Djinang","object"));
        db.wordDao().insertWord(new Word(43,"Dust", R.drawable.dust,"Dooka","object"));
        db.wordDao().insertWord(new Word(51,"Meat", R.drawable.meat,"Dytch","object"));
        db.wordDao().insertWord(new Word(56,"Grass", R.drawable.grass,"Jeerp","object"));
        db.wordDao().insertWord(new Word(71,"Water", R.drawable.water,"Kayibort","object"));
        db.wordDao().insertWord(new Word(76,"Spear", R.drawable.spear,"Kitj","object"));
        db.wordDao().insertWord(new Word(100,"Food", R.drawable.food,"Marany","object"));
        db.wordDao().insertWord(new Word(106,"The Sun", R.drawable.the_sun,"Ngank","object"));
        db.wordDao().insertWord(new Word(126,"Egg", R.drawable.egg,"Noorak","object"));
        db.wordDao().insertWord(new Word(148,"Money", R.drawable.money,"Boorndoong","object"));
        db.wordDao().insertWord(new Word(154,"Shoe", R.drawable.shoe,"Djen Bwoka","object"));
        db.wordDao().insertWord(new Word(157,"Car", R.drawable.car,"Kaditj-Kaditj","object"));
        db.wordDao().insertWord(new Word(164,"Moon", R.drawable.moon,"Maant","object"));
        db.wordDao().insertWord(new Word(15,"Swamp", R.drawable.swamp,"Bibool","place"));
        db.wordDao().insertWord(new Word(22,"Gound", R.drawable.gound,"Boojarra","place"));
        db.wordDao().insertWord(new Word(25,"Country", R.drawable.country,"Budjar","place"));
        db.wordDao().insertWord(new Word(65,"Home", R.drawable.home,"Karlak/Karluk","place"));
        db.wordDao().insertWord(new Word(153,"Forest", R.drawable.forest,"Djaril-mari","Place"));
        db.wordDao().insertWord(new Word(159,"Hill", R.drawable.hill,"Kard","place"));
        db.wordDao().insertWord(new Word(168,"Sea", R.drawable.sea,"MoomBoyet","place"));
        db.wordDao().insertWord(new Word(177,"Cave", R.drawable.cave,"Yorakal","Place"));
        db.wordDao().insertWord(new Word(6,"Jump/fly/step", R.drawable.jump,"Bardang","verb"));
        db.wordDao().insertWord(new Word(7,"Biting", R.drawable.biting,"Barkanyin","verb"));
        db.wordDao().insertWord(new Word(8,"Hopping", R.drawable.hopping,"Barlanginy","verb"));
        db.wordDao().insertWord(new Word(10,"Strangle", R.drawable.strangle,"Bearn","verb"));
        db.wordDao().insertWord(new Word(11,"Walk", R.drawable.walk,"Barn","verb"));
        db.wordDao().insertWord(new Word(19,"Sleep", R.drawable.sleep,"Bijaarr","verb"));
        db.wordDao().insertWord(new Word(20,"Sleeping", R.drawable.sleeping,"Bitgarra","verb"));
        db.wordDao().insertWord(new Word(32,"Running", R.drawable.running,"Daarlnyininy","verb"));
        db.wordDao().insertWord(new Word(37,"Swim", R.drawable.swim,"Djabaly","verb"));
        db.wordDao().insertWord(new Word(41,"Look/see", R.drawable.look,"Djinang","verb"));
        db.wordDao().insertWord(new Word(44,"Knocking", R.drawable.knocking,"Dorll Dorliny","verb"));
        db.wordDao().insertWord(new Word(52,"Eat", R.drawable.eat,"Gnarn","verb"));
        db.wordDao().insertWord(new Word(62,"Laughing", R.drawable.laughing,"Ka Ka Winning","verb"));
        db.wordDao().insertWord(new Word(72,"Sing", R.drawable.sing,"Keape","verb"));
        db.wordDao().insertWord(new Word(73,"Dance", R.drawable.dance,"keniny","verb"));
        db.wordDao().insertWord(new Word(90,"Throw", R.drawable.throw_,"Koordidj","verb"));
        db.wordDao().insertWord(new Word(91,"Go", R.drawable.go,"Koorl","verb"));
        db.wordDao().insertWord(new Word(94,"Thinking", R.drawable.thinking,"Kuttajinoong","verb"));
        db.wordDao().insertWord(new Word(111,"A Cry", R.drawable.a_cry,"Ngiy","verb"));
        db.wordDao().insertWord(new Word(115,"Bleeding", R.drawable.bleeding,"Ngoonpulung","verb"));
        db.wordDao().insertWord(new Word(120,"Listen", R.drawable.listen,"Ni","verb"));
        db.wordDao().insertWord(new Word(132,"Talk", R.drawable.talk,"Waangkiny","verb"));
        db.wordDao().insertWord(new Word(133,"Play", R.drawable.play,"Wabiny","verb"));
        db.wordDao().insertWord(new Word(999,"Doing", 0,"Warrdint","verb"));
        db.wordDao().insertWord(new Word(162,"Wait", R.drawable.wait,"Kwidi","verb"));
        db.wordDao().insertWord(new Word(175,"Sit", R.drawable.sit,"Nyin","verb"));
        db.wordDao().insertWord(new Word(13,"Summer", R.drawable.summer,"Beeruk","weather"));
        db.wordDao().insertWord(new Word(28,"Rain", R.drawable.rain,"Burong/Djart","weather"));
        db.wordDao().insertWord(new Word(36,"Spring", R.drawable.spring,"Dirdong","weather"));
        db.wordDao().insertWord(new Word(45,"Mist/Fog", R.drawable.mist,"Dudja/Djindi","weather"));
        db.wordDao().insertWord(new Word(55,"Night", R.drawable.night,"Jadulukmaradony","weather"));
        db.wordDao().insertWord(new Word(66,"Hot/Hot Weather", R.drawable.hot_weather,"Karlawooliny/Karlawoorliny","weather"));
        db.wordDao().insertWord(new Word(92,"Cloud", R.drawable.cloud,"Koornden","weather"));
        db.wordDao().insertWord(new Word(93,"Thunder", R.drawable.thunder,"Koorndilla","weather"));
        db.wordDao().insertWord(new Word(107,"Sunrise", R.drawable.sunrise,"Ngank Barlunginy","weather"));
        db.wordDao().insertWord(new Word(108,"Sunset", R.drawable.sunset,"Ngank Weerdiny","weather"));
        db.wordDao().insertWord(new Word(135,"Rainbow", R.drawable.rainbow,"Walken","weather"));
        db.wordDao().insertWord(new Word(150,"Daylight", R.drawable.daylight,"Birayit","weather"));
        db.wordDao().insertWord(new Word(160,"Day", R.drawable.day,"Kedalak","weather"));
        db.wordDao().insertWord(new Word(161,"Night", R.drawable.night,"Kedalaka","weather"));
        db.wordDao().insertWord(new Word(169,"Black Night", R.drawable.black_night,"Moonawooliny","weather"));
        db.wordDao().insertWord(new Word(176,"Sky", R.drawable.sky,"Worl","weather"));
        db.wordDao().insertWord(new Word(5,"Lightning", R.drawable.lightning,"Babanginy","other"));
        db.wordDao().insertWord(new Word(46,"Song", R.drawable.song,"Dudjarak","other"));
        db.wordDao().insertWord(new Word(63,"Smile", R.drawable.smile,"Kar","other"));
        db.wordDao().insertWord(new Word(64,"Fire", R.drawable.fire,"Karl","other"));
        db.wordDao().insertWord(new Word(85,"Liar", R.drawable.liar,"Koolyyumit","other"));
        db.wordDao().insertWord(new Word(87,"Side", R.drawable.side,"Koonga","other"));
        db.wordDao().insertWord(new Word(101,"Sick", R.drawable.sick,"Mindich","other"));
        db.wordDao().insertWord(new Word(109,"I/Me", R.drawable.i,"Ngany","other"));
        db.wordDao().insertWord(new Word(110,"Who", R.drawable.who,"Ngeean","other"));
        db.wordDao().insertWord(new Word(122,"Here", R.drawable.here,"Nitcha","other"));
        db.wordDao().insertWord(new Word(121,"This", 0,"Ngeean","other"));
        db.wordDao().insertWord(new Word(125,"You", R.drawable.you,"Noonuk","other"));
        db.wordDao().insertWord(new Word(128,"What", R.drawable.what,"Nygar","other"));
        db.wordDao().insertWord(new Word(142,"Where", R.drawable.where,"Winjar","other"));
        db.wordDao().insertWord(new Word(149,"Tomorrow", R.drawable.tomorrow,"Benang","other"));
        db.wordDao().insertWord(new Word(171,"We", 0,"Ngalak","other"));
        db.wordDao().insertWord(new Word(172,"Our", 0,"Ngalang","other"));

    }

    public static void insertAllAlphabets() {
        db.alphabetDao().insert(new Alphabet("A","a is always as in father\naa as in Kaat"
                ,"father","tjak/kaat/maat"));
        db.alphabetDao().insert(new Alphabet("B/P","b and p are interchangeable"
                ,"-","balyat/palyat"));
        db.alphabetDao().insert(new Alphabet("D/T","d and t are interchangeable. Also used with j to give a softer version of the 'ch' sound"
                ,"","tjuditj/djuditj"));
        db.alphabetDao().insert(new Alphabet("E","e is always as in fetch"
                ,"fetch","dwert/ketj"));
        db.alphabetDao().insert(new Alphabet("G","g and k are interchangeable"
                ,"-","yonga/yonka"));
        db.alphabetDao().insert(new Alphabet("I","i is always as in fatigue"
                ,"fatigue","nirnam/mirl"));
        db.alphabetDao().insert(new Alphabet("J","j has a softer sound than the English version, as in joint, more linke the j in banjo"
                ,"banjo","djak"));
        db.alphabetDao().insert(new Alphabet("K","k and g are interchangeable"
                ,"-","karda/garda"));
        db.alphabetDao().insert(new Alphabet("KW(gw)","kw is never qu or cw since c and q do not exist"
                ,"as in quit or Gwenda","kwabadak/gwabadak"));
        db.alphabetDao().insert(new Alphabet("N","n is always as in not"
                ,"not","nunuk/noort"));
        db.alphabetDao().insert(new Alphabet("NG","ng appears at the beginning of words(unlike in English where it never appears at the beginning) and has the same sound as in ing sound of sing, never as in finger"
                ,"sing","ganiny/nganjima"));
        db.alphabetDao().insert(new Alphabet("NY","ny as in canyon, never as in pony cr nyal"
                ,"canyon","yingarn/nyitang"));
        db.alphabetDao().insert(new Alphabet("O","always o as in pop"
                ,"pop","ngot"));
        db.alphabetDao().insert(new Alphabet("OO","always oo, as in book. Also interchangeable with u, depending on the word"
                ,"book","noonook/nunuk"));
        db.alphabetDao().insert(new Alphabet("P/B","p and b are interchangeable"
                ,"-","palyat/balyat"));
        db.alphabetDao().insert(new Alphabet("R","r is always as in rake. It does not, however, appear at the beginning of words"
                ,"rake","kara/maar"));
        db.alphabetDao().insert(new Alphabet("RD","pronounced with an accent on the r, as in American accents, never with the preceding consonant or vowel"
                ,"hard","karda/noort"));
        db.alphabetDao().insert(new Alphabet("RL","pronounced with an accent on the r, as in American accents, never with the preceding consonant or vowel"
                ,"burley","karla/marlak"));
        db.alphabetDao().insert(new Alphabet("RN","pronounced with an accent on the r, as in American accents, never with the preceding consonant or vowel"
                ,"born","boorn/nyingarn"));
        db.alphabetDao().insert(new Alphabet("RR","slightly trilled, as in a Scottish accent"
                ,"sporran","warrkaly"));
        db.alphabetDao().insert(new Alphabet("RT","pronounced with an accent on the r, as in American accents, never with the preceding consonant or vowel"
                ,"start","koort/dwert"));
        db.alphabetDao().insert(new Alphabet("T/D","t and d are interchangeable. Also used with j to give a softer version of the 'ch' sound"
                ,"-","tjuditj/djuditj"));
        db.alphabetDao().insert(new Alphabet("U","u is always as in put. Also interchangeable with oo, depending on the word"
                ,"put","Nyungar/Noongar"));
        db.alphabetDao().insert(new Alphabet("W","w is always as in water"
                ,"water","wetj/wirlo"));
        db.alphabetDao().insert(new Alphabet("Y","y is always as in yellow"
                ,"yellow","yooran/yandjet"));
    }

    public static void setWordsToWordGroups(ArrayList<WordGroup> wordsToWordGroups) {
        for (WordGroup x : wordsToWordGroups) {
            x.setWord1(db.wordDao().getWordsById(x.getWord1_id()));
            x.setWord2(db.wordDao().getWordsById(x.getWord2_id()));
        }
    }

}
