package com.example.aula2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    int i=0;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("CICLO", "onCreate");

        Button btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(this);

        editText = findViewById(R.id.textCelsius);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("CICLO", "onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("CICLO", "onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("CICLO", "onPause");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("CICLO", "onRestart");
        editText.getText().clear();
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("CICLO", "onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("CICLO", "onDestroy");
    }

    @Override
    public void onClick(View view) {
        i++;
        Log.v("CLICKBOTAO", "Clicks: "+i);
        if(!editText.getText().toString().equals("")) {
            double tempCelsius = Double.parseDouble(editText.getText().toString());
            Intent intent = new Intent(this, Conversao.class);
            intent.putExtra("Celsius", tempCelsius);
            startActivity(intent);
        }
    }
}
