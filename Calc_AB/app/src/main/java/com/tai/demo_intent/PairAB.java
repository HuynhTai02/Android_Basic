package com.tai.demo_intent;

import java.io.Serializable;

public class PairAB implements Serializable {
    private final double a;
    private final double b;

    public PairAB(double a, double b){
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }
}
