package com.example.student_manager_sqlite.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys = {"StudentId"})
public class Student {
    @NonNull
    @ColumnInfo(name = "StudentId")
    public String id;

    @NonNull
    @ColumnInfo(name = "FullName")
    public String name;

    @NonNull
    @ColumnInfo(name = "Age")
    public int age;
}
