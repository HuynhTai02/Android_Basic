package com.example.student_manager_sqlite.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys = {"StudentId"})
public class StudentScore extends Student {
    @NonNull
    @ColumnInfo(name = "Name")
    public String name;

    @ColumnInfo(name = "Score")
    public int score;
}
