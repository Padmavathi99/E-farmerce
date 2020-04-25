package com.example.mycart;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycart.Model.orders;
import com.example.mycart.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartActivity1 extends AppCompatActivity {

    TextView total;
    List<String> arrName = new ArrayList<>();
    List<String> arrQuantity = new ArrayList<>();
    //List<String> arrImage = new ArrayList<>();
    List<String> arrPrice = new ArrayList<>();
    CardView card;
    Button button;
    Context context;
    LinearLayout linearLayout;
    int count = 0;
    int i = 0;
    LinearLayout linear;
    @Override
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart1);
        total=(TextView) findViewById(R.id.total);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Cart list/"+ Prevalent.currentOnlineUser.getPhone()+"/Products");


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    String name = postSnapshot.child("name").getValue(String.class);
                    //Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
                    Double quantity = postSnapshot.child("quantity").getValue(Double.class);
                    //String link = postSnapshot.child("image").getValue(String.class);
                    Double price = postSnapshot.child("price").getValue(Double.class);
                    //Toast.makeText(context, "hey"+quantity, Toast.LENGTH_SHORT).show();

                    count+=quantity*price;








                    //Picasso.get().load(link).into();
                    //Uri linkuri = Uri.parse(link);
//                    arrQuantity.add(quantity);
//
//                    arrImage.add(link);
//                    arrName.add(name);
//                    arrPrice.add(price);
//
                    CreateCardViewProgrammatically(name, quantity, price);







                    // here you can access to name property like university.name

                }
//                Toast.makeText(context, "hey"+count, Toast.LENGTH_SHORT).show();

               total.setText("The total amount is:"+count);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        context = getApplicationContext();

        linear = (LinearLayout) findViewById(R.id.l1);
        //System.out.println(arrName.get());
//        for(i = 0;i < 1;i++)
//        {
//            //Log.e("hello","hi");
//            CreateCardViewProgrammatically(arrName.get(i), arrQuantity.get(i), arrPrice.get(i));
//
//        }
//        try {
//            CreateCardViewProgrammatically(arrName.get(1),arrQuantity.get(1),arrPrice.get(1));
//
//        }
//        catch(Exception e)
//        {
//
//        }
//
//
//        int i=10;



    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void CreateCardViewProgrammatically(String name, Double quantity, Double price) {
        card = new CardView(context);
        // Set the CardView layoutParams
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        card.setLayoutParams(params);

        // Set CardView corner radius
        card.setRadius(9);

        // Set cardView content padding
        card.setContentPadding(15, 50, 15, 50);
        // Set a background color for CardView
        card.setCardBackgroundColor(Color.parseColor("#ffffff"));
        // Set the CardView maximum elevation
        //card.setMaxCardElevation(15);
        // Set CardView elevation
        card.setCardElevation(7);

        RelativeLayout layout = new RelativeLayout(CartActivity1.this);

        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        ImageView imageView=new ImageView(CartActivity1.this);
//




//        ImageView imageView = new ImageView(MainActivity.this);
//        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(500, 500);
//        //p.addRule(RelativeLayout.CENTER_HORIZONTAL);
//        imageView.setLayoutParams(p);
//


//        imageView.setId(View.generateViewId());
//
//        layout.addView(imageView);
//        Picasso.get().load(image).into(imageView);


        TextView textView1 = new TextView(CartActivity1.this);
        textView1.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        textView1.setText("Name : " + name);
        textView1.setId(View.generateViewId());
        //params1.addRule(RelativeLayout.RIGHT_OF, imageView.getId());
        //params1.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layout.addView(textView1, params1);


        TextView textView2 = new TextView(CartActivity1.this);
        textView2.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        textView2.setText("Quantity : " + quantity);
        textView2.setId(View.generateViewId());
        params2.addRule(RelativeLayout.BELOW, textView1.getId());

        //params2.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layout.addView(textView2, params2);


        TextView textView3 = new TextView(CartActivity1.this);
        textView3.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        textView3.setText("Price : " + price);
        //textView3.setId(View.generateViewId());
        params3.addRule(RelativeLayout.BELOW, textView2.getId());

        //params2.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layout.addView(textView3, params3);

        card.addView(layout);
        View v = new View(this);
        v.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                7
        ));
        View v1 = new View(this);
        v1.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                10
        ));
        v1.setBackgroundColor(android.R.color.white);



        linear.addView(card);
        linear.addView(v);
        linear.addView(v1);






    }


}
