package com.example.mycart;

import android.content.Intent;
import android.os.Bundle;

import com.example.mycart.Prevalent.Prevalent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<Product> productList;
//    ProductAdapter.ProductsAdapterListener listener;


    RecyclerView recycler_View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Paper.init(this);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent=new Intent(HomeActivity.this,CartActivity1.class);
               startActivity(intent);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView=navigationView.getHeaderView(0);
        TextView userEmailTextView=headerView.findViewById(R.id.profile_email);

        userEmailTextView.setText(Prevalent.currentOnlineUser.getEmail());

        recycler_View=findViewById(R.id.recycler_view);
        recycler_View.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recycler_View.setLayoutManager(layoutManager);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
//                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
//                .setDrawerLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    protected void onStart()
    {
     super.onStart();

        productList = new ArrayList<>();
        productList.add(
                new Product("Plant O Boost","Overall Growth booster ,10g",11.0,R.drawable.pic1));
        productList.add(
                new Product("Plant O Boost","Special flower booster ,10g",11.0,R.drawable.pic2));
        productList.add(
                new Product("Toprose","Rose Plant Food ,500g",60.0,R.drawable.pic3));
        productList.add(
                new Product("Growtabbs","Soil Enricher Tablets ,100g",60.0,R.drawable.pic4));
        productList.add(
                new Product("Biogrow","All purpose organic manure,1kg",60.0,R.drawable.pic5));
        productList.add(
                new Product("Polestar","Organic Food Waste Compost,1kg",80.0,R.drawable.pic6));
        productList.add(
                new Product("Nutri Boom","Odourless Granular Plant Food ,250g",80.0,R.drawable.pic7));
        productList.add(
                new Product("Garden Green","Organic Compost Soil Conditioner ,1kg",120.0,R.drawable.pic8));
        productList.add(
                new Product("Soil mix","Nutrient rich general purpose potting soil mix,10kg",749,R.drawable.pic9)); productList.add(
            new Product("My Organic Garden","Plant Protection and Enhancer Kit ",1349.0,R.drawable.pic10));



        ProductAdapter adapter = new ProductAdapter(this, productList);
        recycler_View.setAdapter(adapter);

    }


    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_cart)
        {
            Intent intent=new Intent(HomeActivity.this,CartActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_orders)
        {

        }

        else if (id == R.id.nav_settings)
        {

        }
        else if (id == R.id.nav_logout)
        {
            Paper.book().destroy();

            Intent intent = new Intent(HomeActivity.this, first.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }
}
