package com.example.mycart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    List<Product> productList;
//    ProductAdapter.ProductsAdapterListener listener;


    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productList = new ArrayList<>();
        productList.add(
                new Product("Plant O Boost","Overall Growth booster ,10g",11,R.drawable.pic1));
        productList.add(
                new Product("Plant O Boost","Special flower booster ,10g",11,R.drawable.pic2));
        productList.add(
                new Product("Toprose","Rose Plant Food ,500g",60,R.drawable.pic3));
        productList.add(
                new Product("Growtabbs","Soil Enricher Tablets ,100g",60,R.drawable.pic4));
        productList.add(
                new Product("Biogrow","All purpose organic manure,1kg",60,R.drawable.pic5));
        productList.add(
                new Product("Polestar","Organic Food Waste Compost,1kg",80,R.drawable.pic6));
        productList.add(
                new Product("Nutri Boom","Odourless Granular Plant Food ,250g",80,R.drawable.pic7));
        productList.add(
                new Product("Garden Green","Organic Compost Soil Conditioner ,1kg",120,R.drawable.pic8));
        productList.add(
                new Product("Soil mix","Nutrient rich general purpose potting soil mix,10kg",749,R.drawable.pic9)); productList.add(
                new Product("My Organic Garden","Plant Protection and Enhancer Kit ",1349,R.drawable.pic10));



        ProductAdapter adapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(adapter);
    }

//    @Override
//    public void insertvalues(View v, int position) {
//        Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show();
//
//    }
}

