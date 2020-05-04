package com.example.sqliteapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Grupa  {
    @PrimaryKey(autoGenerate = true) public int idgrupa;

    public String nazwa;

    public Grupa(@NonNull String nazwa) {
        this.nazwa = nazwa;
    }
}
