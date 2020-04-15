package com.example.aula5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;

public class ExerciciosSpinnerEListViewActivity extends AppCompatActivity {
    private Spinner spinner;
    String[] uf = {"RS", "SC", "PR"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicios_spinner_e_list_view);
        setTitle(Html.fromHtml("<font color='#000000'>Exercício parte 3 e 4</font>"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFF00")));

        Intent intent = getIntent();
        String nome = intent.getStringExtra("Nome");
        TextView textView = findViewById(R.id.exercicios_nome);
        textView.setText(nome);

        spinner = findViewById(R.id.exercicios_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, uf);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                preencheListView(uf[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    protected void preencheListView(String uf){
        ListView listView = findViewById(R.id.exercicios_listView);
        String[] cidades = null;

        switch(uf){
            case "RS":
                cidades = new String[]{"Santa Cruz do Sul", "Porto Alegre", "Santa Maria"};
                break;
            case "SC":
                cidades = new String[]{"Joinville", "Florianópolis", "Chapecó"};
                break;
            case "PR":
                cidades = new String[]{"Londrina", "Curitiba", "Maringá"};
                break;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Arrays.asList(cidades));
        listView.setAdapter(adapter);
    }
}
