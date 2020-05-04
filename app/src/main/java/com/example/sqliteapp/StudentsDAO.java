package com.example.sqliteapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentsDAO {

    @Insert
    void insert(Students student);

    @Query("DELETE FROM students WHERE idstudent = :studentID")
    public void deleteByTitle(int studentID);


    @Query("UPDATE students SET imie = :imie, nazwisko = :nazwisko," +
            " wiek = :wiek, grupa_idgrupa = :grupa_idgrupa" +
            " WHERE idstudent = :idstudent")
    void update(String imie,String nazwisko,int wiek, int grupa_idgrupa, int idstudent);

    @Query("select idstudent,imie, nazwisko,wiek,grupa_idgrupa from students where grupa_idgrupa = :idgrupa")
    public List<Students> ListUsersOfGivenGroup(int idgrupa);


    @Query("select students.imie as imieStudenta,students.nazwisko as nazwiskoStudenta," +
            "grupa.nazwa as nazwaGrupy" +
            " from students,grupa " +
            "where grupa.idgrupa = students.grupa_idgrupa")
    public List<StudentsGrupa>getAllUsersWithGroupName();

    static class StudentsGrupa{
        public String imieStudenta;
        public String nazwiskoStudenta;
        public String nazwaGrupy;
    }

}
