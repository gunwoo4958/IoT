package com.example.soo.bungbung2;

public class Contents {
    String id;
    String x;
    String y;





    public Contents(String id, String x, String y) {
        this.id = id;
        this.x = x;
        this.y = y;
//        this.number = number;
//        this.boolen = boolen;

    }

    public String toString() {
        return String.format(" x : %s \n y : %s", x, y);
    }


}
