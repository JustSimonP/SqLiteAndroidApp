package com.example.sqliteapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper dbHelperInstance;

    public static final String DB_NAME = "student.db";

        public static final String STUDENT_TABLE_NAME = "students";



    public DatabaseHelper(@Nullable Context context) {
        super(context, STUDENT_TABLE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table grupa(idgrupa INTEGER PRIMARY KEY AUTOINCREMENT, nazwa TEXT);");
        db.execSQL("create table students (idstudent INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nazwisko TEXT,imie TEXT,wiek INTEGER,grupa_idgrupa INTEGER," +
                "FOREIGN KEY(grupa_idgrupa) REFERENCES grupa(idgrupa) ON DELETE CASCADE) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.setForeignKeyConstraintsEnabled(true);
        db.execSQL("DROP TABLE IF EXISTS students;");
        db.execSQL("DROP TABLE IF EXISTS grupa;");
        onCreate(db);
    }
    public static DatabaseHelper getInstance(Context context){
        if(dbHelperInstance == null){
            dbHelperInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return dbHelperInstance;
    }

}
