package com.firebase.myapplication.Dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.myapplication.R;
import com.firebase.myapplication.Screen.LogIn;
import com.firebase.myapplication.Screen.Register;

public class HomeScreen extends AppCompatActivity {

    AppCompatButton car_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        getSupportActionBar().hide();

    }
}