package com.example.mycart;
import com.example.mycart.Model.Users;
import com.example.mycart.Prevalent.Prevalent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mycart.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class first extends AppCompatActivity {

    private Button join_now_btn,login_btn;
    private EditText mobile,password;
    private ProgressDialog loadingbar;
    private CheckBox checkBoxRememberMe;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        join_now_btn=(Button) findViewById(R.id.join_now_btn);
        login_btn=(Button) findViewById(R.id.login_btn);
        mobile=(EditText) findViewById(R.id.mobile);
        password=(EditText) findViewById(R.id.password);
        loadingbar=new ProgressDialog(this);
        checkBoxRememberMe=(CheckBox) findViewById(R.id.remember_me);
        Paper.init(this);

        join_now_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(first.this,reg_activity.class);
                startActivity(intent);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });


        String UserPhoneKey=Paper.book().read(Prevalent.UserPhoneKey);
        String UserPasswordKey=Paper.book().read(Prevalent.UserPasswordKey);


        if(UserPhoneKey!=""&&UserPasswordKey!="")
        {

            if(!TextUtils.isEmpty(UserPhoneKey)&&!TextUtils.isEmpty(UserPasswordKey))
            {


                AllowAccess(UserPhoneKey,UserPasswordKey);

                loadingbar.setTitle("Already Logged in");
                loadingbar.setMessage("Please wait...");
                loadingbar.setCanceledOnTouchOutside(false);
                loadingbar.show();
            }
        }




    }

    private void AllowAccess(final String mob, final String pwd)
    {

        final DatabaseReference rootref;
        rootref= FirebaseDatabase.getInstance().getReference();

        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child("Users").child(mob).exists())
                {


                    Users userData=dataSnapshot.child("Users").child(mob).getValue(Users.class);


                    if(userData.getPhone().equals(mob))
                    {
                        if(userData.getPassword().equals(pwd))
                        {
//                            Toast.makeText(first.this,"Logged In! ",Toast.LENGTH_SHORT).show();
                            Toast.makeText(first.this, "hey", Toast.LENGTH_SHORT).show();
                            loadingbar.dismiss();

                            Intent intent=new Intent(first.this,HomeActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(first.this,"Incorrect Password! ",Toast.LENGTH_SHORT).show();
                            loadingbar.dismiss();
                        }
                    }

                }
                else
                {
                    Toast.makeText(first.this,"Account with"+mob+"does not exist!",Toast.LENGTH_SHORT).show();
                    loadingbar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void loginUser() {

        String mob=mobile.getText().toString();
        String pwd=password.getText().toString();

        if(TextUtils.isEmpty(mob))
        {
            Toast.makeText(this,"Please enter your email address",Toast.LENGTH_SHORT).show();

        }
        else if(TextUtils.isEmpty(pwd))
        {
            Toast.makeText(this,"Please enter a password",Toast.LENGTH_SHORT).show();


        }
        else
        {
            loadingbar.setTitle("Verifying Credentials");
            loadingbar.setMessage("Please wait while we are verifying the credentials");
            loadingbar.setCanceledOnTouchOutside(false);
            loadingbar.show();

            AllowAccessToAccount(mob,pwd);
        }
    }

    private void AllowAccessToAccount(final String mob, final String pwd)
    {
        if(checkBoxRememberMe.isChecked())
        {
            Paper.book().write(Prevalent.UserPhoneKey,mob);
            Paper.book().write(Prevalent.UserPasswordKey,pwd);

            Toast.makeText(this, Prevalent.UserPhoneKey, Toast.LENGTH_SHORT).show();


        }
        final DatabaseReference rootref;
        rootref= FirebaseDatabase.getInstance().getReference();

        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child("Users").child(mob).exists())
                {


                    Users userData=dataSnapshot.child("Users").child(mob).getValue(Users.class);


                    if(userData.getPhone().equals(mob))
                    {
                        if(userData.getPassword().equals(pwd))
                        {
                            Toast.makeText(first.this, Prevalent.UserPhoneKey, Toast.LENGTH_SHORT).show();
                            Toast.makeText(first.this,"Logged In! ",Toast.LENGTH_SHORT).show();
                            loadingbar.dismiss();

                            Intent intent=new Intent(first.this,HomeActivity.class);

                            Prevalent.currentOnlineUser=userData;
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(first.this,"Incorrect Password! ",Toast.LENGTH_SHORT).show();
                            loadingbar.dismiss();
                        }
                    }

                }
                else
                {
                    Toast.makeText(first.this,"Account with"+mob+"does not exist!",Toast.LENGTH_SHORT).show();
                    loadingbar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
