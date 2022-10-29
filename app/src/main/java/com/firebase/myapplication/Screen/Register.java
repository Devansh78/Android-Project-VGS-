package com.firebase.myapplication.Screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    AppCompatButton SignupBtn,LoginBtn;
    TextInputEditText userFullName,userEmail,userPassword,userMobile;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        userFullName = findViewById(R.id.fullName_text_filed);
        userEmail = findViewById(R.id.Email_text_field);
        userPassword = findViewById(R.id.Password_text_field_design);
        userMobile = findViewById(R.id.mobile_text_field);

        SignupBtn = findViewById(R.id.signup_btn);
        LoginBtn = findViewById(R.id.Login_btn_SignupActivity);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        SignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerAccount();
            }
        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,LogIn.class));
            }
        });
    }

    private void registerAccount() {
        String name = userFullName.getText().toString();
        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();
        String mobile = userMobile.getText().toString();

        if(name.isEmpty())
        {
            userFullName.requestFocus();
            userFullName.setError("Please Enter Name");
        }

        else if(!email.matches("[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+"))
        {
            userEmail.requestFocus();
            userEmail.setError("Please Enter Valid Email");
        }

        else if(password.length()<6)
        {
            userPassword.requestFocus();
            userPassword.setError("Your password must be less then 6 character");
        }

        else if(mobile.length()<10)
        {
            userMobile.requestFocus();
            userMobile.setError("Please Enter Valid Mobile Number");
        }

        else{
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Register.this, "Create User SuccessFully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this,LogIn.class));

                        }
                        else{
                            Toast.makeText(Register.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        }
    }
}