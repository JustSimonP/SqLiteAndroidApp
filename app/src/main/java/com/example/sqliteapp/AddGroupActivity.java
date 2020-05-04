package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddGroupActivity extends AppCompatActivity {
EditText groupName;
DatabaseHelper db;
AppDatabase appDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        groupName = findViewById(R.id.groupName);

        //db = DatabaseHelper.getInstance(this);
        appDB = AppDatabase.getDatabase(this);
    }

    public void addGroup(View view){
        final String groupNameToDB = groupName.getText().toString();
        if (groupNameToDB.matches("")) {
            Toast.makeText(this,"Empty groupName is prohibited",Toast.LENGTH_SHORT).show();

        }else {
//            SQLiteDatabase database =  db.getWritableDatabase();
//            ContentValues values = new ContentValues();
//            values.put("nazwa",groupNameToDB);
//           database.insert("grupa",null,values);

            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    appDB.grupaDAO().insert(new Grupa(groupNameToDB));
                    System.out.println("Grupa added succesfully!");
                }
            });

        }
    }
}
