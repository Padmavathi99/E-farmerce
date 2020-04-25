package com.example.mycart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class reg_activity extends AppCompatActivity {

    private Button register_btn;
    private EditText email_reg,mobile_reg,password_reg;
    private ProgressDialog loadingbar;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        register_btn=(Button) findViewById(R.id.register_btn);
        email_reg=(EditText) findViewById(R.id.email_reg);
        password_reg=(EditText) findViewById(R.id.password_reg);
        mobile_reg=(EditText) findViewById(R.id.mobile_reg);
        loadingbar=new ProgressDialog(this);




        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createAccount();
            }
        });




    }
    public void createAccount()
    {
        String email=email_reg.getText().toString();
        String password=password_reg.getText().toString();
        String phone=mobile_reg.getText().toString();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Please enter your email address",Toast.LENGTH_SHORT).show();

        }
        else if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please enter a password",Toast.LENGTH_SHORT).show();

        }
        else if(TextUtils.isEmpty(phone))
        {
            Toast.makeText(this,"Please enter your mobile number",Toast.LENGTH_SHORT).show();


        }
        else
        {
            loadingbar.setTitle("Create Account");
            loadingbar.setMessage("Please wait while we are checking the credentials");
            loadingbar.setCanceledOnTouchOutside(false);
            loadingbar.show();

            Validateemail(email,phone,password);
        }
    }

    private void Validateemail(final String email, final String phone, final String password) {


        final DatabaseReference rootref;
        rootref= FirebaseDatabase.getInstance().getReference();

        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Toast.makeText(reg_activity.this,"In function",Toast.LENGTH_SHORT).show();
                if(!(dataSnapshot.child("Users").child(phone).exists()))
                {

                    HashMap<String,Object> userdataMap=new HashMap<>();
                    userdataMap.put("email",email);
                    userdataMap.put("phone",phone);
                    userdataMap.put("password",password);


                    rootref.child("Users").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(reg_activity.this,"Your account has been created!",Toast.LENGTH_SHORT).show();
                                        loadingbar.dismiss();

                                        Intent intent=new Intent(reg_activity.this,first.class);
                                        startActivity(intent);


                                    }
                                    else
                                    {
                                        Toast.makeText(reg_activity.this,"Network Error , Please try again!",Toast.LENGTH_SHORT).show();
                                        loadingbar.dismiss();
                                    }
                                }
                            });


                }
                else
                {
                    Toast.makeText(reg_activity.this,"This"+email+"already exists!",Toast.LENGTH_SHORT).show();
                    loadingbar.dismiss();

                    Intent intent =new Intent(reg_activity.this,first.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
