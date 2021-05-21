package com.pkasemer.sensetweet;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SenseDBHelper extends SQLiteOpenHelper {

    private  static final  String DB_NAME  = "SENSE_DB"; // db name;
    private  static final int DB_VERSION = 1; //version of the app
    private  static final String DB_TABLE = "TWEET"; //version of the app
    List<Tweet> tweetList;

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


    private  static  void insertTweet(SQLiteDatabase db,
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

        db.insert(DB_TABLE, null, tweetValues);

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

//            insertTweet(db, "1395488974374793216","It was a pleasure to share the stage with @JaneRuth_Aceng, @RicardoEchalar and Abbey Byrne today and discuss applyi… https://t.co/HhssbP2wrJ", 0, 0,"AmandaMcClella2","Kironde Yasin","http://pbs.twimg.com/profile_images/1032710011527553024/NBJR9ttc_normal.jpg" ,"2021-05-20 21:12:57");
//            insertTweet(db, "1395488974374793216","It was a pleasure to share the stage with @JaneRuth_Aceng, @RicardoEchalar and Abbey Byrne today and discuss applyi… https://t.co/HhssbP2wrJ", 0, 0,"AmandaMcClella2","Amanda McClelland","http://pbs.twimg.com/profile_images/1032710011527553024/NBJR9ttc_normal.jpg" ,"2021-05-20 21:12:57");
//            insertTweet(db, "1395488974374793216","It was a pleasure to share the stage with @JaneRuth_Aceng, @RicardoEchalar and Abbey Byrne today and discuss applyi… https://t.co/HhssbP2wrJ", 0, 0,"AmandaMcClella2","Natureeba paul","http://pbs.twimg.com/profile_images/1032710011527553024/NBJR9ttc_normal.jpg" ,"2021-05-20 21:12:57");
//            insertTweet(db, "1395488974374793216","It was a pleasure to share the stage with @JaneRuth_Aceng, @RicardoEchalar and Abbey Byrne today and discuss applyi… https://t.co/HhssbP2wrJ", 0, 0,"AmandaMcClella2","MALE MABIRIZI KIWANUKA","http://pbs.twimg.com/profile_images/1032710011527553024/NBJR9ttc_normal.jpg" ,"2021-05-20 21:12:57");
//            insertTweet(db, "1395488974374793216","It was a pleasure to share the stage with @JaneRuth_Aceng, @RicardoEchalar and Abbey Byrne today and discuss applyi… https://t.co/HhssbP2wrJ", 0, 0,"AmandaMcClella2","Owen Robert","http://pbs.twimg.com/profile_images/1032710011527553024/NBJR9ttc_normal.jpg" ,"2021-05-20 21:12:57");
//
//            insertTweet(db, "1395488974374793216","It was a pleasure to share the stage with @JaneRuth_Aceng, @RicardoEchalar and Abbey Byrne today and discuss applyi… https://t.co/HhssbP2wrJ", 0, 0,"AmandaMcClella2","Rukundo Paul","http://pbs.twimg.com/profile_images/1032710011527553024/NBJR9ttc_normal.jpg" ,"2021-05-20 21:12:57");
//
        }
        if(oldVersion < 3){
//            ContentValues tweetValues = new ContentValues();
//            tweetValues.put("text","@UG_Airlines @KagutaMuseveni Can we sell off the gulf stream already. Thanks to H.E @SuluhuSamia for setting a good… https://t.co/EpVHg06mLA");
//            db.update(DB_TABLE, tweetValues, "id_str = ?", new String[] {"1395480974374793216"});

//            ADD NEW COLUMN TO TABLE
//            db.execSQL("ALTER TABLE HOUSE ADD COLUMN FAVORITE NUMERIC;");

//            DELETE RECORDS FROM TABLE
//            db.delete("HOUSE", "NAME = ?", new String[] {"3 Room House"});
        }
    }


    List<Tweet> listTweetsBD() {
        String sql = "select * from " + DB_TABLE + " order by _id DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        tweetList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String id_str = cursor.getString(1);
                String text = cursor.getString(2);
                double subjectivity = cursor.getDouble(4);
                double polarity = cursor.getDouble(3);
                String username = cursor.getString(5);
                String name = cursor.getString(6);
                String profile_image_url = cursor.getString(7);
                String received_at = cursor.getString(8);

                tweetList.add(new Tweet(id_str, text, polarity,subjectivity,username,name,profile_image_url,received_at));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return tweetList;
    }
    void addTweet(String id_str, String text, double polarity, double subjectivity, String username, String name, String profile_image_url, String received_at) {
        ContentValues values = new ContentValues();
        values.put("id_str", id_str);
        values.put("text", text);
        values.put("polarity", polarity);
        values.put("subjectivity", subjectivity);
        values.put("username", username);
        values.put("name", name);
        values.put("profile_image_url", profile_image_url);
        values.put("received_at", received_at);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DB_TABLE, null, values);
    }
    void updateTweet(Tweet tweet) {
        ContentValues values = new ContentValues();
        values.put("name", tweet.getName());
        values.put("username", tweet.getUsername());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(DB_TABLE, values, "id_str" + " = ?", new String[]{String.valueOf(tweet.getId_str())});
    }



    void deleteTweet(String id_str) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_TABLE, "id_str" + " = ?", new String[]{id_str});
    }

    public boolean checktweetindb(String id_str){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DB_TABLE,
                new String[]{"id_str", "username","name"},
                "id_str = ?",
                new String[] {id_str},
                null, null, null, null);
        if (cursor.moveToFirst()) {
           //recordexist
            cursor.close();
            return false;
        } else {
            //record not existing
            cursor.close();
            return true;
        }
    }


}
