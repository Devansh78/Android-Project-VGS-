package com.firebase.myapplication.Screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    TextInputEditText userEmail;
    Button link,login;

    FirebaseAuth mAuth;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().hide();
        
        userEmail = findViewById(R.id.forgot_email);
        
        link = findViewById(R.id.GetLink);
        login = findViewById(R.id.login_forgotActivity);

        mAuth = FirebaseAuth.getInstance();

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPassword();
            }
        });
        
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPassword.this,LogIn.class));
            }
        });
        
    }

    private void forgotPassword() {

        String email = userEmail.getText().toString();

        if(!email.matches("[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+"))
        {
            userEmail.requestFocus();
            userEmail.setError("Please Enter Valid Email");
        }

        else {
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(ForgotPassword.this, "Check Your Email", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ForgotPassword.this,LogIn.class));
                    }
                    else
                    {
                        Toast.makeText(ForgotPassword.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}