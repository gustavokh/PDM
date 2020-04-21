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
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class Exercicio2Activity extends AppCompatActivity {

    private Spinner spinner;
    String[] cores = {"Selecione uma cor", "Preto", "Cinza", "Âmbar", "Marrom"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicio2);

        spinner = findViewById(R.id.spinnerExercicio2);
        spinner.setAdapter(new AdaptadorExercicio(this, R.layout.spinner_item_exercicio, cores));
    }

    private class AdaptadorExercicio extends ArrayAdapter {

        public AdaptadorExercicio(Context contexto, int spinner_item_exercicio, String[] cores) {
            super(contexto, spinner_item_exercicio, cores);
        }

        private View getCustomView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.spinner_item_exercicio, parent, false);

            TextView tvCor = (TextView) layout.findViewById(R.id.txtCor);
            tvCor.setText(cores[position]);
            tvCor.setTextColor(Color.WHITE);

            switch(position){
                case 1:
                    tvCor.setBackgroundColor(Color.rgb(0, 0, 0));
                    break;
                case 2:
                    tvCor.setBackgroundColor(Color.rgb(128, 128, 128));
                    break;
                case 3:
                    tvCor.setBackgroundColor(Color.rgb(255, 191, 0));
                    break;
                case 4:
                    tvCor.setBackgroundColor(Color.rgb(150, 75, 0));
                    break;
            }

            if(position == 0 ) {
                tvCor.setTextColor(Color.BLACK);
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
