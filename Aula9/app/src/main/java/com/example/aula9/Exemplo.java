package com.example.aula9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Exemplo extends AppCompatActivity implements SensorEventListener {
    private TextView a, b, c, d, e, f, g, h, i;
    SensorManager manager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo);

        a = findViewById(R.id.a);
        b = findViewById(R.id.b);
        c = findViewById(R.id.c);
        d = findViewById(R.id.d);
        e = findViewById(R.id.e);
        f = findViewById(R.id.f);
        g = findViewById(R.id.g);
        h = findViewById(R.id.h);
        i = findViewById(R.id.i);

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        manager.registerListener(this, manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this, manager.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this, manager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this, manager.getDefaultSensor(Sensor.TYPE_PRESSURE), SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this, manager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            a.setText("Accel X: " + event.values[0]);
            b.setText("Accel Y: " + event.values[1]);
            c.setText("Accel Z: " + event.values[2]);
        }
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            d.setText("Proximity: " + event.values[0]);
        }
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            e.setText("Light: " + event.values[0]);
        }
        if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {
            f.setText("Pressure: " + event.values[0]);
        }
        if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
            g.setText("Orient X: " + event.values[0]);
            h.setText("Orient Y: " + event.values[1]);
            i.setText("Orient Z: " + event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        manager.unregisterListener(this);
    }

    public void onClickGps(View view) {
        startActivity(new Intent(this, ExemploGps.class));
    }
}
