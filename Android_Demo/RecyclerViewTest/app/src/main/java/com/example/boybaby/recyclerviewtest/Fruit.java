package com.example.boybaby.recyclerviewtest;

/**
 * Created by Boy Baby on 2018/4/29.
 */

public class Fruit {
    private String name;
    private int imageID;

    public Fruit(String name, int imageID) {
        this.name = name;
        this.imageID = imageID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
