package com.huynhngoctai.test.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.huynhngoctai.test.database.entity.Tour;

import java.util.List;

@Dao
public interface TourDAO {
    @Insert
    void insertTour(Tour tour);

    @Query("SELECT * FROM Tour")
    List<Tour> getAllTour();

    @Update
    void editTour(Tour tour);

    @Delete
    void deleteTour(Tour tour);

    @Query("DELETE FROM Tour")
    void deleteAllTour();

    @Query("SELECT * FROM Tour WHERE tourName LIKE '%' || :name || '%'")
    List<Tour> searchNameTour(String name);
}

