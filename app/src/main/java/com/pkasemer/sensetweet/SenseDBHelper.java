package com.pkasemer.sensetweet;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SenseDBHelper extends SQLiteOpenHelper {

    private  static final  String DB_NAME  = "SENSE_DB"; // db name;
    private  static final int DB_VERSION = 1; //version of the app

    SenseDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public  void onCreate(SQLiteDatabase db){
        updateMyDatabase(db, 0, DB_VERSION);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        updateMyDatabase(db, oldVersion, newVersion);
    }


    private  static  void insertHouse(SQLiteDatabase db,
                                      String id_str,
                                      String text,
                                      double polarity,
                                      double subjectivity,
                                      String username,
                                      String name,
                                      String profile_image_url,
                                      String received_at){
        ContentValues tweetValues = new ContentValues();
        tweetValues.put("id_str", id_str);
        tweetValues.put("text", text);
        tweetValues.put("polarity", polarity);
        tweetValues.put("subjectivity", subjectivity);
        tweetValues.put("username", username);
        tweetValues.put("name", name);
        tweetValues.put("profile_image_url", profile_image_url);
        tweetValues.put("received_at", received_at);

        db.insert("TWEET", null, tweetValues);

    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion < 2){
            db.execSQL("CREATE TABLE TWEET (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "id_str TEXT," +
                    "text TEXT," +
                    "polarity REAL,"+
                    "subjectivity REAL,"+
                    "username TEXT," +
                    "name TEXT,"+
                    "profile_image_url TEXT," +
                    "received_at TEXT)");

            insertHouse(db, "1395488974374793216","It was a pleasure to share the stage with @JaneRuth_Aceng, @RicardoEchalar and Abbey Byrne today and discuss applyi… https://t.co/HhssbP2wrJ", 0, 0,"AmandaMcClella2","Kironde Yasin","http://pbs.twimg.com/profile_images/1032710011527553024/NBJR9ttc_normal.jpg" ,"2021-05-20 21:12:57");
            insertHouse(db, "1395480974374793216","It was a pleasure to share the stage with @JaneRuth_Aceng, @RicardoEchalar and Abbey Byrne today and discuss applyi… https://t.co/HhssbP2wrJ", 0, 0,"AmandaMcClella2","Amanda McClelland","http://pbs.twimg.com/profile_images/1032710011527553024/NBJR9ttc_normal.jpg" ,"2021-05-20 21:12:57");
            insertHouse(db, "1395480974374793216","It was a pleasure to share the stage with @JaneRuth_Aceng, @RicardoEchalar and Abbey Byrne today and discuss applyi… https://t.co/HhssbP2wrJ", 0, 0,"AmandaMcClella2","Natureeba paul","http://pbs.twimg.com/profile_images/1032710011527553024/NBJR9ttc_normal.jpg" ,"2021-05-20 21:12:57");
            insertHouse(db, "1395480974374793216","It was a pleasure to share the stage with @JaneRuth_Aceng, @RicardoEchalar and Abbey Byrne today and discuss applyi… https://t.co/HhssbP2wrJ", 0, 0,"AmandaMcClella2","MALE MABIRIZI KIWANUKA","http://pbs.twimg.com/profile_images/1032710011527553024/NBJR9ttc_normal.jpg" ,"2021-05-20 21:12:57");
            insertHouse(db, "1395480974374793216","It was a pleasure to share the stage with @JaneRuth_Aceng, @RicardoEchalar and Abbey Byrne today and discuss applyi… https://t.co/HhssbP2wrJ", 0, 0,"AmandaMcClella2","Owen Robert","http://pbs.twimg.com/profile_images/1032710011527553024/NBJR9ttc_normal.jpg" ,"2021-05-20 21:12:57");

            insertHouse(db, "1395480974374793216","It was a pleasure to share the stage with @JaneRuth_Aceng, @RicardoEchalar and Abbey Byrne today and discuss applyi… https://t.co/HhssbP2wrJ", 0, 0,"AmandaMcClella2","Rukundo Paul","http://pbs.twimg.com/profile_images/1032710011527553024/NBJR9ttc_normal.jpg" ,"2021-05-20 21:12:57");
        }
        if(oldVersion < 3){
            ContentValues tweetValues = new ContentValues();
            tweetValues.put("text","@UG_Airlines @KagutaMuseveni Can we sell off the gulf stream already. Thanks to H.E @SuluhuSamia for setting a good… https://t.co/EpVHg06mLA");
            db.update("TWEET", tweetValues, "id_str = ?", new String[] {"1395480974374793216"});

//            ADD NEW COLUMN TO TABLE
//            db.execSQL("ALTER TABLE HOUSE ADD COLUMN FAVORITE NUMERIC;");

//            DELETE RECORDS FROM TABLE
//            db.delete("HOUSE", "NAME = ?", new String[] {"3 Room House"});
        }
    }
}
