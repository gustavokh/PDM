package com.example.aula6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class SlidesParte2Activity extends AppCompatActivity {

    private Spinner spinner;
    String[] linguagens = {"Selecione uma linguagem", "C# Language", "HTML Language", "PHP Language", "XML Language"};
    int[] imagens = {0, R.drawable.csharp, R.drawable.html, R.drawable.php, R.drawable.xml};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slides_parte2);

        spinner = findViewById(R.id.spinner);
        
        spinner.setAdapter(new MyAdapter(this, R.layout.spinner_item, linguagens));
    }

    private class MyAdapter extends ArrayAdapter {

        public MyAdapter(Context contexto, int spinner_item, String[] linguagens) {
            super(contexto, spinner_item, linguagens);
        }

        private View getCustomView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.spinner_item, parent, false);

            TextView tvLinguagem = (TextView) layout.findViewById(R.id.conteudo);
            tvLinguagem.setText(linguagens[position]);
            tvLinguagem.setTextColor(Color.BLUE);

            ImageView img = (ImageView) layout.findViewById(R.id.imgLinguagem);
            img.setImageResource(imagens[position]);

            if(position == 0) {
                img.setVisibility(View.GONE);
                tvLinguagem.setTextSize(20f);
                tvLinguagem.setTextColor(Color.BLACK);
            }

            return layout;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }
    }
}
