package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ChangeGroupActivity extends AppCompatActivity {
DatabaseHelper db;
EditText idOfGroup;
EditText newName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_group);
        db = DatabaseHelper.getInstance(this);
        idOfGroup = findViewById(R.id.idOfGroup);
        newName = findViewById(R.id.newName);
    }

    public void changeGroupName(View view){
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues values = new ContentValues();
       // values.put("idgrupa",idOfGroup.getText().toString());
        values.put("nazwa",newName.getText().toString());
        database.update("grupa", values,"idgrupa = ?", new String[] {idOfGroup.getText().toString()});
    }

    public void deleteGroup(View view){
        SQLiteDatabase database = db.getWritableDatabase();
        String groupID = idOfGroup.getText().toString();
        if(groupID.matches("")){
            Toast.makeText(this,"ID not inserted",Toast.LENGTH_SHORT).show();
        } else {
            database.delete("grupa","idgrupa = ?", new String[] {groupID});
        }
    }
}
