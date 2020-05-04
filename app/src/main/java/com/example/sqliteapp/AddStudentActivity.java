package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudentActivity extends AppCompatActivity {
EditText studentName;
EditText studentLastName;
EditText studentAge;
EditText groupId;
EditText idOfStudent;
DatabaseHelper db;
AppDatabase appDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        //db = DatabaseHelper.getInstance(this);
        studentName = findViewById(R.id.studentName);
        studentLastName = findViewById(R.id.studentLastName);
        studentAge = findViewById(R.id.studentAge);
        groupId = findViewById(R.id.groupId);
        idOfStudent = findViewById(R.id.idOfStudent);
        appDB = AppDatabase.getDatabase(this);
    }

   public void addStudent(View view){
        final String studName = studentName.getText().toString();
        final String studLastName = studentLastName.getText().toString();
        String studAge = studentAge.getText().toString();
        String groupID = groupId.getText().toString();

        if (studAge.matches("") || studLastName.matches("")|| studName.matches("")|| groupID.matches("")){
            Toast.makeText(this,"Leaving empty field is prohibited",Toast.LENGTH_SHORT).show();
        } else {
//            SQLiteDatabase database =  db.getWritableDatabase();
//            ContentValues values = new ContentValues();
//            values.put("nazwisko", studLastName);
//            values.put("imie", studName);
//            values.put("wiek", studAge);
//            values.put("grupa_idgrupa", groupID);
//           database.insert("students",null, values);
            final int age = Integer.parseInt(studAge);
            final int groupNr = Integer.parseInt(groupID);
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    appDB.studentsDAO().insert(new Students(studLastName,studName,age,groupNr));
                }
            });

        }
    }

    public void changeStudent(View view){
        String studName = studentName.getText().toString();
        String studLastName = studentLastName.getText().toString();
        String studAge = studentAge.getText().toString();
        String groupID = groupId.getText().toString();
        String iDOfStudent = idOfStudent.getText().toString();
        if(iDOfStudent.matches("")){
            Toast.makeText(this,"Id of student was not inserted",Toast.LENGTH_SHORT).show();
        }else{
            SQLiteDatabase database = db.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nazwisko", studLastName);
            values.put("imie", studName);
            //values.put("wiek", studAge);
            //values.put("grupa_idgrupa", groupID);
            database.update("students",values,"idstudent = ?",new String[]{iDOfStudent});

        }
    }

    public void deleteStudent(View view){

        String iDOfStudent = idOfStudent.getText().toString();
        if (iDOfStudent.matches("")){
            Toast.makeText(this,"Id of student was not inserted",Toast.LENGTH_SHORT).show();
        }else{
            SQLiteDatabase database = db.getWritableDatabase();
            int isDeleted = database.delete("students","idstudent = ?",new String[]{iDOfStudent} );
            if (isDeleted != 0)Toast.makeText(this,"Record successfully deleted",Toast.LENGTH_SHORT).show();
        }

    }
}
