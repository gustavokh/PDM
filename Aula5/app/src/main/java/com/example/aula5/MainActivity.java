package com.example.aula5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void aulaSlidesClick(View view) {
        Intent intent = new Intent(this, SlidesActivity.class);
        startActivity(intent);
    }

    public void aulaExerciciosClick(View view) {
        Intent intent = new Intent(this, ExerciciosActivity.class);
        startActivity(intent);
    }
}
