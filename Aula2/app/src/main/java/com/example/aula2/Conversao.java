package com.example.aula2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Conversao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversao);

        Intent intent = getIntent();
        double tempCelsius = intent.getDoubleExtra("Celsius", 0);

        TextView tv = findViewById(R.id.textFahrenheit);
        tv.setText(String.format("%.2f°C = %.2f°F", tempCelsius, converteTemp(tempCelsius)));
    }

    protected double converteTemp(double tempCelsius){
        double fahrenheit = (tempCelsius*1.8)+32;

        return fahrenheit;
    }
}
