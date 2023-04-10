package com.example.student_manager_sqlite;

import android.app.Application;

import androidx.room.Room;

import com.example.student_manager_sqlite.db.StudentDB;

public class App extends Application {
    private static App instance;
    private StudentDB studentDB;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initDB();
    }

    private void initDB() {
        studentDB = Room.databaseBuilder(getApplicationContext(),
                StudentDB.class,"student-DB").build();
    }

    public StudentDB getStudentDB() {
        return studentDB;
    }

    public static App getInstance() {
        return instance;
    }
}

