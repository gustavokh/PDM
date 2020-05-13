package com.example.aula7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class Exercicio extends AppCompatActivity {
    private TextView txtTemperatura, txtUmidade, txtOrvalho, txtPressao;
    private ListView listView;
    String de[] = {"temperatura", "umidade", "orvalho", "pressão"};
    int para[] = {R.id.txtTemperatura_,  R.id.txtUmidade_, R.id.txtPontoOrvalho_, R.id.txtPressao_};
    ArrayList<Map<String, String>> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicio);

        txtTemperatura = findViewById(R.id.txtTemperatura);
        txtUmidade = findViewById(R.id.txtUmidade);
        txtOrvalho = findViewById(R.id.txtPontoOrvalho);
        txtPressao = findViewById(R.id.txtPressao);
        listView = findViewById(R.id.listView);
    }

    public void onClickBuscarExercicio(View view) {
        lista = new ArrayList<>();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://ghelfer.net/la/weather.json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String info = new String(response);
                loadData(info);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
            }
        });
    }

    private void loadData(String str) {
        double somaTemp = 0, somaUmidade = 0, somaOrvalho = 0, somaPressao = 0;

        try {
            JSONObject obj = new JSONObject(str);
            JSONArray array = obj.getJSONArray("weather");
            for(int i=0; i<array.length(); i++) {
                JSONObject json = array.getJSONObject(i);
                String temperatura = json.get("temperature").toString();
                somaTemp += Double.parseDouble(temperatura);
                String umidade = json.get("humidity").toString();
                somaUmidade += Double.parseDouble(umidade);
                String orvalho = json.get("dewpoint").toString();
                somaOrvalho += Double.parseDouble(orvalho);
                String pressao = json.get("pressure").toString();
                somaPressao += Double.parseDouble(pressao);

                Map<String, String> mapa = new HashMap<>();
                mapa.put("temperatura", "Temperatura: "+temperatura);
                mapa.put("umidade", "Umidade: "+umidade);
                mapa.put("orvalho", "Ponto de orvalho: "+orvalho);
                mapa.put("pressão", "Pressão: "+pressao);
                lista.add(mapa);
            }
            txtTemperatura.setText(String.valueOf(somaTemp / array.length()));
            txtUmidade.setText(String.valueOf(somaUmidade / array.length()));
            txtOrvalho.setText(String.valueOf(somaOrvalho / array.length()));
            txtPressao.setText(String.valueOf(somaPressao / array.length()));

            SimpleAdapter adapter = new SimpleAdapter(this, lista, R.layout.linha_tempo, de, para);
            listView.setAdapter(adapter);
        }
        catch (JSONException e) {
            Log.d("ERRO", e.getMessage());
        }

    }
}
