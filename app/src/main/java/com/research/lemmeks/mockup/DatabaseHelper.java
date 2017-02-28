package com.research.lemmeks.mockup;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LemmeKS on 2/2/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "subjectM.db";

    public static final String TABLE_NAME = "subjectM_table"; //master db for testing
    public static final String ID = "ID";
    public static final String SES_DATE = "DATE";
    public static final String USER_ID  = "USER_ID";
    public static final String SCO_INTO = "SCORE_INTONATION";
    public static final String SCO_PRON = "SCORE_PRONUNCIATION";
    public static final String SCO_TEMP = "SCORE_TEMPO";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +SES_DATE + " TEXT, " + USER_ID + " INTEGER, " + SCO_INTO +" REAL, " + SCO_PRON + " REAL, " + SCO_TEMP + " REAL)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /*public boolean insertData(String date, String scoInto, String scoPron, String scoTemp){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SES_DATE, date);
        contentValues.put(SCO_INTO, scoInto);
        contentValues.put(SCO_PRON, scoPron);
        contentValues.put(SCO_TEMP, scoTemp);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return  false;
        else
            return true;
    }*/
}
