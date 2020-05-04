package com.example.sqliteapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Grupa  {
    @PrimaryKey public int idgrupa;

    public String nazwa;
}
