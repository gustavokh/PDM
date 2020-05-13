package com.example.aula7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickExemplo(View view) {
        Intent intent = new Intent(this, Exemplo.class);
        startActivity(intent);
    }

    public void onClickExercicio(View view) {
        Intent intent = new Intent(this, Exercicio.class);
        startActivity(intent);
    }
}
