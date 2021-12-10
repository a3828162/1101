package com.cornez.shippingcalculator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;


import java.util.ArrayList;
import java.util.HashMap;
import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.session.PlaybackState;
import android.util.Log;
import java.util.ArrayList;



class DBHelper extends SQLiteOpenHelper
{

    String TAG = DBHelper.class.getSimpleName();
    String TableName;


    public DBHelper(@Nullable Context context
            , @Nullable String dataBaseName
            , @Nullable SQLiteDatabase.CursorFactory factory, int version, String TableName) {
        super(context, dataBaseName, factory, version);
        this.TableName = TableName;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*String SQLTable = "CREATE TABLE IF NOT EXISTS " + TableName + "( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Name TEXT, " +
                "Height TEXT," +
                "Weight TEXT," +
                "Activity TEXT" +
                ");";*/
        String SQLTable = "CREATE TABLE IF NOT EXISTS " + TableName + "( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Name TEXT, " +
                "Height TEXT," +
                "Weight TEXT," +
                "Activity TEXT," +
                "Knee TEXT," +
                "Age TEXT," +
                "Gender TEXT," +
                "Mode TEXT" +
                ");";
        db.execSQL(SQLTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        final String SQL = "DROP TABLE " + TableName;
        db.execSQL(SQL);

    }

    /*=======================================自定義方法區↓=======================================*/

    //檢查資料表狀態，若無指定資料表則新增
    public void chickTable(){
        Cursor cursor = getWritableDatabase().rawQuery(
                "select DISTINCT tbl_name from sqlite_master where tbl_name = '" + TableName + "'", null);
        if (cursor != null) {
            if (cursor.getCount() == 0)
                /*getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS " + TableName + "( " +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Name TEXT, " +
                        "Height TEXT," +
                        "Weight TEXT," +
                        "Activity TEXT" +
                        ");");*/
                getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS " + TableName + "( " +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Name TEXT, " +
                        "Height TEXT," +
                        "Weight TEXT," +
                        "Activity TEXT," +
                        "Knee TEXT," +
                        "Age TEXT," +
                        "Gender TEXT," +
                        "Mode TEXT" +
                        ");");
            cursor.close();
        }
    }

    //新增資料
    public void addData(String name, String height, String weight, String activity,String knee,String age,String gender,String mode) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", name);
        values.put("Height", height);
        values.put("Weight", weight);
        values.put("Activity", activity);
        values.put("Knee",knee);
        values.put("Age",age);
        values.put("Gender",gender);
        values.put("Mode",mode);
        db.insert(TableName, null, values);
    }

    //顯示所有資料
    public ArrayList<HashMap<String, String>> showAll() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(" SELECT * FROM " + TableName, null);
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        while (c.moveToNext()) {
            HashMap<String, String> hashMap = new HashMap<>();

            String id = c.getString(0);
            String name = c.getString(1);
            String height = c.getString(2);
            String weight = c.getString(3);
            String activity = c.getString(4);
            String knee = c.getString(5);
            String age = c.getString(6);
            String gender = c.getString(7);
            String mode = c.getString(8);


            hashMap.put("id", id);
            hashMap.put("Name", name);
            hashMap.put("Height", height);
            hashMap.put("Weight", weight);
            hashMap.put("Activity", activity);
            hashMap.put("Knee", knee);
            hashMap.put("Age", age);
            hashMap.put("Gender", gender);
            hashMap.put("Mode", mode);

            arrayList.add(hashMap);
        }
        return arrayList;

    }

    //以興趣篩選資料
    public ArrayList<HashMap<String, String>> searchByName(String getName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(" SELECT * FROM " + TableName
                + " WHERE Name =" + "'" + getName + "'", null);
        //boolean tmp = c.moveToNext();
        //boolean tmp =false;
        //int a=5;
       // int b=4;
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        if (c.getCount()>0) {
            HashMap<String, String> hashMap = new HashMap<>();
            c.moveToFirst();
            String id = c.getString(0);
            String name = c.getString(1);
            String height = c.getString(2);
            String weight = c.getString(3);
            String activity = c.getString(4);
            String knee = c.getString(5);
            String age = c.getString(6);
            String gender = c.getString(7);
            String mode = c.getString(8);

            hashMap.put("id", id);
            hashMap.put("Name", name);
            hashMap.put("Height", height);
            hashMap.put("Weight", weight);
            hashMap.put("Activity", activity);
            hashMap.put("Knee", knee);
            hashMap.put("Age", age);
            hashMap.put("Gender", gender);
            hashMap.put("Mode", mode);

            arrayList.add(hashMap);
        }
        return arrayList;
    }

    //以id刪除資料(簡單)
    public void deleteByNameEZ(String name){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TableName,"_id = " + name,null);
    }
}
