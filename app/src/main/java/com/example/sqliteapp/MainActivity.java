package com.example.sqliteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText nameOfGroup;
    AppDatabase appDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //db = new DatabaseHelper(this);
        nameOfGroup = findViewById(R.id.groupNumber);
    }

    public void addStudent(View view){
        Intent intent = new Intent(this,AddStudentActivity.class);
        startActivity(intent);
    }

    public void addGroup(View view){
        Intent intent = new Intent(this,AddGroupActivity.class);
        startActivity(intent);
    }

    public void changeGroup(View view){
        Intent intent = new Intent(this,ChangeGroupActivity.class);
        startActivity(intent);
    }

    public void displayStudentsOfGivenGroup(View view){
        final String groupNr = nameOfGroup.getText().toString();
        final StringBuilder stringBuilder= new StringBuilder();
        if(groupNr.matches("")){
            Toast.makeText(this,"No id of group inserted",Toast.LENGTH_SHORT).show();
        } else {
            //db = DatabaseHelper.getInstance(this);
            //SQLiteDatabase database = db.getReadableDatabase();
            //String selectGivenGroupOfStudents = "select imie, nazwisko from students where grupa_idgrupa = " + groupNr + ";";
           // Cursor cursor = database.rawQuery(selectGivenGroupOfStudents,null);
//            if (cursor.getCount()==0){
//                Toast.makeText(this,"No data to display",Toast.LENGTH_SHORT).show();
//            }else {
//                StringBuilder stringBuilder = new StringBuilder();
//                while (cursor.moveToNext()) {
//                    stringBuilder.append("Imie: " + cursor.getString(0) + "\n");
//                    stringBuilder.append("Nazwisko: " + cursor.getString(1) + "\n\n");
//                    stringBuilder.append("Grupa: " + cursor.getString(2) + "\n\n");
//                     stringBuilder.append("Age: " + cursor.getString(3) + "\n");
//                     stringBuilder.append("Group: " + cursor.getString(4) + "\n"+"\n");
//                }
                appDB = AppDatabase.getDatabase(this);
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    List<Students> listOfGroupStudents = appDB.studentsDAO().ListUsersOfGivenGroup(Integer.parseInt(groupNr));
                    for(Students student:listOfGroupStudents){
                        stringBuilder.append("Imie: " + student.imie + "\n");
                        stringBuilder.append("Nazwisko: " + student.nazwisko + "\n\n");

                    }
                }
            });
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(stringBuilder.toString());
                builder.show();
                System.out.println(stringBuilder.toString());
                //cursor.close();
            }
        }


    public void displayStudents(View view){
        db = DatabaseHelper.getInstance(this);
        SQLiteDatabase database = db.getReadableDatabase();
        String niceQuery = "select students.imie,students.nazwisko,grupa.nazwa from students,grupa " +
                "where students.grupa_idgrupa = grupa.idgrupa;" ;
        Cursor cursor =database.rawQuery(niceQuery,null);
        if (cursor.getCount()==0){
            Toast.makeText(this,"No data to display",Toast.LENGTH_SHORT).show();
        }else{
            StringBuilder stringBuilder = new StringBuilder();
            while(cursor.moveToNext()){
                stringBuilder.append("Imie: " + cursor.getString(0) + "\n");
                stringBuilder.append("Nazwisko: " + cursor.getString(1) + "\n");
                stringBuilder.append("Grupa: " + cursor.getString(2) + "\n\n");

            }
            AlertDialog.Builder builder = new  AlertDialog.Builder(this);
            builder.setMessage(stringBuilder.toString());
            builder.show();
            cursor.close();
        }
    }
}
