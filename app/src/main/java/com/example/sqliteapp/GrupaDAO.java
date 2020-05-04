package com.example.sqliteapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface GrupaDAO {
    @Insert
    void insert(Grupa grupa);

    @Delete
    void delete(Grupa grupa);

    @Update
    void update(Grupa grupa);
}
