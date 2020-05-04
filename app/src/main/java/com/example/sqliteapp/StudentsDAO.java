package com.example.sqliteapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface StudentsDAO {

    @Insert
    void insert(Students student);

    @Delete
    void delete(Students student);

    @Update
    void update(Students student);

    @Query("select imie, nazwisko from students where grupa_idgrupa = :idgrupa")
    public Students[] ListUsersOfGivenGroup(int idgrupa);


    @Query("select students.imie as imieStudenta,students.nazwisko as nazwiskoStudenta,grupa.nazwa as nazwaGrupy from students,grupa " +
            "where grupa.idgrupa = students.grupa_idgrupa")
    public LiveData<StudentsGrupa>getAllUsersWithGroupName();

    static class StudentsGrupa{
        public String imieStudenta;
        public String nazwiskoStudenta;
        public String nazwaGrupy;
    }

}
