package com.huynhngoctai.test.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Tour")
public class Tour implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int tourID;
    private String tourName;
    private String tourDescription;
    private int numbOfGuests;
    private String schedule;
    private double tourPrice;

    public Tour(String tourName, String tourDescription, int numbOfGuests, String schedule, double tourPrice) {
        this.tourName = tourName;
        this.tourDescription = tourDescription;
        this.numbOfGuests = numbOfGuests;
        this.schedule = schedule;
        this.tourPrice = tourPrice;
    }

    public int getTourID() {
        return tourID;
    }

    public void setTourID(int tourID) {
        this.tourID = tourID;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getTourDescription() {
        return tourDescription;
    }

    public void setTourDescription(String tourDescription) {
        this.tourDescription = tourDescription;
    }

    public int getNumbOfGuests() {
        return numbOfGuests;
    }

    public void setNumbOfGuests(int numbOfGuests) {
        this.numbOfGuests = numbOfGuests;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public double getTourPrice() {
        return tourPrice;
    }

    public void setTourPrice(double tourPrice) {
        this.tourPrice = tourPrice;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "tourID=" + tourID +
                ", tourName='" + tourName + '\'' +
                ", tourDescription='" + tourDescription + '\'' +
                ", numbOfGuests=" + numbOfGuests +
                ", schedule='" + schedule + '\'' +
                ", tourPrice=" + tourPrice +
                '}';
    }
}