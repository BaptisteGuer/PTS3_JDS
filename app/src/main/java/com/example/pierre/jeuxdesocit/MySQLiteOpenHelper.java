package com.example.pierre.jeuxdesocit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private String creation = "create table Item("
            +"id INTEGER PRIMARY KEY AUTOINCREMENT,"
            +"nom TEXT NOT NULL,"
            +"valeur TEXT NOT NULL,"
            +"nomKit TEXT NOT NULL);";

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(creation);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}