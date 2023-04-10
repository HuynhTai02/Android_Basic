package com.example.student_manager_sqlite.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys = {"SubjectId"})
public class Subject {
    @NonNull
    @ColumnInfo(name = "SubjectId")
    public String id;

    @NonNull
    @ColumnInfo(name = "StudentId")
    public String studentId;

    @NonNull
    @ColumnInfo(name = "Name")
    public String name;

    @ColumnInfo(name = "Number")
    public int number;
}
