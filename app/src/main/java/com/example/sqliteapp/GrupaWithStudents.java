package com.example.sqliteapp;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class GrupaWithStudents {

    @Embedded public Grupa grupa;
    @Relation(
            parentColumn = "idgrupa",
            entityColumn = "grupa_idgrupa"
    )
    public List<Students> students;
}
