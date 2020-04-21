package com.example.aula6;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

class MeuAdaptador extends SimpleAdapter {
    public MeuAdaptador(Context contexto, List<Map<String, Object>> lista, int line_item, String[] de, int[] para) {
        super(contexto, lista, line_item, de, para);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view =  super.getView(position, convertView, parent);

        if(position % 2 == 0) {
            view.setBackgroundColor(Color.CYAN);
        }
        else {
            view.setBackgroundColor(Color.YELLOW);
        }

        return view;
    }
}
