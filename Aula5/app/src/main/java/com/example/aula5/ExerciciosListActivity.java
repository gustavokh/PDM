package com.example.aula5;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;

public class ExerciciosListActivity extends ListActivity implements AdapterView.OnItemClickListener {
    String[] nomes = {"Lamb Ari", "Beto Neira", "Brita Deira", "Gil Ete", "Astolfo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicios_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Arrays.asList(nomes));
        setListAdapter(adapter);

        ListView listView = getListView();
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView textView = (TextView) view;
        String nome = textView.getText().toString();

        Intent intent = new Intent(this, ExerciciosSpinnerEListViewActivity.class);
        intent.putExtra("Nome", nome);
        startActivity(intent);
    }
}
