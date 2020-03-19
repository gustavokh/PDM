package com.example.aula3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botao1 = (Button) findViewById(R.id.button1);
        botao1.setOnClickListener(this);
        Button botao2 = (Button) findViewById(R.id.button2);
        botao2.setOnClickListener(this);
        Button botao3 = (Button) findViewById(R.id.button3);
        botao3.setOnClickListener(this);
        Button botao4 = (Button) findViewById(R.id.button4);
        botao4.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.button1:
                Intent intent1 = new Intent(this, Questao1.class);
                startActivity(intent1);
                break;
            case R.id.button2:
                Intent intent2 = new Intent(this, Questao2.class);
                startActivity(intent2);
                break;
            case R.id.button3:
                Intent intent3 = new Intent(this, null);
                break;
            case R.id.button4:
                Intent intent4 = new Intent(this, null);
                break;
        }
    }
}
