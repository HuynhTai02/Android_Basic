package com.huynhngoctai.test.database.room;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.huynhngoctai.test.database.dao.TourDAO;
import com.huynhngoctai.test.database.entity.Tour;

@Database(entities = {Tour.class}, version = 1)
public abstract class TourRoom extends RoomDatabase {
    private static final String DATABASE_NAME = "tour.db";
    private static TourRoom instance;

    public static synchronized TourRoom getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), TourRoom.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract TourDAO tourDAO();
}
