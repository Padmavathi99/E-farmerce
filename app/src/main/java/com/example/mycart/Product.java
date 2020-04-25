package com.example.mycart;

public class Product {
    private String title;
    private String shortdesc;
    private double price;
    private int image;

    public Product( String title, String shortdesc, double price, int image) {

        this.title = title;
        this.shortdesc = shortdesc;

        this.price = price;
        this.image = image;
    }



    public String getTitle() {
        return title;
    }

    public String getShortdesc() {
        return shortdesc;
    }



    public double getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }
}