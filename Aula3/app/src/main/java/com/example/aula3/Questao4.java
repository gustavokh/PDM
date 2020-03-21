package com.example.aula3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

public class Questao4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questao4);

        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#919891")));
    }
}
