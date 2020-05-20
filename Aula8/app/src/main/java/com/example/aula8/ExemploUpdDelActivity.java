package com.example.aula8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ExemploUpdDelActivity extends AppCompatActivity {
    private TextView textViewId;
    private EditText textModelo, textAno, textValor;
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_upd_del);

        textViewId = findViewById(R.id.textId);
        textModelo = findViewById(R.id.textModelo);
        textAno = findViewById(R.id.textAno);
        textValor = findViewById(R.id.textValor);
        helper = new DatabaseHelper(this);

        Intent intent = getIntent();
        textViewId.setText(intent.getStringExtra("id"));
        textModelo.setText(intent.getStringExtra("modelo"));
        textAno.setText(intent.getStringExtra("ano"));
        textValor.setText(intent.getStringExtra("valor"));
    }

    public void delClick(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] where = new String[] {textViewId.getText().toString()};

        long result = db.delete("carro","id = ?", where);
        if (result != -1) {
            Toast.makeText(this, "Conteúdo removido com sucesso.", Toast.LENGTH_SHORT).show();
            textModelo.setText("");
            textAno.setText("");
            textValor.setText("");
        }
        else
            Toast.makeText(this, "Erro na remoção.", Toast.LENGTH_SHORT).show();
    }

    public void updClick(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] where = new String[] {textViewId.getText().toString()};

        ContentValues cv = new ContentValues();
        cv.put("modelo", textModelo.getText().toString());
        cv.put("ano", Integer.parseInt(textAno.getText().toString()));
        cv.put("valor", Double.parseDouble(textValor.getText().toString()));

        long result = db.update("carro", cv, "id = ?", where);
        if (result != -1) {
            Toast.makeText(this, "Conteúdo atualizado com sucesso.", Toast.LENGTH_SHORT).show();
            textModelo.setText("");
            textAno.setText("");
            textValor.setText("");
        }
        else
            Toast.makeText(this, "Erro na atualização.", Toast.LENGTH_SHORT).show();
    }
}
