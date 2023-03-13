package com.tai.viewp2_ex_utc2.model;

public class Beer {
    // id ảnh
    int thumbBeer;
    String nameBeer;
    Double priceBeer;

    public Beer(int thumbBeer, String nameBeer, Double priceBeer) {
        this.thumbBeer = thumbBeer;
        this.nameBeer = nameBeer;
        this.priceBeer = priceBeer;
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

    public Double getPriceBeer() {
        return priceBeer;
    }

    public void setPriceBeer(Double priceBeer) {
        this.priceBeer = priceBeer;
    }
}
