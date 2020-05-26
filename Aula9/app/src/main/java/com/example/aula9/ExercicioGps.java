package com.example.aula9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ExercicioGps extends AppCompatActivity implements LocationListener {
    private TextView txtLatExercicio, txtLongExercicio, txtStatusExercicio;
    private LocationManager manager;
    private ListView listView;
    private ArrayList<String> listaPosicoes, listaLat = new ArrayList<>(), listaLong = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicio_gps);

        txtLatExercicio = findViewById(R.id.txtLatExercicio);
        txtLongExercicio = findViewById(R.id.txtLongExercicio);
        txtStatusExercicio = findViewById(R.id.txtStatusExercicio);
        listView = findViewById(R.id.listViewExercicio);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ExercicioMap.class);
                intent.putStringArrayListExtra("lat", listaLat);
                intent.putStringArrayListExtra("lon", listaLong);
                startActivity(intent);
            }
        });

        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        long tempo = 0;
        float distancia = 10;
        listaPosicoes = new ArrayList<>();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this,"Favor permitir uso da localização nas Configurações do dispositivo", Toast.LENGTH_LONG).show();

            return;
        }
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, tempo, distancia, this);
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,tempo, distancia, this);

    }

    @Override
    public void onLocationChanged(Location location) {
        txtLatExercicio.setText("Latitude = " + location.getLatitude());
        txtLongExercicio.setText("Longitude = " + location.getLongitude());
        txtStatusExercicio.setText("Provider = " + location.getProvider());

        String str = "Latitude = " + location.getLatitude() + " Longitude = " + location.getLongitude();
        listaPosicoes.add(str);
        listaLat.add(String.valueOf(location.getLatitude()));
        listaLong.add(String.valueOf(location.getLongitude()));

        ArrayAdapter adapter =  new ArrayAdapter(this,android.R.layout.simple_list_item_1, listaPosicoes);
        listView.setAdapter(adapter);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}