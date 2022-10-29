package com.firebase.myapplication.Screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.myapplication.Dashboard.HomeScreen;
import com.firebase.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {

    AppCompatButton signup,login;
    TextView forgotpsw;
    TextInputEditText userEmail,userPassword;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getSupportActionBar().hide();

        userEmail = findViewById(R.id.login_email);
        userPassword = findViewById(R.id.login_password);
        forgotpsw = findViewById(R.id.forgotPassword);
        login = findViewById(R.id.login_btn);
        signup = findViewById(R.id.signup_btn_loginActivity);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        forgotpsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn.this,ForgotPassword.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAccount();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn.this,Register.class));
            }
        });

    }

    private void loginAccount() {

        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();

        if(!email.matches("[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+"))
        {
            userEmail.requestFocus();
            userEmail.setError("Please Enter Valid Email");
        }

        else if(password.length()<6)
        {
            userPassword.requestFocus();
            userPassword.setError("Your password must be less then 6 character");
        }

        else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {

                            Toast.makeText(LogIn.this, "LogIn SuccessFully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LogIn.this, HomeScreen.class));

                    }
                    else{
                        Toast.makeText(LogIn.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}