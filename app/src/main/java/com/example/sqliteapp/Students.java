package com.example.sqliteapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Students {

    @PrimaryKey(autoGenerate = true) public int idstudent;

    public String nazwisko;
    public String imie;
    public int wiek;
    public int grupa_idgrupa;

    public Students(String nazwisko, String imie, int wiek, int grupa_idgrupa) {
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.wiek = wiek;
        this.grupa_idgrupa = grupa_idgrupa;
    }
}
