package com.example.mycart.Model;

import android.widget.TextView;

public class orders {
    public String prodname;
    public int quant;
    public int price;

    public orders(String prodname, int quant, int price) {
        this.prodname = prodname;
        this.quant = quant;
        this.price = price;

    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
