package com.example.mycart;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.mycart.Model.orders;
import com.example.mycart.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Belal on 10/18/2017.
 */


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;
    private DatabaseReference dat;

    //we are storing all the products in a list
    private List<Product> productList;
//    public ProductsAdapterListener onClickListener;

    //getting the context and product list with constructor
    public ProductAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
//        this.onClickListener=listener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_item, null);
        final ProductViewHolder holder=new ProductViewHolder(view);
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(mCtx, holder.textViewPrice.getText().toString(), Toast.LENGTH_SHORT).show();
                DatabaseReference cartlistref=FirebaseDatabase.getInstance().getReference().child("Cart list");
                final HashMap<String,Object> cartMap=new HashMap<>();
                 cartMap.put("name", holder.textViewTitle.getText().toString());
                cartMap.put("price",Double.valueOf(holder.textViewPrice.getText().toString()));
                cartMap.put("quantity",Double.valueOf(holder.quant.getNumber()));

                cartlistref.child(Prevalent.currentOnlineUser.getPhone()).child("Products").child(holder.textViewTitle.getText().toString())
                .updateChildren(cartMap);


//                Toast.makeText(mCtx, holder.textViewPrice.getText().toString(), Toast.LENGTH_SHORT).show();
//                DatabaseReference cartlistref=FirebaseDatabase.getInstance().getReference();
//                final HashMap<String,Object> cartMap=new HashMap<>();
//                cartMap.put("name",holder.textViewTitle.getText().toString());
//                cartMap.put("price",holder.textViewPrice.getText().toString());
//                cartMap.put("quantity",holder.quant.getNumber());
//
//                cartlistref.child("Users").child(Prevalent.currentOnlineUser.getPhone()).child("Cart").child(holder.textViewTitle.getText().toString()).updateChildren(cartMap);


//                dat=FirebaseDatabase.getInstance().getReference();
//
//
//                orders order=new orders(holder.textViewTitle.getText().toString(),Integer.parseInt(holder.textViewPrice.getText().toString()),Integer.parseInt(holder.quant.getNumber()));
//                dat.child("Users").child(Prevalent.UserPhoneKey).child("orders").setValue(order);

            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {
        //getting the product of the specified position
        Product product = productList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getTitle());
        holder.textViewShortDesc.setText(product.getShortdesc());
        holder.textViewPrice.setText(String.valueOf(product.getPrice()));

        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));



    }
    protected void insertvalues(ProductViewHolder holder)
    {






    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    static class ProductViewHolder extends RecyclerView.ViewHolder {

         TextView textViewTitle;
        TextView textViewShortDesc;
         TextView textViewPrice;
        ImageView imageView;
        Button add;
        ElegantNumberButton quant;

        public ProductViewHolder(View itemView) {
            super(itemView);
            this.add = itemView.findViewById(R.id.add);
            this.textViewTitle = itemView.findViewById(R.id.textViewTitle);
            this.textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            this.textViewPrice = itemView.findViewById(R.id.textViewPrice);
            this.imageView = itemView.findViewById(R.id.imageView);
            this.quant = itemView.findViewById(R.id.quant);


        }
    }
    




//    public interface ProductsAdapterListener
//    {
//        void insertvalues(View v,int position);
//    }



}
