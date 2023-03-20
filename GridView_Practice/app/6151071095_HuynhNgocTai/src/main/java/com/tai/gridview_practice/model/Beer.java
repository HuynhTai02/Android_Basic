package com.tai.gridview_practice.model;

public class Beer {
    // id ảnh
    int thumbBeer;
    String nameBeer;

    public Beer(int thumbBeer, String nameBeer) {
        this.thumbBeer = thumbBeer;
        this.nameBeer = nameBeer;
    }

    public int getThumbBeer() {
        return thumbBeer;
    }

    public void setThumbBeer(int thumbBeer) {
        this.thumbBeer = thumbBeer;
    }

    public String getNameBeer() {
        return nameBeer;
    }

    public void setNameBeer(String nameBeer) {
        this.nameBeer = nameBeer;
    }
}
