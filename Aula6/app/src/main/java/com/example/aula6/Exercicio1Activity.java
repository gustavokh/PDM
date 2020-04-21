package com.example.aula6;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exercicio1Activity extends ListActivity {

    String[] de = {"posicao", "imagem", "time", "pontos"};
    int[] para = {R.id.txtPosicao, R.id.imgTime, R.id.txtTime, R.id.txtPontos};
    List<Map<String, Object>> lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicio1);

        lista = new ArrayList<>();
        String[] clubes = {"Palmeiras", "Flamengo", "Atlético Mineiro", "Corinthians", "Santos", "Grêmio",
                "Ponte Preta", "Fluminense", "Atlético Paranaense", "Chapecoense", "Botafogo", "São Paulo",
                "Sport", "Cruzeiro", "Vitória", "Coritiba", "Internacional", "Figueirense", "Santa Cruz", "América Mineiro"};
        int[] pontos = {43, 40, 39, 37, 36, 36, 34, 34, 33, 30, 29, 28, 27, 26, 26, 26, 24, 24, 19, 13};
        int[] img = {R.drawable.pal, R.drawable.fla, R.drawable.cam, R.drawable.cor, R.drawable.san, R.drawable.gre,
                R.drawable.pon, R.drawable.flu, R.drawable.cap, R.drawable.cha, R.drawable.bot, R.drawable.sao,
                R.drawable.spt, R.drawable.cru, R.drawable.vit, R.drawable.cfc, R.drawable.inter, R.drawable.fig, R.drawable.sta, R.drawable.ame};

        for(int i=0; i<clubes.length; i++) {
            Map<String, Object> mapa = new HashMap<>();
            mapa.put(de[0], String.format("%dº", i+1));
            mapa.put(de[1], img[i]);
            mapa.put(de[2], clubes[i]);
            mapa.put(de[3], pontos[i]);
            lista.add(mapa);
        }

        SimpleAdapter adapter = new AdaptadorExercicio(this, lista, R.layout.line_item_exercicio, de, para);
        ListView listView = getListView();
        listView.setAdapter(adapter);
    }

    private class AdaptadorExercicio extends SimpleAdapter {
        public AdaptadorExercicio(Context contexto, List<Map<String, Object>> lista, int line_item_exercicio, String[] de, int[] para) {
            super(contexto, lista, line_item_exercicio, de, para);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            TextView txtPosicao = (TextView) view.findViewById(R.id.txtPosicao);
            TextView txtTime = (TextView) view.findViewById(R.id.txtTime);
            TextView txtPontos = (TextView) view.findViewById(R.id.txtPontos);

            if(position % 2 == 0) {
                view.setBackgroundColor(Color.GREEN);
                txtPosicao.setTextColor(Color.WHITE);
                txtTime.setTextColor(Color.WHITE);
                txtPontos.setTextColor(Color.WHITE);
            }
            else {
                view.setBackgroundColor(Color.YELLOW);
                txtPosicao.setTextColor(Color.BLACK);
                txtTime.setTextColor(Color.BLACK);
                txtPontos.setTextColor(Color.BLACK);
            }

            return view;
        }

    }
}
