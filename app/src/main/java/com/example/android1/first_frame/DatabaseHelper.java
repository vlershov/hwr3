package com.example.android1.first_frame;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH; // полный путь к базе данных
    public static int DATABASE_VARSION = 1;
    public static final String DB_NAME = "customers.db";
    public static final String DB_NAME_DROP = "customers";
    public static final String TABLE_CONSTANS = "constans";
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_DATE = "date";
    public static final String KEY_MASSEGES = "massegse";
    private Context myContext;


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VARSION);
        myContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "CREATE TABLE IF NOT EXISTS " + TABLE_CONSTANS + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL," +
                KEY_NAME + " TEXT  NOT NULL, " +
                KEY_MASSEGES + " TEXT  NOT NULL, " +
                KEY_DATE + " TEXT  NOT NULL); ";
        db.execSQL(query1);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {

        db.execSQL("pragma user_version = 1");
        db.execSQL("DROP TABLE IF EXISTS " + DB_NAME_DROP);

        onCreate(db);
    }


    void create_db(SQLiteDatabase db, String name, String date, String massegse){

        ContentValues newValues = new ContentValues();

        newValues.put(KEY_NAME, name);
        newValues.put(KEY_DATE, date);
        newValues.put(KEY_MASSEGES, massegse);

        db.insert(TABLE_CONSTANS, null, newValues);

    }
    public SQLiteDatabase open()throws SQLException {

        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }

}
