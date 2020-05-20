package com.example.aula8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ExemploAddActivity extends AppCompatActivity {
    private EditText textModelo, textAno, textValor;
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_add);

        textModelo = findViewById(R.id.textModelo);
        textAno = findViewById(R.id.textAno);
        textValor = findViewById(R.id.textValor);

        helper = new DatabaseHelper(this);
    }

    public void onClickAdiciona(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("modelo", textModelo.getText().toString());
        cv.put("ano", Integer.parseInt(textAno.getText().toString()));
        cv.put("valor", Double.parseDouble(textValor.getText().toString()));

        long result = db.insert("carro", null, cv);
        if (result != -1) {
            Toast.makeText(this, "Conteúdo inserido com sucesso.", Toast.LENGTH_SHORT).show();
            textModelo.setText("");
            textAno.setText("");
            textValor.setText("");
        }
        else
            Toast.makeText(this, "Erro na inserção.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        helper.close();
        super.onDestroy();
    }
}
