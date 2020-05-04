package com.example.sqliteapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Students {

    @PrimaryKey public int idstudent;

    public String nazwisko;
    public String imie;
    public int wiek;
    public int grupa_idgrupa;
}
