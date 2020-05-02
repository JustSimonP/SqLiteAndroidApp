package com.example.sqliteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayStudentsActivity extends AppCompatActivity {
    DatabaseHelper db;
    ListView viewResult;
    ArrayAdapter arrayAdapter;
    ArrayList arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_students);
        db = DatabaseHelper.getInstance(this);
        SQLiteDatabase database = db.getReadableDatabase();
        String query = "select * from students";
        Cursor cursor =database.rawQuery(query,null);
        if (cursor.getCount()==0){
            Toast.makeText(this,"No data to display",Toast.LENGTH_SHORT).show();
        }else{
            StringBuilder stringBuilder = new StringBuilder();
            while(cursor.moveToNext()){
                stringBuilder.append("Id: " + cursor.getString(0) + "\n");
                stringBuilder.append("LastName: " + cursor.getString(1) + "\n");
                stringBuilder.append("Name: " + cursor.getString(2) + "\n");
                stringBuilder.append("Age: " + cursor.getString(3) + "\n");
                stringBuilder.append("Group: " + cursor.getString(4) + "\n"+"\n");
            }
            AlertDialog.Builder builder = new  AlertDialog.Builder(this);
            builder.setMessage(stringBuilder);
            builder.show();
            System.out.println(stringBuilder.toString());
        }
    }

        public Cursor viewStudents(){
                return null;
        }
}
