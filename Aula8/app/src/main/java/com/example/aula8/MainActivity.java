package com.example.aula8;

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

    public void onClickExemplo(View view) {
        startActivity(new Intent(this, SelectActivity.class));
    }

    public void onClickExercicio(View view) {
        startActivity(new Intent(this, ExercicioActivity.class));
    }
}
