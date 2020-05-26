package com.example.aula9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ExemploGps extends AppCompatActivity implements LocationListener {
    private TextView txtLat, txtLong, txtStatus;
    private LocationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_gps);

        txtLat = findViewById(R.id.txtLat);
        txtLong = findViewById(R.id.txtLong);
        txtStatus = findViewById(R.id.txtStatus);

        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        long tempo = 0;
        float distancia = 0;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "Permitir permissão de uso da localização nas configurações.", Toast.LENGTH_SHORT);
            return;
        }
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, tempo, distancia, this);
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, tempo, distancia, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        txtLat.setText("Latitude: " + location.getLatitude());
        txtLong.setText("Longitude: " + location.getLongitude());
        txtStatus.setText("Provider: " + location.getProvider());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
