package com.example.aula6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void parte1OnClick(View view) {
        Intent intent = new Intent(this, SlidesParte1Activity.class);
        startActivity(intent);
    }

    public void parte2OnClick(View view) {

    }
}
