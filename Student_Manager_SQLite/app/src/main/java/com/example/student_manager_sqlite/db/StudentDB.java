package com.example.student_manager_sqlite.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.student_manager_sqlite.db.dao.StudentDAO;
import com.example.student_manager_sqlite.db.entities.Point;
import com.example.student_manager_sqlite.db.entities.Student;
import com.example.student_manager_sqlite.db.entities.Subject;

@Database(entities = {Student.class, Subject.class, Point.class},version = 1)
public abstract class StudentDB extends RoomDatabase {
    public abstract StudentDAO studentDAO();
}
