package com.example.aula8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class SelectActivity extends AppCompatActivity {
    private ListView listView;
    private EditText textAno, textNome;
    private ArrayList<HashMap<String, Object>> lista;
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        listView = findViewById(R.id.listView);
        textAno = findViewById(R.id.textAno);
        textNome = findViewById(R.id.textNome);
        helper = new DatabaseHelper(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textViewId = view.findViewById(R.id.textViewId);
                TextView textViewModelo = view.findViewById(R.id.textViewModelo);
                TextView textViewAno = view.findViewById(R.id.textViewAno);
                TextView textViewValor = view.findViewById(R.id.textViewValor);
                Intent intent = new Intent(getApplicationContext(), ExemploUpdDelActivity.class);
                intent.putExtra("id", textViewId.getText().toString());
                intent.putExtra("modelo", textViewModelo.getText().toString());
                intent.putExtra("ano", textViewAno.getText().toString());
                intent.putExtra("valor", textViewValor.getText().toString());
                startActivity(intent);
            }
        });
    }

    public void searchClick(View view) {
        String query = "";
        if (textAno.getText().toString().isEmpty()) {
            query = "SELECT * FROM carro";
        }
        else {
            query = "SELECT * FROM carro WHERE ano = " + textAno.getText().toString();
        }
        //lista = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        carregaLista(c);
        /*c.moveToFirst();
        for (int i=0; i<c.getCount(); i++) {
            HashMap<String, Object> mapa = new HashMap<>();
            String id = c.getString(0);
            String modelo = c.getString(1);
            String ano = c.getString(2);
            String valor = c.getString(3);
            mapa.put("id", id);
            mapa.put("modelo", modelo);
            mapa.put("ano", ano);
            mapa.put("valor", valor);
            lista.add(mapa);
            c.moveToNext();
        }
        c.close();
        SimpleAdapter adapter = new SimpleAdapter(this, lista, R.layout.listagem,
                new String[] {"id", "modelo", "ano", "valor"},
                new int[] {R.id.textViewId, R.id.textViewModelo, R.id.textViewAno, R.id.textViewValor});
        listView.setAdapter(adapter);*/
    }

    public void addClick(View view) {
        startActivity(new Intent(this, ExemploAddActivity.class));
    }

    public void buscarExercicioClick(View view) {
        SQLiteDatabase db = helper.getReadableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables("carro");
        String[] procura = new String[] {"*"};
        String seleciona = "modelo = ?";
        String[] argumentos = new String[] {textNome.getText().toString()};
        Cursor c = builder.query(db, procura, seleciona, argumentos, null, null, "ano DESC");
        carregaLista(c);
    }

    public void carregaLista(Cursor c) {
        lista = new ArrayList<>();
        c.moveToFirst();
        for (int i=0; i<c.getCount(); i++) {
            HashMap<String, Object> mapa = new HashMap<>();
            String id = c.getString(0);
            String modelo = c.getString(1);
            String ano = c.getString(2);
            String valor = c.getString(3);
            mapa.put("id", id);
            mapa.put("modelo", modelo);
            mapa.put("ano", ano);
            mapa.put("valor", valor);
            lista.add(mapa);
            c.moveToNext();
        }
        c.close();
        SimpleAdapter adapter = new SimpleAdapter(this, lista, R.layout.listagem,
                new String[] {"id", "modelo", "ano", "valor"},
                new int[] {R.id.textViewId, R.id.textViewModelo, R.id.textViewAno, R.id.textViewValor});
        listView.setAdapter(adapter);
    }
}
