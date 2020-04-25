package com.example.mycart;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class consult extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button btn;
    Context context;
    Button p, v;
    CardView cardview;
    TextView textview;
    LinearLayout linearLayout;
    RelativeLayout.LayoutParams layoutparams;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);
        btn = findViewById(R.id.btn);
        context = getApplicationContext();
        //context1=getApplicationContext();
        linearLayout = (LinearLayout) findViewById(R.id.rel);
        Spinner spinner = findViewById(R.id.spin);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        final String text = adapterView.getItemAtPosition(i).toString();
        //Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout.removeAllViews();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference().child("consult");
                ref.addValueEventListener(new ValueEventListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            String url = snapshot.child("image").getValue(String.class);
                            String phone = snapshot.child("phone").getValue(Long.class).toString();
                            String name = snapshot.child("name").getValue(String.class);
                            String spec = snapshot.child("specialization").getValue(String.class);
                            //Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();

                            if(text.equalsIgnoreCase(spec))
                            {

                                CreateCardViewProgrammatically(name, url, spec, phone);
                            }
                            else if(text.equalsIgnoreCase("All"))
                            {
                                CreateCardViewProgrammatically(name, url, spec, phone);
                            }

                        }
                    }

                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    private void CreateCardViewProgrammatically(String name, String url, String spec, final String phone) {
                        cardview = new CardView(context);

                        layoutparams = new RelativeLayout.LayoutParams(
                                RelativeLayout.LayoutParams.MATCH_PARENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT
                        );

                        layoutparams.setMargins(20,20,20,20);
                        cardview.setLayoutParams(layoutparams);
                        //cardview.setLayoutParams(layoutparams1);
                        cardview.setMaxCardElevation(50);
                        cardview.setCardElevation(10);
                        cardview.setContentPadding(50, 50, 50, 50);
                        //cardview.setPadding(20,20,20,20);





                        RelativeLayout layout = new RelativeLayout(consult.this);


                        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                        RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                        RelativeLayout.LayoutParams params4 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);


                        imageView = new ImageView(context);
                        imageView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT));
                        imageView.setId(View.generateViewId());
                        Picasso.get().load(url).into(imageView);
                        layout.addView(imageView, params1);


                        textview = new TextView(context);
                        textview.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT));
                        textview.setText("Name : " + name + "\n" + "Specialization : " + spec);
                        textview.setId(View.generateViewId());
                        params2.addRule(RelativeLayout.RIGHT_OF, imageView.getId());
                        layout.addView(textview, params2);
                        //cardview.addView(layout);


                        p = new Button(context);
                        p.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT));
                        p.setText("Voice Call");
                        p.setId(View.generateViewId());
                        params3.addRule(RelativeLayout.BELOW, textview.getId());
                        params3.addRule(RelativeLayout.RIGHT_OF, imageView.getId());
                        layout.addView(p, params3);

                        p.setOnClickListener(new View.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.M)
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(Intent.ACTION_CALL);

                                intent.setData(Uri.parse("tel:" + phone));
                                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    // TODO: Consider calling
                                    //    Activity#requestPermissions
                                    // here to request the missing permissions, and then overriding
                                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                    //                                          int[] grantResults)
                                    // to handle the case where the user grants the permission. See the documentation
                                    // for Activity#requestPermissions for more details.
                                    return;
                                }
                                startActivity(intent);
                            }
                        });

                        v=new Button(context);
                        v.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT));
                        v.setText("Video Call");
                        v.setId(View.generateViewId());
                        params4.addRule(RelativeLayout.BELOW, p.getId());
                        params4.addRule(RelativeLayout.RIGHT_OF, imageView.getId());
                        layout.addView(v,params4);

                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent i = new Intent();
                                i.setPackage("com.google.android.apps.tachyon");
                                i.setAction("com.google.android.apps.tachyon.action.CALL");
                                i.setData(Uri.parse("tel:"+phone));
                                startActivity(i);
                            }
                        });


                        cardview.addView(layout);
                        linearLayout.addView(cardview);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

