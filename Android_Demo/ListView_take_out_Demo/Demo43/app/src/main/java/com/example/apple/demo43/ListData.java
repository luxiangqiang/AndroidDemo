package com.example.apple.demo43;

/**
 * Created by apple on 2018/4/4.
 */

public class ListData {
    //食品图片id
    private int imageID;
    //食品名称
    private String name;
    //食品价格
    private double price;
    //食品份数
    private int count;
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public void setImageID(int imageID) {

        this.imageID = imageID;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setPrice(double price) {

        this.price = price;
    }
    public int getImageID() {

        return imageID;
    }

    public String getName() {

        return name;
    }

    public double getPrice() {

        return price;
    }

}
