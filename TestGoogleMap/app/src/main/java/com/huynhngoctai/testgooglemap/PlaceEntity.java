package com.huynhngoctai.testgooglemap;

import com.google.android.gms.maps.model.LatLng;

public class PlaceEntity {
    private String name, address, description;
    private int photoBG;
    private final LatLng location;

    public PlaceEntity(String name, String address, String description, int photoBG, LatLng location) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.photoBG = photoBG;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPhotoBG() {
        return photoBG;
    }

    public void setPhotoBG(int photoBG) {
        this.photoBG = photoBG;
    }

    public LatLng getLocation() {
        return location;
    }
}
